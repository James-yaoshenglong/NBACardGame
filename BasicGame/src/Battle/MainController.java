/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battle;

import com.jme3.app.SimpleApplication;
import com.jme3.scene.Node;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author shenglyao2
 */
public class MainController {
    private SimpleApplication app;
    private float camera_width;
    private ArrayList<Card> myTeam;
    private ArrayList<Card> lineup;
    private ArrayList<Card> enemyTeam;
    private DisplayController display;
    
    public MainController(SimpleApplication mainApp, float camWidth, float camHeight, SelfCardsNode HandCardNode, Node ButtonNode){
        this.app = mainApp;
        this.camera_width = camWidth;
        this.myTeam = new ArrayList<>();
        this.enemyTeam = new ArrayList<>();
        this.lineup = new ArrayList<>();
        this.display = new DisplayController(app, HandCardNode, ButtonNode, camWidth, camHeight);
        int teamSize = 10;
        int totalPlayerSize = 100; 
        //random assign player to player's team
        Random r = new Random();
        ArrayList<Integer> arr = new ArrayList<>();
        int count = 0;
        while(count < teamSize){
            int number = r.nextInt(totalPlayerSize);  
            if(!arr.contains(number)){
                arr.add(number);
                count++;
            }
        }
        //sed this id list and get the id list from the server
        ArrayList<Integer> EnemyIdList = arr;
        for(int i=0; i<teamSize; i++){
            myTeam.add(new Card(arr.get(i),camera_width,app));
        }
        for(int i=0; i<teamSize; i++){
            enemyTeam.add(new Card(EnemyIdList.get(i),camera_width,app));
        }
        HandCardNode.licensing(myTeam);   
        display.confirmlineup();
    }
    
//    public void enterScene(){
//        display.licensing(myTeam);
//    }
    
    public void playin(Card c){
        lineup.add(c);
        myTeam.remove(c);
    }
    
    public void exitlineup(Card c){
        lineup.remove(c);
        myTeam.add(c);
    }
    
    public int chosenplayer(){
        return lineup.size();
    }
    
    
}
