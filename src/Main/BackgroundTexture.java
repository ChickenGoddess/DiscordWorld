package Main;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chicken
 */
public class BackgroundTexture extends GameObj{

    private ImageLoader load = new ImageLoader();
    
    private boolean atSide = false;
    
    private BufferedImage bi;
    
    public BackgroundTexture(int x, int y, String name, String file) {
        super(x, y, ID.Background, name);
        bi = load.loadImage(file);
    }

    @Override
    public void init() {
        this.setSprite(bi); //Temp, probably.
        this.width = bi.getWidth();
        this.height = bi.getHeight();
        this.origin_width = width;
        this.origin_height = height;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(this.getSprite(), x, y, (int)(width * Game.getScale()), (int)(height * Game.getScale()), null);
    }
}

