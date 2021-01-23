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
    
    public Player(int id, String aName, int aS, int aB, int aP, int aDB, int aDS, int aDP, int apower, String image){
    //use the only parameter id to construct the player, other data will be accessed from the database    
        this.id = id;
        this.name = aName;
        this.S = aS;
        this.B = aB;
        this.P = aP;
        this.DB = aDB;
        this.DS = aDS;
        this.DP = aDP;
        this.power = apower;
        this.image = image;
        allplayers.add(this);
    }
            
//    public static Player searchPlayer(ArrayList<Player> list, int idtosearch){
//        for(Player p : list){
//            if(p!= null && p.getID()==idtosearch)
//                return p;
//        }
//        return null;
//    }
    
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
