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
public class Player1OperationState implements ButtonState{
    private SimpleApplication app;
    private OperationBox opBox;
    
    private static Player1OperationState instance = new Player1OperationState();
    
    public static Player1OperationState getInstance(){
        return instance;
    }
    
    private Player1OperationState(){
    }
    
    @Override
    public void showActionButton() {
        app.getStateManager().getState(MainPrepare.class).showPlayer1ActionButtons();
        app.getStateManager().getState(MainPrepare.class).changeActionButtonState(this);
    }

    @Override
    public void showCards() {
    }

    @Override
    public void changePlayerButton() {
    }

    @Override
    public void changeOperationBUtton(AttackActionState state) {
        opBox.changeAttackActionState(state);
    }

    @Override
    public void setOperator(SimpleApplication mainApp, OperationBox anOpBox) {
        this.app = mainApp;
        this.opBox = anOpBox;
    }

    
}
