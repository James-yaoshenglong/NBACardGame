/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

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
}
