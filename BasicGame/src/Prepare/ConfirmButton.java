/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prepare;

import ActualCombat.MainActualCombat;
import Battle.Card;
import Battle.MainGame;
import Main.Main;
import Widgets.CombatDataOperation;
import Widgets.MyRay;
import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.material.Material;
import com.jme3.math.Ray;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import java.util.ArrayList;
import network.client.GameClient;
import network.data.AttackData;
import network.data.DefendData;
import network.data.ResponseData;
import network.data.ResponseOperation;

/**
 *
 * @author shenglyao2
 */
public class ConfirmButton extends Node implements ActionListener{
    private Geometry buttonGeom;
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;
    
    public ConfirmButton(SimpleApplication mainApp, float aCamWidth, float aCamHeight){
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        initialize();
    }
    
    
    private void initialize(){
        Quad quad = new Quad(width/16,height/16);
        buttonGeom = new Geometry("confirm_button",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/confirm_button.jpg");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        buttonGeom.setMaterial(mat);
        buttonGeom.setLocalTranslation(-width/32, (float)(height*(0.15)), 0.1f);
        this.attachChild(buttonGeom);
    }
    

    @Override
    public void onAction(String name, boolean isClicked, float tpf) {
        if(isClicked && name.equals("CLICK") && this.getParent() != null){
            Ray ray = MyRay.createRay(app);
            CollisionResults results = new CollisionResults();
            this.collideWith(ray, results);
            if(results.size() > 0 && !CombatDataOperation.getInstance().getSendFlag()){
                AttackData msg = app.getStateManager().getState(MainPrepare.class).setMessage();
                if(msg != null){
                    msg.setLineUp(getLineUpId());
                    CombatDataOperation.getInstance().switchState();
                    GameClient.getInstance().transportData(msg);
                }
            }
        }
    }
    
    private ArrayList<Integer> getLineUpId(){
        ArrayList<Integer> tmp = new ArrayList<>(); 
        for(Card c : app.getStateManager().getState(MainGame.class).getLineupCards()){
            tmp.add(c.getID());
        }
        return tmp;
    }
}
