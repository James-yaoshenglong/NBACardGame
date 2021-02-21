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
 * @author shenglyao2
 */
public class OperationButton extends Node implements ActionListener{
    private Geometry buttonGeom;
    private Geometry shootGeom;
    private Geometry passGeom;
    private Geometry breakthroughGeom;
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;
    private ButtonState opButtonState;
    
    public OperationButton(SimpleApplication mainApp, float aCamWidth, float aCamHeight, ButtonState anOpButtonState){
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        this.opButtonState = anOpButtonState;
        initialize();
    }
    
    
    private void initialize(){
        Quad quad = new Quad(width*(0.32f/3f),width*(0.24f*7f/9f));
        buttonGeom = new Geometry("operation_button",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/action_button.jpg");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        buttonGeom.setMaterial(mat);
        buttonGeom.setLocalTranslation(width*(0.32f/12f), width*(0.24f/9f), 0.1f);
        this.attachChild(buttonGeom);
        
        shoot_init();
        breakthrough_init();
        pass_init();
    }
    

    @Override
    public void onAction(String name, boolean isClicked, float tpf) {
        if(isClicked && name.equals("CLICK") && this.getParent() != null){
            Ray ray = MyRay.createRay(app);
                CollisionResults results = new CollisionResults();
                this.collideWith(ray, results);
                if(results.size() > 0){
                    opButtonState.showActionButton();
                }
        }
    }
    
    private void shoot_init(){
        Quad quad = new Quad(width*(0.32f/3f),width*(0.24f*7f/9f));
        shootGeom = new Geometry("shoot_chosen",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/shoot_chosen.jpg");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        shootGeom.setMaterial(mat);
        shootGeom.setLocalTranslation(width*(0.32f/12f), width*(0.24f/9f), 0.2f);
    }
    
    private void breakthrough_init(){
        Quad quad = new Quad(width*(0.32f/3f),width*(0.24f*7f/9f));
        breakthroughGeom = new Geometry("breakthrough_chosen",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/breakthrough_chosen.jpg");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        breakthroughGeom.setMaterial(mat);
        breakthroughGeom.setLocalTranslation(width*(0.32f/12f), width*(0.24f/9f), 0.2f);
    }
    
    private void pass_init(){
        Quad quad = new Quad(width*(0.32f/3f),width*(0.24f*7f/9f));
        passGeom = new Geometry("pass_chosen",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/pass_chosen.jpg");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        passGeom.setMaterial(mat);
        passGeom.setLocalTranslation(width*(0.32f/12f), width*(0.24f/9f), 0.2f);
    }
    
    
    public void shoot_chosen(){
        this.detachChild(buttonGeom);
        this.detachChild(breakthroughGeom);
        this.detachChild(passGeom);
        this.attachChild(shootGeom);
    }
    
    public void breakthrough_chosen(){
        this.detachChild(buttonGeom);
        this.detachChild(passGeom);
        this.detachChild(shootGeom);
        this.attachChild(breakthroughGeom);
    }
    
    public void pass_chosen(){
        this.detachChild(buttonGeom);
        this.detachChild(breakthroughGeom);
        this.detachChild(shootGeom);
        this.attachChild(passGeom);
    }
    
    public ButtonState getState(){
        return opButtonState;
    }
}
    
