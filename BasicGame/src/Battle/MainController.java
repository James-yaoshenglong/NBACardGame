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
    private ArrayList<Card> enemyTeam;
    private DisplayController display;
    
    public MainController(SimpleApplication mainApp, float camWidth, Node battleNode){
        this.app = mainApp;
        this.camera_width = camWidth;
        this.myTeam = new ArrayList<>();
        this.enemyTeam = new ArrayList<>();
        this.display = new DisplayController(app, battleNode);
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
        display.licensing(myTeam);   
    }
    
    public void enterScene(){
        display.licensing(myTeam);
    }
}
