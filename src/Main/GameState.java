/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.Game.WIDTH;
import Rooms.Room;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class GameState {
    
    private static BackgroundTexture TEXTURE;
    private static List<Room> ROOMS = new ArrayList<>();
    private static List<OverworldObj> objects = new ArrayList<>();
    
    private static GameState GAME_STATE = new GameState();
    private static Room CURRENT_ROOM;
    private static GameCamera CAMERA = new GameCamera(0, 0);
    private static Player player;
    
    public static Room getRoom(){
        return GameState.CURRENT_ROOM;
    }
    
    public static void addRoom(Room room){
        ROOMS.add(room);
    }
    
    public static List getROOMS(){
        return ROOMS;
    }
    
    public static void setCurrentRoom(Room room){
        GameState.CURRENT_ROOM = room;
    }
    
    public static void setTexture(BackgroundTexture texture){
        GameState.TEXTURE = texture;
    }
    
    public static BackgroundTexture getTexture(){
        return TEXTURE;
    }
    
    public static GameState instance(){
        return GAME_STATE;
    }
    
    public static GameCamera getCamera(){
        return CAMERA;
    }
    
    public static void setCameraTarget(GameObj obj){
        CAMERA.setTarget(obj);
    }
    
    public static void spawnPlayer(int x, int y){
        player.setX(x);
        player.setY(y);
        player.setUnscaledX(x);
        player.setUnscaledY(y);
    } 
    
    public static void init(){
        player = new Player(Game.getFrameWidth(), Game.getFrameHeight(), ID.Player);
        setCameraTarget(player);
        Handler.instance().setCamera(CAMERA);
    }
    
    public static void populateRoom(){
        setTexture(getRoom().getTexture());
        Handler.instance().addObj(TEXTURE);
        Handler.instance().addObj(player);
        Handler.instance().addOverObj(player);
        Handler.instance().addObj(CAMERA);
        
        for(OverworldObj obj : objects){
            Handler.instance().addObj(obj);
            getRoom().init();
            //Handler.instance().addOverObj(obj);
        }
    }
    
    public static void addObject(OverworldObj obj){
        objects.add(obj);
    }
    
    public static Player getPlayer(){
        return player;
    }
    
    public static void setPlayer(Player player){
        GameState.player = player;
    }
    
}
