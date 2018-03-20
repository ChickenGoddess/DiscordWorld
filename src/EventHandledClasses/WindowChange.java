/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandledClasses;

import Main.Game;
import Main.Handler;
import Main.Window;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
/**
 *
 * @author User
 */
public class WindowChange extends JPanel{
    JLabel display;
    JFrame frame;
    private int prevWidth;
    private int prevHeight;
    private float temp;
    public WindowChange(JFrame frame){
        display = new JLabel("---");
        this.frame = frame;
        
        frame.addComponentListener(new FrameListen());
        add(display);
        setBackground(Color.white);
    }
    
    private class FrameListen implements ComponentListener{
        @Override
        public void componentHidden(ComponentEvent arg0){
            //System.out.println("Hidden");
        }
        @Override
        public void componentMoved(ComponentEvent arg0){
            //System.out.println("Moved");
        }

        @Override
        public void componentResized(ComponentEvent e) {
            prevWidth = Game.getFrameWidth();
            prevHeight = Game.getFrameHeight();
            Game.setNoBorderWidth(Window.getFrame().getContentPane().getWidth());
            Game.setNoBorderHeight(Window.getFrame().getContentPane().getHeight());
            Game.setWidth(frame.getWidth());
            Game.setHeight(frame.getHeight());
            if(frame.getWidth() >= frame.getHeight()){
                Game.setTemp(Game.getFrameHeight());
                Game.setByHeight(true);
            } else{
                Game.setTemp(Game.getFrameWidth());
                Game.setByHeight(false);
            }
            
            
            Handler.changeScaled(true);
            Handler.setScaledLocation();
            if(Game.getTexture() != null){
                Handler.setBackgroundWidth();
                Handler.setBackgroundHeight();
                //System.out.println("Ayyo");
            }
            System.out.println("Changed");
        }

        @Override
        public void componentShown(ComponentEvent e) {
            //System.out.println("Shown");
        }
        
    }
}
