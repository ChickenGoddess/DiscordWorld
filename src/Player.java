
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chicken
 */
public class Player extends GameObj{
    
    ImageLoader load = new ImageLoader();
    ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();

    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void init() {
        BufferedImage louder = load.loadImage("res/louder.png");
        width = louder.getWidth();
        height = louder.getHeight();
        sprites.add(louder);
        this.setSprite(louder);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;        
        
        x = Game.clamp(x, -100, Game.CURRENTTEXTURE.sprite.getWidth() - 100 - width);
        y = Game.clamp(y, -100, Game.CURRENTTEXTURE.sprite.getHeight() - 100 - height);
    }

    @Override
    public void render(Graphics g) {
    //    g.setColor(Color.red);
    //    g.drawRect(x, y, 32, 32);
    }
    
}
