package nh.tetris;

public class Tetris
{
    private Board board;
    private Tetromino tetromino;
    private TetrominoType nextPiece;
    
    private int fallSpeed, lockSpeed;
    private int fallTimer, lockTimer;
    private boolean startLock;
    
    private int score;
    
    private int level, lineBase;
    private double levelMul;
    private int linesForLevel, lineGoal;
    
    private boolean lost;
    
    private BagGenerator tetroGen;
    
    private int timer;
    
    public Tetris() 
    {
        board = new Board(10, 21);

        reset();
    }
    
    public boolean hasLostGame() { return lost; }
    
    public String getTime() 
    {
        int tenths = (timer/10)%10;
        
        int seconds = (timer/100) % 60;
        
        int minutes = (timer/100/60) % 60;
        
        int hours = (timer/100/60/60);
        
        return String.format("%02d:%02d:%02d.%01d", hours, minutes, seconds, tenths);
    }
    
    public void reset() 
    {
        timer = 0;
        
        board.clear();
        
        tetroGen = new BagGenerator(System.nanoTime());
        
        lost = false;
        
        genTetromino();
        
        fallSpeed = 80;
        lockSpeed = 100;
        
        fallTimer = 0;
        
        startLock = false;
        
        level = -1;
        levelMul = 1.5;
        lineBase = 4;
        
        lost = false;
        
        score = 0;
        linesForLevel = 0;
        lineGoal = 0;
        
        updateLevel();
    }
    
    public Board getBoard() { return board; }
    
    public void updateLevel() 
    {
        while (linesForLevel >= lineGoal) 
        {
            level++;
            
//            linesForLevel -= lineGoal;
            
            lineGoal += lineBase + (int) (level * levelMul);
            
            /*
             * Level 20 = Fastest
             */
            fallSpeed = 80 - (4 * level); //80 / ((int) (1.5 * level + 1));
            
            if (fallSpeed < 0) fallSpeed = 0;
            
            addToScore(level * 500);
        }
    }
    
    public void checkLines() 
    {
        int lines = 0;
        
        for (int y = board.getHeight() - 1; y >= 0; y--) 
        {
            if (board.isLineFull(y)) 
            {
                lines++;
                board.clearLine(y);
            }
        }
        
        switch (lines) 
        {
            case 1: addToScore(40 * (level + 1)); break;
            case 2: addToScore(100 * (level + 1)); break;
            case 3: addToScore(300 * (level + 1)); break;
            case 4: addToScore(1200 * (level + 1)); break;
        }
        
        linesForLevel += lines;
    }
    
    public int getLinesForLevel() { return linesForLevel; }
    public int getLineGoal() { return lineGoal; }
    
    public void addToScore(int i)
    {
        score += i;
    }
    
    public int getScore() { return score; }
    
    public int getLevel() { return level; }

    public TetrominoType getNextTetrominoType() { return nextPiece; }
    
    public Tetromino getTetromino() { return tetromino; }
    
    public Tetromino getGhostTetromino() 
    {
        Tetromino t = tetromino.copy();
        
        while (board.isTetrominoValid(t)) 
        {
            t.move(0, -1);
        }
        
        t.move(0, 1);
        
        return t;
    }
    
    public Tetromino genTetromino(TetrominoType type) 
    {
        tetromino = board.genNewTetromino(type);
        
        if (!board.isTetrominoValid(tetromino)) lost = true;
        
        return tetromino;
    }
    
    public Tetromino genTetromino() 
    {
        return genTetromino(genTetrominoType());
    }
    
    public TetrominoType genTetrominoType() 
    {
        TetrominoType now = nextPiece;
        
        nextPiece = tetroGen.next();
        
//        int i = (int)(Math.random() * 7);
//        
//        switch (i) 
//        {
//            case 0: nextPiece = TetrominoType.I; break;
//            case 1: nextPiece = TetrominoType.L; break;
//            case 2: nextPiece = TetrominoType.J; break;
//            case 3: nextPiece = TetrominoType.T; break;
//            case 4: nextPiece = TetrominoType.O; break;
//            case 5: nextPiece = TetrominoType.S; break;
//            case 6: default: nextPiece = TetrominoType.Z; break;
//        }
        
        if (now == null) return genTetrominoType();
        
        return now;
    }
    
    public void update() 
    {
        if (lost) return;
        
        timer++;
        
        updateLevel();
        
        fallTimer++;
        
        startLock = !canMoveDown();
        
        if (canMoveDown()) 
        {
            startLock = false;
            lockTimer = 0;
        }
        
        if (startLock) 
        {
            fallTimer = 0;
            
            lockTimer++;
            
            if (lockTimer >= lockSpeed && !canMoveDown()) 
            {
                moveTetrominoDown(true);//lockTetromino();
                
                startLock = false;
            }
        }
        
        if (fallTimer >= fallSpeed && !startLock) 
        {
            moveTetrominoDown(true);
        }
        
        checkLines();
    }
    
    public void lockTetromino() 
    {
        board.lockTetromino(tetromino);
        
        genTetromino();
        
        lockTimer = 0;
    }
    
    public boolean canMoveDown() 
    {
        if (lost) return false;
        
        tetromino.move(0, -1);
        
        if (!board.isTetrominoValid(tetromino))
        {
            tetromino.move(0, 1);
            
            return false;
        }
        
        tetromino.move(0, 1);
        
        return true;
    }
    
    /*
     * returns if could move piece, if false, it locked
     */
    public boolean moveTetrominoDown(boolean countToScore) 
    {
        if (lost) return false;
        
        if (!moveTetromino(0, -1)) 
        {
            lockTetromino();
            addToScore(1);
            
            return false;
        }
        
        fallTimer = 0;
        
        return true;
    }
    
    public boolean moveTetrominoDownManually() 
    {
        if (lost) return false;
        
        if (!moveTetromino(0, -1)) 
        {
            lockTetromino();
            addToScore(2);
            
            return false;
        }
        
        fallTimer = 0;
        
        return true;
    }
    
    public void hardDropTetromino() 
    {
        if (lost) return;
        
        while (canMoveDown()) moveTetrominoDown(false);
        
        lockTetromino();
        
        addToScore(5);
    }
    
    public boolean moveTetromino(int dx, int dy) 
    {
        if (lost) return false;
        
        tetromino.move(dx, dy);
        
        if (!board.isTetrominoValid(tetromino))
        {
            tetromino.move(-dx, -dy);
            
            return false;
        }
        
        return true;
    }
    
    public void rotateTetromino(int rot) 
    {
        if (lost) return;
        
        int origLeftOff = tetromino.getLeftOffset();
        int origRightOff = tetromino.getRightOffset();
        int origBotOff = tetromino.getBottomOffset();
        int origTopOff = tetromino.getTopOffset();
        
        tetromino.rotate(rot);
        
        int leftOff = origLeftOff - tetromino.getLeftOffset();
        int rightOff = origRightOff - tetromino.getRightOffset();
        int botOff = origBotOff - tetromino.getBottomOffset();
        int topOff = origTopOff - tetromino.getTopOffset();
        
        /*
         * try offseting from left side
         */
        if (!board.isTetrominoValid(tetromino)) 
        {
            tetromino.move(leftOff, 0);
            
            /*
             * try offseting from right side
             */
            if (!board.isTetrominoValid(tetromino)) 
            {
                tetromino.move(-leftOff, 0);
                tetromino.move(rightOff, 0);
                
                /*
                 * try offseting from bottom
                 */
                if (!board.isTetrominoValid(tetromino)) 
                {
                    tetromino.move(-rightOff, 0);
                    tetromino.move(0, botOff);
                    
                    /*
                     * try offseting from top
                     */
                    if (!board.isTetrominoValid(tetromino)) 
                    {
                        tetromino.move(0, -botOff);
                        tetromino.move(0, topOff);
                        
                        /*
                         * put back to orig position
                         */
                        if (!board.isTetrominoValid(tetromino)) 
                        {
                            tetromino.move(0, -topOff);
                            
                            tetromino.rotate(-rot);
                        }
                    }
                }
            }
        }
    }
}
