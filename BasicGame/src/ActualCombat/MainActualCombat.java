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
    
    public void setData(AttackData ad, DefendData dd){
        this.attackData = ad;
        this.defendData = dd;
    }
}

