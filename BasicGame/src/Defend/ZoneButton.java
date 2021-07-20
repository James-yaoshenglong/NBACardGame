/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Defend;

import Main.Main;
import Widgets.MyRay;
import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.material.Material;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import network.client.GameClient;
import network.data.AttackData;
import network.data.DefendData;
import network.data.ResponseData;
import network.data.ResponseOperation;

/**
 *
 * @author yuchwang7
 */
public class ZoneButton extends Node implements ActionListener, ResponseOperation{
    private Geometry buttonGeom;
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;
    private int switchFlag;
    private DefendData defendData;
    private AttackData attackData;
    private boolean sendFlag;
    
    public ZoneButton(SimpleApplication mainApp, float aCamWidth, float aCamHeight){
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        this.switchFlag = 0;
        this.sendFlag = false;
        initialize();
    }
    
    
    private void initialize(){
        Quad quad = new Quad(width/3,width*4/15);
        Geometry geom = new Geometry("Frame", quad);
        Texture tex = app.getAssetManager().loadTexture("Textures/pic/zone_defense.png");
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap",tex);
        geom.setMaterial(mat);
        geom.setLocalTranslation(-5*width/12,-(height/4),0);
        this.attachChild(geom);
    }
    

    @Override
    public void onAction(String name, boolean isClicked, float tpf) {
        if(isClicked && this.getParent() != null && name.equals("CLICK")){
            Ray ray = MyRay.createRay(app);
            CollisionResults results = new CollisionResults();
            this.collideWith(ray, results);
            if(results.size() > 0){
                //((Main)app).switchfromDefendModeChoicetoDefend();
                DefendData data = new DefendData();
                GameClient.getInstance().transportData(data);
                switchState();
            }
        }

    }

    @Override
    public void operate(ResponseData rd) {
        if(rd instanceof AttackData){
            switchFlag++;
            attackData = (AttackData)rd;
        }
        if(rd instanceof DefendData){
            switchFlag++;
            defendData = (DefendData)rd;
        }
        if(switchFlag == 2){
            switchFlag = 0;
            System.out.println("Enter the compare page");
            switchState();
        }
    }

    public void switchState(){
        sendFlag = !sendFlag;
    }
    
}
