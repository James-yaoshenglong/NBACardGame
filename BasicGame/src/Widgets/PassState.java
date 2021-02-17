/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Widgets;

import Prepare.OperationButton;

/**
 *
 * @author shenglyao2
 */
public class PassState implements AttackActionState{
    
    @Override
    public void showIcon(OperationButton opButton) {
        opButton.pass_chosen();
    }
    
    @Override
    public void sendMessage() {
    }

}
