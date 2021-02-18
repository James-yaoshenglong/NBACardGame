/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Widgets;

import Prepare.MainPrepare;
import Prepare.OperationBox;
import com.jme3.app.SimpleApplication;

/**
 *
 * @author shenglyao2
 */
public class Player2OperationState implements ButtonState{
    private SimpleApplication app;
    private OperationBox opBox;
    
    private static Player2OperationState instance = new Player2OperationState();
    
    public static Player2OperationState getInstance(){
        return instance;
    }
    
    public Player2OperationState(){
    }
    
    @Override
    public void showActionButton() {
        app.getStateManager().getState(MainPrepare.class).showPlayer2ActionButtons();
    }

    @Override
    public void showCards() {
    }

    @Override
    public void changePlayerButton() {
    }

    @Override
    public void changeOperationBUtton() {
    }

    @Override
    public void setOperator(SimpleApplication mainApp, OperationBox anOpBox) {
        this.app = mainApp;
        this.opBox = anOpBox;
    }

    
}
