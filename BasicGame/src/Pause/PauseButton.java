/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pause;

import Main.Main;
import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.material.Material;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;

/**
 *
 * @author shenglyao2
 */
public class PauseButton extends Node implements ActionListener{
    private Geometry buttonGeom;
    private SimpleApplication app;
    private float width;
    private float height;
    
    public PauseButton(SimpleApplication mainApp, float aCamWidth, float aCamHeight){
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        initialize();
    }
    
    private void initialize(){
        Quad quad = new Quad(width/24,height/24);
        buttonGeom = new Geometry("confirm_button",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/confirm_button.jpg");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        buttonGeom.setMaterial(mat);
        buttonGeom.setLocalTranslation(width/24*11, height/24*11, 0);
        this.attachChild(buttonGeom);
    }
    
   @Override
    public void onAction(String name, boolean isClicked, float tpf) {
        if(name.equals("PAUSE") && isClicked){
            ((Main)app).switchfromMaintoPause();
        }
        if(isClicked && name.equals("CLICK")){
            Ray ray = createRay();
            CollisionResults results = new CollisionResults();
            this.collideWith(ray, results);
            if(results.size() > 0){
                ((Main)app).switchfromMaintoPause();
            }
        }
    }
    
    
        private Ray createRay(){
        Ray ray = new Ray();
        //set the origin of the ray
        ray.setOrigin(app.getCamera().getLocation());
        //compute the direction of the ray
        Vector2f screenCoord = app.getInputManager().getCursorPosition();
        Vector3f worldCoord = app.getCamera().getWorldCoordinates(screenCoord, 1f);
        Vector3f dir = worldCoord.subtract(app.getCamera().getLocation());
        dir.normalizeLocal();
        ray.setDirection(dir);
        return ray;
    }
}