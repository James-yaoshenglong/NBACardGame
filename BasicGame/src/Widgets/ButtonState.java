/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Widgets;

import Prepare.OperationBox;
import com.jme3.app.SimpleApplication;

/**
 *
 * @author shenglyao2
 */
public interface ButtonState {
    void showActionButton();
    void showCards();
    void changePlayerButton();
    void changeOperationBUtton();
    void setOperator(SimpleApplication mainApp, OperationBox anOpBox);
}
