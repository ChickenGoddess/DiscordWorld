
import java.awt.Graphics;
import java.util.LinkedList;

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
    
    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObj tempObj = object.get(i);
            tempObj.tick();
        }
    }
    
    public void render(Graphics g){
        
    }
    
}
