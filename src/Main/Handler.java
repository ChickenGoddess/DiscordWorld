package Main;


import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.util.List;

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
    
    public static List<GameObj> object = new LinkedList<>();
    public static List<OverworldObj> items = new LinkedList<>();
    private GameCamera camera;
    private boolean initialized = false;
    private static int BACKGROUND_HEIGHT = 0;
    private static int BACKGROUND_WIDTH = 0;
    private boolean freeCam = true;
    private boolean done = true;
    private boolean largerX = false;
    private boolean largerY = false;
    private static boolean changedScale = false;
    
    public void init() {
        for (GameObj tempObj : object) {
            
            tempObj.init();
            if(tempObj.getID() == ID.Background){
                //System.out.println(tempObj.getHeight());
                BACKGROUND_HEIGHT = tempObj.getHeight();
                BACKGROUND_WIDTH = tempObj.getWidth();
            }
        }
        if(BACKGROUND_WIDTH <= Game.WIDTH){
            largerX = true;
        }
        if(BACKGROUND_HEIGHT <= Game.HEIGHT - 18){
            largerY = true;
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
        int count = 1;
        for(OverworldObj tempObj : items){
            this.checkCollision(tempObj);
        }
        for (GameObj tempObj : object) {
            //System.out.println(tempObj.getName());
            BufferedImage tempSprite = tempObj.getSprite();
            if (tempSprite != null) {
                
                if(tempObj.getID() == ID.Player){
                    if(freeCam = true){
                        this.camera.setFollowing(true, true);
                    }
                    //System.out.println(BACKGROUND_WIDTH);
                    //System.out.println(tempObj.getPosX());
                    if(largerX == false && largerY == false){
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
                        } if(tempObj.getPosX() >= BACKGROUND_WIDTH - Game.WIDTH/2 + Game.BACKGROUND_OFFSET_X - (camera.getTarget().getWidth()*Game.getScale())/2 + 10 * Game.getScale()){
                            this.camera.setFollowX(false);
                            this.camera.setX(BACKGROUND_WIDTH - Game.WIDTH + (int)(6 * Game.getScale()));
                            done = false;
                            freeCam = false;

                        } if(tempObj.getPosY() >= BACKGROUND_HEIGHT - Game.HEIGHT/2 + Game.BACKGROUND_OFFSET_Y){
                            this.camera.setFollowY(false);
                            this.camera.setY(BACKGROUND_HEIGHT - Game.HEIGHT + camera.getTarget().getHeight()/2);
                            done = false;
                            freeCam = false;
                        } 
                        if(done = true){
                            freeCam = true;
                        }
                        done = true;
                    } else if(largerX == true && largerY == false){
                        this.camera.setX(BACKGROUND_WIDTH/2 - Game.WIDTH/2);
                        if(freeCam = true){
                            this.camera.setFollowing(false, true);
                        }
                        if(tempObj.getPosY() <= Game.HEIGHT/2 + Game.BACKGROUND_OFFSET_Y - camera.getTarget().getHeight()/2){
                            this.camera.setFollowY(false);
                            this.camera.setY(0);
                            done = false;
                            freeCam = false;
                        }
                        if(tempObj.getPosY() >= BACKGROUND_HEIGHT - Game.HEIGHT/2 + Game.BACKGROUND_OFFSET_Y - 6){
                            this.camera.setFollowY(false);
                            this.camera.setY(BACKGROUND_HEIGHT - Game.HEIGHT + camera.getTarget().getHeight()/2 - 6);
                            done = false;
                            freeCam = false;
                        }
                        if(done = true){
                            freeCam = true;
                        }
                        done = true;
                    } else if(largerY == true && largerX == false){
                        this.camera.setY(BACKGROUND_HEIGHT/2 - Game.HEIGHT/2);
                        if(freeCam = true){
                            this.camera.setFollowing(true, false);
                        }
                        if(tempObj.getPosX() <= Game.WIDTH/2 + Game.BACKGROUND_OFFSET_X - (camera.getTarget().getWidth()*Game.getScale())/2){
                            this.camera.setFollowX(false);
                            this.camera.setX(0);
                            done = false;
                            freeCam = false;
                        }
                        if(done = true){
                            freeCam = true;
                        }
                        done = true;
                        if(tempObj.getPosX() >= BACKGROUND_WIDTH - Game.WIDTH/2 + Game.BACKGROUND_OFFSET_X - (camera.getTarget().getWidth()*Game.getScale())/2){
                            this.camera.setFollowX(false);
                            this.camera.setX(BACKGROUND_WIDTH - Game.WIDTH);
                            done = false;
                            freeCam = false;

                        }
                    } else if(largerX == true && largerY == true){
                        this.camera.setFollowY(false);
                        this.camera.setFollowX(false);
                        this.camera.setX(BACKGROUND_WIDTH/2 - Game.WIDTH/2);
                        this.camera.setY(BACKGROUND_HEIGHT/2 - Game.HEIGHT/2 + 18);
                    }

                }

                int xDelta = (int)((tempObj.getPosX() - this.camera.getPosX()));
                int yDelta = (int)((tempObj.getPosY() - this.camera.getPosY()));
                if(getChangeScaled() == true){
                    if(tempObj.getID() == ID.Background){
                        tempObj.setHeight((int)(tempObj.getHeight() * Game.getScale()));
                        tempObj.setWidth((int)(tempObj.getHeight() * Game.getScale()));
                        if(BACKGROUND_WIDTH <= Game.WIDTH){
                            largerX = true;
                        }
                        if(BACKGROUND_HEIGHT <= Game.HEIGHT - 18){
                            largerY = true;
                        }
                        if(BACKGROUND_WIDTH > Game.WIDTH){
                            largerX = false;
                        }
                        if(BACKGROUND_HEIGHT > Game.HEIGHT){
                            largerY = false;
                        }
                        //System.out.println(tempObj.getID());
                    }
                    
                    tempObj.setX((int)(tempObj.getUnscaledX() * Game.getScale()));
                    tempObj.setY((int)(tempObj.getUnscaledY() * Game.getScale()));
                    //System.out.println(tempObj.getID() + ": " + tempObj.getPosX() + ", " + tempObj.getPosY());
                    count++;
                    if(object.size() == count){
                        changeScaled(false);
                        count = 1;
                    }
                    //System.out.println(count);
                    //System.out.println(object.size());
                }
                g.drawImage(tempSprite, xDelta, yDelta, (int)(Game.getScale() * tempObj.getOriginWidth()), (int)(Game.getScale() * tempObj.getOriginHeight()), null);
            }
        }
    }
    
    public void addObj(GameObj obj){
        object.add(obj);
    }
    
    public void removeObj(GameObj obj){
        object.remove(obj);
    }
    
    public void addOverObj(OverworldObj obj){
        items.add(obj);
    }
    
    public void removeOverObj(OverworldObj obj){
        items.remove(obj);
    }
    
    public void setCamera(GameCamera camera) {
        this.camera = camera;
    }
    public static void changeScaled(boolean change){
        changedScale = change;
    }
    public boolean getChangeScaled(){
        return changedScale;
    }
    public static void setScaledLocation(){
        for(GameObj tempObj : object){
            
            tempObj.setX((int)(tempObj.getPosX() * Game.getScale()));
            tempObj.setY((int)(tempObj.getPosY() * Game.getScale()));
            
        }
    }
    public static void setBackgroundWidth(int width){
        BACKGROUND_WIDTH = (int)(width * Game.getScale());
    }
    public static void setBackgroundHeight(int height){
        BACKGROUND_HEIGHT = (int)(height * Game.getScale());
    }
    public void checkCollision(OverworldObj tempObj){
        for(OverworldObj item : items){
            item.collision(tempObj);
            //System.out.println("TempObj: " + tempObj.getName());
            //System.out.println("Item: " + item.getName());
            //System.out.println("Ayyy");
        }
    }
}
