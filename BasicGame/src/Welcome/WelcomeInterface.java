/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Welcome;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;

/**
 *
 * @author shenglyao2
 */
public class WelcomeInterface extends BaseAppState{
    private SimpleApplication app;
    private AssetManager assetManager;
    private AppStateManager stateManager;
    private InputManager inputManager;
    private Camera cam;
    private Node guiNode;
    
    @Override
    protected void initialize(Application mainApp) {
        this.app = (SimpleApplication)mainApp;
        this.assetManager = mainApp.getAssetManager();
        this.stateManager = mainApp.getStateManager();
        this.inputManager = mainApp.getInputManager();
        this.cam = mainApp.getCamera();
        this.guiNode = app.getGuiNode();
        
        
    }

    @Override
    protected void cleanup(Application app) {
        
    }

    @Override
    protected void onEnable() {
        Geometry bg = getpic();
        guiNode.attachChild(bg);
    }

    @Override
    protected void onDisable() {
        
    }
    
    private Geometry getpic(){
        float width = app.getContext().getSettings().getWidth(); // the screen width
        float height = app.getContext().getSettings().getHeight(); // the screen height
        Quad quad = new Quad(width, height);
        Geometry geom = new Geometry("bg", quad);
        Texture tex = assetManager.loadTexture("Interface/bg/Welcome_bg.jpeg");
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap",tex);
        geom.setMaterial(mat);
        geom.setLocalTranslation(0, 0, -2);
//        geom.center();
        return geom;
    }
}
