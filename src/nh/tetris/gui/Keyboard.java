package nh.tetris.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener
{
    private int[] keys;
    
    public Keyboard() 
    {
        keys = new int[1024];
        
        for (int i = 0; i < keys.length; i++) keys[i] = -1;
    }
    
    public void update() 
    {
        for (int i = 0; i < keys.length; i++) 
        {
            if (keys[i] != -1) keys[i]++;
        }
    }
    
    public int getKeyLength(int id) 
    {
        return keys[id];
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int id = e.getKeyCode();
        if (keys[id] == -1) keys[id] = 0;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int id = e.getKeyCode();
        keys[id] = -1;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        // TODO Auto-generated method stub
        
    }
}
