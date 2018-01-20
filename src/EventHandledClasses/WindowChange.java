/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandledClasses;

import Main.Game;
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
            System.out.println("Hidden");
        }
        @Override
        public void componentMoved(ComponentEvent arg0){
            System.out.println("Moved");
        }

        @Override
        public void componentResized(ComponentEvent e) {
            prevWidth = Game.getFrameWidth();
            prevHeight = Game.getFrameHeight();
            Game.setWidth(frame.getWidth());
            Game.setHeight(frame.getHeight());
            System.out.println(prevWidth + ", " + prevHeight + ", " + Game.getFrameWidth() + ", " + Game.getFrameHeight());
            if(frame.getWidth() >= frame.getHeight()){
                temp = (frame.getHeight() - prevHeight)/prevHeight;
                System.out.println("Height: " + temp);
                //Game.setScale(WIDTH);
            } else{
                temp = (frame.getWidth() - prevWidth)/prevWidth;
                System.out.println("Width: " + temp);
            }
        }

        @Override
        public void componentShown(ComponentEvent e) {
            System.out.println("Shown");
        }
        
    }
}
