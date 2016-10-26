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
    private boolean following;
    
    public GameCamera(int x, int y){
        super(x, y, ID.Camera);
    }

    public GameCamera(GameObj target, boolean following) {
        super(0, 0, ID.Camera);
        this.target = target;
        this.following = following;
    }

    public GameCamera(GameObj target) {
        this(target, true);
    }

    @Override
    public void tick() {
        
        if (this.target != null && this.following) {
            this.setX(this.target.getPosX() - Game.WIDTH/2);
            this.setY(this.target.getPosY() - Game.HEIGHT/2);
        }
    }
    
    public GameObj getTarget() {
        return this.target;
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
