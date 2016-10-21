
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chicken
 */
public class Window extends Canvas{
    
    public Window(String title, int height, int width, Game game){
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(height, width));
        frame.setMinimumSize(new Dimension(height, width));
        frame.setMaximumSize(new Dimension(height, width));
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setName(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        game.start();
    }
    
}
