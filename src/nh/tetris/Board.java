package nh.tetris;

public class Board
{
    private int width, height;
    private int[][] blocks;
    
    public Board(int w, int h) 
    {
        width = w;
        height = h;
        
        blocks = new int[width][height];
    }
    
    public void clear() 
    {
        for (int x = 0; x < width; x++) 
        {
            for (int y = 0; y < height; y++) 
            {
                setBlock(x, y, 0);
            }
        }
    }
    
    public Tetromino genNewTetromino(TetrominoType type) 
    {
        return new Tetromino(type, width / 2 - (int)Math.ceil(type.getSize() / 2.0), height - type.getTopOffset(0) - 1, 0);
    }
    
    public void lockTetromino(Tetromino tetromino) 
    {
        int[][] drawCurBlock = tetromino.getBlocks();
        for (int i = 0; i < drawCurBlock.length; i++) 
        {
            int x = drawCurBlock[i][0];
            int y = drawCurBlock[i][1];
            int val = drawCurBlock[i][2];
            
            setBlock(x, y, val);
        }
    }
    
    public boolean isTetrominoValid(Tetromino block) 
    {
        int[][] pts = block.getBlocks();
        
        for (int[] p : pts) 
        {
            if (!inBounds(p[0], p[1])) return false;
            
            if (getBlock(p[0], p[1]) != 0) return false;
        }
        
        return true;
    }
    
    public boolean isLineFull(int line) 
    {
        for (int x = 0; x < width; x++) 
        {
            if (getBlock(x, line) == 0) return false;
        }
        
        return true;
    }
    
    public void clearLine(int line) 
    {
        for (int x = 0; x < width; x++) 
        {
            setBlock(x, line, 0);
        }
        
        for (int y = line + 1; y < height; y++) 
        {
            for (int x = 0; x < width; x++) 
            {
                setBlock(x, y - 1, getBlock(x, y));
            }
        }
    }
    
    public int getBlock(int x, int y) 
    {
        return blocks[x][y];
    }
    
    public void setBlock(int x, int y, int id) 
    {
        if (inBounds(x, y)) blocks[x][y] = id;
    }
    
    public boolean inBounds(int x, int y) 
    {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
    
    public int getWidth() { return width; }
    
    public int getHeight() { return height; }
}
