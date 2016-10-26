
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
        sprites.add(louder);
        this.setSprite(louder);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        x = Game.clamp(x, 0, Game.WIDTH);
        y = Game.clamp(y, 0, Game.HEIGHT);
    }

    @Override
    public void render(Graphics g) {
    //    g.setColor(Color.red);
    //    g.drawRect(x, y, 32, 32);
    }
    
}
