package Main;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
    
    public KeyInput(Handler handler){
        this.handler = handler;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        
        int key = e.getKeyCode();
        
        for(int i = 0; i < handler.object.size(); i++){
            GameObj tempObj = handler.object.get(i);
            
            if(tempObj.getID() == ID.Player){
                if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
                    tempObj.setVelY(-5);
                }
                if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
                    tempObj.setVelY(5);
                }
                if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
                    tempObj.setVelX(-5);
                }
                if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
                    tempObj.setVelX(5);
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
            if(key == KeyEvent.VK_ESCAPE){
                System.exit(0);
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        
        int key = e.getKeyCode();
        
        for(int i = 0; i < handler.object.size(); i++){
            GameObj tempObj = handler.object.get(i);
            
            if(tempObj.getID() == ID.Player){
                if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
                    tempObj.setVelY(0);
                }
                if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
                    tempObj.setVelY(0);
                }
                if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
                    tempObj.setVelX(0);
                }
                if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
                    tempObj.setVelX(0);
                }
            }
            
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
        }
        
    }
    
}
