/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chicken
 */
public class GameCamera {
    private float xMovePos;
    private float yMovePos;
    
    public GameCamera(float xMovePos, float yMovePos){
        this.xMovePos = xMovePos;
        this.yMovePos = yMovePos;
    }
    
    public void move(float xAmt, float yAmt){
        xMovePos += xAmt;
        yMovePos += yAmt;
    }
    
    public float getXOffset(){
        return xMovePos;
    }
    
    public float getYOffset(){
        return yMovePos;
    }
}
