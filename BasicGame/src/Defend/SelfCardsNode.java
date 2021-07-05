/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Defend;

import Battle.Card;
import Battle.MainGame;
import Widgets.MyRay;
import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.RawInputListener;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import java.util.ArrayList;
import java.util.Random;

public class SelfCardsNode extends Node implements ActionListener, RawInputListener{
    private ArrayList<Card> lineup;
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;
    private Boolean isPressed = false; //check whether the mouse is pressed
    private Card targetCardNode; //the chosen card
    private Vector3f originPosition; //this is an object reference
    private float relativeWidth; //the relative distance between the click position and the node
    private float relativeHeight;
    
    public SelfCardsNode(SimpleApplication mainApp, float aCamWidth, float aCamHeight){
        this.lineup = new ArrayList<>();
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        for(Card c : app.getStateManager().getState(MainGame.class).getLineupCards()){
            Card card = new Card(c.getID(),width,app);
            lineup.add(card);
        }
        licensing();
    }
    
    @Override
    public void onAction(String name, boolean isClicked, float tpf) {
        if(isClicked && this.getParent() != null){
            if(name.equals("CLICK")){
                choose();
            }
        }
        if(!isClicked && this.getParent() != null){
            if(name.equals("CLICK")){
                drop();
            }
        }
    }
    
    private void choose(){
        Ray ray = MyRay.createRay(app);
        CollisionResults results = new CollisionResults();
        this.collideWith(ray, results);
        if(results.size() > 0){
            Geometry targetCardGeom = results.getFarthestCollision().getGeometry(); //get the closest target in our eyes
            if(targetCardGeom.getParent().getClass() == Card.class){
                Vector2f screenCoord = app.getInputManager().getCursorPosition();
                targetCardNode = (Card)targetCardGeom.getParent();
                isPressed = true;
                originPosition = new Vector3f(targetCardNode.getLocalTranslation());
                relativeWidth = width/app.getCamera().getWidth()*screenCoord.getX()-width/2-targetCardNode.getLocalTranslation().getX();
                relativeHeight = height/app.getCamera().getHeight()*screenCoord.getY()-height/2-targetCardNode.getLocalTranslation().getY();
            }
        }
    }
    
    private void drop(){
        if(targetCardNode != null){
            isPressed = false;
            Ray ray = MyRay.createRay(app);
            CollisionResults results = new CollisionResults();
            this.getParent().getChild("positionsNode").collideWith(ray, results);
            if(results.size() > 0){
                Geometry positionGeom = results.getFarthestCollision().getGeometry(); //get the closest target in our eyes
                if(positionGeom.getParent().getParent().getClass() == PositionsNode.class){
                    DefendPositionNode positionNode = (DefendPositionNode)positionGeom.getParent();
                    Vector3f targetPosition = positionNode.getLocalTranslation();
                    targetCardNode.setLocalTranslation(new Vector3f(targetPosition.getX(),targetPosition.getY(),targetPosition.getZ()+0.1f));
                    targetCardNode = null;
                    originPosition = null;
                    return;
                }
            }
            targetCardNode.setLocalTranslation(originPosition);
            targetCardNode = null;
            originPosition = null;
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
    public void beginInput() {
    }

    @Override
    public void endInput() {
    }

    @Override
    public void onJoyAxisEvent(JoyAxisEvent evt) {
    }

    @Override
    public void onJoyButtonEvent(JoyButtonEvent evt) {
    }

    @Override
    public void onMouseMotionEvent(MouseMotionEvent evt) {
        if(isPressed && targetCardNode != null){
            Vector2f screenCoord = app.getInputManager().getCursorPosition();          
            targetCardNode.setLocalTranslation(width/app.getCamera().getWidth()*screenCoord.getX()-width/2-relativeWidth,
                                                height/app.getCamera().getHeight()*screenCoord.getY()-height/2-relativeHeight,
                                                0f);
        }
    }

    @Override
    public void onMouseButtonEvent(MouseButtonEvent evt) {
    }

    @Override
    public void onKeyEvent(KeyInputEvent evt) {
    }

    @Override
    public void onTouchEvent(TouchEvent evt) {
    }
    
}
