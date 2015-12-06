package nh.tetris.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nh.tetris.Tetris;

@SuppressWarnings("serial")
public class TetrisWindow extends JFrame
{
    private JPanel boardPanel;
    private Keyboard keys;
    
    public TetrisWindow(Tetris tetris) 
    {
        super("Falling Blocks");
        
        boardPanel = new BoardPanel(tetris);
        
        keys = new Keyboard();
        
//        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        this.setResizable(false);
        
        this.add(boardPanel);
        this.pack();
        
        this.setLocationRelativeTo(null);
        this.setVisible(false);
        
        this.addKeyListener(keys);
        
        this.requestFocus();
    }
    
    public TetrisWindow(Tetris tetris, Tetris tetris2)
    {
        super("Falling Blocks");
        
        boardPanel = new DoubleBoardPanel(tetris, tetris2);
        
        keys = new Keyboard();
        
//        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        this.setResizable(false);
        
        this.add(boardPanel);
        this.pack();
        
        this.setLocationRelativeTo(null);
        this.setVisible(false);
        
        this.addKeyListener(keys);
        
        this.requestFocus();
    }
    
    public void render() 
    {
        this.repaint();
    }
    
    public Keyboard getKeyboard() 
    {
        return keys;
    }
    
//    public void paintComponent(Graphics g) 
//    {
//        g.setColor(Color.yellow);
//        
//        g.drawString("FPS: " + game.getFPS(), 10, 20);
//        g.drawString("UPS: " + game.getUPS(), 10, 30);
//    }
}
