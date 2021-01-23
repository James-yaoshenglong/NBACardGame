/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battle;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.math.Quaternion;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import java.util.ArrayList;

/**
 *
 * @author shenglyao2
 */

public class DisplayController {
    private SimpleApplication app;
    private AssetManager assetManager;
    private AppStateManager stateManager;
    private InputManager inputManager;
    private Camera cam;
    private Node rootNode;
    
    public DisplayController(SimpleApplication mainApp){
        this.app = (SimpleApplication)mainApp;
        this.assetManager = mainApp.getAssetManager();
        this.stateManager = mainApp.getStateManager();
        this.inputManager = mainApp.getInputManager();
        this.cam = mainApp.getCamera();
        this.rootNode = app.getRootNode();
    }
    
    public void licensing(ArrayList<Card> cardList){
        for(int i =0; i<cardList.size(); i++){
            Geometry geom = cardList.get(i).getPic();
            float[] angles = new float[3];
            angles[0] = (float) Math.toRadians(i*0f);
            angles[1] = (float) Math.toRadians(i*0f);
            angles[2] = (float) Math.toRadians(i*5f);

            Quaternion rot = new Quaternion(angles);

            geom.rotate(rot);
            rootNode.attachChild(geom);
        }
    }
}
