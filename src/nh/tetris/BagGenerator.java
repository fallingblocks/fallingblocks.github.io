package nh.tetris;

import java.util.Random;

public class BagGenerator implements TetrominoGenerator
{
    private int index;
    private int[] next;
    
    private Random random;
    
    public BagGenerator(long seed) 
    {
        random = new Random(seed);
        
        next = new int[7];
        
        index = 0;
        
        genNextBag();
    }
    
    private void genNextBag() 
    {
        for (int i = 0; i < next.length; i++) 
        {
            next[i] = i % 7;
        }
        
        for (int i = 0; i < next.length; i++) 
        {
            int j = random.nextInt(next.length);
            
            int tmp = next[i];
            
            next[i] = next[j];
            next[j] = tmp;
        }
    }
    
    public void printBag() 
    {
        for (int i = 0; i < next.length; i++) 
        {
            System.out.print(getTetrominoType(next[i]).getName());
            
            if (i != next.length - 1) System.out.print(", ");
        }
        
        System.out.println();
    }

    @Override
    public TetrominoType next()
    {
        TetrominoType type = getTetrominoType(next[index]);
        
        index++;
        
        if (index >= next.length) 
        {
            index = 0;
            genNextBag();
        }
        
        return type;
    }
    
    private TetrominoType getTetrominoType(int i) 
    {
        switch (i) 
        {
            case 0: return TetrominoType.I;
            case 1: return TetrominoType.L;
            case 2: return TetrominoType.J;
            case 3: return TetrominoType.T;
            case 4: return TetrominoType.O;
            case 5: return TetrominoType.S;
            case 6: default: return TetrominoType.Z;
        }
    }
}
