/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import Main.Main;
import com.jme3.app.SimpleApplication;
import com.jme3.input.controls.ActionListener;

/**
 *
 * @author 影音娱乐剪辑
 */
public class PauseListener implements ActionListener{
    private SimpleApplication app;
    
    public PauseListener(SimpleApplication mainApp){
        this.app = mainApp;
    }
    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if(name.equals("PAUSE") && isPressed){
//            ((Main)app).switchfromMaintoPause();
        }
    }
    
}
