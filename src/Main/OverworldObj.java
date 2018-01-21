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
    
    private String name;
    
    public OverworldObj(int x, int y, ID id, String name) {
        super(x, y, id, name);
    }
    
    public void collision(OverworldObj object){
        if(this.getPosX() <= (object.getPosX() + object.getOriginWidth()) * Game.getScale() &&
                this.getPosY() <= (object.getPosY() + object.getOriginWidth()) * Game.getScale() &&
                (this.getPosX() + this.getOriginWidth()) * Game.getScale() >= object.getPosX() &&
                (this.getPosY() + this.getOriginHeight()) * Game.getScale() >= object.getPosY()){
            this.setVelX(0);
            this.setVelY(0);
            //System.out.println(this.getPosX() + "/ " + object.getPosX() + "/ " + object.getOriginWidth());
            //System.out.println("Stuff");
        }
    }
    
    
    
}
