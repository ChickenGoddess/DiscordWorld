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
    
    private static Handler HANDLER = new Handler();
    
    public static Handler instance(){
        return HANDLER;
    }
    
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
                        /*
                        System.out.println("Game width/height: " + Game.WIDTH + ", " + Game.HEIGHT);
                        System.out.println("Background width/height: " + BACKGROUND_WIDTH + ", " + BACKGROUND_HEIGHT);
                        System.out.println("Sprite width/height: " + camera.getTarget().getWidth() + ", " + camera.getTarget().getHeight());
                        System.out.println("Player Location: " + camera.getTarget().getPosX() + ", " + camera.getTarget().getPosY());
                        System.out.println("Player Unscaled Location: " + camera.getTarget().getUnscaledX() + ", " + camera.getTarget().getUnscaledY());
                        System.out.println("Camera Location: " + camera.getPosX() + ", " + camera.getPosY());
                        System.out.println("No Border Width/Height: " + Game.getNoBorderWidth() + ", " + Game.getNoBorderHeight());
                        System.out.println("tempObj.getPosX() + tempObj.getWidth()/2: " + (tempObj.getPosX() + tempObj.getWidth()/2));
                        System.out.println("Game.getTexture().getSprite().getWidth() - Game.getNoBorderHeight() + Game.BACKGROUND_OFFSET_X: " + (Game.getTexture().getSprite().getWidth() - Game.getFrameWidth()/2 + Game.BACKGROUND_OFFSET_X));
                        */
                        if(tempObj.getUnscaledX()<= GameState.getOriginWidth()/2 + Game.BACKGROUND_OFFSET_X - camera.getTarget().getOriginWidth()/2){
                            this.camera.setFollowX(false);
                            this.camera.setX(0);
                            done = false;
                            freeCam = false;
                        } if(tempObj.getUnscaledY() <= GameState.getOriginHeight()/2 + Game.BACKGROUND_OFFSET_Y - camera.getTarget().getOriginHeight()/2){
                            this.camera.setFollowY(false);
                            this.camera.setY(0);
                            done = false;
                            freeCam = false;
                        } if(tempObj.getPosX() + tempObj.getWidth()/2 - 9 >= BACKGROUND_WIDTH - Game.getNoBorderWidth()/2 + Game.BACKGROUND_OFFSET_X){
                            this.camera.setFollowX(false);
                            this.camera.setX(BACKGROUND_WIDTH - Game.getNoBorderWidth());
                            done = false;
                            freeCam = false;

                        } if(tempObj.getPosY() + tempObj.getHeight()/2 - 18 >= BACKGROUND_HEIGHT - Game.getNoBorderHeight()/2 + Game.BACKGROUND_OFFSET_Y){
                            this.camera.setFollowY(false);
                            this.camera.setY(BACKGROUND_HEIGHT - Game.getNoBorderHeight());
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
                        if(tempObj.getPosY() >= BACKGROUND_HEIGHT - Game.HEIGHT/2 + Game.BACKGROUND_OFFSET_Y){
                            this.camera.setFollowY(false);
                            this.camera.setY(BACKGROUND_HEIGHT - Game.HEIGHT + camera.getTarget().getHeight()/2);
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
                        if(tempObj.getPosX() <= Game.WIDTH/2 + Game.BACKGROUND_OFFSET_X - (camera.getTarget().getWidth()*GameState.getScale())/2){
                            this.camera.setFollowX(false);
                            this.camera.setX(0);
                            done = false;
                            freeCam = false;
                        }
                        if(done = true){
                            freeCam = true;
                        }
                        done = true;
                        if(tempObj.getPosX() >= BACKGROUND_WIDTH - Game.WIDTH/2 + Game.BACKGROUND_OFFSET_X - (camera.getTarget().getWidth()*GameState.getScale())/2){
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
                    /*
                    tempObj.setX((int)(tempObj.getUnscaledX() * Game.getScale()));
                    tempObj.setY((int)(tempObj.getUnscaledY() * Game.getScale()));
                    tempObj.setWidth((int)(tempObj.getOriginWidth() * Game.getScale()));
                    tempObj.setHeight((int)(tempObj.getOriginHeight() * Game.getScale()));
*/
                    //System.out.println(tempObj.getName() + ": " + tempObj.getHeight() + ", " + tempObj.getWidth());
                    //System.out.println(tempObj.getID() + ": " + tempObj.getPosX() + ", " + tempObj.getPosY());
                    count++;
                    if(object.size() == count){
                        changeScaled(false);
                        count = 1;
                    }
                    //System.out.println(count);
                    //System.out.println(object.size());
                }
                
                tempObj.setActX(tempObj.getUnscaledX() + tempObj.getOriginWidth()/2);
                tempObj.setActY(tempObj.getUnscaledY() + tempObj.getOriginHeight()/2);
                //System.out.println(tempObj.getName() + ": (" + tempObj.getActX() + ", " + tempObj.getActY() + ")");
                g.drawImage(tempSprite, xDelta, yDelta, (int)(GameState.getScale() * tempObj.getOriginWidth()), (int)(GameState.getScale() * tempObj.getOriginHeight()), null);
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
            GameState.calculateScale(GameState.getByHeight());
            tempObj.setX((int)(tempObj.getUnscaledX() * GameState.getScale()));
            tempObj.setY((int)(tempObj.getUnscaledY() * GameState.getScale()));
            //System.out.println(tempObj.getName() + ": " + tempObj.getPosX() + ", " + tempObj.getPosY() + "/" + tempObj.getUnscaledX() + ", " + tempObj.getUnscaledY());
            tempObj.setWidth((int)(tempObj.getOriginWidth() * GameState.getScale()));
            tempObj.setHeight((int)(tempObj.getOriginHeight() * GameState.getScale()));
        }
    }
    public static void setBackgroundWidth(){
        //System.out.println("ORIGIN: " + BACKGROUND_HEIGHT);
        double d = GameState.getTexture().getOriginWidth() * GameState.getScale();
        //System.out.println("Interim: " + d);
        BACKGROUND_WIDTH = (int)d;
        GameState.getTexture().setWidth(BACKGROUND_WIDTH);
        //System.out.println("CHANGE: " + BACKGROUND_HEIGHT);
    }
    public static void setBackgroundHeight(){
        //System.out.println("ORIGIN: " + BACKGROUND_HEIGHT);
        double d = GameState.getTexture().getOriginHeight() * GameState.getScale();
        //System.out.println("Interim: " + d);
        BACKGROUND_HEIGHT = (int)d;
        GameState.getTexture().setHeight(BACKGROUND_HEIGHT);
        //System.out.println("CHANGE: " + BACKGROUND_HEIGHT);
    }
    public void checkCollision(OverworldObj tempObj){
        for(OverworldObj item : items){
            item.collision(tempObj);
            if(item.checkCollision(tempObj) == false && (item.getName() == null ? tempObj.getName() != null : !item.getName().equals(tempObj.getName()))){
                tempObj.setCollided(false);
                //tempObj.setCollided(false);
                //System.out.println("TempObj: " + tempObj.getName());
                //System.out.println("Item: " + item.getName() + "\n");
            }
            //System.out.println("TempObj: " + tempObj.getName());
            //System.out.println("Item: " + item.getName());
            //System.out.println("Ayyy");
        }
        //System.out.println("");
    }
}
