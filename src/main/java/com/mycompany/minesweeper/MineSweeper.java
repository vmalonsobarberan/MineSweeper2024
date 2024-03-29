/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.minesweeper;

import static com.mycompany.minesweeper.MineButton.BUTTON_SIZE;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author puntual
 */
public class MineSweeper extends javax.swing.JFrame {
    
    /**
     * Creates new form MineSweeper
     */
    public MineSweeper() {
        initComponents();
        board1.setFlagPanelInterface(flagPanel);
        board1.setTimerInterface(timerText1);
        resetGame();
        //setResizable(false);
        setPlayIcon();
        //pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        flagPanel = new com.mycompany.minesweeper.FlagPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jButtonPlay = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        timerText1 = new com.mycompany.minesweeper.TimerText();
        jPanel1 = new javax.swing.JPanel();
        board1 = new com.mycompany.minesweeper.Board();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setBackground(new java.awt.Color(204, 204, 255));
        jToolBar1.setRollover(true);
        jToolBar1.add(flagPanel);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Beginner", "Intermediate", "Difficult" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jComboBox1);
        jToolBar1.add(filler1);

        jButtonPlay.setFocusable(false);
        jButtonPlay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPlay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlayActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonPlay);
        jToolBar1.add(filler2);

        timerText1.setBackground(new java.awt.Color(0, 0, 0));
        timerText1.setForeground(new java.awt.Color(204, 0, 51));
        timerText1.setText("00:00");
        jToolBar1.add(timerText1);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        board1.setLayout(new java.awt.GridLayout(10, 10));
        getContentPane().add(board1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        setBounds(0, 0, 525, 330);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlayActionPerformed
        resetGame();

    }//GEN-LAST:event_jButtonPlayActionPerformed

    
    private void resetGame() {
        board1.removeAll();
           
        int numRows = ConfigData.getInstance().getNumRows();
        int numCols = ConfigData.getInstance().getNumCols();
        int width = numCols * MineButton.BUTTON_SIZE;
        int height = numRows * MineButton.BUTTON_SIZE;
        flagPanel.reset();
        timerText1.reset();
        board1.setLayout(new GridLayout(numRows, numCols));
        board1.initBoard();  
        board1.setPreferredSize(new Dimension(width, height));        
        board1.validate();
        pack();
        setLocationRelativeTo(null);
    }
    
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        switch (jComboBox1.getSelectedIndex()) {
            case 0:
                ConfigData.getInstance().setLevel(LevelType.BEGINNER);
                break;
            case 1:
                ConfigData.getInstance().setLevel(LevelType.INTERMEDIATE);
                break;
            case 2:
                ConfigData.getInstance().setLevel(LevelType.DIFFICULT);
                break;
            default:
                throw new AssertionError();
        }
        resetGame();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MineSweeper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MineSweeper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MineSweeper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MineSweeper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MineSweeper().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.minesweeper.Board board1;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private com.mycompany.minesweeper.FlagPanel flagPanel;
    private javax.swing.JButton jButtonPlay;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private com.mycompany.minesweeper.TimerText timerText1;
    // End of variables declaration//GEN-END:variables

    private void setPlayIcon() {
        Image image = new ImageIcon(getClass().getResource("/images/smiley.png")).getImage();
        Image newimg = image.getScaledInstance
                (50, 50,  java.awt.Image.SCALE_SMOOTH); 
        Icon icon = new ImageIcon(newimg);
        jButtonPlay.setIcon(icon);
    }
}
