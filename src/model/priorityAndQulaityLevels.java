/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jonas
 */
public enum priorityAndQulaityLevels {
    High(1),Medium(2),Low(3);
    int Value= 0;
    
    priorityAndQulaityLevels(int ord) {
        this.Value = ord;
    }
     public static priorityAndQulaityLevels getTypes(int ord) {
        return priorityAndQulaityLevels.values()[ord-1]; // less safe
    }

}
