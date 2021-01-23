/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battle;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import com.jme3.ui.Picture;
import java.util.ArrayList;


public class Card extends Node{
//    private ArrayList<Player> allplayers;
    float width;
    float height;
    Player player;
    Application app;
    int power;
    
    public Card(int id, float camera_width, Application anApp){
        this.player = Player.getPlayer(id);//change to the constructer of the player class
        this.width = camera_width/6;
        this.height = width*7/5;
        this.app = anApp;
        this.power = player.getPower();
    }
    
    public Geometry getPic(){
        Quad quad = new Quad(width,height);
        Geometry geom = new Geometry("Card", quad);
        Texture tex = app.getAssetManager().loadTexture(player.getImage());
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap",tex);
        
        geom.setMaterial(mat);
        return geom;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    
    
    
}
