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
    private int attack, power, defence;
    private static ArrayList<Player> allplayers;
    
    public Player(int id, String aName, int attack, int power, int defence, String image){
        this.id = id;
        this.name = aName;
        this.attack = attack;
        this.power = power;
        this.defence = defence;
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
