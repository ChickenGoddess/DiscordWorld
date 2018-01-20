
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
    private static int BACKGROUND_HEIGHT = 0;
    private static int BACKGROUND_WIDTH = 0;
    private boolean freeCam = true;
    private boolean done = true;
    
    public void init() {
        for (GameObj tempObj : object) {
            
            tempObj.init();
            if(tempObj.getID() == ID.Background){
                System.out.println(tempObj.getHeight());
                BACKGROUND_HEIGHT = tempObj.getHeight();
                BACKGROUND_WIDTH = tempObj.getWidth();
            }
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
    
    public void render(Graphics g, int width, int height){
        /*for(int i = 0; i < object.size(); i++){
            GameObj tempObj = object.get(i);
            tempObj.render(g);
        }*/

        for (GameObj tempObj : object) {
            BufferedImage tempSprite = tempObj.getSprite();
            if (tempSprite != null) {
                
                if(tempObj.getID() == ID.Player){
                    if(freeCam = true){
                        this.camera.setFollowing(true, true);
                    }
                    if(tempObj.getPosX() <= Game.WIDTH/2 + Game.BACKGROUND_OFFSET_X - camera.getTarget().getWidth()/2){
                        this.camera.setFollowX(false);
                        this.camera.setX(0);
                        done = false;
                        freeCam = false;
                    } if(tempObj.getPosY() <= Game.HEIGHT/2 + Game.BACKGROUND_OFFSET_Y - camera.getTarget().getHeight()/2){
                        this.camera.setFollowY(false);
                        this.camera.setY(0);
                        done = false;
                        freeCam = false;
                    } if(tempObj.getPosX() >= BACKGROUND_WIDTH - Game.WIDTH/2 + Game.BACKGROUND_OFFSET_X - camera.getTarget().getWidth()/2){
                        this.camera.setFollowX(false);
                        this.camera.setX(BACKGROUND_WIDTH - Game.WIDTH);
                        done = false;
                        freeCam = false;
                        
                    } if(tempObj.getPosY() >= BACKGROUND_HEIGHT - Game.HEIGHT/2 + Game.BACKGROUND_OFFSET_Y - 6){
                        this.camera.setFollowY(false);
                        this.camera.setY(BACKGROUND_HEIGHT - Game.HEIGHT + camera.getTarget().getHeight()/2 - 6);
                        done = false;
                        freeCam = false;
                    }
                    if(done = true){
                        freeCam = true;
                    }
                    done = true;
                }

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
