/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

/**
 *
 * @author shenglyao2
 */
public class ClickListener implements ActionListener{
    private SimpleApplication app;
    private Node targetNode;
    
    public ClickListener(SimpleApplication mainApp, Node aTargetNode){
        this.app = mainApp;
        this.targetNode = aTargetNode;
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
        targetNode.collideWith(ray, results);
        if(results.size() > 0){
            Geometry geom = results.getFarthestCollision().getGeometry(); //get the closest target in our eyes 
            //still have the bug do not know which one is choosed when stack together
            geom.getParent().removeFromParent();
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
