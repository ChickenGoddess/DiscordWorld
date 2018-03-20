/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandledClasses;

import Rooms.Room;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class ImageOrderer {
    
    private String filename;
    private String currentRoomName;
    private Room currentRoom;
    
    public ImageOrderer(){
        
    }
    
    public ImageOrderer(String filename) throws FileNotFoundException{
        Scanner scan = new Scanner(new BufferedReader(new FileReader(filename)));
        String line;
    }
    
}
