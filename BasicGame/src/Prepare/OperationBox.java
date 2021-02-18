/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prepare;

import Battle.Card;
import Widgets.AttackActionState;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;

/**
 *
 * @author shenglyao2
 */
public class OperationBox extends Node{
    private Geometry BgGeom;
    private OperationButton opButtonNode;
    private PlayerButton playerButtonNode;
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;
    private Card player1;
    private AttackActionState actionState;
    
    
    public OperationBox(SimpleApplication mainApp, float aCamWidth, float aCamHeight, OperationButton anOpButtonNode, PlayerButton apButtonNode){
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        this.opButtonNode = anOpButtonNode;
        this.playerButtonNode = apButtonNode;
        initialize();
    }
    
    private void initialize(){
        Quad quad = new Quad(width*0.32f,width*0.24f);
        BgGeom = new Geometry("Op_box",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/frame.jpg");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap",tex);
        BgGeom.setMaterial(mat);
        BgGeom.setLocalTranslation(width*(-0.16f),0,0);
        this.attachChild(BgGeom);
        this.attachChild(opButtonNode);
    }
    
    public void choosePlayer1(int id){
        if(player1 == null){
            player1 = new Card(id,width,app); 
            player1.setSize(0.8f);
            player1.setLocalTranslation((float)(-1f/15f)*width, width*(0.24f/9), 1);
            this.attachChild(player1);
        }
        else if(player1.getID() != id){
            player1.removeFromParent();
            player1 = new Card(id,width,app);
            player1.setSize(0.8f);
            player1.setLocalTranslation((float)(-1f/15f)*width, width*(0.24f/9), 1);
            this.attachChild(player1);
        }
    }
    
    public void changeAttackActionState(AttackActionState state){
        this.actionState = state;
        actionState.showIcon(opButtonNode);
    }
}
