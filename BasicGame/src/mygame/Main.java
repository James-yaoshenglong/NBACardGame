package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import com.jme3.ui.Picture;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        
        // 初始化摄像机位置
        cam.setLocation(new Vector3f(9.443982f, 13.542627f, 8.93058f));
        cam.setRotation(new Quaternion(-0.015316938f, 0.9377411f, -0.34448296f, -0.041695934f));

        flyCam.setMoveSpeed(10);
        
//        addPicture();
        
        // 添加物体
        addObjects();

        // 添加光源
        addLights();

        // 添加图片
       
        
        
//        Box b = new Box(200, 200, 200);
//        Geometry geom = new Geometry("Box", b);
//
//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        Texture tex = assetManager.loadTexture("Textures/pic/hhh.png");
//        //mat.setColor("Color", ColorRGBA.Blue);
//        mat.setTexture("ColorMap",tex);
//        geom.setMaterial(mat);
//        
//        
//        
        rootNode.attachChild(getpic());
//        rootNode.attachChild(geom);
        
        
        
        
        
    
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    private Geometry getpic(){
        float width = cam.getWidth();
        float height = cam.getHeight();
        Quad quad = new Quad(width, height);
        Geometry geom = new Geometry("bg", quad);
        Texture tex = assetManager.loadTexture("Interface/bg/bg.jpg");
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap",tex);
        geom.setMaterial(mat);
        geom.setLocalTranslation(0, 0, -2);
        return geom;
    }
    
    private void addObjects() {
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");

        // 创建一个平面，把它作为地板，用来承载光影
        Geometry geom = new Geometry("Floor", new Quad(17, 17));
        geom.setMaterial(mat);
        geom.setShadowMode(ShadowMode.Receive);// 承载阴影

        geom.rotate(-FastMath.HALF_PI, 0, 0);
        rootNode.attachChild(geom);

        // 创造多个方块
        for (int y = 0; y < 10; y += 3) {
            for (int x = 0; x < 10; x += 3) {
                geom = new Geometry("Cube", new Box(0.5f, 0.5f, 0.5f));
                geom.setMaterial(mat);
                geom.setShadowMode(ShadowMode.Cast);// 产生阴影
                geom.move(x + 4, 0.5f, -y - 4);
                rootNode.attachChild(geom);
            }
        }
    }

    /**
     * 添加光源
     */
    private void addLights() {

        // 定向光
        DirectionalLight sunLight = new DirectionalLight();
        sunLight.setDirection(new Vector3f(-1, -2, -3));
        sunLight.setColor(new ColorRGBA(0.2f, 0.2f, 0.2f, 1f));

        // 环境光
        AmbientLight ambientLight = new AmbientLight();
        ambientLight.setColor(new ColorRGBA(0.2f, 0.2f, 0.2f, 1f));

        // 将模型和光源添加到场景图中
        rootNode.addLight(sunLight);
        rootNode.addLight(ambientLight);
    }
    
    private void addPicture() {
        Picture pic = new Picture("picture");

        // 设置图片
        pic.setImage(assetManager, "Interface/bg/bg.jpg", true);

        // 设置图片全屏显示
        pic.setWidth(cam.getWidth()/2);
        pic.setHeight(cam.getHeight()/2);

        // 将图片后移一个单位，避免遮住状态界面。
        pic.setLocalTranslation(0, 0, -1);

        rootNode.attachChild(pic);
    }
    
    
}
