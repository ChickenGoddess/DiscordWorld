/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Owner
 */
public class TextPane{

    private String text;
    private JLabel label;
    private int charIndex = 0;

    public TextPane(String s) {
        text = s;
        label = new JLabel();
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String labelText = label.getText();
                labelText += text.charAt(charIndex);
                label.setText(labelText);
                charIndex++;
                if (charIndex >= text.length()) {
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
}    

