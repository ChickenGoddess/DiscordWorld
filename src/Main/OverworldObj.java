package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chicken
 */
public abstract class OverworldObj extends GameObj{
    
    public OverworldObj(int x, int y, ID id) {
        super(x, y, id);
    }
    
    public void collision(OverworldObj object){
        if(this.getPosX() < (object.getPosX() + object.getOriginWidth()) * Game.getScale() &&
                this.getPosY() < (object.getPosY() + object.getOriginWidth()) * Game.getScale() &&
                ){
            
        }
    }
    
}
