/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battle;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
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
    private Node handCardNode;
    private Node battleNode;
    private float width;
    private float height;
    
    public DisplayController(SimpleApplication mainApp, Node aHandCardNode, float aCamWidth, float aCamHeight){
        this.app = (SimpleApplication)mainApp;
        this.assetManager = mainApp.getAssetManager();
        this.stateManager = mainApp.getStateManager();
        this.inputManager = mainApp.getInputManager();
        this.cam = mainApp.getCamera();
        this.rootNode = app.getRootNode();
        this.handCardNode = aHandCardNode;
        this.battleNode = handCardNode.getParent();
        this.width = aCamWidth;
        this.height = aCamHeight;
    }
    
    public void licensing(ArrayList<Card> cardList){
        for(int i =0; i<cardList.size(); i++){
//            float[] angles = new float[3];
//            angles[0] = (float) Math.toRadians(i*0f);
//            angles[1] = (float) Math.toRadians(i*0f);
//            angles[2] = (float) Math.toRadians(i*5f);
//
//            Quaternion rot = new Quaternion(angles);
            
            Node card = cardList.get(i);
            if(i<5){
                card.setLocalTranslation((i%5-2)*(width/5),height/20,0);
            }
            else{
                card.setLocalTranslation((i%5-2)*(width/5), (float)(height*(-0.4)), 0);
            }
            
            handCardNode.attachChild(card);
//            card.rotate(rot);
            
        }
    }
    
 
}
