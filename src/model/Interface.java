/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author jonas
 */
public class Interface implements Serializable {
    private priorityAndQulaityLevels Priority;
    private priorityAndQulaityLevels Quality;
    private boolean Pruducer;
    private boolean Consumer;
    private InterfaceTypes type;
    private String Name;
    
    
            
    public Interface (){
    
    
}
    public Interface(String name,priorityAndQulaityLevels priority,priorityAndQulaityLevels quality,InterfaceTypes type){
        this.Name = name;
        this.Priority = priority;
        this.Quality = quality;
        this.type = type;
    }
    
    public void SetPriority(priorityAndQulaityLevels Priority){
        this.Priority=Priority;
    }

  

    /**
     * @param Priority the Priority to set
     */
   

    

    /**
     * @param Quality the Quality to set
     */
    public void setQuality(priorityAndQulaityLevels Quality) {
        this.setQuality(Quality);
    }

    /**
     * @return the Pruducer
     */
    public boolean isPruducer() {
        return Pruducer;
    }

    /**
     * @param Pruducer the Pruducer to set
     */
    public void setPruducer(boolean Pruducer) {
        this.Pruducer = Pruducer;
    }

    /**
     * @return the Consumer
     */
    public boolean isConsumer() {
        return Consumer;
    }

    /**
     * @param Consumer the Consumer to set
     */
    public void setConsumer(boolean Consumer) {
        this.Consumer = Consumer;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

   
   

    /**
     * @return the Priority
     */
    public priorityAndQulaityLevels getPriority() {
        return Priority;
    }

    /**
     * @return the Quality
     */
    public priorityAndQulaityLevels getQuality() {
        return Quality;
    }

    /**
     * @return the type
     */
    public InterfaceTypes getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(InterfaceTypes type) {
        this.type = type;
    }

    
    
}
