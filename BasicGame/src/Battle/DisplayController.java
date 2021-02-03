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
import com.jme3.material.Material;
import com.jme3.math.Quaternion;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
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
    private Node buttonNode;
    private Node battleNode;
    private float width;
    private float height;
    
    public DisplayController(SimpleApplication mainApp, Node aHandCardNode, Node abuttonNode, float aCamWidth, float aCamHeight){
        this.app = (SimpleApplication)mainApp;
        this.assetManager = mainApp.getAssetManager();
        this.stateManager = mainApp.getStateManager();
        this.inputManager = mainApp.getInputManager();
        this.cam = mainApp.getCamera();
        this.rootNode = app.getRootNode();
        this.handCardNode = aHandCardNode;
        this.buttonNode = abuttonNode;
        this.battleNode = handCardNode.getParent();
        this.width = aCamWidth;
        this.height = aCamHeight;
    }
    
//    public void licensing(ArrayList<Card> cardList){
//        for(int i =0; i<cardList.size(); i++){
////            float[] angles = new float[3];
////            angles[0] = (float) Math.toRadians(i*0f);
////            angles[1] = (float) Math.toRadians(i*0f);
////            angles[2] = (float) Math.toRadians(i*5f);
////
////            Quaternion rot = new Quaternion(angles);
//            
//            Node card = cardList.get(i);
//            if(i<5){
//                card.setLocalTranslation((i%5-2)*(width/5),height/20,0);
//            }
//            else{
//                card.setLocalTranslation((i%5-2)*(width/5), (float)(height*(-0.4)), 0);
//            }
//            
//            handCardNode.attachChild(card);
////            card.rotate(rot);
//            
//        }
//    }
    
    public void confirmlineup(){
        Quad quad = new Quad(width/16,height/16);
        Geometry geom = new Geometry("confirm_button",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/confirm_button.jpg");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        geom.setMaterial(mat);
        geom.setLocalTranslation(-width/32, (float)(height*(-0.48)), 0);
        buttonNode.attachChild(geom);
    }
 
}
