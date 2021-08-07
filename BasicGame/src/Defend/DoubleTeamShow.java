/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Defend;

import Widgets.MyRay;
import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.Ray;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;

/**
 *
 * @author yuchwang7
 */
public class DoubleTeamShow extends Node{
    private Geometry buttonGeom;
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;
    
    public DoubleTeamShow(SimpleApplication mainApp, float aCamWidth, float aCamHeight){
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        initialize();
    }
    
    
    private void initialize(){   
        Quad quad = new Quad(width/16,width*(3f/88f));
        buttonGeom = new Geometry("DoubleTeamShiw",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/doubleteamShow.png");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        buttonGeom.setMaterial(mat);
        mat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        buttonGeom.setQueueBucket(RenderQueue.Bucket.Transparent); 
        buttonGeom.setLocalTranslation(-width/2f, -width*(3f/176f), 0);
        this.attachChild(buttonGeom);
    }
    
}
