/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Defend;

import Battle.Card;
import Battle.MainGame;
import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import java.util.ArrayList;

public class PositionsNode extends Node{
    private ArrayList<DefendPositionNode> lineup;
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;
    private float relativeWidth; //the relative distance between the click position and the node
    private float relativeHeight;
    
    public PositionsNode(SimpleApplication mainApp, float aCamWidth, float aCamHeight){
        this.setName("positionsNode");
        this.lineup = new ArrayList<>();
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        //for(Card c : app.getStateManager().getState(MainGame.class).getLineupCards()){
        //    Card card = new Card(c.getID(),width,app);
        //    lineup.add(card);
        //}
        for(int i=0; i<5;i++){
            DefendPositionNode defensenode = new DefendPositionNode(app,width,height);
            lineup.add(defensenode);
        }
        licensing();
    }
    
    private void licensing(){
        for(int i=0; i<lineup.size();i++){
            DefendPositionNode defendNode = lineup.get(i);
            System.out.println(defendNode);
            defendNode.setLocalTranslation((i%5-2)*(width/5),height/20,0);
            this.attachChild(defendNode);
        }
    }
    
    public DefendPositionNode getNode(int id){
        return lineup.get(id);
    }
}

