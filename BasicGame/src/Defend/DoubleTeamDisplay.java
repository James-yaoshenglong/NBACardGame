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
import com.jme3.math.Ray;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;

/**
 *
 * @author yuchwang7
 */
public class DoubleTeamDisplay extends Node{
    private Geometry dtGeom;
    private Geometry ugGeom;
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;
    
    public DoubleTeamDisplay(SimpleApplication mainApp, float aCamWidth){
        this.app = mainApp;
        this.width = aCamWidth;
        dtInitialize();
        unguardedInitialize();
    }
    
    
    private void dtInitialize(){
        Quad quad = new Quad(width*(2f/15f),width/15);
        dtGeom = new Geometry("double_team_display",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/double_team_display.jpg");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        dtGeom.setMaterial(mat);
        dtGeom.setLocalTranslation(-((BoundingBox)dtGeom.getWorldBound()).getXExtent(),0.0f,0.0f);
    }
    
    private void unguardedInitialize(){
        Quad quad = new Quad(width*(2f/15f),width/15);
        ugGeom = new Geometry("unguarded_button",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/unguarded_display.jpg");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        ugGeom.setMaterial(mat);
        ugGeom.setLocalTranslation(-((BoundingBox)ugGeom.getWorldBound()).getXExtent(),0.0f,0.0f);
    }
    
    public void dtDisplay(){
        this.attachChild(dtGeom);
    }
    
    public void dtVanish(){
        this.detachChild(dtGeom);
    }
    
    public void ugDisplay(){
        this.attachChild(ugGeom);
    }
    
    public void ugVanish(){
        this.detachChild(ugGeom);
    }
    
}
