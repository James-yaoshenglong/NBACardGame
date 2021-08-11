/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battle;

import Action.ClickListener;
import Action.PauseListener;
import Pause.PauseButton;
import Widgets.CombatDataOperation;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import java.util.ArrayList;
import network.client.GameClient;

/**
 *
 * @author shenglyao2
 */
public class MainGame extends BaseAppState{
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
    private Node battleNode;
    private SelfCardsNode selfCardsNode;
//    private Node buttonNode;
    private ConfirmButton confirmButton;
    public final static String PAUSE = "PAUSE"; //the pause message
//    private ActionListener pauseListener;
    
    private PauseButton pauseButton;
    
    
    public final static String CLICK = "CLICK"; //the CLICK message
//    private ClickListener clickListener;
    
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
        
        
        
        this.battleNode = new Node();
//        this.buttonNode = new Node();
//        battleNode.attachChild(buttonNode);
        //construct the background
        constructBackground();
        //Game initalize
        this.selfCardsNode = new SelfCardsNode(app, camZ*ratio, camZ); // here pay attention
        battleNode.attachChild(selfCardsNode);
        this.confirmButton = new ConfirmButton(app, camZ*ratio, camZ, selfCardsNode);
        battleNode.attachChild(confirmButton);
        this.pauseButton = new PauseButton(app, camZ*ratio, camZ);
        battleNode.attachChild(pauseButton);
        
//        this.cardManager = new MainController(app,camZ*ratio,camZ,selfCardsNode,confirmButton);
//        pauseListener = new PauseListener(app);
//        clickListener = new ClickListener(app, handCardNode, cardManager);
    }

    @Override
    protected void cleanup(Application arg0) {
    }

    @Override
    protected void onEnable() {
        //add event listener
        inputManager.addMapping(PAUSE, new KeyTrigger(KeyInput.KEY_DELETE));
//        inputManager.addListener(pauseListener,PAUSE);
        
        //one trigger can only band to one message , now can see many listener can listen one messsage
        //if later there is some problem can use raw input manager
        inputManager.addMapping(CLICK, new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(selfCardsNode,CLICK);
        
        inputManager.addListener(confirmButton, CLICK);
        //initialize the background
        rootNode.attachChild(battleNode);
        
        inputManager.addListener(pauseButton, CLICK, PAUSE);
//        cardManager.enterScene();

        inputManager.addRawInputListener(selfCardsNode);
        
        GameClient.getInstance().registerOperation(CombatDataOperation.getInstance());
    }

    @Override
    protected void onDisable() {
        battleNode.removeFromParent();
        //remove the listener
//        inputManager.removeListener(pauseListener);
        inputManager.deleteMapping(PAUSE);
        inputManager.removeListener(selfCardsNode);
        inputManager.deleteMapping(CLICK);
        inputManager.removeListener(confirmButton);
        inputManager.removeListener(pauseButton);
        inputManager.removeRawInputListener(selfCardsNode);
    }
    
    private void constructBackground(){
        Material backgroundMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture backgroundTex = assetManager.loadTexture("Interface/bg/bg.jpg");
        backgroundMat.setTexture("ColorMap", backgroundTex);

        float width = camZ*ratio;
        float height = camZ;

        Quad fsq = new Quad(width, height);
        Geometry backgroundGeom = new Geometry("Background", fsq);
        backgroundGeom.setQueueBucket(RenderQueue.Bucket.Sky);
        backgroundGeom.setCullHint(Spatial.CullHint.Never);
        backgroundGeom.setMaterial(backgroundMat);
        backgroundGeom.setLocalTranslation(-(width / 2), -(height/ 2), 0); //Need to Divide by two because the quad origin is bottom left
        battleNode.attachChild(backgroundGeom);
    }
    
    private void screenSetting(){
        //screen setting
        float w = app.getContext().getSettings().getWidth(); // the screen width
        float h = app.getContext().getSettings().getHeight(); // the screen width
        ratio = w/h; //the width-height ratio of the screen
        cam.setLocation(Vector3f.ZERO.add(new Vector3f(0.0f, 0.0f,100f)));//Move the Camera back
        camZ = cam.getLocation().getZ()-15; //No Idea why I need to subtract 15
    }
    
    public ArrayList<Card> getLineupCards(){
        return selfCardsNode.getLineup();
    }
}
