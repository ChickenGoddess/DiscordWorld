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
        super(x, y, id, "Player");
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
        if(x < 0){
            x = 0;
        }
        if(y < 0){
            y = 0;
        }
        if(x > Game.getTexture().getWidth() - this.getWidth()){
            x = Game.getTexture().getWidth() - this.getWidth();
        }
        if(y > Game.getTexture().getHeight() - this.getHeight()){
            y = Game.getTexture().getHeight() - this.getHeight();
        }
        /*
        if(this.getTriangle() == 1 && this.getCollided() == true){
            y -= (velY * Game.getScale());
            unscaledY -= velY;
            //System.out.println("1");
        }
        if(this.getTriangle() == 2 && this.getCollided() == true){
            x -= (velX * Game.getScale());
            unscaledX -= velX;
            //System.out.println("2");
        }
        if(this.getTriangle() == 3 && this.getCollided() == true){
            y -= (velY * Game.getScale());
            unscaledY -= velY;
            //System.out.println("3");
        }
        if(this.getTriangle() == 4 && this.getCollided() == true){
            x -= velX * Game.getScale();
            unscaledX -= velX;
            //System.out.println("4");
        }
*/
        //System.out.println(this.getHeight());
        //System.out.println(this.getWidth());
        //System.out.println(this.getPosX());
        //System.out.println(this.getPosY());
        //System.out.println(Game.getTexture().getHeight());
        //System.out.println(Game.getTexture().getWidth());
        if(unscaledX > Game.getTexture().getOriginWidth() - this.getOriginWidth()){
            unscaledX = Game.getTexture().getOriginWidth() - this.getOriginWidth();
        }
        if(unscaledY > Game.getTexture().getOriginHeight() - this.getOriginHeight()){
            unscaledY = Game.getTexture().getOriginHeight() - this.getOriginHeight();
        }
        if(unscaledX < 0){
            unscaledX = 0;
        }
        if(unscaledY < 0){
            unscaledY = 0;
        }
        //x = Game.clamp(x, Game.BACKGROUND_OFFSET_X, (int)((Game.CURRENTTEXTURE.sprite.getWidth() + Game.BACKGROUND_OFFSET_X - width)*Game.getScale()));
        //DSDdy = Game.clamp(y, Game.BACKGROUND_OFFSET_Y, (int)((Game.CURRENTTEXTURE.sprite.getHeight() + Game.BACKGROUND_OFFSET_Y - height)*Game.getScale()));
    }

    @Override
    public void render(Graphics g) {
    //    g.setColor(Color.red);
    //    g.drawRect(x, y, 32, 32);
    }
    
}
