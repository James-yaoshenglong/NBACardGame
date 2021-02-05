/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prepare;

import com.jme3.app.SimpleApplication;
import com.jme3.input.controls.ActionListener;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;

/**
 *
 * @author feegee2000
 */
public class PassButton extends Node implements ActionListener{
    private Geometry buttonGeom;
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;

    public PassButton(SimpleApplication mainApp, float aCamWidth, float aCamHeight){
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        initialize();
    }
    
    
    private void initialize(){
        Quad quad = new Quad(width/7,width/7);
        buttonGeom = new Geometry("pass_button",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/pass_button.png");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        buttonGeom.setMaterial(mat);
        mat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        buttonGeom.setQueueBucket(RenderQueue.Bucket.Transparent); 
        buttonGeom.setLocalTranslation(width*(11f/42f), height*0.2f, 0);
        this.attachChild(buttonGeom);
    }
    

    @Override
    public void onAction(String arg0, boolean arg1, float arg2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
