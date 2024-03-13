/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.minesweeper;

import static com.mycompany.minesweeper.MineButton.BUTTON_SIZE;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

/**
 *
 * @author puntual
 */
public class Board extends javax.swing.JPanel {
    
    private int [][] matrix;
    private MineButton[][] buttons;
    
    private static MouseAdapter mouseAdapter = null;
    
    private FlagPanelInterface flagPanelInterface;
    private TimerInterface timerInterface;
    private boolean firstTime;

    /**
     * Creates new form Board
     */
    public Board() {
        initComponents();
        //GridLayout g = new GridLayout(numRows, numCols);
        //g.removeLayoutComponent(this);
        mouseAdapter = new MouseAdapter() {
            @Override
                public void mouseClicked(MouseEvent e) {
                    MineButton b = (MineButton) e.getComponent();
                    
                    if (e.getButton() == MineButton.LEFT_BUTTON) {
                        if (b.getState() == ButtonState.CLOSED) {                            
                            openCell(b.getRow(), b.getCol());
                        }
                        
                    }
                    
                }
        };
    }
    
    public void setFlagPanelInterface(FlagPanelInterface flagPanelInterface) {
        this.flagPanelInterface = flagPanelInterface;
    }
    
    public void setTimerInterface(TimerInterface timerInterface) {
        this.timerInterface = timerInterface;
    }
    
    public void openCell(int row, int col) {
        if (!isValid(row, col)) return;
        MineButton button = buttons[row][col];
        if (button.getState() != ButtonState.CLOSED) return;
        button.open();
        if (firstTime) {
            timerInterface.reset();
            timerInterface.start();
            firstTime = false;
        }
        if (matrix[row][col] == 0) {
            for (int incRow = -1; incRow <= 1; incRow++) {
                for (int incCol = -1; incCol <= 1; incCol++) {
                    int newRow = row + incRow;
                    int newCol = col + incCol;
                    if (isValid(newRow, newCol)) {
                        MineButton newButton = buttons[newRow][newCol];
                        if (!(newRow == row && newCol == col) &&
                            (newButton.getState() != ButtonState.OPEN)) {
                                openCell(newRow, newCol);
                        
                        }
                    }
                    
                }
            }
        }

    }
    
    private Dimension getCellDimension() {
        return new Dimension(MineButton.BUTTON_SIZE, 
                                       MineButton.BUTTON_SIZE);        
    }
    
    public void initBoard() {
        firstTime =true;
        int numRows = ConfigData.getInstance().getNumRows();
        int numCols = ConfigData.getInstance().getNumCols();
        
        initMatrix(numRows, numCols);
        addBombs(numRows, numCols);
        addOneToCells(numRows, numCols);
        printMatrix(numRows, numCols);
        
        buttons = new MineButton[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                JPanel panel = new JPanel();
                panel.setPreferredSize(getCellDimension());
                panel.setLayout(new OverlayLayout(panel));
                               
                MineButton button = new MineButton(row, col);
                button.setFlagPanelInterface(flagPanelInterface);
                buttons[row][col] = button;
                button.addMouseListener(mouseAdapter);
                panel.add(button);
                
                JLabel label = getLabelFor(row, col);
                panel.add(label);
                
                
                add(panel);
            }
        }
        
    }
    
    private JLabel getLabelFor(int row, int col) {        
        final Color[] colors = {Color.BLACK, Color.BLUE, Color.GREEN.darker(), Color.RED,
            Color.CYAN, Color.MAGENTA, Color.ORANGE,  Color.YELLOW, Color.GRAY
        };
        JLabel label = new JLabel();
        label.setFont(new Font("Futura", Font.PLAIN, 16));
        //label.setOpaque(true);
        //label.setBackground(Color.red);
        label.setPreferredSize(getCellDimension());
        label.setMinimumSize(getCellDimension());
        label.setMaximumSize(getCellDimension());
        int number = matrix[row][col];
        if (number > 0) {
            label.setForeground(colors[number]);
            label.setText("" + number);           
        } else if (number == -1) {
            Image image   = new ImageIcon(getClass().getResource("/images/bomb.png")).getImage();
            Image newimg = image.getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, java.awt.Image.SCALE_SMOOTH); 
            Icon icon = new ImageIcon(newimg);
            label.setIcon(icon);
        }
        
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }
    
    public void initMatrix(int numRows, int numCols) {
        matrix = new int[numRows][numCols];
        for (int row = 0; row < numRows; row ++) {
            for (int col = 0; col < numCols; col ++) {
                matrix[row][col] = 0;
            }
        }
    }
    
    public void addBombs(int numRows, int numCols) {
        int numBombs = ConfigData.getInstance().getNumBombs();
        int bombsCounter = 0;
        while (bombsCounter < numBombs) {
            int row = (int) (Math.random() * numRows);
            int col = (int) (Math.random() * numCols);
            if (matrix[row][col] != -1) {
                matrix[row][col] = -1;
                bombsCounter ++;
            }
        }
    }
    
    
    public void addOneToCells(int numRows, int numCols) {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (matrix[row][col] == -1) {
                    addOneAround(row, col);
                }
            }
        }
    }
    
    public void addOneAround(int row, int col) {
        for (int incRow = -1; incRow <= 1; incRow++) {
            for (int incCol = -1; incCol <= 1; incCol++) {
                if (incRow != 0 || incCol != 0) {
                    int newRow = row + incRow;
                    int newCol = col + incCol;
                    if (isValid(newRow, newCol) && matrix[newRow][newCol] != - 1) {
                        matrix[newRow][newCol]++;
                    } 
                }
            }
        }
    }
    
    
    public void printMatrix(int numRows, int numCols) {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println("");
        }
        
    }
    
    public boolean isValid(int row, int col) {
        return row >= 0 && row < ConfigData.getInstance().getNumRows() &&
                col >= 0 && col < ConfigData.getInstance().getNumCols();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
