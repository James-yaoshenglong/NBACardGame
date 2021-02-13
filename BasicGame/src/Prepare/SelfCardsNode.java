/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prepare;

import Battle.Card;
import Battle.MainGame;
import Widgets.MyRay;
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
    private OperationBox opBox;
    
    public SelfCardsNode(SimpleApplication mainApp, float aCamWidth, float aCamHeight, OperationBox opBox){
        this.app = mainApp;
        lineup = new ArrayList<>();
        this.width = aCamWidth;
        this.height = aCamHeight;
        this.opBox = opBox;
        for(Card c : app.getStateManager().getState(MainGame.class).getLineupCards()){
            Card card = new Card(c.getID(),width,app);
            lineup.add(card);
        }
    }
    
    public void licensing(){
        for(int i =0; i<lineup.size(); i++){

            Card card = lineup.get(i);
            card.setLocalTranslation((i%5-2)*(width/5),height*(-0.45f),0);
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
        Ray ray = MyRay.createRay(app);
        CollisionResults results = new CollisionResults();
        this.collideWith(ray, results);
        if(results.size() > 0){
            Geometry targetCardGeom = results.getFarthestCollision().getGeometry(); //get the closest target in our eyes
            Card targetCardNode = (Card)targetCardGeom.getParent();
            targetCardGeom.getMaterial().setColor("Color", new ColorRGBA(0.2f,0.2f,0.2f,1f));
            opBox.choosePlayer1(targetCardNode.getID());
//            app.getStateManager().getState(MainPrepare.class).showButton();
              
        }
    }
    
}
