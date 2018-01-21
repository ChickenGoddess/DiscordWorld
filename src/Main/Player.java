package Main;


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
public class Player extends OverworldObj{
    
    ImageLoader load = new ImageLoader();
    ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();

    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void init() {
        BufferedImage louder = load.loadImage("../res/louder.png");
        width = louder.getWidth();
        height = louder.getHeight();
        origin_width = width;
        origin_height = height;
        sprites.add(louder);
        this.setSprite(louder);
        unscaledX = x;
        unscaledY = y;
    }

    @Override
    public void tick() {
        x += (velX * Game.getScale());
        y += (velY * Game.getScale());
        unscaledX += velX;
        unscaledY += velY;
        x = Game.clamp(x, Game.BACKGROUND_OFFSET_X, (int)((Game.CURRENTTEXTURE.sprite.getWidth() + Game.BACKGROUND_OFFSET_X - width)*Game.getScale()));
        y = Game.clamp(y, Game.BACKGROUND_OFFSET_Y, (int)((Game.CURRENTTEXTURE.sprite.getHeight() + Game.BACKGROUND_OFFSET_Y - height)*Game.getScale()));
    }

    @Override
    public void render(Graphics g) {
    //    g.setColor(Color.red);
    //    g.drawRect(x, y, 32, 32);
    }
    
}
