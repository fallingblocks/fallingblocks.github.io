package nh.tetris;

public class TetrominoType
{
    public static final TetrominoType I = new TetrominoType(
            new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
            }, "I");
    
    public static final TetrominoType J = new TetrominoType(
            new int[][] {
                { 2, 0, 0 },
                { 2, 2, 2 },
                { 0, 0, 0 }
            }, "J");
    
    public static final TetrominoType L = new TetrominoType(
            new int[][] {
                { 0, 0, 0 },
                { 3, 3, 3 },
                { 3, 0, 0 }
            }, "L");
    
    public static final TetrominoType T = new TetrominoType(
            new int[][] {
                { 0, 0, 0 },
                { 4, 4, 4 },
                { 0, 4, 0 }
            }, "T");
    
    public static final TetrominoType S = new TetrominoType(
            new int[][] {
                { 0, 0, 0 },
                { 0, 5, 5 },
                { 5, 5, 0 }
            }, "S");
    
    public static final TetrominoType Z = new TetrominoType(
            new int[][] {
                { 0, 0, 0 },
                { 6, 6, 0 },
                { 0, 6, 6 }
            }, "Z");
    
    public static final TetrominoType O = new TetrominoType(
            new int[][] {
                { 0, 0, 0, 0 },
                { 0, 7, 7, 0 },
                { 0, 7, 7, 0 },
                { 0, 0, 0, 0 }
            }, "O");
    
    private int size;
    private int[][] matrix;
    
    private String name;
    
    /*
     * rotation (4), y, x
     */
    public TetrominoType(int[][] matrix, String name) 
    {
        this.name = name;
        
        this.matrix = matrix;
        size = matrix.length;
    }
    
    public String getName() { return name; }
    
    public int getSize() { return size; }
    
    public int getValue(int x, int y, int rot) 
    {
        int xx = getX(x, y, rot);
        int yy = getY(x, y, rot);
        
        return matrix[yy][xx];
    }
    
    public int getNumBlocks() 
    {
        int num = 0;
        
        for (int x = 0; x < getSize(); x++) 
            for (int y = 0; y < getSize(); y++) 
            {
                if (matrix[y][x] != 0) num++;
            }
        
        return num;
    }
    
    public int getLeftOffset(int rot) 
    {
        for (int x = 0; x < size; x++) 
        {
            for (int y = 0; y < size; y++) 
            {
                if (getValue(x, y, rot) != 0) return x;
            }
        }
        
        return size - 1;
    }
    
    public int getRightOffset(int rot) 
    {
        for (int x = size - 1; x >= 0; x--) 
        {
            for (int y = 0; y < size; y++) 
            {
                if (getValue(x, y, rot) != 0) return x;
            }
        }
        
        return 0;
    }
    
    public int getBottomOffset(int rot)
    {
        for (int y = 0; y < size; y++) 
        {
            for (int x = 0; x < size; x++) 
            {
                if (getValue(x, y, rot) != 0) return y;
            }
        }
        
        return 0;
    }
    
    public int getTopOffset(int rot)
    {
        for (int y = size - 1; y >= 0; y--) 
        {
            for (int x = 0; x < size; x++) 
            {
                if (getValue(x, y, rot) != 0) return y;
            }
        }
        
        return 0;
    }
    
    private int getX(int x, int y, int rot) 
    {
        switch (rot) 
        {
            case 0: return size - 1 - x;
            case 1: return y;
            case 2: return x;
            case 3: return size - 1 - y;
        }
        
        return -1;
    }
    
    private int getY(int x, int y, int rot) 
    {
        switch (rot) 
        {
            case 0: return y;
            case 1: return x;
            case 2: return size - 1 - y;
            case 3: return size - 1 - x;
        }
        
        return -1;
    }
}
