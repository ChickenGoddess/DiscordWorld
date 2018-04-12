package Main;

import java.util.Scanner;

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
    
    protected int depth;
    protected int quadrant;
    protected int triangle;
    protected int prevX;
    protected int prevY;
    protected static int count = 0;
    
    public OverworldObj(int x, int y, ID id, String name) {
        super(x, y, id, name);
        overworld = true;
    }
    
    public OverworldObj(Scanner scan){
        super(scan);
    }
    
    public void collision(OverworldObj object){
        //System.out.println("TempObj: " + object.getName() + " (" + object.getPosX() + ", " + (object.getPosX() + object.getOriginWidth()) + ")" + "/(" + object.getPosY() + ", " + (object.getPosY() + object.getOriginHeight()) + ")");
        //System.out.println("Item: " + this.getName() + " (" + this.getPosX() + ", " + (this.getPosX() + this.getOriginWidth()) + ")" + "/(" + this.getPosY() + ", " + (this.getPosY() + this.getOriginHeight()) + ")");
        if(checkCollision(object)){
            //System.out.println("Colided");
            if(object.getID() == ID.Player){
                setQuadrant(object, this);
                int tri1 = checkDiagonal1(object, this);
                int tri2 = checkDiagonal2(object, this);
                checkTriangle(object, this, tri2, tri1);
                object.setPrev(this);
            }
            //System.out.println(this.getPosX() + "/ " + object.getPosX() + "/ " + object.getOriginWidth());
            //System.out.println("Stuff");
        }
        
    }
    
    public void setPrev(OverworldObj target){
        //System.out.println("Name this: " + this.getName() + " Name target: " + target.getName());
        if(triangle == 2){
            prevX = target.getPosX() + target.getWidth();
        } else if(triangle == 1){
            prevY = target.getPosY() - this.getHeight();
        } else if(triangle == 3){
            prevY = target.getPosY() + target.getHeight();
        } else if(triangle == 4){
            prevX = target.getPosX() - this.getWidth();
        }
        //System.out.println(prevX + " " + prevY);
    }
    
    public void setPrevY(int y){
        this.prevY = y;
    }
    
    private void setQuadrant(OverworldObj player, OverworldObj target){
        
        if(player.getActX() < target.getActX() && player.getActY() < target.getActY()){
            quadrant = 1; //Lower right
        } else if(player.getActX() > target.getActX() && player.getActY() < target.getActY()){
            quadrant = 2; //Lower left
        } else if(player.getActX() < target.getActX() && player.getActY() > target.getActY()){
            quadrant = 3; //Upper right
        } else if(player.getActX() > target.getActX() && player.getActY() > target.getActY()){
            quadrant = 4; //Upper left
        }
        //System.out.println("Quad: " + quadrant);
    }
    
    private int checkDiagonal1(OverworldObj player, OverworldObj target){
        double pointX = 0;
        double pointY = 0;
        double percent = 0;
        
        if(quadrant == 4){
            pointX = player.getPosX();
            pointY = player.getPosY();
        } else if(quadrant == 3){
            pointX = player.getPosX() + player.getWidth();
            pointY = player.getPosY();
        } else if(quadrant == 2){
            pointX = player.getPosX();
            pointY = player.getPosY() + player.getHeight();
        } else if(quadrant == 1){
            pointX = player.getPosX() + player.getWidth();
            pointY = player.getPosY() + player.getHeight();
        }
        
        //System.out.println("Pos playerX: " + player.getPosX() + "\nPos point: " + point + "\nPos target: " + target.getPosX() + "\nPos target w/width: " + (target.getPosX() + target.getWidth()) + "\nWidth target: " + target.getWidth());
        percent = (((double)target.getPosX() + (double)target.getWidth()) - pointX)/(double)target.getWidth();
        
        //System.out.println("Player Local X: " + (pointX - target.getPosX()));
        int comp = compareY(player, target, percent, pointY);
        
        return comp;
    }
    
    private int compareY(OverworldObj player, OverworldObj target, double percent, double point){
        int value = 0;
        
        double newHeight = ((double)target.getHeight() * percent);
        int newY = target.getPosY() + (int) newHeight;
        if(point <= newY){
            value = 0;
        } else{
            value = 1;
        }
        
        //System.out.println("Diagonal Y: " + newHeight);
        
        //System.out.println("Y: " + newY);
        //System.out.println("Y value: " + value);
        //if value = 0, left. If 1, right.
        return value;
    }
    
    private int checkDiagonal2(OverworldObj player, OverworldObj target){
        double pointX = 0;
        double pointY = 0;
        double percent = 0;
        
        if(quadrant == 4){
            pointX = player.getPosX();
            pointY = player.getPosY();
        } else if(quadrant == 3){
            pointX = player.getPosX() + player.getWidth();
            pointY = player.getPosY();
        } else if(quadrant == 2){
            pointX = player.getPosX();
            pointY = player.getPosY() + player.getHeight();
        } else if(quadrant == 1){
            pointX = player.getPosX() + player.getWidth();
            pointY = player.getPosY() + player.getHeight();
        }
        
        //System.out.println("Pos playerY: " + player.getPosY() + "\nPos point: " + point + "\nPos target: " + target.getPosY() + "\nPos target w/height: " + (target.getPosY() + target.getHeight()) + "\nHeight target: " + target.getHeight());
        percent = (((double)target.getPosY() + (double)target.getHeight()) - pointY)/(double)target.getHeight();
        //System.out.println("Player Local Y: " + (pointY - target.getPosY()));
        int comp = compareX(player, target, 1 - percent, pointX);
        
        return comp;
    }
    
    private int compareX(OverworldObj player, OverworldObj target, double percent, double point){
        int value = 0;
        
        double newWidth = ((double)target.getWidth() * percent);
        int newX = target.getPosX() + (int) newWidth;
        if(point <= newX){
            value = 0;
        } else{
            value = 1;
        }
        
        //System.out.println("Diagonal X: " + newWidth);
       
        //System.out.println("X: " + newX);
        //System.out.println("X Value: " + value);
        //if value = 0, left. If 1, right.
        return value;
    }
    
    public int checkTriangle(OverworldObj player, OverworldObj target, int diagonal1, int diagonal2){
        int tri = 0;
        
        if(diagonal1 == 1 && diagonal2 == 1){
            tri = 2;
            //prevX = target.getPosX() + target.getWidth();
        } else if(diagonal1 == 1 && diagonal2 == 0){
            tri = 1;
            //prevY = target.getPosY() + player.getHeight();
        } else if(diagonal1 == 0 && diagonal2 == 1){
            tri = 3;
            //prevY = target.getPosY() + target.getHeight();
        } else if(diagonal1 == 0 && diagonal2 == 0){
            tri = 4;
            //prevX = target.getPosX() - player.getWidth();
        } else{
            tri = 0;
        }
        //System.out.println(prevX + " " + prevY);
        player.triangle = tri;
        //System.out.println(tri);
        return tri;
    }
    
    public int getTriangle(){
        return triangle;
    }
    
    public boolean checkCollision(OverworldObj object){
        /*
        System.out.println(object.getName() + " pos: (" + object.getPosX() + ", " + object.getPosY() + ")");
        System.out.println(object.getName() + " unscaled pos: (" + object.getUnscaledX() + ", " + object.getUnscaledY() + ")");
        System.out.println(object.getName() + " size: (" + object.getWidth() + ", " + object.getHeight() + ")");
        System.out.println(object.getName() + " unscaled size: (" + object.getOriginWidth() + ", " + object.getOriginHeight() + ")");
        System.out.println(object.getName() + " this pos: (" + this.getPosX() + ", " + this.getPosY() + ")");
        System.out.println(object.getName() + " this unscaled pos: (" + this.getUnscaledX() + ", " + this.getUnscaledY() + ")");
        System.out.println(object.getName() + " this size: (" + this.getWidth() + ", " + this.getHeight() + ")");
        System.out.println(object.getName() + " this unscaled size: (" + this.getOriginWidth() + ", " + this.getOriginHeight() + ")");
        System.out.println("");
*/
        if(this.getPosX() <= (object.getUnscaledX() + object.getOriginWidth()) * GameState.getScale() &&
                this.getPosY() <= (object.getUnscaledY() + object.getOriginWidth()) * GameState.getScale() &&
                (this.getUnscaledX() + this.getOriginWidth()) * GameState.getScale() >= object.getPosX() &&
                (this.getUnscaledY() + this.getOriginHeight()) * GameState.getScale() >= object.getPosY() &&
                (this.getName() == null ? object.getName() != null : !this.getName().equals(object.getName()))){
            System.out.println("Collided");
            count++;
            System.out.println(count);
            this.setCollided(true);
            object.setCollided(true);
            return true;
        }
        else{
            return false;
        }
    }
    public int getPrevX(){
        return prevX;
    }
    public int getPrevY(){
        return prevY;
    }
    
}
