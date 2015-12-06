package nh.tetris;

import nh.tetris.gui.Keyboard;

public class Controls
{
    public int up, down, left, right, hardDown, rotLeft, rotRight;
    
    public int repeatDelay;
    public int repeatWait;
    
    private Keyboard keys;
    
    public Controls(Keyboard keys) 
    {
        this.keys = keys;
    }
    
    public void update(Tetris tetris) 
    {
        if (isKeyJustDown(up)) tetris.rotateTetromino(1);
        if (isKeyDown(down)) tetris.moveTetrominoDownManually();
        if (isKeyDown(right)) tetris.moveTetromino(1, 0);
        if (isKeyDown(left)) tetris.moveTetromino(-1, 0);
        
        if (isKeyJustDown(hardDown)) tetris.hardDropTetromino();
        
        if (isKeyJustDown(rotRight)) tetris.rotateTetromino(1);
        if (isKeyJustDown(rotLeft)) tetris.rotateTetromino(-1);
    }
    
    public boolean isKeyDown(int key) 
    {
        int time = keys.getKeyLength(key);
        
        return time == 1 || (time >= repeatWait && (time - repeatWait) % repeatDelay == 0);
    }
    
    public boolean isKeyJustDown(int key) 
    {
        int time = keys.getKeyLength(key);
        
        return time == 1;

    }
}
