/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActualCombat;

        
import Pause.PauseButton;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import network.data.AttackData;
import network.data.DefendData;
import network.data.TeamData;
import Battle.Card;
import Battle.MainGame;
import Battle.Player;
import java.util.ArrayList;

/**
 *
 * @author shenglyao2
 */
public class MainActualCombat extends BaseAppState{
    //copy of reference of some common use attributes from app
    private SimpleApplication app;
    private AssetManager assetManager;
    private AppStateManager stateManager;
    private InputManager inputManager;
    private Camera cam;
    private Node rootNode;
//    private MainController cardManager;
    private float camZ;
    private float ratio;
    private float width;
    private float height;
    private Node curNode;
    public final static String PAUSE = "PAUSE"; //the pause message
//    private ActionListener pauseListener;
    
    private PauseButton pauseButton;
    
    
    public final static String CLICK = "CLICK"; //the CLICK message
//    private ClickListener clickListener;
    
    private AttackData attackData;
    private DefendData defendData;
    private TeamData myTeam;
    private TeamData rivalTeam;
    
    @Override
    protected void initialize(Application mainApp) {
        //add a copy of reference of some common use attributes from app
        this.app = (SimpleApplication)mainApp;
        this.assetManager = mainApp.getAssetManager();
        this.stateManager = mainApp.getStateManager();
        this.inputManager = mainApp.getInputManager();
        this.cam = mainApp.getCamera();
        this.rootNode = app.getRootNode();
        //setting the screen, this should be first
        screenSetting();
        width = camZ*ratio;
        height = camZ;
        
        this.curNode = new Node();
        //construct the background
        constructBackground();
        //Game initalize
        this.pauseButton = new PauseButton(app, camZ*ratio, camZ);
        curNode.attachChild(pauseButton);
        
        
//        this.cardManager = new MainController(app,camZ*ratio,camZ,selfCardsNode,confirmButton);
//        pauseListener = new PauseListener(app);
//        clickListener = new ClickListener(app, handCardNode, cardManager);
    }

    @Override
    protected void cleanup(Application arg0) {
    }

    @Override
    protected void onEnable() {
        
        
        rootNode.attachChild(curNode);
                
        //add event listener
        inputManager.addMapping(PAUSE, new KeyTrigger(KeyInput.KEY_DELETE));
        
        //one trigger can only band to one message , now can see many listener can listen one messsage
        //if later there is some problem can use raw input manager
        inputManager.addMapping(CLICK, new MouseButtonTrigger(MouseInput.BUTTON_LEFT));    
        //initialize the background

        
        inputManager.addListener(pauseButton, CLICK, PAUSE);
        judge_score();
        
        //register operation
    }

    @Override
    protected void onDisable() {
        curNode.removeFromParent();
        //remove the listener
        //inputManager.removeListener(pauseListener);
        inputManager.deleteMapping(PAUSE);
        inputManager.deleteMapping(CLICK);
        inputManager.removeListener(pauseButton);
    }
    
    private void constructBackground(){
        Material backgroundMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture backgroundTex = assetManager.loadTexture("Interface/bg/bg.jpg");
        backgroundMat.setTexture("ColorMap", backgroundTex);
        Quad fsq = new Quad(width, height);
        Geometry backgroundGeom = new Geometry("Background", fsq);
        backgroundGeom.setQueueBucket(RenderQueue.Bucket.Sky);
        backgroundGeom.setCullHint(Spatial.CullHint.Never);
        backgroundGeom.setMaterial(backgroundMat);
        backgroundGeom.setLocalTranslation(-(width / 2), -(height/ 2), 0); //Need to Divide by two because the quad origin is bottom left
        curNode.attachChild(backgroundGeom);
    }
    
    private void screenSetting(){
        //screen setting
        float w = app.getContext().getSettings().getWidth(); // the screen width
        float h = app.getContext().getSettings().getHeight(); // the screen width
        ratio = w/h; //the width-height ratio of the screen
        cam.setLocation(Vector3f.ZERO.add(new Vector3f(0.0f, 0.0f,100f)));//Move the Camera back
        camZ = cam.getLocation().getZ()-15; //No Idea why I need to subtract 15
    }
    
    public void setData(AttackData ad, DefendData dd, TeamData mT, TeamData rT){
        this.attackData = ad;
        this.defendData = dd;
        this.myTeam = mT;
        System.out.println(mT.getOrder());
        this.rivalTeam = rT;
        System.out.println(rT.getOrder());
    }
    
    private void judge_score(){
        System.out.printf("your team score: %d, rival team score: %d \n", myTeam.getScore(),rivalTeam.getScore());
        ArrayList<Player> a_lineup = new ArrayList<Player>();
        ArrayList<Player> d_lineup = new ArrayList<Player>();
        for(int num : attackData.getLineup()){
            Player p = new Player(num);
            a_lineup.add(p);
        }
        for(int num : attackData.getLineup()){
            Player p = new Player(num);
            d_lineup.add(p);
        }
        Boolean isZone = defendData.getZoneFlag();
        if(isZone){
            for(Player p:d_lineup){
                p.setDB(1.3f);
                p.setDS(0.8f);
            }
            int average_dp=0;
            int average_db=0;
            int average_ds=0;
            for(Player p:d_lineup){
                average_dp+=p.getDP();
                average_db+=p.getDB();
                average_ds+=p.getDS();
            }
            average_dp = average_dp/5;
            average_ds = average_ds/5;
            average_db = average_db/5;
            String op = attackData.getOP1();
            Player ap = a_lineup.get(findMatchup(attackData.getPlayer1(),a_lineup));
            if(op.equals("pass")){    
                if(!is_success(ap.getP(),average_dp)){
                    System.out.println("Pass failed");
                    return;
                    //exchange offence and defence
                }
                ap = a_lineup.get(findMatchup(attackData.getPlayer2(),a_lineup));
                op = attackData.getOP2();
            }
            if(op.equals("break")){
                if(is_success(ap.getB(),average_db)){
                    System.out.println("Offend team scores 2 points");                    
                }
                return;
                //exchange offence and defence
            }
            else{
                if(is_success(ap.getS(),average_ds)){
                    if(is_3pts()){
                        System.out.println("Offend team scores 3 points");
                    }
                    else{
                        System.out.println("Offend team scores 2 points");   
                    }                   
                }
                return;
                //exchange offence and defence
            }            
        }
        else{
            int dt_id=-1;
            int ug_id=-1;
            if(defendData.isDoubleTeam()){
                Player dpdt;
                Player dpug;
                dt_id=findMatchup(defendData.getDoubleTeam(),d_lineup);
                a_lineup.get(dt_id).setB(0.8f);
                a_lineup.get(dt_id).setS(0.8f);
                d_lineup.get(dt_id).setDP(1.3f);
                ug_id=findMatchup(defendData.getUnguarded(),d_lineup);
                a_lineup.get(ug_id).setB(1.3f);
                a_lineup.get(ug_id).setS(1.3f);
                d_lineup.get(ug_id).setP(1.3f);
            }
            String op = attackData.getOP1();
            int matchup_id = findMatchup(attackData.getPlayer1(),a_lineup);
            Player ap = a_lineup.get(matchup_id);
            Player dp = d_lineup.get(matchup_id);
            if(op.equals("pass")){    
                if(defendData.isDoubleTeam()){
                    int dtDP = (d_lineup.get(dt_id).getDP()+d_lineup.get(ug_id).getDP())/2;
                    if(!is_success(ap.getP(),dtDP)){
                        System.out.println("Pass failed");
                        return;
                        //exchange offence and defence
                    }
                }
                else{
                    if(!is_success(ap.getP(),dp.getDP())){
                        System.out.println("Pass failed");
                        return;
                        //exchange offence and defence
                    }   
                }
                
                ap = a_lineup.get(findMatchup(attackData.getPlayer2(),a_lineup));
                op = attackData.getOP2();
                dp = d_lineup.get(findMatchup(attackData.getPlayer2(),a_lineup));
            }
            if(op.equals("break")){
                if(is_success(ap.getB(),dp.getB())){
                    System.out.println("Offend team scores 2 points");                    
                }
                return;
                //exchange offence and defence
            }
            else{
                if(is_success(ap.getS(),dp.getS())){
                    if(is_3pts()){
                        System.out.println("Offend team scores 3 points");
                    }
                    else{
                        System.out.println("Offend team scores 2 points");   
                    }                   
                }
                return;
                //exchange offence and defence
            }
            
        }
    }
    
    private Boolean is_success(int a, int d){
        return Math.random()>0.5;
    }
    
    private Boolean is_3pts(){
        return Math.random()>0.5;
    }
    
    private int findMatchup(int playerID,ArrayList<Player> lineup){
        int id=0;
        for(Player p:lineup){
            if(p.getID()==playerID){
                break;
            }
            id++;
        }
        return id;
    }
}

