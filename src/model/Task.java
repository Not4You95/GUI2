/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jonas
 */
public class Task implements Serializable{
     private  ArrayList<TSN> noder;
     private SimpleStringProperty Name = new SimpleStringProperty();
     private SimpleStringProperty info = new SimpleStringProperty();
     private SimpleStringProperty orginsastion = new SimpleStringProperty();
     private IntegerProperty rank = new SimpleIntegerProperty();
     private Calendar StartTime ;
     private Calendar EndTime ;    
     private ObservableList<TSN> listOfNodesErros;       
     private SimpleStringProperty ErrorNodeName = new SimpleStringProperty();
     private IntegerProperty percentOfWorkingsNodes = new SimpleIntegerProperty();
  
     
     
     
     
     public Task(String name,String info, String Orginsation){        
         noder = new ArrayList<TSN>();
         setName(name);
         setInfo(info);
         setOrginsastion(Orginsation);
         setErrorNodeName("Nods");
         listOfNodesErros = FXCollections.observableArrayList();       
        
     }
     public Task(){
    noder = new ArrayList<TSN>();
    }
    
     
   

    /**
     * @return the noder
     */
    public ArrayList<TSN> getNoder() {
        ArrayList<TSN> temp = new ArrayList<TSN>();
        temp.addAll(noder);
               
        return temp;
    }
    
    public ArrayList<Interface> getInterfaces(){
        ArrayList<Interface> temp = new ArrayList<>();
        for (int i = 0; i < noder.size(); i++) {
            temp.addAll(noder.get(i).getInterface());
        }
        return temp;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name.get();
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info.get();
    }

    /**
     * @param info the info to set
     */
    public void setInfo(String info) {
        this.info.set(info);
    }

    /**
     * @return the rank
     */
    public Integer getRank() {
        return rank.get();
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(int rank) {
        this.rank.set(rank);
    }    

    /**
     * @return the orginsastion
     */
    public String getOrginsastion() {
        return orginsastion.get();
    }

    /**
     * @param orginsastion the orginsastion to set
     */
    public void setOrginsastion(String orginsastion) {
        this.orginsastion.set(orginsastion);
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name.set(Name);
    }

    /**
     * @param noder the noder to set
     */
    public void setNoder(ArrayList<TSN> noder) {
        this.noder = noder;
    }

    /**
     * @return the StartTime
     */
    public Calendar getStartTime() {
        return StartTime;
    }

    /**
     * @param StartTime the StartTime to set
     */
    public void setStartTime(Calendar StartTime) {
        this.StartTime = StartTime;
    }

    /**
     * @return the EndTime
     */
    public Calendar getEndTime() {
        return EndTime;
    }

    /**
     * @param EndTime the EndTime to set
     */
    public void setEndTime(Calendar EndTime) {
        this.EndTime = EndTime;
    }
    
    public void newUpdatedNode(TSN temp){
        int nr = noder.size();
        
        for (int i = 0; i < noder.size(); i++) {
            if (noder.get(i).getName().toLowerCase().contains(temp.getName().toLowerCase())) {
                noder.remove(i);
            }
        }
        if (nr > noder.size()) {
            noder.add(temp);
        }
    }


 
    /**
     * @return the listOfNodesErros
     */
    public ObservableList<TSN> getListOfNodesErros() {       
        ObservableList<TSN> temp = FXCollections.observableArrayList();
        temp.addAll(listOfNodesErros);
        return temp;
    }

    /**
     * @param listOfNodesErros the listOfNodesErros to set
     */
    public void setListOfNodesErros(TSN node) {
        this.listOfNodesErros.add(node);
        setErrorNodeName(listOfNodesErros.get(0).getName());
    }

    /**
     * @return the ErrorNodeName
     */
    public String getErrorNodeName() {
        return ErrorNodeName.get();
    }

    /**
     * @param ErrorNodeName the ErrorNodeName to set
     */
    public void setErrorNodeName(String ErrorNodeName) {
        this.ErrorNodeName.set(ErrorNodeName);
        
    }

    /**
     * @return the percentOfWorkingsNodes
     */
    public Integer getPercentOfWorkingsNodes() {
        int temp = 100-(listOfNodesErros.size()*100)/(noder.size());
        setPercentOfWorkingsNodes(temp);
       
        return percentOfWorkingsNodes.get();
    }

    /**
     * @param percentOfWorkingsNodes the percentOfWorkingsNodes to set
     */
    public void setPercentOfWorkingsNodes(Integer percentOfWorkingsNodes) {
        this.percentOfWorkingsNodes.set(percentOfWorkingsNodes);
    }
  
  
}
