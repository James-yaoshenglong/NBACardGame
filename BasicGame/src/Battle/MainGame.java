/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battle;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.material.Material;
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

    @Override
    protected void initialize(Application mainApp) {
        //add a copy of reference of some common use attributes from app
        this.app = (SimpleApplication)mainApp;
        this.assetManager = mainApp.getAssetManager();
        this.stateManager = mainApp.getStateManager();
        this.inputManager = mainApp.getInputManager();
        this.cam = mainApp.getCamera();
        this.rootNode = app.getRootNode();
       
    }

    @Override
    protected void cleanup(Application arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onEnable() {
        //initialize the background
        app.getFlyByCamera().setEnabled(false);
        constructBackground();
       
    }

    @Override
    protected void onDisable() {
        rootNode.removeFromParent();
    }
    
    private void constructBackground(){
        Material backgroundMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture backgroundTex = assetManager.loadTexture("Interface/bg/bg.jpg");
        backgroundMat.setTexture("ColorMap", backgroundTex);

        float w = app.getContext().getSettings().getWidth(); // the screen width
        float h = app.getContext().getSettings().getHeight(); // the screen width
        float ratio = w/h; //the width-height ratio of the screen

        cam.setLocation(Vector3f.ZERO.add(new Vector3f(0.0f, 0.0f,100f)));//Move the Camera back
        float camZ = cam.getLocation().getZ()-15; //No Idea why I need to subtract 15
        float width = camZ*ratio;
        float height = camZ;

        Quad fsq = new Quad(width, height);
        Geometry backgroundGeom = new Geometry("Background", fsq);
        backgroundGeom.setQueueBucket(RenderQueue.Bucket.Sky);
        backgroundGeom.setCullHint(Spatial.CullHint.Never);
        backgroundGeom.setMaterial(backgroundMat);
        backgroundGeom.setLocalTranslation(-(width / 2), -(height/ 2), 0); //Need to Divide by two because the quad origin is bottom left
        rootNode.attachChild(backgroundGeom);
    }
}
