/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jonas
 */
public class Orginasation implements Serializable {
    private String name;
    private String info;
    private priorityAndQulaityLevels priotetForAllTSN=null;
    private priorityAndQulaityLevels QualityForAllTSN=null;
    private ArrayList<Task> Tasks;
   
    
    public Orginasation (String InputName, String Inputinfo){
        this.name = InputName;
        this.info = Inputinfo;
        Tasks = new ArrayList<Task>();
       
        
    }
    
    public Orginasation(){
        this.name = null;
        this.info = null;
        Tasks = new ArrayList<Task>();
    }

    /**
     * @return Returns the name of the orgination
     */
    
    public String getName() {
        return name;
    }

    /**
     * @return Returns the info the orginations 
     */
    public String getInfo() {
        return info;
    }
 

    /**
     * @return the priotetForAllTSN
     */
    public priorityAndQulaityLevels getPriotetForAllTSN() {
        return priotetForAllTSN;
    }
    
    public ArrayList<String> GetTaskNames(){
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < Tasks.size(); i++) {
            temp.add(Tasks.get(i).getName());
            System.out.println(Tasks.get(i).getName());
        }
        return temp;
    }
    /**
     * @param priotetForAllTSN the priotetForAllTSN to set
     */
    public void setPriotetForAllTSN(priorityAndQulaityLevels priotetForAllTSN) {
        this.priotetForAllTSN = priotetForAllTSN;
        for (int i = 0; i < getTasks().size(); i++) {
            getTasks().get(i).SetpriorityForAllTSN(priotetForAllTSN);
        }
    }

    /**
     * @return the QualityForAllTSN
     */
    public priorityAndQulaityLevels getQualityForAllTSN() {
        return QualityForAllTSN;
    }

    /**
     * @param QualityForAllTSN the QualityForAllTSN to set
     */
    public void setQualityForAllTSN(priorityAndQulaityLevels QualityForAllTSN) {
        this.QualityForAllTSN = QualityForAllTSN;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @return the Tasks
     */
    public ArrayList<Task> getTasks() {
        ArrayList<Task> temp = new ArrayList<Task>();
        temp.addAll(Tasks);
        return temp;
    }

    /**
     * @param Tasks the Tasks to set
     */
    public void setTasks(ArrayList<Task> Tasks) {
        this.Tasks.addAll(Tasks);
    }
    
    public Task getTask(int name){
        Task temp = new Task();
       /* for (int i = 0; i <Tasks.size(); i++) {
         if (Tasks.get(i).getName() == name) {
                temp = Tasks.get(i);                              
            }    
        }*/
       temp = Tasks.get(name);
        return temp;
    }
    
}
