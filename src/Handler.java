
import java.awt.Graphics;
import java.util.LinkedList;
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
public class Handler {
    
    LinkedList<GameObj> object = new LinkedList<>();
    private GameCamera camera;
    private boolean initialized = false;
    
    public void init() {
        for (GameObj tempObj : object) {
            tempObj.init();
        }
        initialized = true;
    }

    public void tick(){
        if (!initialized) {
            return;
        }

        for (GameObj tempObj : object) {
            tempObj.tick();
        }
    }
    
    public void render(Graphics g){
        /*for(int i = 0; i < object.size(); i++){
            GameObj tempObj = object.get(i);
            tempObj.render(g);
        }*/

        for (GameObj tempObj : object) {
            BufferedImage tempSprite = tempObj.getSprite();
            if (tempSprite != null) {
                int xDelta = tempObj.getPosX() - this.camera.getPosX();
                int yDelta = tempObj.getPosY() - this.camera.getPosY();
                g.drawImage(tempSprite, xDelta, yDelta, null);
            }
        }
    }
    
    public void addObj(GameObj obj){
        object.add(obj);
    }
    
    public void removeObj(GameObj obj){
        object.remove(obj);
    }
    
    public void setCamera(GameCamera camera) {
        this.camera = camera;
    }
}
