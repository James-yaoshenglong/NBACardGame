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
public class ShootState implements AttackActionState{
    private final String name = "shoot";

    @Override
    public void showIcon(OperationButton opButton) {
        opButton.shoot_chosen();
    }

    @Override
    public String getName() {
        return name;
    }
    
}
