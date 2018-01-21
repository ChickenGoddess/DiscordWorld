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
    static int ORIGIN_WIDTH = 620;
    static int ORIGIN_HEIGHT = 420;
    static int WIDTH = ORIGIN_WIDTH;
    static int HEIGHT = ORIGIN_HEIGHT;
    private Handler handler;
    BufferedImage image;
    static boolean clampedSide = false;
    static boolean clampedUpdown = false;
    private static GameCamera camera;
    static BackgroundTexture CURRENTTEXTURE;
    static int BACKGROUND_OFFSET_X;
    static int BACKGROUND_OFFSET_Y;
    Player player;
    Obstacle fence;
    static Window window;
    private static double SCALE = 1.0000000000000000;
    private static double TEMP = HEIGHT;
    private static double PREV_TEMP = HEIGHT;
    private static BackgroundTexture texture;
    KeyInput input;
    
    public Game(){
        setBackgroundOffset(0, 0);
        window = new Window("Practice", WIDTH, HEIGHT, this);
        player = new Player(WIDTH/2, HEIGHT/2, ID.Player);
        fence = new Obstacle(WIDTH/2, HEIGHT/2, "Fence");
        spawnPlayer(400, 400);
        setObstacle(70, 70, fence);
        handler = new Handler();
        input = new KeyInput(handler);
        this.addKeyListener(input);
        texture = new BackgroundTexture(BACKGROUND_OFFSET_X, BACKGROUND_OFFSET_Y, "Background_1");
        handler.addObj(texture);
        handler.addObj(player);
        handler.addObj(fence);
        handler.addOverObj(fence);
        handler.addOverObj(player);
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
    
    public void setObstacle(int x, int y, Obstacle obstacle){
        obstacle.setX(x);
        obstacle.setY(y);
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
            if(System.currentTimeMillis() - timer > 100){
                calculateScale();
                if(HEIGHT == ORIGIN_HEIGHT){
                    SCALE = 1.0;
                }
                if(HEIGHT >= WIDTH && WIDTH == ORIGIN_WIDTH){
                    SCALE = 1 + (ORIGIN_WIDTH - ORIGIN_HEIGHT)/(double)ORIGIN_HEIGHT;
                    TEMP = WIDTH;
                    PREV_TEMP = WIDTH;
                }
                if(WIDTH >= HEIGHT && HEIGHT == ORIGIN_HEIGHT*2){
                    SCALE = 2.0;
                    TEMP = ORIGIN_HEIGHT * 2;
                    PREV_TEMP = ORIGIN_HEIGHT * 2;
                }
                if(HEIGHT >= WIDTH && WIDTH == ORIGIN_HEIGHT * 2){
                    SCALE = 2.0;
                    TEMP = ORIGIN_HEIGHT * 2;
                    PREV_TEMP = ORIGIN_HEIGHT * 2;
                }
                if(WIDTH >= HEIGHT && (double) HEIGHT == (double)ORIGIN_HEIGHT*1.5){
                    SCALE = 1.5;
                    //System.out.println("SCALE @4: " + SCALE);
                    TEMP = ORIGIN_HEIGHT * 1.5;
                    PREV_TEMP = ORIGIN_HEIGHT * 1.5;
                    //System.out.println("SCALE AFTER 4: " + SCALE);
                }
                if(HEIGHT >= WIDTH && (double)WIDTH == (double)ORIGIN_HEIGHT * 1.5){
                    SCALE = 1.5;
                    TEMP = ORIGIN_HEIGHT * 1.5;
                    PREV_TEMP = ORIGIN_HEIGHT * 1.5;
                }
                if(WIDTH >= HEIGHT && (double)HEIGHT == (double)ORIGIN_HEIGHT*1.25){
                    SCALE = 1.25;
                    TEMP = ORIGIN_HEIGHT * 1.25;
                    PREV_TEMP = ORIGIN_HEIGHT * 1.25;
                }
                if(HEIGHT >= WIDTH && (double)WIDTH == (double)ORIGIN_HEIGHT * 1.25){
                    SCALE = 1.25;
                    TEMP = ORIGIN_HEIGHT * 1.25;
                    PREV_TEMP = ORIGIN_HEIGHT * 1.25;
                }
                if(WIDTH >= HEIGHT && (double)WIDTH == (double)ORIGIN_HEIGHT*1.75){
                    SCALE = 1.75;
                    TEMP = ORIGIN_HEIGHT * 1.75;
                    PREV_TEMP = ORIGIN_HEIGHT * 1.75;
                }
                if(HEIGHT >= WIDTH && (double)WIDTH == (double)ORIGIN_HEIGHT * 1.75){
                    SCALE = 3;
                    TEMP = ORIGIN_HEIGHT * 3;
                    PREV_TEMP = ORIGIN_HEIGHT * 3;
                }
                if(WIDTH >= HEIGHT && (double)WIDTH == (double)ORIGIN_HEIGHT*3){
                    SCALE = 3;
                    TEMP = ORIGIN_HEIGHT * 3;
                    PREV_TEMP = ORIGIN_HEIGHT * 3;
                }
                if(HEIGHT >= WIDTH && (double)WIDTH == (double)ORIGIN_HEIGHT * 3){
                    SCALE = 3;
                    TEMP = ORIGIN_HEIGHT * 3;
                    PREV_TEMP = ORIGIN_HEIGHT * 3;
                }
                if(WIDTH >= HEIGHT && (double)WIDTH == (double)ORIGIN_HEIGHT*4){
                    SCALE = 4;
                    TEMP = ORIGIN_HEIGHT * 4;
                    PREV_TEMP = ORIGIN_HEIGHT * 4;
                }
                if(HEIGHT >= WIDTH && (double)WIDTH == (double)ORIGIN_HEIGHT * 4){
                    SCALE = 4;
                    TEMP = ORIGIN_HEIGHT * 4;
                    PREV_TEMP = ORIGIN_HEIGHT * 4;
                }
                if(WIDTH >= HEIGHT && (double)WIDTH == (double)ORIGIN_HEIGHT*5){
                    SCALE = 5;
                    TEMP = ORIGIN_HEIGHT * 5;
                    PREV_TEMP = ORIGIN_HEIGHT * 5;
                }
                if(HEIGHT >= WIDTH && (double)WIDTH == (double)ORIGIN_HEIGHT * 5){
                    SCALE = 5;
                    TEMP = ORIGIN_HEIGHT * 5;
                    PREV_TEMP = ORIGIN_HEIGHT * 5;
                }
                if(WIDTH >= HEIGHT && (double)WIDTH == (double)ORIGIN_HEIGHT*2.5){
                    SCALE = 2.5;
                    TEMP = ORIGIN_HEIGHT * 2.5;
                    PREV_TEMP = ORIGIN_HEIGHT * 2.5;
                }
                if(HEIGHT >= WIDTH && (double)WIDTH == (double)ORIGIN_HEIGHT * 2.5){
                    SCALE = 2.5;
                    TEMP = ORIGIN_HEIGHT * 2.5;
                    PREV_TEMP = ORIGIN_HEIGHT * 2.5;
                }
                if(WIDTH >= HEIGHT && (double)WIDTH == (double)ORIGIN_HEIGHT*3.5){
                    SCALE = 3.5;
                    TEMP = ORIGIN_HEIGHT * 3.5;
                    PREV_TEMP = ORIGIN_HEIGHT * 3.5;
                }
                if(HEIGHT >= WIDTH && (double)WIDTH == (double)ORIGIN_HEIGHT * 3.5){
                    SCALE = 3.5;
                    TEMP = ORIGIN_HEIGHT * 3.5;
                    PREV_TEMP = ORIGIN_HEIGHT * 3.5;
                }
            }
            input.moveCharacter();
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                System.out.println("SCALE: " + SCALE);
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
    public static void setTemp(double temp){
        TEMP = temp;
    }
    public void calculateScale(){
        SCALE = SCALE + ((TEMP - PREV_TEMP)/PREV_TEMP);
        PREV_TEMP = TEMP;
        
    }
    public void setPrevTemp(double prevTemp){
        PREV_TEMP = prevTemp;
    }
    public static double getScale(){
        return SCALE;
    }
    public static BackgroundTexture getTexture(){
        return texture;
    }
}
