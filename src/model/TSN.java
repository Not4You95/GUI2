/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.jar.Attributes;

/**
 *
 * @author jonas
 */
public class TSN implements Serializable{
private String name;
private TSNTypes type;
private String info;
private ArrayList<Interface> ListOfInterface;


/**
 * Constructur
 * @param name 
 */
public TSN(String name)
{
       this.name = name;
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
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
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
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }
}
