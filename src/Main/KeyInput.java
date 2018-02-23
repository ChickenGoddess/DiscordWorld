package Main;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chicken
 */
public class KeyInput extends KeyAdapter{
    
    private Handler handler;
    private int playerVel = 1;
    private final Set<Integer> pressed = new HashSet<Integer>();
    private boolean dir1 = false;
    private boolean dir2 = false;
    private boolean dir3 = false;
    private boolean dir4 = false;
    private boolean running = false;
    
    public KeyInput(Handler handler){
        this.handler = handler;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        
        pressed.add(e.getKeyCode());
        
        
        for(int i = 0; i < handler.object.size(); i++){
            GameObj tempObj = handler.object.get(i);
            if(tempObj.getID() == ID.Player){
                if(pressed.size() >= 1){
                    for(int key : pressed){
                        if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
                            dir1 = true;
                        }
                        if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
                            dir2 = true;
                        }
                        if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
                            dir3 = true;
                        }
                        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
                            dir4 = true;
                        }
                        if(key == KeyEvent.VK_SHIFT){
                            //playerVel = 10;
                            running = true;
                        }
                        if(!pressed.contains(KeyEvent.VK_SHIFT)){
                            running = false;
                        }
                    }
                }
            }
            /*
            if(tempObj.getID() == ID.Background){
                if(key == KeyEvent.VK_W){
                    tempObj.setVelY(-5);
                }
                if(key == KeyEvent.VK_S){
                    tempObj.setVelY(5);
                }
                if(key == KeyEvent.VK_A){
                    tempObj.setVelX(-5);
                }
                if(key == KeyEvent.VK_D){
                    tempObj.setVelX(5);
                }
            }
*/          
            if(pressed.size() >= 1){
                for(int key : pressed){
                    if(key == KeyEvent.VK_ESCAPE){
                        System.exit(0);
                    }
                }
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        
        int key = e.getKeyCode();
        
        for(int i = 0; i < handler.object.size(); i++){
            GameObj tempObj = handler.object.get(i);
            if(tempObj.getID() == ID.Player){
                if(pressed.size() >= 1){
                    
                    if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
                        dir1 = false;
                    }
                    if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
                        dir2 = false;
                    }
                    if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
                        dir3 = false;
                    }
                    if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
                        dir4 = false;
                    }
                    if(key == KeyEvent.VK_SHIFT){
                        //playerVel = 5;
                        running = false;
                    }
                        
                    
                    pressed.remove(e.getKeyCode());
                }
            }
            /*
            if(tempObj.getID() == ID.Background){
                if(key == KeyEvent.VK_W){
                    tempObj.setVelY(0);
                }
                if(key == KeyEvent.VK_S){
                    tempObj.setVelY(0);
                }
                if(key == KeyEvent.VK_A){
                    tempObj.setVelX(0);
                }
                if(key == KeyEvent.VK_D){
                    tempObj.setVelX(0);
                }
            }
*/
        }
        
    }
    
    public void moveCharacter(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObj tempObj = handler.object.get(i);
            if(tempObj.getID() == ID.Player){
                if(running){
                    playerVel = 50;
                } else{
                    playerVel = 1;
                }
                if(dir1){
                    tempObj.setVelY(-playerVel);
                }
                if(dir2){
                    tempObj.setVelY(playerVel);
                }
                if(dir3){
                    tempObj.setVelX(-playerVel);
                }
                if(dir4){
                    tempObj.setVelX(playerVel);
                }
                if(!dir1 && !dir2){
                    tempObj.setVelY(0);
                }
                if(!dir3 && !dir4){
                    tempObj.setVelX(0);
                }
                if((dir1 && dir2) || (dir3 && dir4)){
                    tempObj.setVelX(0);
                    tempObj.setVelY(0);
                }
            }
        }
    }
    
}
