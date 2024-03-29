/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battle;

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

/**
 *
 * @author 影音娱乐剪辑
 */
public class SelfCardsNode extends Node implements ActionListener, RawInputListener{
    private ArrayList<Card> myTeam;
    private ArrayList<Card> lineup;
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;
    
    public SelfCardsNode(SimpleApplication mainApp, float aCamWidth, float aCamHeight){
        this.myTeam = new ArrayList<>();
        this.lineup = new ArrayList<>();
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        deal();
        licensing(myTeam);
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
        if(isClicked && this.getParent() != null){
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
            if(targetCardGeom.getParent().getClass() == Card.class){
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
    }
        
    
    
    public void licensing(ArrayList<Card> cardList){
        for(int i =0; i<cardList.size(); i++){
//            float[] angles = new float[3];
//            angles[0] = (float) Math.toRadians(i*0f);
//            angles[1] = (float) Math.toRadians(i*0f);
//            angles[2] = (float) Math.toRadians(i*5f);
//
//            Quaternion rot = new Quaternion(angles);
            
            Card card = cardList.get(i);
//            this.attachChild(card);
            if(i<5){
                card.setLocalTranslation((i%5-2)*(width/5),height/20,0);
            }
            else{
                card.setLocalTranslation((i%5-2)*(width/5), (float)(height*(-0.4)), 0);
            }
            
            this.attachChild(card);
//            card.rotate(rot);
            
        }
    }
    
    
    private void deal(){
        int teamSize = 10;
        int totalPlayerSize = 20; 
        //random assign player to player's team
        Random r = new Random();
        ArrayList<Integer> arr = new ArrayList<>();
        int count = 0;
        while(count < teamSize){
            int number = 1+r.nextInt(totalPlayerSize);  
            if(!arr.contains(number)){
                arr.add(number);
                count++;
            }
        }
        
        //later need to send this id list to the server
        ArrayList<Integer> EnemyIdList = arr;
        for(int i=0; i<teamSize; i++){
            myTeam.add(new Card(arr.get(i),width,app));
        }
    }
    
    public ArrayList<Card> getLineup(){
        return lineup;
    }
    
    public boolean checkLineup(){
        if(lineup.size() == 5){
            return true;
        }
        return false;
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
        Ray ray = MyRay.createRay(app);
        CollisionResults results = new CollisionResults();
        this.collideWith(ray, results);
        if(results.size() > 0){
            //may be can use Z value test to solve the stack pic problem 
            Geometry targetCardGeom = results.getFarthestCollision().getGeometry(); //get the closest target in our eyes
            if(targetCardGeom.getParent().getClass() == Card.class){
                Card targetCardNode = (Card)targetCardGeom.getParent();
                targetCardNode.showBattleValue();
            }
        }
        else{
            for(Card c : myTeam){
                c.hideBattleValue();
            }
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
