/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author User
 */
public class Obstacle extends OverworldObj{

    ImageLoader load = new ImageLoader();
    
    public Obstacle(int x, int y) {
        super(x, y, ID.Obstacle);
    }

    @Override
    public void init() {
        BufferedImage louder = load.loadImage("../res/fence_tile.png");
        width = louder.getWidth();
        height = louder.getHeight();
        origin_width = width;
        origin_height = height;
        this.setSprite(louder);
        unscaledX = x;
        unscaledY = y;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        
    }
    
}
