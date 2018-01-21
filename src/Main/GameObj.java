package Main;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chicken
 */
public abstract class GameObj {
    
    protected int x;
    protected int y;
    protected ID id;
    protected int velX;
    protected int velY;
    protected BufferedImage sprite;
    protected int height;
    protected int width;
    protected int origin_width;
    protected int origin_height;
    protected int unscaledX;
    protected int unscaledY;
    protected String name;
    
    public GameObj(int x, int y, ID id, String name){
        this.x = x;
        this.y = y;
        this.id = id;
        sprite = null;
        height = 0;
        width = 0;
        this.name = name;
    }
    
    public abstract void init();
    public abstract void tick();
    public abstract void render(Graphics g);

    public int getHeight(){
        return height;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getPosX(){
        return x;
    }
    public int getPosY(){
        return y;
    }
    public ID getID(){
        return id;
    }
    public int getVelX(){
        return velX;
    }
    public int getVelY(){
        return velY;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setVelX(int velX){
        this.velX = velX;
    }
    public void setVelY(int velY){
        this.velY = velY;
    }
    public void setID(ID id){
        this.id = id;
    }
    public BufferedImage getSprite(){
        return this.sprite;
    }
    public void setSprite(BufferedImage sprite){
        this.sprite = sprite;
    }
    public void setOriginWidth(int width){
        this.origin_width = width;
    }
    public void setOriginHeight(int height){
        this.origin_height = height;
    }
    public int getOriginWidth(){
        return origin_width;
    }
    public int getOriginHeight(){
        return origin_height;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public int getUnscaledX(){
        return unscaledX;
    }
    public int getUnscaledY(){
        return unscaledY;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
}
