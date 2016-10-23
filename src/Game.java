
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
    static boolean clamped = false;
    private static GameCamera camera;
    
    public Game(){
        Window window = new Window("Practice", WIDTH, HEIGHT, this);
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        handler.addObj(new BackgroundTexture(-100, -100, ID.Background));
        handler.addObj(new Player(200, 200, ID.Player));
        camera = new GameCamera(0,0);
        //bi = load.loadImage("C:\\Users\\Chicken\\Documents\\NetBeansProjects\\DiscordWorld\\testpic.PNG");
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
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
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
        handler.render(g);
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
            clamped = true;
            return var = min;
        }
        else if(var <= -max){
            clamped = true;
            return var = -max;
        }
        else{
            clamped = false;
            return var;
        }        
    }
    
    public static boolean isClamped(){
            return clamped;
    }
}
