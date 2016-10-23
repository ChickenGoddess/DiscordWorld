
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
            System.out.println("Object position: " + tempObj.getPosX());
            System.out.println(" Half of Game Size: " + Game.WIDTH/2);
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
            
            if(tempObj.getID() == ID.Player && Game.isClamped()){
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
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        
        int key = e.getKeyCode();
        
        for(int i = 0; i < handler.object.size(); i++){
            GameObj tempObj = handler.object.get(i);
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
            
            if(tempObj.getID() == ID.Player && Game.isClamped()){
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
