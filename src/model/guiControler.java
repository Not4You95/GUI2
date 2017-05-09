/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import gui2.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.control.TreeItem;
import model.*;

/**
 *
 * @author jonas
 */
public class guiControler {
    private GUI2 gui;
    private GUImodel model;
    private ArrayList<String> tempTask;
    private ArrayList<String> NodesAndComType;
    private boolean Plan=true,Live=false,Simulate=false;
    
    
    public guiControler(GUI2 GUICLASS){
        gui = GUICLASS; 
        model = new GUImodel();
        model.test();
        tempTask = new ArrayList<String>();
        NodesAndComType = new ArrayList<>();
        
        
    }
    
    public void SetGroup(){
        
        
        
    }
    
    public void SetDate(LocalDate date){
        System.out.println("Contoler: "+date.toString());
        model.setDayOfMission(date);
        setScreenForPlanMode();
    }
    
    public void setDesierdTask(int DesierdTask){
        if (DesierdTask >=0) {             
             model.SetTempTask(DesierdTask);
            upDateTabs();
        }
    }
    
    public void upDateTabs(){
        gui.SetTabsForLiveMode();
        gui.topLineforEdeting();
             upDateInterface();
             upDateNode();
             Overview(); 
    }
    
   
    
    public void Overview(){
        gui.OverViewSceen(model.GetOrgPriorityForAll(),model.GetOrgInfo(),model.getStarDate(),model.getEndDate());
       
    }
    
    public void setScreen(){      
        gui.TaskMenu();
        gui.InterfaceMenu();
        gui.P_2_PMenu();
        gui.SendMenu();        
        gui.setListOfTask();
        tempTask.clear();
        gui.ModeMenu();
       
        //UppdateScreen();
        
    }
    public void setScreenLiveMode(){
        
         gui.P_2_PMenu();       
         tempTask.clear();
         gui.ModeMenu();      
    }
    
    public void setScreenForPlanMode(){        
         gui.screenForPlanMode(model.getTaskList());
      
    }
    
    public void ChoiseOfTaskPlanMode(Task object){
        model.settempTask(object);       
        upDateTabs();
     
    }
    
    public void UppdateScreen(){       
    
        if (Plan) {
            setScreenForPlanMode();
          
            
        }else if(Live){
            ArrayList<String> temp = new ArrayList<String>(); 
            temp.addAll(model.GetTaskNames());
            gui.SetScreenForLiveMode();
            gui.UppdateListOfTask(temp);             
            System.out.println("Hello");
              
            
        }
        else if(Simulate){
            
        }
        
       // tempTask.add("Test");
       // tempTask.add("Hello");
       
        
       
    }
    
    public void updateP_2_P(){
          gui.P_2_PScreen(model.getNodesOfTypeString(),model.getInterfacesOfTypeStrings());
      }
    
    public void choiseOfInterface(String temp){
        //gui.makeNewTabView(temp,"");
        System.out.println("hello");
       
    }
    
    public void newTabInterface(Object node){
        Interface temp  = model.getInterface(node.toString());
        System.out.println("node: "+node.toString());
        System.out.println("test: "+temp.getName());
        if (temp != null) {
            gui.nodeAndComtypeTab(null, temp);
        }
    }    
    
    
    public void newTabNode(Object node){        
        TSN temp = model.getNode(node.toString());
        if (temp != null) {
            gui.nodeAndComtypeTab(temp,null);
        }
    }
    
    public void modeState(){
        modeState(Plan, Live, Simulate);
    }
    
   public void modeState(boolean Plan,boolean Live,boolean Simulate){
          this.Plan = Plan;
          this.Live = Live;
          this.Simulate = Simulate;
          UppdateScreen();
          gui.upDateModeState(Plan, Live, Simulate);
      }
   public void upDateNode(){
       
       gui.NodeTabScreen(model.getNodes());
   }
      public void upDateInterface(){
         
          gui.InterfaceScreen(model.getInterfacesTypes());
      }
      
     public void setNodesAndComTypeForP_2_P(String Node1,String Node2,String Com_type,String priority,String Quality){
         System.out.println("Node 1: "+Node1);
         System.out.println("Node 2: "+Node2);
         System.out.println("Com type: "+Com_type);
         System.out.println("Priority: "+priority);
         System.out.println("Quality: "+Quality);
     }
     
     public void newInterface(Interface temp){
         model.newInterface(temp);
     }
     
     public void newNode(TSN temp){
         model.newNode(temp);
     }
    
}
