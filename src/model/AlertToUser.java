/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.lang.RuntimeException;
/**
 *
 * @author jonas
 */
public class AlertToUser extends RuntimeException{
    
    private String alertInformation;
    /**
     * constructor
     */
    public AlertToUser(String info) {
        super(info);
        alertInformation = info;
    }

    /**
     * This method returns the error massage
     *
     * @return alert message
     */
    @Override
    public String getMessage() {
        return alertInformation;
    }
}
