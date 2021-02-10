/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prepare;

import com.jme3.app.SimpleApplication;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

/**
 *
 * @author shenglyao2
 */
public class OperationBox extends Node{
    private Geometry BgGeom;
    private SimpleApplication app;
    private float width; //screen width and height
    private float height;
    
    
    public OperationBox(SimpleApplication mainApp, float aCamWidth, float aCamHeight){
        this.app = mainApp;
        this.width = aCamWidth;
        this.height = aCamHeight;
        initialize();
    }
    
    private void initialize(){
    
    }
}
