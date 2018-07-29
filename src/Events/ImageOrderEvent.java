/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Rooms.Room;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class ImageOrderEvent extends Event{
    
    private String filename;
    private String currentRoomName;
    private Room currentRoom;

    public ImageOrderEvent(String s) throws FileNotFoundException{
        super(s);
        Scanner scan = new Scanner(new BufferedReader(new FileReader(filename)));
        String line;
    }

    @Override
    public void trigger() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
