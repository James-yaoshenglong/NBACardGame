/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Welcome;

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
import com.simsilica.lemur.style.BaseStyles;
/**
 *
 * @author shenglyao2
 */
public class WelcomeInterface extends BaseAppState{
    private SimpleApplication app;
    private AssetManager assetManager;
    private AppStateManager stateManager;
    private InputManager inputManager;
    private Camera cam;
    private Node guiNode;
    private float width;
    private float height;
    
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
    }

    @Override
    protected void cleanup(Application app) {
        
    }

    @Override
    protected void onEnable() {
        constructBG();
        // 初始化Lemur GUI
        
        GuiGlobals.initialize(app);

        // 加载 'glass' 样式
        BaseStyles.loadGlassStyle();

        // 将'glass'设置为GUI默认样式
        GuiGlobals.getInstance().getStyles().setDefaultStyle("glass");

        // 创建一个Container作为窗口中其他GUI元素的容器
        Container myWindow = new Container();
        guiNode.attachChild(myWindow);

        // 设置窗口在屏幕上的坐标
        // 注意：Lemur的GUI元素是以控件左上角为原点，向右、向下生成的。
        // 然而，作为一个Spatial，它在GuiNode中的坐标原点依然是屏幕的左下角。
        myWindow.setLocalTranslation(width/2, height/2, 0);

        // 添加一个Label控件
        myWindow.addChild(new Label("Hello, World."));


        // 添加一个Button控件
        Button clickMe = myWindow.addChild(new Button("Click Me"));
        clickMe.addClickCommands(new Command<Button>() {
                @Override
                public void execute(Button source) {
                        ((Main)app).switchfromWeltoMain();
                }
        });

        Container text = new Container();
        guiNode.attachChild(text);

        text.setLocalTranslation(width/2, (float) (height*0.7),0);
        text.addChild(new Label("Hello, World."));       
		
    }

    @Override
    protected void onDisable() {
        
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
        guiNode.attachChild(geom);
    }
    
    private void makeButton(){
        
    }
}
