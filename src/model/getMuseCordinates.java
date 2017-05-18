/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author jonas
 */
public class getMuseCordinates {
    private Node node;
    private TableColumn colum;
    
    public getMuseCordinates(Node node){
        this.node =node;
        
        node.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               System.out.println("-------------------------------------------------------");               
               System.out.println("Node");
               System.out.println("Mouse X-cordinate: "+event.getSceneX());
               System.out.println("Muse Y-cordinate: "+event.getSceneY());
                System.out.println("Width: "+ node.getBoundsInLocal().getWidth());
               System.out.println("-------------------------------------------------------");
            }
        });
    }
    
    
    
}
