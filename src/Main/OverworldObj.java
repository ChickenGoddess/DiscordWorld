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
        //System.out.println("TempObj: " + object.getName() + " (" + object.getPosX() + ", " + (object.getPosX() + object.getOriginWidth()) + ")" + "/(" + object.getPosY() + ", " + (object.getPosY() + object.getOriginHeight()) + ")");
        //System.out.println("Item: " + this.getName() + " (" + this.getPosX() + ", " + (this.getPosX() + this.getOriginWidth()) + ")" + "/(" + this.getPosY() + ", " + (this.getPosY() + this.getOriginHeight()) + ")");
        if(this.getPosX() <= (object.getPosX() + object.getOriginWidth()) * Game.getScale() &&
                this.getPosY() <= (object.getPosY() + object.getOriginWidth()) * Game.getScale() &&
                (this.getPosX() + this.getOriginWidth()) * Game.getScale() >= object.getPosX() &&
                (this.getPosY() + this.getOriginHeight()) * Game.getScale() >= object.getPosY() &&
                this.getName() != object.getName()){
            System.out.println("Colided");
            this.setVelX(0);
            this.setVelY(0);
            //System.out.println(this.getPosX() + "/ " + object.getPosX() + "/ " + object.getOriginWidth());
            //System.out.println("Stuff");
        }
    }
    
    
    
}
