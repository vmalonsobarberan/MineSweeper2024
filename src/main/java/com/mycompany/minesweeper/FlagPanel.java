/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minesweeper;

import static com.mycompany.minesweeper.MineButton.BUTTON_SIZE;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author victor
 */
public class FlagPanel extends JPanel implements FlagPanelInterface {
    
    private JTextField textField;
    private int countFlags;
    
    public FlagPanel() {
        setLayout(new FlowLayout());
        Image image = new ImageIcon(getClass().getResource("/images/flag.png")).getImage();
        Image newimg = image.getScaledInstance
                (BUTTON_SIZE, BUTTON_SIZE,  java.awt.Image.SCALE_SMOOTH); 
        Icon icon = new ImageIcon(newimg);
        JLabel labelFlag = new JLabel(icon);
        add(labelFlag);
        countFlags = 0;
        textField = new JTextField("");
        textField.setBackground(Color.black);
        textField.setForeground(Color.red);
        reset();
        textField.setEditable(false);
        textField.setFocusable(false);
        textField.setPreferredSize(new Dimension(40, 30));
        add(textField);
    }
    
    public int getNumFlags() {
        return countFlags;
    }
    
    public void addOne() {
        countFlags++;
        textField.setText("" + countFlags);
    }
    
    public void subtractOne() {
        if (countFlags > 0) {
            countFlags --;
            textField.setText("" + countFlags);
        }
    }
    
    public void reset() {
        countFlags = ConfigData.getInstance().getNumBombs();
        textField.setText("" + countFlags);
    }
}
