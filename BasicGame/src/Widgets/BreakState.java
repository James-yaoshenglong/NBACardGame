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
public class BreakState implements AttackActionState{
    private final String name = "break";

    @Override
    public void showIcon(OperationButton opButton) {
        opButton.breakthrough_chosen();
    }

    @Override
    public String getName() {
        return name;
    }
    
}
