/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Defend;

import Battle.Card;
import Battle.MainGame;
import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import java.util.ArrayList;

public class DefendPositionNode extends Node{
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;
    private float relativeWidth; //the relative distance between the click position and the node
    private float relativeHeight;
    
    public DefendPositionNode(SimpleApplication mainApp, float aCamWidth, float aCamHeight){
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        initialize();
    }
    
    private void initialize(){
        Quad quad = new Quad(width/6,(width/30)*7);
        Geometry geom = new Geometry("Frame", quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/defend_frame.jpg");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap",tex);
        geom.setMaterial(mat);
        geom.setLocalTranslation(-((BoundingBox)geom.getWorldBound()).getXExtent(),0.0f,0.0f);
        this.attachChild(geom);
    }
}
