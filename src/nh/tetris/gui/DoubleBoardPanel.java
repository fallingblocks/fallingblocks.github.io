package nh.tetris.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import nh.tetris.Board;
import nh.tetris.Tetris;
import nh.tetris.Tetromino;
import nh.tetris.TetrominoType;

@SuppressWarnings("serial")
public class DoubleBoardPanel extends JPanel
{
    private Tetris tetris, tetris2;
    
    private int blockSize = 32;
    
    public DoubleBoardPanel(Tetris tetris, Tetris tetris2) 
    {
        this.tetris = tetris;
        this.tetris2 = tetris2;
        
        Board board = tetris.getBoard();
        
        Dimension size = new Dimension((blockSize * board.getWidth() + 200) * 2, blockSize * board.getHeight());
        
        this.setPreferredSize(size);
    }
    
    public void paintComponent(Graphics g) 
    {
        Board board = tetris.getBoard();
        Tetromino tetromino = tetris.getTetromino();
        
        Color back = new Color(0x7f7f7f);
        Color border = new Color(0x6F6F6F);
        
        int halfWidth = getWidth() / 2;
        
        g.setColor(border);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        for (int x = 0; x < board.getWidth(); x++) 
        {
            for (int y = 0; y < board.getHeight(); y++) 
            {
                int yy = board.getHeight() - y - 1;
                
                g.setColor(back);
                
                if (board.getBlock(x, y) != 0) g.setColor(getColor(board.getBlock(x, y)));
                
                g.fillRect(x * blockSize + 1, yy * blockSize + 1, blockSize - 2, blockSize - 2);
            }
        }
        
        int[][] drawCurBlock = tetris.getGhostTetromino().getBlocks();
        for (int i = 0; i < drawCurBlock.length; i++) 
        {
            g.setColor(border);
            
            int x = drawCurBlock[i][0];
            int y = board.getHeight() - 1 - drawCurBlock[i][1];
            
            g.fillRect(x * blockSize + 1, y * blockSize + 1, blockSize - 2, blockSize - 2);
        }
        
        drawCurBlock = tetromino.getBlocks();
        for (int i = 0; i < drawCurBlock.length; i++) 
        {
            g.setColor(getColor(drawCurBlock[i][2]));
            
            int x = drawCurBlock[i][0];
            int y = board.getHeight() - 1 - drawCurBlock[i][1];
            
            g.fillRect(x * blockSize + 1, y * blockSize + 1, blockSize - 2, blockSize - 2);
        }
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        g.drawString("Next Piece", getWidth() - 200 + 20 - halfWidth, 40);
        {
            TetrominoType next = tetris.getNextTetrominoType();
            
            for (int x = 0; x < 4; x++) 
            {
                for (int y = 0; y < 4; y++) 
                {
                    int xx = board.getWidth() + 1 + x;
                    int yy = 5 - y;
                    
                    g.setColor(back);
                    
                    g.fillRect(xx * blockSize + 1, yy * blockSize + 1, blockSize - 2, blockSize - 2);
                    
                    int val, size = next.getSize();
                    if (x < size && y < size && (val = next.getValue(x, y, 0)) != 0) 
                    {
                        g.setColor(getColor(val));
                        
                        g.fillRect(xx * blockSize + 1, yy * blockSize + 1, blockSize - 2, blockSize - 2);
                    }
                }
            }
        }
        
        g.setColor(Color.BLACK);
        g.drawString("Score", getWidth() - 200 + 20 - halfWidth, 200 + 40);
        g.drawString("" + tetris.getScore(), getWidth() - 200 + 20 - halfWidth, 200 + 60);
        
        g.drawString("Level " + tetris.getLevel(), getWidth() - 200 + 20 - halfWidth, 200 + 100);
        
        g.drawString("Lines", getWidth() - 200 + 20 - halfWidth, 200 + 140);
        g.drawString(tetris.getLinesForLevel() + " / " + tetris.getLineGoal(), getWidth() - 200 + 20 - halfWidth, 200 + 160);
        
        g.drawString(tetris.getTime(), getWidth() - 200 + 20 - halfWidth, 200 + 200);
        
        /*
         * ==============================
         * ==========Player 2============
         * ==============================
         */
        
        board = tetris2.getBoard();
        tetromino = tetris2.getTetromino();
        
        for (int x = 0; x < board.getWidth(); x++) 
        {
            for (int y = 0; y < board.getHeight(); y++) 
            {
                int yy = board.getHeight() - y - 1;
                
                g.setColor(back);
                
                if (board.getBlock(x, y) != 0) g.setColor(getColor(board.getBlock(x, y)));
                
                g.fillRect(halfWidth + x * blockSize + 1, yy * blockSize + 1, blockSize - 2, blockSize - 2);
            }
        }
        
        drawCurBlock = tetris2.getGhostTetromino().getBlocks();
        for (int i = 0; i < drawCurBlock.length; i++) 
        {
            g.setColor(border);
            
            int x = drawCurBlock[i][0];
            int y = board.getHeight() - 1 - drawCurBlock[i][1];
            
            g.fillRect(halfWidth + x * blockSize + 1, y * blockSize + 1, blockSize - 2, blockSize - 2);
        }
        
        drawCurBlock = tetromino.getBlocks();
        for (int i = 0; i < drawCurBlock.length; i++) 
        {
            g.setColor(getColor(drawCurBlock[i][2]));
            
            int x = drawCurBlock[i][0];
            int y = board.getHeight() - 1 - drawCurBlock[i][1];
            
            g.fillRect(halfWidth + x * blockSize + 1, y * blockSize + 1, blockSize - 2, blockSize - 2);
        }
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        g.drawString("Next Piece", getWidth() - 200 + 20, 40);
        {
            TetrominoType next = tetris2.getNextTetrominoType();
            
            for (int x = 0; x < 4; x++) 
            {
                for (int y = 0; y < 4; y++) 
                {
                    int xx = board.getWidth() + 1 + x;
                    int yy = 5 - y;
                    
                    g.setColor(back);
                    
                    g.fillRect(halfWidth + xx * blockSize + 1, yy * blockSize + 1, blockSize - 2, blockSize - 2);
                    
                    int val, size = next.getSize();
                    if (x < size && y < size && (val = next.getValue(x, y, 0)) != 0) 
                    {
                        g.setColor(getColor(val));
                        
                        g.fillRect(halfWidth + xx * blockSize + 1, yy * blockSize + 1, blockSize - 2, blockSize - 2);
                    }
                }
            }
        }
        
        g.setColor(Color.BLACK);
        g.drawString("Score", getWidth() - 200 + 20, 200 + 40);
        g.drawString("" + tetris2.getScore(), getWidth() - 200 + 20, 200 + 60);
        
        g.drawString("Level " + tetris2.getLevel(), getWidth() - 200 + 20, 200 + 100);
        
        g.drawString("Lines", getWidth() - 200 + 20, 200 + 140);
        g.drawString(tetris2.getLinesForLevel() + " / " + tetris2.getLineGoal(), getWidth() - 200 + 20, 200 + 160);
        
        g.drawString(tetris2.getTime(), getWidth() - 200 + 20, 200 + 200);
        
        if (tetris2.hasLostGame()) 
        {
            g.setColor(new Color(0x7FAFAFAF, true));
            
            g.fillRect(getWidth()/2, getHeight()/2 + 100, getWidth()/2, 100);
            
            g.setColor(new Color(0x000000));
            
            g.setFont(new Font("Tahoma", Font.PLAIN, 72));
            g.drawString("Finished Game", 3*getWidth()/4 - 235, getHeight()/2 + 180);
        }
        
        if (tetris.hasLostGame()) 
        {
            g.setColor(new Color(0x7FAFAFAF, true));
            
            g.fillRect(0, getHeight()/2 + 100, getWidth()/2, 100);
            
            g.setColor(new Color(0x000000));
            
            g.setFont(new Font("Tahoma", Font.PLAIN, 72));
            g.drawString("Finished Game", getWidth()/4 - 235, getHeight()/2 + 180);
        }
    }
    
    private Color getColor(int type) 
    {
        switch (type) 
        {
            case 1: return new Color(0x7FFFFF);
            case 2: return new Color(0x0000FF);
            case 3: return new Color(0xFF7F00);
            case 4: return new Color(0x5F1F8F);
            case 5: return new Color(0x00FF00);
            case 6: return new Color(0xBF1F1F);
            case 7: return new Color(0xFFFF00);
            default: return new Color(0x66BBFF);
        }
    }
}
