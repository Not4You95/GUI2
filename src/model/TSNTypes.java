/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.lang.model.element.UnknownAnnotationValueException;

/**
 *
 * @author jonas
 */
public enum TSNTypes {
    Troups(1),UAV(2),Radar(3),Hospital(4),Comand_Central(5);
    int Value= 0;
    
    TSNTypes(int ord) {
        this.Value = ord;
    }
     public static TSNTypes getTypes(int ord) {
        return TSNTypes.values()[ord-1]; // less safe
    }

}
