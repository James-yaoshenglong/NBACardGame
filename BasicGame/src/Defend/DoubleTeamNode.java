/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Defend;

import Battle.Card;
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
 * @author yuchwang7
 */
public class DoubleTeamNode extends Node implements ActionListener{
    
    private ArrayList<DoubleTeamButton> buttons;
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;
    private PositionsNode pNode;
    private DoubleTeamCardsNode dtCardsNode;
    private DoubleTeamShow dtShow;
    private int hasShown;
    private Card doubleteamCard;
    private Card unguardedCard;
    private Card lastdtCard;
    private Card lastugCard;
    
    public DoubleTeamNode(SimpleApplication mainApp, float aCamWidth, float aCamHeight){
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        this.buttons = new ArrayList<>();
        //this.dtCardsNode = new DoubleTeamCardsNode(app,width,height);
        this.dtCardsNode = null;
        this.dtShow = new DoubleTeamShow(app,width,height);
        for(int i=0;i<5;i++){
            DoubleTeamButton dbt = new DoubleTeamButton(app,width,height);
            buttons.add(dbt);
        }
        hasShown=0;
        //this.attachChild(dtCardsNode);
        this.attachChild(dtShow);
    }
    
    public void arrange(ArrayList<DoubleTeamButton> buttons){
        for(int i = 0; i<buttons.size();i++){
            DoubleTeamButton dtb = buttons.get(i);
            dtb.setLocalTranslation((i%5-2)*(width/5),height*(-1/64),0);
            this.attachChild(dtb);
        }
    }
    
    public void hideButtons(ArrayList<DoubleTeamButton> buttons){
        for(int i = 0; i<buttons.size();i++){
            DoubleTeamButton dtb = buttons.get(i);
            this.detachChild(dtb);
        }        
    }
    
    public void setPNode(PositionsNode node){
        pNode = node;
    }
    
    @Override
    public void onAction(String name, boolean isClicked, float tpf) {
        if(isClicked && this.getParent() != null){
            if(name.equals("CLICK")){
                handle();
            }
        }

    }
    
    private void handle(){
        Ray ray = MyRay.createRay(app);
        CollisionResults results = new CollisionResults();
        this.collideWith(ray, results);
        if(results.size() > 0){
            Geometry targetDTGeom = results.getFarthestCollision().getGeometry(); //get the closest target in our eyes
            if(targetDTGeom.getParent().getClass() == DoubleTeamShow.class){
                if(pNode.getChosen()==5){
                    if(hasShown==0){
                        arrange(buttons);
                        hasShown+=1;
                    }
                    else if(hasShown==1){
                        hideButtons(buttons);
                        hasShown-=1;
                    }
                    else if(hasShown==2){
                        this.detachChild(dtCardsNode);
                        app.getStateManager().getState(MainDefend.class).setLocked(Boolean.FALSE);
                        hasShown-=1;
                    }                    
                }
            }
            if(targetDTGeom.getParent().getClass() == DoubleTeamButton.class){
                if(pNode.getChosen()==5){
                    DoubleTeamButton targetDT = (DoubleTeamButton)targetDTGeom.getParent();
                    int id=0;
                    for(DoubleTeamButton button : buttons){
                        if(button.equals(targetDT)){
                            break;
                        }
                        id++;
                    }
                    if(doubleteamCard != null && hasShown!=2){
                        lastdtCard = doubleteamCard;
                    }
                    doubleteamCard = pNode.getDefendPlayer(id);
                    System.out.printf("current card:%d\n",doubleteamCard.getID());
                    if(hasShown==2){
                        hasShown=1;
                        this.detachChild(dtCardsNode);
                        dtCardsNode = null;
                    }
                    this.dtCardsNode = new DoubleTeamCardsNode(app,width,height,doubleteamCard);
                    if(doubleteamCard != null){
                        this.attachChild(dtCardsNode);
                        hasShown++;
                    }
                    app.getStateManager().getState(MainDefend.class).setLocked(Boolean.TRUE);                   
                }

            }
            if(targetDTGeom.getParent().getClass() == Card.class){
                Card targetCard = (Card)targetDTGeom.getParent();
                for(Card c:dtCardsNode.getLineups()){
                    if(c.getID()==targetCard.getID()){
                        if(unguardedCard != null){
                            lastugCard = unguardedCard;
                        }
                        unguardedCard = c;
                        break;
                    }
                }
                if(lastdtCard != null){
                    app.getStateManager().getState(MainDefend.class).hideCardDoubleteam(lastdtCard);
                }
                if(lastugCard != null){
                    app.getStateManager().getState(MainDefend.class).hideCardUnguarded(lastugCard);
                }
                app.getStateManager().getState(MainDefend.class).showCardDoubleteam(doubleteamCard);
                app.getStateManager().getState(MainDefend.class).showCardUnguarded(unguardedCard);
                System.out.printf("unguarded card:%d\n", unguardedCard.getID());
                this.detachChild(dtCardsNode);
                dtCardsNode = null;
                hasShown-=1;
                app.getStateManager().getState(MainDefend.class).setLocked(Boolean.FALSE);
            }
        }        
    }
    
    public Card getCardDoubleteam(){
        return doubleteamCard;
    }
    
    public Card getCardUnguarded(){
        return unguardedCard;
    }
    
}
