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
import Pause.PauseButton;
import Widgets.ButtonState;
import Widgets.Player1OperationState;
import Widgets.Player2OperationState;
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
    private OperationBox opBox1;
    private OperationButton opButtonNode1;
    private PlayerButton playerButtonNode1;
    private OperationBox opBox2;
    private OperationButton opButtonNode2;
    private PlayerButton playerButtonNode2;
    private float camZ;
    private float ratio;
    public final static String PAUSE = "PAUSE"; //the pause message
    public final static String CLICK = "CLICK"; //the CLICK message
    
    private PauseButton pauseButton;
    
    private boolean buttonShown = false;
    
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
        this.opButtonNode1 = new OperationButton(app, camZ*ratio, camZ, Player1OperationState.getInstance());
        this.playerButtonNode1 = new PlayerButton(app, camZ*ratio, camZ, Player1OperationState.getInstance());
        this.opBox1 = new OperationBox(app, camZ*ratio, camZ, opButtonNode1,playerButtonNode1);
        this.opButtonNode2 = new OperationButton(app, camZ*ratio, camZ, Player2OperationState.getInstance());
        this.playerButtonNode2 = new PlayerButton(app, camZ*ratio, camZ, Player2OperationState.getInstance());
        this.opBox2 = new OperationBox(app, camZ*ratio, camZ, opButtonNode2,playerButtonNode2);
        this.shootButton = new ShootButton(app, camZ*ratio, camZ, opBox1);
        this.breakthroughButton = new BreakthroughButton(app, camZ*ratio, camZ, opBox1);
        this.passButton = new PassButton(app, camZ*ratio, camZ, opBox1);
        prepareNode.attachChild(opBox1);
        prepareNode.attachChild(opBox2);
        opBox1.setLocalTranslation((camZ*ratio)*(-0.25f),0,0);
        opBox2.setLocalTranslation((camZ*ratio)*(0.25f), 0, 0);
        
        this.pauseButton = new PauseButton(app, camZ*ratio, camZ);
        prepareNode.attachChild(pauseButton);
        
//        clickListener = new ClickListener(app, handCardNode, cardManager);
    }
    
    @Override
    protected void cleanup(Application arg0) {
    }
    
    @Override
    protected void onEnable() {
        
        //initialize the background
        this.selfCardsNode = new SelfCardsNode(app, camZ*ratio, camZ, opBox1); // here pay attention
        rootNode.attachChild(prepareNode);
        
        //add event listener
        inputManager.addMapping(PAUSE, new KeyTrigger(KeyInput.KEY_DELETE));
        
        inputManager.addMapping(CLICK, new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(selfCardsNode,CLICK);
        inputManager.addListener(pauseButton, CLICK, PAUSE);
        inputManager.addListener(opButtonNode1, CLICK);
        inputManager.addListener(playerButtonNode1, CLICK);
        
        inputManager.addListener(opButtonNode2, CLICK);
        inputManager.addListener(playerButtonNode2, CLICK);
        
        inputManager.addListener(shootButton, CLICK);
        inputManager.addListener(passButton, CLICK);
        inputManager.addListener(breakthroughButton, CLICK);
//        cardManager.enterScene();
    }
    
        @Override
    protected void onDisable() {
        prepareNode.removeFromParent();
        //remove the listener
        inputManager.deleteMapping(PAUSE);
        inputManager.removeListener(selfCardsNode);
        inputManager.deleteMapping(CLICK);
        inputManager.removeListener(pauseButton);
        inputManager.removeListener(opButtonNode1);
        inputManager.removeListener(shootButton);
        inputManager.removeListener(passButton);
        inputManager.removeListener(breakthroughButton);
        inputManager.removeListener(playerButtonNode1);
        inputManager.removeListener(opButtonNode1);
        inputManager.removeListener(playerButtonNode2);
        inputManager.removeListener(opButtonNode2);
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
    
    public void showPlayer1ActionButtons(){
        hidePlayerLists();
        prepareNode.attachChild(shootButton);
        prepareNode.attachChild(breakthroughButton);
        prepareNode.attachChild(passButton);
    }
    
    public void showPlayer2ActionButtons(){
        hideActionButtons();
        hidePlayerLists();
        prepareNode.attachChild(shootButton);
        prepareNode.attachChild(breakthroughButton);
    }
    
    public void hideActionButtons(){
        shootButton.removeFromParent();
        breakthroughButton.removeFromParent();
        passButton.removeFromParent();

    }
    
    public void showPlayerLists(){
        hideActionButtons();
        prepareNode.attachChild(selfCardsNode);
        selfCardsNode.licensing();
    }
    
    public void hidePlayerLists(){
        selfCardsNode.removeFromParent();
    }
    
    public void changeActionButtonState(ButtonState state){
        shootButton.changeButtonState(state);
        breakthroughButton.changeButtonState(state);
    }
    
    public void changeCardsState(ButtonState state){
        selfCardsNode.changeCardsState(state);
    }
}
