/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import java.io.FileNotFoundException;

/**
 *
 * @author Owner
 */
public class EventManager {
    private static EventManager instance = new EventManager();
    
    public static EventManager getInstance(){
        return instance;
    }
    
    public Event getEvent(String eventName, String parameters) throws FileNotFoundException{
        switch(eventName){
            case "text":
                return new TextEvent(parameters);
            case "setloc":
                return new SetLocEvent(parameters);
            case "stop":
                return new StopEvent(parameters);
            case "orderimage":
                return new ImageOrderEvent(parameters);
            default:
                assert false;
                return null;
        }
    }
}
