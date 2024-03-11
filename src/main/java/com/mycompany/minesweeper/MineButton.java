/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minesweeper;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author puntual
 */
public class MineButton extends JButton {
    
    public static final int BUTTON_SIZE = 30;
    
    public static final int LEFT_BUTTON = 1;
    public static final int RIGHT_BUTTON = 3;
    
    private int row;
    private int col;
    private ButtonState state;
    
    private static Icon iconButton = null;
    private static Icon iconButtonPressed = null;
    
    private static MouseAdapter mouseAdapter = null;
 
    public MineButton(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        state = ButtonState.CLOSED;
        
        setMargin(new Insets(0, 0, 0, 0));
        setContentAreaFilled(false);
        setBorderPainted(false);
        if (mouseAdapter == null) {
            mouseAdapter = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    
                    if (e.getButton() == LEFT_BUTTON) {
                        MineButton b = (MineButton) e.getComponent();
                        b.setIcon(iconButtonPressed);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.getButton() == LEFT_BUTTON) { 
                        MineButton b = (MineButton) e.getComponent();
                        b.setIcon(iconButton);
                    } 
                }
                @Override
                public void mouseClicked(MouseEvent e) {
                    MineButton b = (MineButton) e.getComponent();                    
                    if (e.getButton() == RIGHT_BUTTON) {
                        b.changeState();
                    }
                    
                }
            };
        }
        setIcon();
        addMouseListener(mouseAdapter);
    }
    
    public void setState(ButtonState state) {
        this.state = state;
        repaint();
    }
    
    public ButtonState getState() {
        return state;
    }
    
    public void open() {
        state = ButtonState.OPEN;
        setVisible(false);
    }
    
    public void changeState() {
        if (state == ButtonState.CLOSED) {
            state = ButtonState.FLAG;
        } else if (state == ButtonState.FLAG) {
            state = ButtonState.QUESTION;
        } else if (state == ButtonState.QUESTION) {
            state = ButtonState.CLOSED;
        }
        repaint();    
    }
    
    public Icon getIcon(String path) {
        Image image = new ImageIcon(getClass().getResource(path)).getImage();
        Image newimg = image.getScaledInstance
                (BUTTON_SIZE, BUTTON_SIZE,  java.awt.Image.SCALE_SMOOTH); 
        Icon icon = new ImageIcon(newimg);
        return icon;
 
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (state == ButtonState.FLAG) {
            Icon flag = getIcon("/images/flag.png");
            flag.paintIcon(this, g, 0, 0);
        } else if (state == ButtonState.QUESTION) {
            Icon question = getIcon("/images/question.png");
            question.paintIcon(this, g, 0, 0);
        }
        
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    private void setIcon() {
        if (iconButton == null) {
            Image image   = new ImageIcon(getClass().getResource("/images/boton.jpg")).getImage();
            Image newimg = image.getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, java.awt.Image.SCALE_SMOOTH); 
            iconButton = new ImageIcon(newimg);
        }
        if (iconButtonPressed == null) {
            Image image   = new ImageIcon(getClass().getResource("/images/boton_pressed.jpg")).getImage();
            Image newimg = image.getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, java.awt.Image.SCALE_SMOOTH); 
            iconButtonPressed = new ImageIcon(newimg);
        }
        setIcon(iconButton);
    }
    
   /* private Image getImageForState() {
        System.out.println(state);
        if (state == ButtonState.FLAG) {
            return flagImage;
        } else if (state == ButtonState.QUESTION) {
           
            return questionImage;
            
        } else {
            return null;
        }
    } */
    
}
