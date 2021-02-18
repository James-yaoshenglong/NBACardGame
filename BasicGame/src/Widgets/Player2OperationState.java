/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Widgets;

import Prepare.MainPrepare;
import com.jme3.app.SimpleApplication;

/**
 *
 * @author shenglyao2
 */
public class Player2OperationState implements ButtonState{
        private SimpleApplication app;
    
    public Player2OperationState(SimpleApplication mainApp){
        this.app = mainApp;
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

    
}
