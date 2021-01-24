/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battle;

import java.util.ArrayList;

/**
 *
 * @author feegee2000
 */
public class Player {
    private String name, image;
    private int id;
    private int S, B, P, DB, DS, DP, power;
    private static ArrayList<Player> allplayers;
    
    public Player(int id){
    //use the only parameter id to construct the player, other data will be accessed from the database    
        this.id = id;
        readData();
//        allplayers.add(this);
    }
            
//    public static Player searchPlayer(ArrayList<Player> list, int idtosearch){
//        for(Player p : list){
//            if(p!= null && p.getID()==idtosearch)
//                return p;
//        }
//        return null;
//    }
    
    private void readData(){
        //just for test
        this.S = 10;
        this.B = 10;
        this.P = 10;
        this.DB = 10;
        this.DS = 10;
        this.DP = 10;
        this.power = 10;
        this.image = "Textures/pic/1.jpg";
        //database file read process should in this process 
    }
    
    
    
    
    public int getID(){
        return id;
    }
    
    public static Player getPlayer(int ID){
        for(Player p : allplayers){
            if(p != null && p.getID()==ID)
                return p;
        }
        return null;
    }
    
    public String getImage(){
        return image;
    }
    
    public int getPower(){
        return power;
    }
    
    public int getS(){
        return S;
    }
    
    public int getB(){
        return B;
    }
    
    public int getP(){
        return P;
    }
    
    public int getDS(){
        return DS;
    }
    
    public int getDB(){
        return DB;
    }
    
    public int getDP(){
        return DP;
    }
    
}
