/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battle;

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
 * @author 影音娱乐剪辑
 */
public class SelfCardsNode extends Node implements ActionListener{
    private ArrayList<Card> lineup;
    private SimpleApplication app;
    
    public SelfCardsNode(SimpleApplication mainApp){
        this.lineup = new ArrayList<>();
        this.app = mainApp;
    }
    
    private void playin(Card c){
        lineup.add(c);
    }
    
    private void exitlineup(Card c){
        lineup.remove(c);
    }
    
    private int chosenplayer(){
        return lineup.size();
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
            if(targetCardNode.getStatus() == false){
                if(chosenplayer()<5){
                   targetCardGeom.getMaterial().setColor("Color", new ColorRGBA(0.2f,0.2f,0.2f,1f));
                   playin(targetCardNode);
                   targetCardNode.toggleStatus();
                }
            }
            else{
                targetCardGeom.getMaterial().setColor("Color", new ColorRGBA(1f,1f,1f,0f));
                exitlineup(targetCardNode);
                targetCardNode.toggleStatus();
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
