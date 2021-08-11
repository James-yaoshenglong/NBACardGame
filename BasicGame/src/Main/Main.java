package Main;

import ActualCombat.MainActualCombat;
import Battle.MainGame;
import Widgets.CombatDataOperation;
import Defend.MainDefend;
import Pause.PauseInterface;
import Prepare.MainPrepare;
import Welcome.WelcomeInterface;
import Login.LoginInterface;
import Defend.DefendMode;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import com.jme3.ui.Picture;
import java.util.logging.Level;
import java.util.logging.Logger;

import network.client.GameClient;
import network.data.ResponseOperation;


/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    private WelcomeInterface welcomeUI;
    private LoginInterface loginUI;
    private MainGame mainGame;
    private PauseInterface pauseUI;
    private MainPrepare mainPrepare;
    private MainDefend mainDefend;
    private DefendMode defendMode;
    private MainActualCombat mainCombat;
    private BaseAppState currentState; //do not count pause
    private GameClient client;
    private int order; //show current is attack or defend
    
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }


    @Override

    public void simpleInitApp(){
        //setting the fly cam disable;
        this.getFlyByCamera().setEnabled(false);
        loginUI = new LoginInterface();
        pauseUI = new PauseInterface();
        currentState = loginUI;

        stateManager.attach(loginUI);
        stateManager.attach(pauseUI);
        stateManager.getState(PauseInterface.class).setEnabled(false);
        
        client = GameClient.getInstance();
        try {
            client.start();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CombatDataOperation.getInstance().setApp(this);
    }
    
    public void switchfromLogtoWel(){
        stateManager.getState(LoginInterface.class).setEnabled(false);
        welcomeUI = new WelcomeInterface();
        stateManager.attach(welcomeUI);
        currentState = welcomeUI;
    }
    
    public void switchfromWeltoMain(){
        stateManager.getState(WelcomeInterface.class).setEnabled(false);
        mainGame = new MainGame();
        mainPrepare = new MainPrepare();
        mainDefend = new MainDefend();
        defendMode = new DefendMode();
        mainCombat = new MainActualCombat();
        stateManager.attach(mainGame);
        stateManager.attach(mainPrepare);
        stateManager.attach(mainDefend);
        stateManager.attach(defendMode);
        stateManager.attach(mainCombat);
        stateManager.getState(MainPrepare.class).setEnabled(false);
        stateManager.getState(MainDefend.class).setEnabled(false);
        stateManager.getState(DefendMode.class).setEnabled(false);
        stateManager.getState(MainActualCombat.class).setEnabled(false);
        currentState = mainGame;
    }
    
    public void switchfromPausetoWel(){
        stateManager.getState(PauseInterface.class).setEnabled(false);
        stateManager.getState(WelcomeInterface.class).setEnabled(true);
        stateManager.detach(mainGame);
        stateManager.detach(mainPrepare);
        stateManager.detach(mainDefend);
        stateManager.detach(defendMode);
        stateManager.detach(mainCombat);
    }
    
//    public void switchfromMaintoPause(){
//        //now just for test use the welcome state
//        stateManager.getState(MainGame.class).setEnabled(false);
//        stateManager.getState(PauseInterface.class).setEnabled(true);
//    }
    
//    public void switchfromPausetoMain(){
//        stateManager.getState(PauseInterface.class).setEnabled(false);
//        stateManager.getState(MainGame.class).setEnabled(true);
//    }
    
    public void switchfromMaintoPrepare(){
        stateManager.getState(MainGame.class).setEnabled(false);
        stateManager.getState(MainPrepare.class).setEnabled(true);
        currentState = mainPrepare;
    }
    
    public void switchfromMaintoDefendModeChoice(){
        stateManager.getState(MainGame.class).setEnabled(false);
        stateManager.getState(DefendMode.class).setEnabled(true);
        currentState = defendMode;
    }

    public void switchfromDefendModeChoicetoDefend(){
        stateManager.getState(DefendMode.class).setEnabled(false);
        stateManager.getState(MainDefend.class).setEnabled(true);
        currentState = mainDefend;
    }
    
    public void returnBackFromPause(){
        stateManager.getState(PauseInterface.class).setEnabled(false);
        stateManager.getState(currentState.getClass()).setEnabled(true);
    }
    
    public void switchToPause(){
        stateManager.getState(MainGame.class).setEnabled(false);
        stateManager.getState(MainPrepare.class).setEnabled(false);
        stateManager.getState(PauseInterface.class).setEnabled(true);
    }
    
    public void switchToActual(){
        stateManager.getState(currentState.getClass()).setEnabled(false);
        stateManager.getState(MainActualCombat.class).setEnabled(true);
        currentState = mainCombat;
    }
    
    /**
    This just makes a box so I can see if the background is rendered last

    */

//    private void initBox(){
//
//        //Make a box
//
//        Box b = new Box(Vector3f.ZERO, 10f, 10f, 10f); // create cube shape at the origin
//
//        geom = new Geometry("Box", b); // create cube geometry from the shape
//
//        Material mat = new Material(assetManager,
//
//        "Common/MatDefs/Misc/Unshaded.j3md"); // create a simple material
//
//        mat.setColor("Color", ColorRGBA.Blue); // set color of material to blue
//
//        geom.setMaterial(mat); // set the cube’s material
//
//        rootNode.attachChild(geom);
//
//    }
    
    
    

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
//        float[] angles = new float[3];
//        angles[0] = (float) Math.toRadians(0.25f);
//        angles[1] = (float) Math.toRadians(0);
//        angles[2] = (float) Math.toRadians(0.25f);
//
//        Quaternion rot = new Quaternion(angles);
//
//        geom.rotate(rot);
        
    }

    
    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    
    private void constructBackground(){
        Material backgroundMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");

        Texture backgroundTex = assetManager.loadTexture("Interface/bg/bg.jpg");

        backgroundMat.setTexture("ColorMap", backgroundTex);

        float w = this.getContext().getSettings().getWidth(); // the screen width

        float h = this.getContext().getSettings().getHeight(); // the screen width

        float ratio = w/h;



        cam.setLocation(Vector3f.ZERO.add(new Vector3f(0.0f, 0.0f,100f)));//Move the Camera back

        float camZ = cam.getLocation().getZ()-15; //No Idea why I need to subtract 15

        float width = camZ*ratio;

        float height = camZ;



        Quad fsq = new Quad(width, height);

        Geometry backgroundGeom = new Geometry("Background", fsq);

        backgroundGeom.setQueueBucket(Bucket.Sky);

        backgroundGeom.setCullHint(CullHint.Never);

        backgroundGeom.setMaterial(backgroundMat);

        backgroundGeom.setLocalTranslation(-(width / 2), -(height/ 2), 0); //Need to Divide by two because the quad origin is bottom left

        rootNode.attachChild(backgroundGeom);
    }
    
    public void setOrder(int or){
        this.order = or;
    }
    
    public int getOrder(){
        return order;
    }     
}
