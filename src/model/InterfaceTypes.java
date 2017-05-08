/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.media.jfxmedia.control.VideoDataBuffer;
import javax.sound.midi.VoiceStatus;

/**
 *
 * @author jonas
 */
public enum InterfaceTypes {
   
        Tracking(1), Video(2), Voice(3), Mesagge(4);
    
    int Value= 0;
    
    InterfaceTypes(int ord) {
        this.Value = ord;
    }
     public static InterfaceTypes getTypes(int ord) {
        return InterfaceTypes.values()[ord-1]; // less safe
    }

}

   
