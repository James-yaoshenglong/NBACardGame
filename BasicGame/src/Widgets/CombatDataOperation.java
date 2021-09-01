package Widgets;

import ActualCombat.MainActualCombat;
import Main.Main;
import com.jme3.app.SimpleApplication;
import network.data.AttackData;
import network.data.DefendData;
import network.data.ResponseData;
import network.data.ResponseOperation;
import network.data.TeamData;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 影音娱乐剪辑
 */
public class CombatDataOperation implements ResponseOperation{
    private int switchFlag;
    private DefendData defendData;
    private AttackData attackData;
    private boolean sendFlag;
    private SimpleApplication app;
    private TeamData myTeam;
    private TeamData rivalTeam;
    
    private CombatDataOperation(){
        this.switchFlag = 0;
        this.sendFlag = false;
    }
    
    private static CombatDataOperation instance = new CombatDataOperation();
    public static CombatDataOperation getInstance(){
        return instance;
    }
    
    
    
    @Override
    public void operate(ResponseData rd) {
        if(rd instanceof AttackData){
            switchFlag++;
            attackData = (AttackData)rd;
        }
        if(rd instanceof DefendData){
            switchFlag++;
            defendData = (DefendData)rd;
        }
        if(rd instanceof TeamData){
            System.out.println("Received Team Data");
            switchFlag++;
            if(((TeamData) rd).getOrder()==((Main)app).getOrder()){
                myTeam = (TeamData)rd;
                System.out.println(myTeam.getOrder());
            }
            else{
                rivalTeam = (TeamData)rd;
                System.out.println(rivalTeam.getOrder());
            }
        }
        if(switchFlag == 4){
            switchFlag = 0;
            System.out.println("Enter the compare page");
            app.getStateManager().getState(MainActualCombat.class).setData(attackData, defendData, myTeam,rivalTeam);
            ((Main)app).switchToActual();
            switchState();
        }
    }
    
    public void switchState(){
        sendFlag = !sendFlag;
    }   
    
    public void setApp(SimpleApplication anApp){
        this.app = anApp;
    }
    
    public boolean getSendFlag(){
        return sendFlag;
    }
}
