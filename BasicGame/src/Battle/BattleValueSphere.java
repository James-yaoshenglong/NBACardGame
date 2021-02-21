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
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Dome;
import com.jme3.scene.shape.Quad;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;

/**
 *
 * @author 影音娱乐剪辑
 */
public class BattleValueSphere extends Node{
    private Geometry sphereGeom;
    private TrueTypeNode ttkFontNode;
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;
    private Integer value;
    
    public BattleValueSphere(SimpleApplication mainApp, float aCamWidth, float aCamHeight, int aValue){
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        this.value = aValue;
        initialize();
    }
    
    private void initialize(){
//        sphereGeom = new Geometry("hhh", new Sphere(20,20,width/3));
//        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setColor("Color", ColorRGBA.Blue);
//        mat.getAdditionalRenderState().setWireframe(true);
//        sphereGeom.setMaterial(mat);
        Quad quad = new Quad(width/3,width/3);
        sphereGeom = new Geometry("value_display",quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/valuedisplay.png");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);
        sphereGeom.setMaterial(mat);
        mat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        sphereGeom.setQueueBucket(RenderQueue.Bucket.Transparent); 
        sphereGeom.setLocalTranslation(-width/6,0,0.1f);

        app.getAssetManager().registerLoader(TrueTypeLoader.class, "ttf");
        TrueTypeKeyMesh ttk = new TrueTypeKeyMesh("Fonts/arial.ttf", Style.Plain,5);
        TrueTypeFont font = (TrueTypeMesh)(app.getAssetManager()).loadAsset(ttk);
        ttkFontNode = font.getText(value.toString(), 0, ColorRGBA.White);
        ttkFontNode.setLocalTranslation(-width/8, width*(7f/24f), 0.2f);
        
        this.attachChild(sphereGeom);
        this.attachChild(ttkFontNode);
    } 
}
