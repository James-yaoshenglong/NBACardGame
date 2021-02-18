/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prepare;

import Widgets.ButtonState;
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
/**
 *
 * @author feegee2000
 */
public class PlayerButton extends Node implements ActionListener{
    private Geometry buttonGeom;
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;
    private ButtonState playerButtonState;
    
    public PlayerButton(SimpleApplication mainApp, float aCamWidth, float aCamHeight, ButtonState aPlayerButtonState){
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        this.playerButtonState = aPlayerButtonState;
        initialize();
    }
    
    private void initialize(){
        Quad quad = new Quad(width*(0.32f*(5f/12f)-0.002f),width*(0.24f*7f/9f-0.003f));
        buttonGeom = new Geometry("operation_button",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/default_player_bg.jpg");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        buttonGeom.setMaterial(mat);
        buttonGeom.setLocalTranslation(width*(0.32f*(-5f/12f)), width*(0.24f/9f), 2);
        this.attachChild(buttonGeom);
    }
    
    @Override
    public void onAction(String name, boolean isClicked, float tpf) {
        if(isClicked && name.equals("CLICK") && this.getParent() != null){
            Ray ray = MyRay.createRay(app);
                CollisionResults results = new CollisionResults();
                this.collideWith(ray, results);
                if(results.size() > 0){
                    playerButtonState.showCards();
                }
        }
    }
}
