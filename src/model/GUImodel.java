/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import model.*;

/**
 *
 * @author jonas
 */
public class GUImodel {
    private ArrayList<Orginasation> Org;
    private ArrayList<Task> Tasks; 
    private File filename;
    private String OrgName;
    private Orginasation OrgTemp;
    private Task taskTemp = null; 
    private LocalDate dayOfMission;
    private guiControler contoller;
    
    
    
    
    public GUImodel(guiControler input){
        Org = new ArrayList<Orginasation>();
        Tasks = new ArrayList<Task>();
        contoller = input;
       
        filename = new File("test.txt");       
    }
      
    public void setDayOfMission(LocalDate date){
     dayOfMission = date;        //
        
    }
public boolean isAMissionChosen(){
    if (taskTemp != null) {
        return true;
    }
    else{
       
        return false;
    }
}
    
    public ArrayList<String> GetTaskNames(){
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < Tasks.size(); i++) {
            temp.add(Tasks.get(i).getName());
        }        
        
        
        return temp;
    }
    
    public ObservableList<Task> getTaskList(){
        ObservableList<Task> temp = FXCollections.observableArrayList();
        for (int i = 0; i < Tasks.size(); i++) {            
            if (dayOfMission.getDayOfMonth() == Tasks.get(i).getStartTime().get(Calendar.DAY_OF_MONTH) ||  dayOfMission.getDayOfMonth() <= Tasks.get(i).getEndTime().get(Calendar.DAY_OF_MONTH)) {
                temp.add(Tasks.get(i));
            }
        }
        return temp;
        
    }
    
    public void SetTempTask(int task){
        taskTemp = Tasks.get(task);
    }
    
    public void SetTempTask(String name){
        for (int i = 0; i < Tasks.size(); i++) {
            System.out.println("TempTask: "+Tasks.get(i).getName().toLowerCase().contains(name.toLowerCase()));
            if (Tasks.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                taskTemp = Tasks.get(i);
            }
            
        }
    }
    
    public void settempTask(Task obejct){
        for (int i = 0; i < Tasks.size(); i++) {
           if (obejct.getName().toLowerCase().contains(Tasks.get(i).getName().toLowerCase())) {
               taskTemp = Tasks.get(i);
              
        } 
        }
    }
    
    public Calendar getStarDate(){
        return taskTemp.getStartTime();
    }
    
    public Calendar getEndDate(){
        return taskTemp.getEndTime();
    }       
   
    public Task getTask(){
        if (taskTemp != null) {
            return taskTemp;
        }
        else{
            return null;
        }
    }
    
    public String GetOrgInfo(){
        String temp=null;
       for (int i = 0; i < Org.size(); i++) {
            if (Org.get(i).getName() == OrgName) {
                temp = Org.get(i).getInfo();
                              
            } 
        }
       return temp;
    }
    
    public int GetOrgPriorityForAll(){           
     return taskTemp.getRank();
       
    }
    
   
    
    public ArrayList<TSN> getNodes(){
        ArrayList<TSN> temp  = new ArrayList<>();
        temp.addAll(taskTemp.getNoder());
        return temp;
    }
    
    public ArrayList<String> getNodesOfTypeString(){
        ArrayList<String> temp = new ArrayList<>();
        if (taskTemp != null) {
             for (int i = 0; i < taskTemp.getNoder().size(); i++) {
            temp.add(taskTemp.getNoder().get(i).getName());
        }
        }
        else{
            contoller.setWaringForUser("Select mission", "Can not find desierd mission", "Warrning");
        }
        return temp;
    }
    
    public TSN getNode(String name){
        if (taskTemp != null) {
            for (int i = 0; i < taskTemp.getNoder().size(); i++) {               
            if (name.toLowerCase().contains(taskTemp.getNoder().get(i).getName().toLowerCase())) {
                return taskTemp.getNoder().get(i);
            }
        }
        }
        else{
            contoller.setWaringForUser("Select mission", "Can not find desierd mission", "Warrning");
        }
        return null;
    }
    
    public Interface getInterface(String name){
        if(taskTemp != null){            
             ArrayList<Interface> Itemp = new ArrayList<>();
             Itemp.addAll(getInterfacesTypes());
             
            
             for (int i = 0; i < Itemp.size(); i++) {               
                 if (name.toLowerCase().contains(Itemp.get(i).getName().toLowerCase())) {
                     return Itemp.get(i);
                 }
            }
        }
        else
        {
            contoller.setWaringForUser("Select mission", "Can not find desierd mission", "Warrning");
        }
        return null;
}
    
    public ArrayList<Interface> getInterfacesTypes(){
         ArrayList<Interface> temp = new ArrayList<>();
        ArrayList<Interface> Itemp = new ArrayList<>();
        if (taskTemp != null) {
            Itemp.addAll(taskTemp.getInterfaces());
        for (int i = 0; i < Itemp.size(); i++) {
           // temp.add(Itemp.get(i).getName());           
                if (!temp.contains(Itemp.get(i))) {
                temp.add(Itemp.get(i));
            }
            
        }       
            
        }
        else{
            contoller.setWaringForUser("Select mission", "Can not find desierd mission", "Warrning");
        }
        return temp;
    }          

    public ArrayList<String> getInterfacesOfTypeStrings(){
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Interface> Itemp = new ArrayList<>();
        if (taskTemp != null) {
             Itemp.addAll(taskTemp.getInterfaces());
        for (int i = 0; i < Itemp.size(); i++) {
           // temp.add(Itemp.get(i).getName());           
                if (!temp.contains(Itemp.get(i).getName())) {
                temp.add(Itemp.get(i).getName());
            }
            
        }
        }
        else{
            contoller.setWaringForUser("Select mission", "Can not find desierd mission", "Warrning");
        }
        return temp;
    }
    
    public void newInterface(Interface temp){
        for (int i = 0; i < Tasks.size(); i++) {
            if (Tasks.get(i).getName().toLowerCase().contains(taskTemp.getName().toLowerCase())) {
                 for (int j = 0; j < taskTemp.getNoder().size(); j++) {
                  // taskTemp.getNoder().get(j).newInterface(temp);
                    Tasks.get(i).getNoder().get(j).newUpdatedInterface(temp);
                        }
            }
        }
        
        settempTask(taskTemp);
    }
    
    public void newNode(TSN temp){
        for (int i = 0; i < Tasks.size(); i++) {
            if (Tasks.get(i).getName().toLowerCase().contains(taskTemp.getName().toLowerCase())) {               
                    Tasks.get(i).newUpdatedNode(temp);               
            }
        }
        settempTask(taskTemp);
    }
    
    
    
    
      public void test() {
          ArrayList<TSN> temp = new ArrayList<TSN>();
        ArrayList<Orginasation> orgList = new ArrayList<Orginasation>();
        Task task = new Task("Defend the hill", "Defend the hill from being occupied", "7 Bataljonen");
      //  Task task2 = new Task("Defend the food reserve","Defend the food from unauthorized people","Livgardet");
        Task task3 = new Task("Medivac", "Evacuate injured civilians from central Stockholm", "Skyttebataljonen");
        Task task4 = new Task("Standard operation", "Defend The Royal Palace", "Livbataljonen");
        Task task5 = new Task("Defend Musköbasen", "Defend Musköbasen from beeing ocupied", "Amfibiebataljonen");
        ArrayList<Task> taskList = new ArrayList<Task>();      
       
       Interface in1 = new Interface("BFT",InterfaceTypes.Tracking_Types);
       Interface in2 = new Interface("COP", InterfaceTypes.Video_Typs);
       Interface in3 = new Interface("Voice", InterfaceTypes.Voice_Typs);
       Interface in4 = new Interface("ISR", InterfaceTypes.Message_Types);
       Interface in5  =new Interface("Video",  InterfaceTypes.Video_Typs);
       Interface in6 = new Interface("Msg", InterfaceTypes.Message_Types);
       Interface in7 = new Interface("Contol", InterfaceTypes.Message_Types);
       
       
       
       in1.setInfo("Blue Force Tracking is GPS system that provieds location infromation");
       in2.setInfo("Common Operational Picture");
       in3.setInfo("Striming Voice");
       in4.setInfo("Intelligence,survillance and reconnaissace");
       in5.setInfo("Striming Video");
       in6.setInfo("Cimmand andcontol messages");
       in7.setInfo("System Management traffic");
       
       
       
       ArrayList<Interface> uav = new ArrayList<>(),dataFusion = new ArrayList<>(),all= new ArrayList<>();
       uav.add(in1);
       uav.add(in4);
       uav.add(in5);
       uav.add(in7);
       
       dataFusion.add(in2);
       dataFusion.add(in7);
       
       all.add(in1);
       all.add(in2);
       all.add(in3);
       all.add(in4);
       all.add(in5);
       all.add(in6);
       all.add(in7);
       
      
       
       
      
       GregorianCalendar c = new GregorianCalendar() ,c2 =  new GregorianCalendar();         
       c.set(LocalDate.now().getYear(), LocalDate.now().getMonthValue()-1, LocalDate.now().getDayOfMonth() ,LocalTime.now().getHour(),LocalTime.now().getMinute());
   
      
       c2.set(LocalDate.now().getYear(), LocalDate.now().getMonthValue()-1, LocalDate.now().getDayOfMonth()+2,LocalTime.now().getHour()+5 ,LocalTime.now().getMinute());
       task.setStartTime(c);
       task.setEndTime(c2);
       
     /*  task2.setStartTime(c);
       task2.setEndTime(c2);*/
       
       task3.setStartTime(c);
       task3.setEndTime(c2);
       
       task4.setStartTime(c);
       task4.setEndTime(c2);
       
       task5.setStartTime(c);
       task5.setEndTime(c2);
       
       //Image
          Image Comand = new Image(getClass().getResourceAsStream("COM.JPG"));
          Image Hospital = new Image(getClass().getResourceAsStream("Hospital.JPG"));
          Image uavI = new Image(getClass().getResourceAsStream("uav.JPG"));
         // Image uav = new Image(getClass().getResourceAsStream("uav.JPG"));
       
       
       
       //Interfaces 
        TSN one = new TSN("UAV ISR Global");
        one.setType(TSNTypes.UAV);
        one.addInterfaceArray(uav);        
       // one.setImage(uav);
       
        TSN two = new TSN("Datafusion M");
        two.addInterfaceArray(dataFusion);
        two.setType(TSNTypes.Radar);
        
        TSN three = new TSN("Datafusion S");
        three.addInterfaceArray(dataFusion);
        three.setType(TSNTypes.Radar);
        
        TSN FOUR = new TSN("Troups");
        FOUR.addInterfaceArray(all);
        FOUR.setType(TSNTypes.Troup);
        
        TSN five = new TSN("Military Hospital");
        five.addInterfaceArray(all);
        five.setType(TSNTypes.Hospital);
        five.setImage(Hospital);
        five.setInfo("MEDICAL TREATMENT FACILITY");
        
        TSN six = new TSN("BMS/Soldier");
        six.addInterfaceArray(all);
        six.setType(TSNTypes.Troup);
        
        TSN seven = new TSN("Deployed c2");
        seven.addInterfaceArray(all);
        seven.setType(TSNTypes.Comand_Central);
        seven.setImage(Comand);
        
        TSN eight = new TSN("UAV Local");
        eight.addInterfaceArray(uav);
        eight.setType(TSNTypes.UAV);
       // eight.setImage(uavI);
        //System.out.println(one.getName());
        
        System.out.println("-----------------------------------------------");
        ///////////////////////////////////////////////////////////////////////
       one.addListOfErrors(in1);       
       two.addListOfErrors(in7);
       FOUR.addListOfErrors(in5);
       five.addListOfErrors(in7);
       six.addListOfErrors(in3);
       seven.addListOfErrors(in5);
        
        
        
        ////////////////////////////////////////////////////////////////////////
       
      /*  task.setListOfNodesErros(one);   
        task2.setListOfNodesErros(six); */       
        
        task3.setListOfNodesErros(five);
        task3.setListOfNodesErros(six);
        
       
        task4.setListOfNodesErros(five);
        task4.setListOfNodesErros(six);
        
        
        task5.setListOfNodesErros(five);
        task5.setListOfNodesErros(six);
        
        
        
        temp.add(one);
        temp.add(two);
        temp.add(three);
        temp.add(FOUR);
        temp.add(five);
        temp.add(six);
        temp.add(seven);
        temp.add(eight);
        
        
        task.setNoder(temp);
      //  task2.setNoder(temp);
        task3.setNoder(temp);
        task4.setNoder(temp);
        task5.setNoder(temp);
        Tasks.add(task);
      //  Tasks.add(task2);
        Tasks.add(task3);
        Tasks.add(task4);
        Tasks.add(task5);
        
          System.out.println("Test: "+task.getPercentOfWorkingsNodes());
        
        /////////////////////////////////////////////////////
      

      
       
        
    }
      
    
}
