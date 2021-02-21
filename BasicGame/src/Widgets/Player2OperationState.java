/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Widgets;

import Battle.Card;
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
        if(app.getStateManager().getState(MainPrepare.class).getPlayer1ActionState() != null && app.getStateManager().getState(MainPrepare.class).getPlayer1ActionState().getClass() == PassState.class){
            app.getStateManager().getState(MainPrepare.class).showPlayer2ActionButtons();
            app.getStateManager().getState(MainPrepare.class).changeActionButtonState(this);
        }
    }

    @Override
    public void showCards() {
        if(app.getStateManager().getState(MainPrepare.class).getPlayer1ActionState() != null && app.getStateManager().getState(MainPrepare.class).getPlayer1ActionState().getClass() == PassState.class){
            app.getStateManager().getState(MainPrepare.class).showPlayerLists();
            app.getStateManager().getState(MainPrepare.class).changeCardsState(this);
        }
    }

    @Override
    public void changePlayerButton(Card targetCardNode) {
        opBox.choosePlayer1(targetCardNode.getID());
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
