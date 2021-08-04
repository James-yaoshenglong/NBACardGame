/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Defend;

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
import Battle.Card;
import Battle.MainGame;

/**
 *
 * @author 影音娱乐剪辑
 */
public class DoubleTeamCardsNode extends Node{
    private ArrayList<Card> lineup;
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;
    
    public DoubleTeamCardsNode(SimpleApplication mainApp, float aCamWidth, float aCamHeight){
        this.lineup = new ArrayList<>();
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        for(Card c : app.getStateManager().getState(MainGame.class).getLineupCards()){
            Card card = new Card(c.getID(),width,app);
            lineup.add(card);
        }
    }
    
    
    public void licensing(Card currentCard){
        int id = 0;
        for(int i =0; i<lineup.size(); i++){
            Card card = lineup.get(i);
            if(card.getID()==currentCard.getID()){
                continue;
            }
            card.setLocalTranslation((2*id-3)*(width/8),(float)(height*(-0.4)),1f);
            id++;
            this.attachChild(card);

        }
    }

}
