/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import gui2.*;

/**
 *
 * @author jonas
 */
public class Auto {
    private ArrayList<String> ListOfStrings = new ArrayList();
    private TextField textField = new TextField();
    private String filter = "";
    private TextField currentParentField;
    private ContextMenu popup;
    private String Returntemp="";
    private ArrayList<MenuItem> ListOfMenuItems = new ArrayList<>();
    private GUI2 gui;
    
    public Auto(ArrayList<String> temp,TextField fieldInput,GUI2 GUICLASS){
        this.gui = GUICLASS;
        this.ListOfStrings.addAll(temp);
        this.textField = fieldInput;        
        this.textField.setOnKeyPressed(this::handleOnKeyPressed);
         popup = new ContextMenu();
         popup.setOnAction( popupAction );
        
    }
    private void handleOnKeyPressed(KeyEvent e){
        EventTarget target = e.getTarget();
       currentParentField = (TextField)target;
       EventType<? extends Event> type = e.getEventType();
       KeyCode code = e.getCode();
       System.out.println(e.getCode().toString());
       
        
        if (code == KeyCode.ESCAPE) {
			filter = "";
                        System.out.println("Hello escape");
		}
        if (code == KeyCode.BACK_SPACE && filter.length()>0) {
            filter = filter.substring(0, filter.length()-1);
        }
         if (code.isLetterKey()) {
                filter += e.getText();
                System.out.println("Filter: "+filter.toString());
           }
         
        if (filter.length() == 0) {
            setMenuItem(ListOfStrings);
            popup.hide();
        }
      else{
            ObservableList<MenuItem> filteredList = FXCollections.observableArrayList();
            popup.hide();
           Stream<MenuItem> items = ListOfMenuItems.stream();
           String textUsr = filter.toString().toLowerCase();
            System.out.println("TextUsr: "+textUsr);
             popup.getItems().clear();
            for (int i = 0; i <ListOfStrings.size(); i++) {
                if (ListOfStrings.get(i).toLowerCase().contains(textUsr)) {
                    setMenuItemOne(ListOfStrings.get(i));
                }
            }
           popup.show( currentParentField, Side.BOTTOM, 0, 0 );
            
         }
    }
    public void setMenuItemOne(String temp){      
       MenuItem item = new MenuItem(temp);
       popup.getItems().add(item);
   }
   
   public void addToListOfStrings(String[] temp){
       for (int i = 0; i < temp.length; i++) {
           ListOfStrings.add(temp[i]);
       }
   }
   public void setMenuItem(ArrayList<String> temp){
       popup.getItems().clear();
       for (int i = 0; i < temp.size(); i++) {
            MenuItem item = new MenuItem(temp.get(i));            
            popup.getItems().add(item);
       }
   }
   
   EventHandler<ActionEvent> popupAction = new EventHandler<ActionEvent>() {
     public void handle( ActionEvent e ){
       MenuItem src = (MenuItem)e.getTarget();
       String text = src.getText();
       currentParentField.setText( text );
       gui.SetP_2_PChoises(text);
       
    }
     
   };

    
    
}
