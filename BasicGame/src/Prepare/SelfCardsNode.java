/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prepare;

import Battle.Card;
import Battle.MainGame;
import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import java.util.ArrayList;

/**
 *
 * @author feegee2000
 */
public class SelfCardsNode extends Node implements ActionListener{
    private ArrayList<Card> lineup;
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;
    
    public SelfCardsNode(SimpleApplication mainApp, float aCamWidth, float aCamHeight){
        this.app = mainApp;
        lineup = app.getStateManager().getState(MainGame.class).getLineupCards();
        this.width = aCamWidth;
        this.height = aCamHeight;
    }
    
    public void licensing(){
        for(int i =0; i<lineup.size(); i++){
            
            Node card = lineup.get(i);
            
            card.setLocalTranslation((i%5-2)*(width/5),height*(-0.3f),0);
            
            this.attachChild(card);
            
        }
    }

    @Override
    public void onAction(String name, boolean isClicked, float tpf) {
        if(isClicked){
            if(name.equals("CLICK")){
                choose();
            }
        }
    }
    
    private void choose(){
        Ray ray = createRay();
        CollisionResults results = new CollisionResults();
        this.collideWith(ray, results);
        if(results.size() > 0){
            Geometry targetCardGeom = results.getFarthestCollision().getGeometry(); //get the closest target in our eyes
            Card targetCardNode = (Card)targetCardGeom.getParent();
            targetCardGeom.getMaterial().setColor("Color", new ColorRGBA(0.2f,0.2f,0.2f,1f));
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
