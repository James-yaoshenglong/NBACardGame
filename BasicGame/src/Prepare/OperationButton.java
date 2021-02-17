/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prepare;

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
    
    public OperationButton(SimpleApplication mainApp, float aCamWidth, float aCamHeight){
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        initialize();
    }
    
    
    private void initialize(){
        Quad quad = new Quad(width*(0.32f/3f-0.002f),width*(0.24f*7f/9f-0.003f));
        buttonGeom = new Geometry("operation_button",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/action_button.jpg");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        buttonGeom.setMaterial(mat);
        buttonGeom.setLocalTranslation(width*(0.32f/12f), width*(0.24f/9f), 2);
        this.attachChild(buttonGeom);
    }
    

    @Override
    public void onAction(String name, boolean isClicked, float tpf) {
        if(isClicked && name.equals("CLICK")){
            Ray ray = MyRay.createRay(app);
                CollisionResults results = new CollisionResults();
                this.collideWith(ray, results);
                if(results.size() > 0){
                    app.getStateManager().getState(MainPrepare.class).showButton();
                }
        }
    }
    
    public void shoot_chosen(){
        Quad quad = new Quad(width*(0.32f/3f-0.002f),width*(0.24f*7f/9f-0.003f));
        shootGeom = new Geometry("shoot_chosen",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/shoot_chosen.jpg");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        buttonGeom.setMaterial(mat);
        buttonGeom.setLocalTranslation(width*(0.32f/12f), width*(0.24f/9f), 2);
        this.detachChild(buttonGeom);
        this.attachChild(shootGeom);
    }
    
    public void breakthrough_chosen(){
        Quad quad = new Quad(width*(0.32f/3f-0.002f),width*(0.24f*7f/9f-0.003f));
        breakthroughGeom = new Geometry("breakthrough_chosen",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/breakthrough_chosen.jpg");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        buttonGeom.setMaterial(mat);
        buttonGeom.setLocalTranslation(width*(0.32f/12f), width*(0.24f/9f), 2);
        this.detachChild(buttonGeom);
        this.attachChild(breakthroughGeom);
    }
    
    public void pass_chosen(){
        Quad quad = new Quad(width*(0.32f/3f-0.002f),width*(0.24f*7f/9f-0.003f));
        passGeom = new Geometry("pass_chosen",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/pass_chosen.jpg");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        buttonGeom.setMaterial(mat);
        buttonGeom.setLocalTranslation(width*(0.32f/12f), width*(0.24f/9f), 2);
        this.detachChild(buttonGeom);
        this.attachChild(passGeom);
    }
    
}
    
