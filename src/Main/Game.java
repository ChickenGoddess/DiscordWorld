package Main;


import EventHandledClasses.WindowChange;
import Rooms.Room;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    //BufferedImage bi;
    ImageLoader load = new ImageLoader();
    
    private boolean running = false;
    private Thread thread;
    //static int ORIGIN_WIDTH = 1000;
    //static int ORIGIN_HEIGHT = 700;
    //static int WIDTH = ORIGIN_WIDTH;
    //static int HEIGHT = ORIGIN_HEIGHT;
    //BufferedImage image;
    static boolean clampedSide = false;
    static boolean clampedUpdown = false;
    //private static GameCamera camera;
    //static BackgroundTexture CURRENTTEXTURE;
    static int BACKGROUND_OFFSET_X;
    static int BACKGROUND_OFFSET_Y;
    //Player player;
    //Obstacle fence;
    //Obstacle fence2;
    //static Window window;
    //private static double SCALE = 1.0000000000000000;
    //private static double TEMP = HEIGHT;
    private static BackgroundTexture texture;
    KeyInput input;
    //private static boolean BY_HEIGHT = true;
    
    private static volatile int initiated = 0;
    //.cor files are the game files
    //.sav files are the save files
    private static String file = "src/res/test.txt";
    private static volatile int sleeper = 0;
    
    public Game() throws FileNotFoundException{
        Scanner scan = new Scanner(new BufferedReader(new FileReader(file)));
        //Player player = new Player(0, 0, "../res/fence_tile.png");
        GameState.loadGame(scan, this);
        GameState.init();
        //setBackgroundOffset(0, 0);
        //Window window = new Window("Practice", 400, 400, this);
        //player = new Player(WIDTH/2, HEIGHT/2, ID.Player);
        //fence = new Obstacle(WIDTH/2, HEIGHT/2, "Fence", "../res/fence_tile.png");
        //fence2 = new Obstacle(WIDTH, HEIGHT, "Fence2", "../res/fence_tile.png");
        //spawnPlayer(400, 400);
        input = new KeyInput(Handler.instance());
        this.addKeyListener(input);
        //texture = new BackgroundTexture(BACKGROUND_OFFSET_X, BACKGROUND_OFFSET_Y, "Background_1", "../res/LARGE_elevation.jpg");
        //Handler.instance().addObj(texture);
        
        //Room room = new Room("Room 1", GameState.getPlayer(), texture);
        //GameState.addRoom(room);
        //GameState.setCurrentRoom(room);
        //room.setTexture(texture);
        //room.addObject(fence);
        //room.addObject(fence2);
        GameState.init();
        //GameState.spawnPlayer(400, 400);
        //room.spawnObj(fence, 600, 500);
        //room.spawnObj(fence2, 900, 800);
        //room.init();
        GameState.populateRoom();
        //Handler.instance().addObj(fence);
        //Handler.instance().addObj(fence2);
        //Handler.instance().addObj(player);
        //Handler.instance().addOverObj(fence);
        //Handler.instance().addOverObj(fence2);
        //Handler.instance().addOverObj(player);
        //camera = new GameCamera(player);
        //Handler.instance().setCamera(camera);
        //Handler.instance().addObj(camera);
        //CURRENTTEXTURE = texture;
        //bi = load.loadImage("C:\\Users\\Chicken\\Documents\\NetBeansProjects\\DiscordWorld\\testpic.PNG");
        //Handler.instance().init();
        initiated();
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
        try{
            Game game = new Game();
        }
        catch(FileNotFoundException e){
            System.out.println("Game instantiation didn't work");
        }
        
    }
    
    public static void initiated(){
        initiated = 1;
    }
    
    @Override
    public void run() {
        while(initiated == 0){
            //do nothing
            //System.out.print("");
        }
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
                if(GameState.getTexture() != (null)){
                    GameState.getCurrentRoom().getTexture().setWidth((int)(GameState.getTexture().getOriginWidth() * GameState.getScale()));
                    GameState.getCurrentRoom().getTexture().setHeight((int)(GameState.getTexture().getOriginHeight() * GameState.getScale()));
                    GameState.instance().getPlayer().setWidth((int)(GameState.instance().getPlayer().getOriginWidth() * GameState.getScale()));
                    GameState.instance().getPlayer().setHeight((int)(GameState.instance().getPlayer().getOriginHeight() * GameState.getScale()));
                }
            }
            input.scaleWindow();
            input.moveCharacter();
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                /*
                System.out.println("FPS: " + frames);
                System.out.println("SCALE: " + SCALE);
                System.out.println("PLAYER POSITION: " + player.getUnscaledX() + ", " + player.getUnscaledY());
                System.out.println("PLAYER ACTUAL POSITION: " + player.getPosX() + ", " + player.getPosY());
                System.out.println("CAMERA POSITION: " + camera.getUnscaledX() + ", " + camera.getUnscaledY());
                System.out.println("TEXTURE POSITION: " + texture.getUnscaledX() + ", " + texture.getUnscaledY());
                System.out.println("PLAYER WIDTH AND HEIGHT: " + player.getWidth() + ", " + player.getHeight());
                System.out.println("TEXTURE WIDTH AND HEIGHT: " + texture.getWidth() + ", " + texture.getHeight());
                */
                frames = 0;
            }
            try {
                Thread.sleep(16); //Pause thread for x milliseconds. May need to adjust this value later.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(initiated == 0){
                try{
                    Thread.sleep(sleeper);
                    initiated = 1;
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        stop();
    }

    public void tick(){
        Handler.instance().tick();
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
        Handler.instance().render(g, WIDTH, HEIGHT);
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
    
    
    /*
    public static int getFrameWidth(){
        return WIDTH;
    }
    public static int getFrameHeight(){
        return HEIGHT;
    }
    */
    
    
    
    public static BackgroundTexture getTexture(){
        return texture;
    }
    public static void setTexture(BackgroundTexture texture){
        Game.texture = texture;
    }
    
}
