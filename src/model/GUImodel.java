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
import model.*;

/**
 *
 * @author jonas
 */
public class GUImodel {
    private ArrayList<Orginasation> Org;
    private ArrayList<Task> Tasks;
    private ReadAndWriteToFile SaveAndRead;
    private File filename;
    private String OrgName;
    private Orginasation OrgTemp;
    private Task taskTemp = null;
    private int taskLevel;
    private LocalDate dayOfMission;
    
    
    
    
    public GUImodel(){
        Org = new ArrayList<Orginasation>();
        Tasks = new ArrayList<Task>();
        SaveAndRead = new ReadAndWriteToFile();
        filename = new File("test.txt");       
    }
      
    public void setDayOfMission(LocalDate date){
     dayOfMission = date;
        //System.out.println("Datum: "+dayOfMission.getDayOfMonth());
        
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
            System.out.println( dayOfMission.getDayOfMonth());
            if (dayOfMission.getDayOfMonth() == Tasks.get(i).getStartTime().get(Calendar.DAY_OF_MONTH) || dayOfMission.getDayOfMonth() == Tasks.get(i).getEndTime().get(Calendar.DAY_OF_MONTH)) {
                temp.add(Tasks.get(i));
            }
        }
        return temp;
        
    }
    
    public void SetTempTask(int task){
        taskTemp = Tasks.get(task);
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
    
    public void SaveToFile() throws IOException, AlertToUser{
           SaveAndRead.writeToFile(Org, filename);          
    }
    
    public void ReadFromFile() throws AlertToUser, IOException, ClassNotFoundException{
         
         Org.add(SaveAndRead.readFromFile(filename));
        
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
    
    public priorityAndQulaityLevels GetOrgQualityForAll(){
         priorityAndQulaityLevels temp;
       
       temp = OrgTemp.getQualityForAllTSN();
       return temp;
    } 
    
    public ArrayList<TSN> getNodes(){
        ArrayList<TSN> temp  = new ArrayList<>();
        temp.addAll(taskTemp.getNoder());
        return temp;
    }
    
    public ArrayList<String> getNodesOfTypeString(){
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < taskTemp.getNoder().size(); i++) {
            temp.add(taskTemp.getNoder().get(i).getName());
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
        return null;
}
    
    public ArrayList<Interface> getInterfacesTypes(){
         ArrayList<Interface> temp = new ArrayList<>();
        ArrayList<Interface> Itemp = new ArrayList<>();
        Itemp.addAll(taskTemp.getInterfaces());
        for (int i = 0; i < Itemp.size(); i++) {
           // temp.add(Itemp.get(i).getName());           
                if (!temp.contains(Itemp.get(i))) {
                temp.add(Itemp.get(i));
            }
            
        }
        return temp;
    }          

    public ArrayList<String> getInterfacesOfTypeStrings(){
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Interface> Itemp = new ArrayList<>();
        Itemp.addAll(taskTemp.getInterfaces());
        for (int i = 0; i < Itemp.size(); i++) {
           // temp.add(Itemp.get(i).getName());           
                if (!temp.contains(Itemp.get(i).getName())) {
                temp.add(Itemp.get(i).getName());
            }
            
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
        Task task = new Task("Defend the hill", "Test", "7 Bataljonen");
        Task task2 = new Task("Defend the the food reserv","Test","Livgardet");
        ArrayList<Task> taskList = new ArrayList<Task>();
        Orginasation Gotland = new Orginasation();
        Orginasation Blidö = new Orginasation();
        Orginasation Öland = new Orginasation();
       /* BFT – Blue Force Tracking
COP – Common Operational Picture
Voice – Streamed Voice
ISR – Intelligence, surveillance and reconnaissance
Video – Streamed Video
Msg – Command and control messages
Control – System Management*/
       
       Interface in1 = new Interface("BFT",InterfaceTypes.Tracking);
       Interface in2 = new Interface("Voice", InterfaceTypes.Voice);
       Interface in3 = new Interface("ISR", InterfaceTypes.Mesagge);
       Interface in4  =new Interface("Video",  InterfaceTypes.Video);
       Interface in5 = new Interface("Contol", InterfaceTypes.Mesagge);
       
       ArrayList<Interface> listInter = new ArrayList<>();
       listInter.add(in1);
       listInter.add(in2);
       listInter.add(in3);
       listInter.add(in4);
       listInter.add(in5);
       GregorianCalendar c = new GregorianCalendar() ,c2 =  new GregorianCalendar();         
       c.set(LocalDate.now().getYear(), LocalDate.now().getMonthValue()-1, LocalDate.now().getDayOfMonth() ,LocalTime.now().getHour(),LocalTime.now().getMinute());
   
      
       c2.set(LocalDate.now().getYear(), LocalDate.now().getMonthValue()-1, LocalDate.now().getDayOfMonth()+2,LocalTime.now().getHour()+5 ,LocalTime.now().getMinute());
       task.setStartTime(c);
       task.setEndTime(c2);
       
       task2.setStartTime(c);
       task2.setEndTime(c2);
       
        
        TSN one = new TSN("UAV ISR Global");
        one.setType(TSNTypes.UAV);
        one.addInterfaceArray(listInter);
        TSN two = new TSN("Datafusion M");
        two.addInterfaceArray(listInter);
        two.setType(TSNTypes.Radar);
        TSN three = new TSN("Datafusion S");
        three.addInterfaceArray(listInter);
        three.setType(TSNTypes.Radar);
        TSN FOUR = new TSN("Troups");
        FOUR.addInterfaceArray(listInter);
        FOUR.setType(TSNTypes.Troup);
        TSN five = new TSN("Military Hospital");
        five.addInterfaceArray(listInter);
        five.setType(TSNTypes.Hospital);
        TSN six = new TSN("BMS/Soldier");
        six.addInterfaceArray(listInter);
        six.setType(TSNTypes.Troup);
        TSN seven = new TSN("Deployed c2");
        seven.addInterfaceArray(listInter);
        seven.setType(TSNTypes.Comand_Central);
        TSN eight = new TSN("UAV Local");
        eight.addInterfaceArray(listInter);
        eight.setType(TSNTypes.UAV);
        //System.out.println(one.getName());
        
        System.out.println("-----------------------------------------------");
        ///////////////////////////////////////////////////////////////////////
        
        temp.add(one);
        temp.add(two);
        temp.add(three);
        temp.add(FOUR);
        temp.add(five);
        temp.add(six);
        temp.add(seven);
        temp.add(eight);
        task.setNoder(temp);
        task2.setNoder(temp);
        taskList.add(task);
        taskList.add(task2);
        Tasks.add(task);
        Tasks.add(task2);
        /////////////////////////////////////////////////////
        Gotland.setTasks(taskList);
        Gotland.setName("Gotland");
        Gotland.setInfo("Defend Gtoland from Ryssland");
        Gotland.setPriotetForAllTSN(priorityAndQulaityLevels.High);
        Gotland.setQualityForAllTSN(priorityAndQulaityLevels.Medium);
        //////////////////////////////////////////////////////
        Öland.setTasks(taskList);
        Öland.setName("Öland");
        Öland.setInfo("Defend Öland from Denmark");
        Öland.setPriotetForAllTSN(priorityAndQulaityLevels.High);
        Öland.setQualityForAllTSN(priorityAndQulaityLevels.Low);
        //////////////////////////////////////////////////////
        Blidö.setTasks(taskList);
        Blidö.setName("Blidö");
        Blidö.setInfo("Defend from who?");
        Blidö.setPriotetForAllTSN(priorityAndQulaityLevels.High);
        Blidö.setQualityForAllTSN(priorityAndQulaityLevels.Medium);
        ///////////////////////////////////////////////////////////////////////
        Org.add(Öland);
        Org.add(Gotland);
        Org.add(Blidö);

       /*for (int i = 0; i < 1; i++) {
            for (int j = 0; j < temp.size() ; j++) {
                System.out.println(org.getTasks().get(0).getNoder().get(j).getName());
            }
            
        }*/
       
        
    }
      
    
}
