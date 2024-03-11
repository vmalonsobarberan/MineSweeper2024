/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package com.mycompany.minesweeper;

import static com.mycompany.minesweeper.LevelType.BEGINNER;
import static com.mycompany.minesweeper.LevelType.DIFFICULT;
import static com.mycompany.minesweeper.LevelType.INTERMEDIATE;

/**
 *
 * @author puntual
 */
public class ConfigData {
    
    public static final int NUM_ROWS_BEGINNER = 10;
    public static final int NUM_COLS_BEGINNER = 10;
    public static final int RATIO_BEGINNER = 10; // 10%
    
    public static final int NUM_ROWS_INTERMEDIATE = 16;
    public static final int NUM_COLS_INTERMEDIATE = 16;
    public static final int RATIO_INTERMEDIATE = 15; // 15%
    
    public static final int NUM_ROWS_DIFFICULT = 25;
    public static final int NUM_COLS_DIFFICULT = 25;
    public static final int RATIO_DIFFICULT = 20; // 15%
    
    
    private static ConfigData instance;
    
    private LevelType level;
    
    private ConfigData() {
        level = LevelType.BEGINNER;
    }
    
    public void setLevel(LevelType level) {
        this.level = level;
    }
    
    public static ConfigData getInstance() {
        if (instance == null) {
            instance = new ConfigData();
        }
        return instance;
    }
    
    public int getNumRows() {
        switch (level) {
            case BEGINNER:
                return NUM_ROWS_BEGINNER;
            case INTERMEDIATE:
                return NUM_ROWS_INTERMEDIATE;
            case DIFFICULT:
                return NUM_ROWS_DIFFICULT;
            default:
                throw new RuntimeException("Unknown Level");
        }
        
    }
    
    public int getNumCols() {
        switch (level) {
            case BEGINNER:
                return NUM_COLS_BEGINNER;
            case INTERMEDIATE:
                return NUM_COLS_INTERMEDIATE;
            case DIFFICULT:
                return NUM_COLS_DIFFICULT;
            default:
                throw new RuntimeException("Unknown Level");
        }
    }
    
    public int getNumBombs() {
        return getNumRows() * getNumCols() * getBombsRatio() / 100;
    }
    
    private int getBombsRatio() {
        switch (level) {
            case BEGINNER:
                return RATIO_BEGINNER;
            case INTERMEDIATE:
                return RATIO_INTERMEDIATE;
            case DIFFICULT:
                return RATIO_DIFFICULT;
            default:
                throw new RuntimeException("Unknown Level");
        }
        
    }
    
    
    
}
