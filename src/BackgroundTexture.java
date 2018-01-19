
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
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.getSprite(), x, y, null);
    }
}

