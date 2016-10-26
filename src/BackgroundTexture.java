
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chicken
 */
public class BackgroundTexture extends OverworldObj{

    private ImageLoader load = new ImageLoader();
    
    private boolean atSide = false;
    
    public BackgroundTexture(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void init() {
        this.setSprite(load.loadImage("res/testpic.png")); //Temp, probably.
    }

    @Override
    public void tick() {
        
        x -= velX;
        y -= velY;
        
        x = Game.reverseBackgroundClamp(x, 0, load.getWidth(this.getSprite()) - Game.WIDTH);
        if(x >= 0 || x <= -(load.getWidth(this.getSprite()) - Game.WIDTH)){
            Game.clampedSide = true;
        }
        else{
            Game.clampedSide = false;
        }
        y = Game.reverseBackgroundClamp(y, 0, load.getHeight(this.getSprite()) - Game.HEIGHT);
        if(y >= 0 || y <= -(load.getHeight(this.getSprite()) - Game.HEIGHT)){
            Game.clampedUpdown = true;
        }
        else{
            Game.clampedUpdown = false;
        }
        System.out.println("Side clamp: " + Game.clampedSide);
        System.out.println("Vertical clamp: " + Game.clampedUpdown);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.getSprite(), x, y, null);
    }
}

