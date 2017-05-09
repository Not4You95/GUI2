/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.jar.Attributes;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jonas
 */
public class TSN implements Serializable{
private SimpleStringProperty name = new SimpleStringProperty();
private TSNTypes type;
private SimpleStringProperty info = new SimpleStringProperty();
private ArrayList<Interface> ListOfInterface;
private SimpleStringProperty Priority = new SimpleStringProperty();
private SimpleStringProperty quality = new SimpleStringProperty();

/**
 * Constructur
 * @param name 
 */
public TSN(String name)
{
       setName(name);
       ListOfInterface = new ArrayList<Interface>();
    
}
/**
 * Construktur
 */
public TSN(){
    this.name = null;
    ListOfInterface = new ArrayList<Interface>();
}

    /**
     * @return the name
     */
   
    public void SetPriorityForAllInterface(priorityAndQulaityLevels priority){
        for (int i = 0; i < ListOfInterface.size(); i++) {
            ListOfInterface.get(i).SetPriority(priority);
        }
    }
    
    public void SetPriorityForSpecialInterface(){
        
    }
    public void addInterfaceArray(ArrayList<Interface> tempInterface){
        ListOfInterface.addAll(tempInterface);
    }
    public void addInterface(Interface tempinterface){
        ListOfInterface.add(tempinterface);        
    }

    /**
     * @return the type
     */
    public ArrayList<Interface> getInterface() {
        
        ArrayList<Interface> temp = new ArrayList<>();
          temp.addAll(ListOfInterface);
          return temp;
    }
    public void addInterface(ArrayList temp){
        ListOfInterface.addAll(temp);
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.setType(type);
    }

    /**
     * @return the type
     */
    public TSNTypes getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(TSNTypes type) {
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info.get();
    }

    /**
     * @return the Priority
     */
    public String getPriority() {
        return Priority.get();
    }

    /**
     * @param Priority the Priority to set
     */
    public void setPriority(String Priority) {
        this.Priority.set(Priority);
    }

    /**
     * @return the quality
     */
    public String getQuality() {
        return quality.get();
    }

    /**
     * @param quality the quality to set
     */
    public void setQuality(String quality) {
        this.quality.set(quality);
    }

   
}
