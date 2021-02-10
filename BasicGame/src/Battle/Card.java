/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battle;


import com.atr.jme.font.TrueTypeFont;
import com.atr.jme.font.TrueTypeMesh;
import com.atr.jme.font.asset.TrueTypeKeyMesh;
import com.atr.jme.font.asset.TrueTypeLoader;
import com.atr.jme.font.shape.TrueTypeNode;
import com.atr.jme.font.util.Style;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
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
    Geometry geom;
    boolean isInLineup;
    
    public final static int PLAIN = 0;// 普通
    public final static int BOLD = 1;// 粗体
    public final static int ITALIC = 2;// 斜体

    // 字号
    public final static int FONT_SIZE = 64;
    
    
    
    public Card(int id, float camera_width, Application anApp){
        this.player = new Player(id);
        this.width = camera_width/6;
        this.height = width*7/5;
        this.app = anApp;
        this.power = player.getPower();
        this.isInLineup = false;
        setPicGeom();
        setPicBattleValue();
    }
    
//    public Geometry getPic(){
//        Quad quad = new Quad(width,height);
//        Geometry geom = new Geometry("Card", quad);
//        Texture tex = app.getAssetManager().loadTexture(player.getImage());
//        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setTexture("ColorMap",tex);
//        
//        geom.setMaterial(mat);
//        return geom;
//    }
    
    private void setPicGeom(){
        Quad quad = new Quad(width,height);
        geom = new Geometry("Card", quad);
        Texture tex = app.getAssetManager().loadTexture(player.getImage());
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap",tex);
        geom.setMaterial(mat);
        geom.setLocalTranslation(-((BoundingBox)geom.getWorldBound()).getXExtent(),0.0f,0.0f);
        this.attachChild(geom);
    }
    
    public int getID(){
        return player.getID();
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public boolean getStatus(){
        return isInLineup;
    }
    
    public void toggleStatus(){
        isInLineup = !isInLineup;
    }
    
    private void setPicBattleValue(){
	
        // 注册ttf字体资源加载器
        app.getAssetManager().registerLoader(TrueTypeLoader.class, "ttf");

        // 创建字体 (例如：楷书)
        TrueTypeKeyMesh ttk = new TrueTypeKeyMesh("Fonts/arial.ttf", Style.Plain,10);

        TrueTypeFont font = (TrueTypeMesh)(app.getAssetManager()).loadAsset(ttk);
        
        TrueTypeNode trueNode = font.getText("HHH", 0, ColorRGBA.Black);
        trueNode.setLocalTranslation(0f, 10f, 1f);
        this.attachChild(trueNode);
    }
    
    public void setSize(float f){
        geom.setLocalScale(f);
        geom.setLocalTranslation(-((BoundingBox)geom.getWorldBound()).getXExtent(),0.0f,0.0f);
    }
    
    public void setPicNormalColor(){
        geom.getMaterial().setColor("Color", new ColorRGBA(1f,1f,1f,0f));
        geom.setMaterial(geom.getMaterial());
        System.out.println(geom.getMaterial().getParam("Color").toString());
    }
    
   
            
}
