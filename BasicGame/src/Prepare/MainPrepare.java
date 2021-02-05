/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prepare;

import Action.ClickListener;
import Action.PauseListener;
import Battle.ConfirmButton;
//import Battle.MainController;
import static Battle.MainGame.CLICK;
import static Battle.MainGame.PAUSE;
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
/**
 *
 * @author feegee2000
 */
public class MainPrepare extends BaseAppState{
    private SimpleApplication app;
    private AssetManager assetManager;
    private AppStateManager stateManager;
    private InputManager inputManager;
    private Camera cam;
    private Node rootNode;
    private Node prepareNode;
    private SelfCardsNode selfCardsNode;
    private PassButton passButton;
    private ShootButton shootButton;
    private BreakthroughButton breakthroughButton;
    private float camZ;
    private float ratio;
    public final static String PAUSE = "PAUSE"; //the pause message
    private ActionListener pauseListener;
    public final static String CLICK = "CLICK"; //the CLICK message
    
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
        
        
        
        this.prepareNode = new Node();
//        this.buttonNode = new Node();
//        battleNode.attachChild(buttonNode);
        //construct the background
        constructBackground();
        //Game initalize
        this.selfCardsNode = new SelfCardsNode(app, camZ*ratio, camZ); // here pay attention
        prepareNode.attachChild(selfCardsNode);
        this.shootButton = new ShootButton(app, camZ*ratio, camZ);
        prepareNode.attachChild(shootButton);
        this.breakthroughButton = new BreakthroughButton(app, camZ*ratio, camZ);
        prepareNode.attachChild(breakthroughButton);
        this.passButton = new PassButton(app, camZ*ratio, camZ);
        prepareNode.attachChild(passButton);
        
        pauseListener = new PauseListener(app);
//        clickListener = new ClickListener(app, handCardNode, cardManager);
    }
    
    @Override
    protected void cleanup(Application arg0) {
    }
    
    @Override
    protected void onEnable() {
        //add event listener
        inputManager.addMapping(PAUSE, new KeyTrigger(KeyInput.KEY_DELETE));
        inputManager.addListener(pauseListener,PAUSE);
        
        inputManager.addMapping(CLICK, new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(selfCardsNode,CLICK);
        //initialize the background
        rootNode.attachChild(prepareNode);
        selfCardsNode.licensing();
//        cardManager.enterScene();
    }
    
        @Override
    protected void onDisable() {
        prepareNode.removeFromParent();
        //remove the listener
        inputManager.removeListener(pauseListener);
        inputManager.deleteMapping(PAUSE);
        inputManager.removeListener(selfCardsNode);
        inputManager.deleteMapping(CLICK);
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
        prepareNode.attachChild(backgroundGeom);
    }
    
    private void screenSetting(){
        //screen setting
        float w = app.getContext().getSettings().getWidth(); // the screen width
        float h = app.getContext().getSettings().getHeight(); // the screen width
        ratio = w/h; //the width-height ratio of the screen
        cam.setLocation(Vector3f.ZERO.add(new Vector3f(0.0f, 0.0f,100f)));//Move the Camera back
        camZ = cam.getLocation().getZ()-15; //No Idea why I need to subtract 15
    }
}
