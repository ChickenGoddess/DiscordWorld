package Main;

import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chicken
 */
public class GameCamera extends GameObj {
    private GameObj target;
    private boolean followX;
    private boolean followY;
    
    public GameCamera(int x, int y){
        super(x, y, ID.Camera, "Camera");
    }

    public GameCamera(GameObj target, boolean following) {
        super(0, 0, ID.Camera, "Camera");
        this.target = target;
        this.followX = following;
        this.followY = following;
        height = Game.HEIGHT;
        width = Game.WIDTH;
        origin_width = width;
        origin_height = height;
    }

    public GameCamera(GameObj target) {
        this(target, true);
    }

    @Override
    public void tick() {
        
        
        if (this.target != null && this.followX && this.followY) {
            this.setX(this.target.getPosX() - Game.WIDTH/2 + this.target.getWidth()/2);
            this.setY(this.target.getPosY() - Game.HEIGHT/2 + this.target.getHeight()/2);
            //System.out.println("Camera working?");
        }
        else if(this.target != null && this.followX && !this.followY){
            this.setY(y);
            this.setX(this.target.getPosX() - Game.WIDTH/2 + this.target.getWidth()/2);
        }
        else if(this.target != null && !this.followX && this.followY){
            this.setX(x);
            this.setY(this.target.getPosY() - Game.HEIGHT/2 + this.target.getHeight()/2);
        }
        else{
            this.setX(x);
            this.setY(y);
        }
    }
    
    public GameObj getTarget() {
        return this.target;
    }
    
    public void setFollowing(boolean followX, boolean followY){
        this.followX = followX;
        this.followY = followY;
    }
    
    public void setFollowX(boolean x){
        this.followX = x;
    }
    
    public void setFollowY(boolean y){
        this.followY = y;
    }

    public void setTarget(GameObj target) {
        this.target = target;
    }

    @Override
    public void render(Graphics g){};
    
    @Override
    public void init(){ 
        this.setSprite(null);
    };
}
