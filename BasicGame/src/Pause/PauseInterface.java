/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pause;

import Main.Main;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;

import com.simsilica.lemur.Button;
import com.simsilica.lemur.Command;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.TextField;
import com.simsilica.lemur.component.TextComponent;
import com.simsilica.lemur.style.BaseStyles;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.simsilica.lemur.HAlignment;
/**
 *
 * @author shenglyao2
 */
public class PauseInterface extends BaseAppState{
    private SimpleApplication app;
    private AssetManager assetManager;
    private AppStateManager stateManager;
    private InputManager inputManager;
    private Camera cam;
    private Node guiNode;
    private float width;
    private float height;
    private Node rootNode;
    private Node sceneNode;
    
    @Override
    protected void initialize(Application mainApp) {
        this.app = (SimpleApplication)mainApp;
        this.assetManager = mainApp.getAssetManager();
        this.stateManager = mainApp.getStateManager();
        this.inputManager = mainApp.getInputManager();
        this.cam = mainApp.getCamera();
        this.guiNode = app.getGuiNode();
        this.width = cam.getWidth();
        this.height = cam.getHeight();
        this.rootNode = app.getRootNode();
        sceneNode = new Node();
    }

    @Override
    protected void cleanup(Application app) {
        
    }

    @Override
    protected void onEnable() {
        guiNode.attachChild(sceneNode);
        constructBG();
        // 初始化Lemur GUI
        
        GuiGlobals.initialize(app);

        // 加载 'glass' 样式
        BaseStyles.loadGlassStyle();

        // 将'glass'设置为GUI默认样式
        GuiGlobals.getInstance().getStyles().setDefaultStyle("glass");

        // 创建一个Container作为窗口中其他GUI元素的容器
        Container myWindow = new Container();
        sceneNode.attachChild(myWindow);

        // 设置窗口在屏幕上的坐标
       // myWindow.setPreferredSize(new Vector3f(1000f, 1000f,1000f));
        // 注意：Lemur的GUI元素是以控件左上角为原点，向右、向下生成的。
        // 然而，作为一个Spatial，它在GuiNode中的坐标原点依然是屏幕的左下角。
        myWindow.setLocalTranslation(width/2-50f, height/2-15f, 0);


        // 添加一个Button控件
        Button Back = myWindow.addChild(new Button("Back"));
        Back.setFontSize(20f);
        Back.setTextHAlignment(HAlignment.Center);
        Back.setPreferredSize(new Vector3f(100f, 30f,0));
        Back.addClickCommands(new Command<Button>() {
                @Override
                public void execute(Button source) {
                        ((Main)app).returnBackFromPause();
                }
        });
        
        // To Menu Button
        Button Menu = myWindow.addChild(new Button("Menu"));
        Menu.setFontSize(20f);
        Menu.setTextHAlignment(HAlignment.Center);
        Menu.setPreferredSize(new Vector3f(100f, 30f,0));
        Menu.addClickCommands(new Command<Button>() {
                @Override
                public void execute(Button source) {
                        ((Main)app).switchfromPausetoWel();
                }
        });
        

//        Container text = new Container();
//        sceneNode.attachChild(text);

        //text.setLocalTranslation(width/2, (float) (height*0.7),0);
//        TextComponent t = new TextComponent("NBA Card Game",assetManager.loadFont("Interface/Fonts/Default.fnt"));       
        BitmapFont fnt = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText txt = new BitmapText(fnt, false);
        txt.setText("NBA Card Game");
        txt.setLocalTranslation(width/2-150f, (float) (height*0.7),0);
        txt.setColor(new ColorRGBA(137/255,200/255,1,1));
        txt.setSize( 50f );
        sceneNode.attachChild(txt);
        
        
    }

    @Override
    protected void onDisable() {
        sceneNode.removeFromParent();
    }
    
    private void constructBG(){
        Quad quad = new Quad(width, height);
        Geometry geom = new Geometry("bg", quad);
        Texture tex = assetManager.loadTexture("Interface/bg/Welcome_bg.jpeg");
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap",tex);
        geom.setMaterial(mat);
        geom.setLocalTranslation(0, 0, -2);
//        geom.center();
        sceneNode.attachChild(geom);
    }
    
    private void makeButton(){
        
    }
}
