/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jonas
 */
public class ReadAndWriteToFile implements Serializable{
      private File MyFile;

    /**
     * constructor
     */
    public ReadAndWriteToFile() {
        MyFile = new File("test.txt");
    }

   /**
    *  This method saves the data of the type player in to a file   
    * @param file is the file the data will be saved at
    * @throws AlertToUser error massage to the user
    */
    public void writeToFile(Orginasation org, File file) throws IOException, AlertToUser {
        System.out.println("write");
        boolean success = false;
        ObjectOutputStream output = null;
        MyFile = file;

        try {
            output = new ObjectOutputStream(new FileOutputStream(MyFile, false));
            output.writeObject(org);
            ///System.out.println(players.getUserName());

            output.close();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                throw new AlertToUser("Can't save data!");

            }
        }

    }

    /**
     * method reads saved information from text file and returns the player objekt
     * @param file is the file the will be open.  
     * @throws AlertToUser returns error massage to the user
     */
    public Orginasation readFromFile(File file) throws ClassNotFoundException, IOException, AlertToUser {
        Orginasation org = new Orginasation();
        ObjectInputStream input = null;
        MyFile = file;

        try {
            input = new ObjectInputStream(new FileInputStream(MyFile));
            org = (Orginasation) input.readObject();

        } catch (ClassNotFoundException e) {
            throw new AlertToUser("Can't open file!");
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {

            }
        }

        return org;

    }

    void writeToFile(ArrayList<Orginasation> Org, File filename) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
