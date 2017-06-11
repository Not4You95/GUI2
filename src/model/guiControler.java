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
    
    /**
     * Constructor
     * @param GUICLASS 
     */
    public guiControler(GUI2 GUICLASS){
        gui = GUICLASS; 
        model = new GUImodel(this);
        model.test();
        tempTask = new ArrayList<String>();
        NodesAndComType = new ArrayList<>();
        
        
    }
    
   
    /**
     * Gets the selected date from gui and forward it to the model and call function to update the screen for plan mode.
     * @param date 
     */
    public void SetDate(LocalDate date){
        model.setDayOfMission(date);
        setScreenForPlanMode();
    }
    /**
     * Gets a int value from the gui and  forwards it to the model to select the desierd mission
     * @param DesierdTask 
     */
    public void setDesierdTask(int DesierdTask){
        if (DesierdTask >=0) {             
             model.SetTempTask(DesierdTask);
            upDateTabs();
        }
    }
    /**
     * Gets a string value from the gui and  forwards it to the model to select the desierd mission
     * @param name 
     */
    public void setDesierdTask(String name){
        model.SetTempTask(name);
    }
    /**
     * Updating the tabs in the gui
     */
    public void upDateTabs(){
        gui.SetTabsForLiveMode();
        gui.topLineforEdeting();
             upDateInterface();
             upDateNode();
             Overview(); 
    }
     /**
      * Gets a copy of the selected mission and forwards it to the gui
      */
    public void Overview(){
        gui.OverViewSceen(model.getTask());
       
    }
    /**
     * Set the sreen
     */
    public void setScreen(){     
        gui.P_2_PMenu();     
        tempTask.clear();
        gui.ModeMenu();
       
    }
    /**
     * Sets the screen for live mode
     */
    public void setScreenLiveMode(){
        
         gui.P_2_PMenu();       
         tempTask.clear();
         gui.ModeMenu();      
    }
    /**
     * Sets the screen for plan mode
     */
    public void setScreenForPlanMode(){        
         gui.screenForPlanMode(model.getTaskList());
      
    }
    /**
     * Gets the object from the plan mode, and sends it to the model
     * @param object 
     */
    public void ChoiseOfTaskPlanMode(Task object){
        model.settempTask(object);       
        upDateTabs();
     
    }
    /**
     * Updating the screen
     */
    public void UppdateScreen(){       
    
        if (Plan) {
            setScreenForPlanMode();
          
            
        }else if(Live){
            ArrayList<String> temp = new ArrayList<String>(); 
            temp.addAll(model.GetTaskNames());
            model.setDayOfMission(LocalDate.now());
            gui.SetScreenForLiveAndSimulateMode(model.getTaskList(),false);
           
              
            
        }
        else if(Simulate){
            ArrayList<String> temp = new ArrayList<String>(); 
            temp.addAll(model.GetTaskNames());
            model.setDayOfMission(LocalDate.now());
            gui.SetScreenForLiveAndSimulateMode(model.getTaskList(),true);
        }
        
       // tempTask.add("Test");
       // tempTask.add("Hello");
       
        
       
    }
    /**
     * Make a point to point conection in the live mode
     */
    public void updateP_2_P(){
         if (model.isAMissionChosen()) {
             gui.P_2_PScreen(model.getNodesOfTypeString(),model.getInterfacesOfTypeStrings());
        }
         else{
             setWaringForUser("Select mission", "Can not find desierd mission", "Warrning");
         }
      } 
    /**
     * Creates a waring to the user from the model
     * @param info
     * @param head
     * @param titel 
     */
    public void setWaringForUser(String info, String head, String titel){
        gui.AlertToUser(info, head, titel);
    }
    
    /**
     * Gets the selected comunication type from the gui, and opens a tab for the selected object
     * @param intName
     * @param Mission 
     */
    public void choiseOfInterfaceLiveMode(Object intName,String Mission){
        model.SetTempTask(Mission);
        newTabInterface(intName);
    }
    /**
     * Gets the selected node, and opens the selected node
     * @param nodeName
     * @param Mission 
     */
    public void choiseOfNodeLiveMode(Object nodeName,String Mission){
        model.SetTempTask(Mission);
        newTabNode(nodeName);
    }
    /**
     * Opens a new tab in gui for a comuncation type
     * @param node 
     */
    public void newTabInterface(Object node){
        Interface temp  = model.getInterface(node.toString());        
        if (temp != null) {            
            gui.nodeAndComtypeTab(null, temp);
        }
    }    
    
    /**
     * Opens a new tab for a node
     * @param node 
     */
    public void newTabNode(Object node){        
        TSN temp = model.getNode(node.toString());
        if (temp != null) {
            gui.nodeAndComtypeTab(temp,null);
        }
    }
    /**
     *    Updates whish state it is in 
     */
    public void modeState(){
        modeState(Plan, Live, Simulate);
    }
    /**
     * Updates whish state it is in 
     * @param Plan
     * @param Live
     * @param Simulate 
     */
   public void modeState(boolean Plan,boolean Live,boolean Simulate){
          this.Plan = Plan;
          this.Live = Live;
          this.Simulate = Simulate;
          UppdateScreen();
          gui.upDateModeState(Plan, Live, Simulate);
      }
   /**
    * Gets the nodes in the selected mission and send it to the gui
    */
   public void upDateNode(){
       
       gui.NodeTabScreen(model.getNodes());
   }
   /**
    * Gets the Communication type in the selected mission and send it to the gui
    */
      public void upDateInterface(){
         
          gui.InterfaceScreen(model.getInterfacesTypes());
      }
      
     public void setNodesAndComTypeForP_2_P(String Node1,String Node2,String Com_type,String priority,String Quality){
         
     }
     /**
      * Transfers a communication type to the model
      * @param temp 
      */
     public void newInterface(Interface temp){
         model.newInterface(temp);
     }
     /**
      * Transfers a node to the model
      * @param temp 
      */
     public void newNode(TSN temp){
         model.newNode(temp);
     }
    
}
