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
import com.jme3.renderer.Camera;

/**
 *
 * @author shenglyao2
 */
public class MainGame extends BaseAppState{
    private SimpleApplication app;
    private AssetManager assetManager;
    private AppStateManager stateManager;
    private InputManager inputManager;
    private Camera cam;

    @Override
    protected void initialize(Application mainApp) {
        this.app = (SimpleApplication)mainApp;
        this.assetManager = mainApp.getAssetManager();
        this.stateManager = mainApp.getStateManager();
        this.inputManager = mainApp.getInputManager();
        this.cam = mainApp.getCamera();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void cleanup(Application arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onEnable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onDisable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
