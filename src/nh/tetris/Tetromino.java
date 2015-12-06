package nh.tetris;

public class Tetromino
{
    private TetrominoType type;
    private int rot, x, y;
    
    public Tetromino(TetrominoType type, int x, int y, int rot) 
    {
        this.type = type;
        this.x = x;
        this.y = y;
        this.rot = rot;
    }
    
    public Tetromino copy() 
    {
        return new Tetromino(type, x, y, rot);
    }
    
    public TetrominoType getTetrominoType() { return type; }
    
    public int getX() { return x; }
    public int getY() { return y; }
    
    public int getRot() { return rot; }
    
    public void move(int dx, int dy) 
    {
        x += dx;
        y += dy;
    }
    
    public void rotate(int dRot) 
    { 
        rot += dRot; 
        rot %= 4;
        rot += 4; 
        rot %= 4;
    }
    
    public int getLeftOffset() 
    {
        return type.getLeftOffset(rot);
    }
    
    public int getRightOffset() 
    {
        return type.getRightOffset(rot);
    }
    
    public int getBottomOffset() 
    {
        return type.getBottomOffset(rot);
    }
    
    public int getTopOffset() 
    {
        return type.getTopOffset(rot);
    }
    
    public int[][] getBlocks() 
    {
        int[][] res = new int[type.getNumBlocks()][3];
        
        int i = 0, val;
        for (int x = 0; x < type.getSize(); x++) 
        {
            for (int y = 0; y < type.getSize(); y++) 
            {
                if ((val = type.getValue(x, y, rot)) != 0) 
                {
                    res[i][0] = this.x + x;
                    res[i][1] = this.y + y;
                    res[i][2] = val;
                    
                    i++;
                }
            }
        }
        
        return res;
    }
}
