package Main;


import EventHandledClasses.WindowChange;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chicken
 */
public class Game extends Canvas implements Runnable{
    
    BufferedImage bi;
    ImageLoader load = new ImageLoader();
    
    
    
    private boolean running = false;
    private Thread thread;
    static int WIDTH = 620;
    static int HEIGHT = 420;
    private Handler handler;
    BufferedImage image;
    static boolean clampedSide = false;
    static boolean clampedUpdown = false;
    private static GameCamera camera;
    static BackgroundTexture CURRENTTEXTURE;
    static int BACKGROUND_OFFSET_X;
    static int BACKGROUND_OFFSET_Y;
    Player player;
    static Window window;
    private static double SCALE = 1.00;
    
    public Game(){
        setBackgroundOffset(0, 0);
        window = new Window("Practice", WIDTH, HEIGHT, this);
        player = new Player(WIDTH/2, HEIGHT/2, ID.Player);
        spawnPlayer(400, 400);
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        BackgroundTexture texture = new BackgroundTexture(BACKGROUND_OFFSET_X, BACKGROUND_OFFSET_Y, ID.Background);
        System.out.println(texture.getHeight());
        handler.addObj(texture);
        handler.addObj(player);
        camera = new GameCamera(player);
        handler.setCamera(camera);
        handler.addObj(camera);
        CURRENTTEXTURE = texture;
        //bi = load.loadImage("C:\\Users\\Chicken\\Documents\\NetBeansProjects\\DiscordWorld\\testpic.PNG");
        handler.init();
    }
    
    void spawnPlayer(int x, int y){
        player.setX(x);
        player.setY(y);
    }
    
    void setBackgroundOffset(int x, int y){
        BACKGROUND_OFFSET_X = x;
        BACKGROUND_OFFSET_Y = y;
    }
    
    public synchronized void start(){
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        
        Game game = new Game();
        
    }
    
    @Override
    public void run() {
        double lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        double timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            while(delta >= 1){
                tick();
                delta--;
            }
            lastTime = System.nanoTime();
            if(running){
                render();
            }
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
            try {
                Thread.sleep(16); //Pause thread for x milliseconds. May need to adjust this value later.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stop();
    }

    public void tick(){
        handler.tick();
    }
    
    
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            requestFocus();
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        //g.drawImage(bi, 0, 0, null);
        handler.render(g, WIDTH, HEIGHT);
        g.dispose();
        bs.show();
    }  
    
    
    public static int clamp(int var, int min, int max){
        
        if(var >= max){
            return var = max;
        }
        else if(var <= min){
            return var = min;
        }
        else{
            return var;
        }
        
    }
    
    public static int reverseBackgroundClamp(int var, int min, int max){
        
        if(var >= min){
            return var = min;
        }
        else if(var <= -max){
            return var = -max;
        }
        else{
            return var;
        }        
    }
    
    public static boolean isClampedSide(){
        return clampedSide;
    }
    
    /**
     * 
     * @return whether clamp is in effect
     */
    public static boolean isClampedUpdown(){
        return clampedUpdown;
    }
    
    public static void setWidth(int width){
        WIDTH = width;
    }
    
    public static void setHeight(int height){
        HEIGHT = height;
    }
    
    public static int getFrameWidth(){
        return WIDTH;
    }
    public static int getFrameHeight(){
        return HEIGHT;
    }
    public static void setScale(double scale){
        SCALE = scale;
    }
    public double getScale(){
        return SCALE;
    }
}
