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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;


/**
 *
 * @author jonas
 */
public class TSN implements Serializable{
private SimpleStringProperty name = new SimpleStringProperty();
private TSNTypes type;
private SimpleStringProperty info = new SimpleStringProperty();
private ArrayList<Interface> ListOfInterface;
private priorityAndQulaityLevels Priority;
private priorityAndQulaityLevels quality ;
private Image image=null; 
private  ObservableList<Interface> listOfInterfasErros;


/**
 * Constructur
 * @param name 
 */
public TSN(String name)
{
       setName(name);       
       this.Priority = priorityAndQulaityLevels.Standard;
       this.quality = priorityAndQulaityLevels.Standard;
       this.listOfInterfasErros = FXCollections.observableArrayList();
       //"pic\noPic.JPG" 
       this.image = new Image(getClass().getResourceAsStream("noPic.JPG"));
    
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
    
       public void addInterfaceArray(ArrayList<Interface> tempInterface){
       
        this.listOfInterfasErros.addAll(tempInterface);
    }
    public void face(Interface tempinterface){
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

    
    
    public void newUpdatedInterface(Interface temp){
        int nr = ListOfInterface.size();
        for (int i = 0; i < ListOfInterface.size(); i++) {
            if (ListOfInterface.get(i).getName().toLowerCase().contains(temp.getName().toLowerCase())) {
                ListOfInterface.remove(i);
            }
        }
        if (nr > ListOfInterface.size()) {
            ListOfInterface.add(temp);
        }
    }

    /**
     * @param Priority the Priority to set
     */
    public void setPriority(priorityAndQulaityLevels Priority) {
        this.Priority = Priority;
    }
    
     
    public void addListOfErrors(Interface input){        
        listOfInterfasErros.add(input);
    }    
    

    /**
     * @param quality the quality to set
     */
    public void setQuality(priorityAndQulaityLevels quality) {
        this.quality = quality;
    }

    /**
     * @return the Priority
     */
    public priorityAndQulaityLevels getPriority() {
        return Priority;
    }

    /**
     * @return the quality
     */
    public priorityAndQulaityLevels getQuality() {
        return quality;
    }

    /**
     * @return the imgag
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the imgag to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @return the listOfInterfasErros
     */
    public ObservableList<Interface> getListOfInterfasErros() {
        return listOfInterfasErros;
    }

    /**
     * @param listOfInterfasErros the listOfInterfasErros to set
     */
    public void setListOfInterfasErros(ObservableList<Interface> listOfInterfasErros) {
        this.listOfInterfasErros = listOfInterfasErros;
    }

   
}
