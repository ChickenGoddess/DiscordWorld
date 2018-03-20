/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rooms;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Exit {
    
    private Room source;
    private int sourceX;
    private int sourceY;
    private int sourceWidth;
    private int sourceHeight;
    private Room destination;
    private int destX;
    private int destY;
    private int destWidth;
    private int destHeight;
    private boolean locked;
    private String key;
    private int exitSide;
    
    public Exit(){
        
    }
    
    public Exit(Room source, Room destination,
            int x1, int y1, int width1, int height1, 
            int x2, int y2, int width2, int height2, int exitSide) {
        this.sourceX = x1;
        this.sourceY = y1;
        this.sourceWidth = width1;
        this.sourceHeight = height1;
        this.destX = x2;
        this.destY = y2;
        this.destWidth = width2;
        this.destHeight = height2;
        this.source = source;
        this.destination = destination;
        this.exitSide = exitSide;
        source.addExit(this);
    }
    
    public Room getSource(){
        return source;
    }
    
    public Room getDestination(){
        
        return destination;
    }
    
    public boolean getLocked(){
        return locked;
    }
    
    public String getKey(){
        return key;
    }
    
    public void setKey(String key){
        this.key = key;
    }
    
    public void setSource(Room source){
        this.source = source;
    }
    
    public void setDestination(Room dest){
        this.destination = dest;
    }
    
    public void setLocked(boolean bool){
        this.locked = bool;
    }

    /**
     * @return the sourceX
     */
    public int getSourceX() {
        return sourceX;
    }

    /**
     * @param sourceX the sourceX to set
     */
    public void setSourceX(int sourceX) {
        this.sourceX = sourceX;
    }

    /**
     * @return the sourceY
     */
    public int getSourceY() {
        return sourceY;
    }

    /**
     * @param sourceY the sourceY to set
     */
    public void setSourceY(int sourceY) {
        this.sourceY = sourceY;
    }

    /**
     * @return the sourceWidth
     */
    public int getSourceWidth() {
        return sourceWidth;
    }

    /**
     * @param sourceWidth the sourceWidth to set
     */
    public void setSourceWidth(int sourceWidth) {
        this.sourceWidth = sourceWidth;
    }

    /**
     * @return the sourceHeight
     */
    public int getSourceHeight() {
        return sourceHeight;
    }

    /**
     * @param sourceHeight the sourceHeight to set
     */
    public void setSourceHeight(int sourceHeight) {
        this.sourceHeight = sourceHeight;
    }

    /**
     * @return the destX
     */
    public int getDestX() {
        return destX;
    }

    /**
     * @param destX the destX to set
     */
    public void setDestX(int destX) {
        this.destX = destX;
    }

    /**
     * @return the destY
     */
    public int getDestY() {
        return destY;
    }

    /**
     * @param destY the destY to set
     */
    public void setDestY(int destY) {
        this.destY = destY;
    }

    /**
     * @return the destWidth
     */
    public int getDestWidth() {
        return destWidth;
    }

    /**
     * @param destWidth the destWidth to set
     */
    public void setDestWidth(int destWidth) {
        this.destWidth = destWidth;
    }

    /**
     * @return the destHeight
     */
    public int getDestHeight() {
        return destHeight;
    }

    /**
     * @param destHeight the destHeight to set
     */
    public void setDestHeight(int destHeight) {
        this.destHeight = destHeight;
    }
    
    //0 = center
    //1 = top
    //2 = right
    //3 = bottom
    //4 = left
    public int getExitSide(){
        return exitSide;
    }
    
    public void setExitSide(int side){
        this.exitSide = side;
    }
    
}
