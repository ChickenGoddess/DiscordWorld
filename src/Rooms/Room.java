/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rooms;

import Main.BackgroundTexture;
import Main.GameCamera;
import Main.GameObj;
import Main.GameState;
import Main.Handler;
import Main.OverworldObj;
import Main.Player;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Room {
    
    private BackgroundTexture texture;
    private String name;
    private List<OverworldObj> objects = new ArrayList<>();
    private List<Exit> exits = new ArrayList<>();
    private int offsetX;
    private int offsetY;
    
    
    public Room(String name, Player player){
        this.name = name;
    }
    
    public void init(){
        for(OverworldObj obj : objects){
            Handler.instance().addOverObj(obj);
        }
    }  
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void addObject(OverworldObj object){
        objects.add(object);
        GameState.addObject(object);
    }
    
    public OverworldObj getObject(int i){
        return objects.get(i);
    }
    
    public void addExit(Exit exit){
        exits.add(exit);
    }
    
    public Exit getExit(int i){
        return exits.get(i);
    }
    
    public BackgroundTexture getTexture(){
        return texture;
    }
    
    public void setTexture(BackgroundTexture texture){
        this.texture = texture;
    }
    
    public void setOffset(int x, int y){
        this.offsetX = x;
        this.offsetY = y;
    }
    
    public void spawnObj(GameObj obj, int x, int y){
        obj.setX(x);
        obj.setY(y);
        obj.setUnscaledX(x);
        obj.setUnscaledY(y);
    }
    
}
