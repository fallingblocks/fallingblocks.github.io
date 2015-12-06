package nh.tetris.core;


public class TetrisGame extends StateBasedGame
{
    public static final int PLAY_STATE = 0;
    public static final int TITLE_STATE = 1;
    public static final int DUEL_STATE = 2;
    
    public TetrisGame() 
    {
        super();
        
        setFPS(144);
        setUPS(100);
        setSleep(1);
        
        addGameState(new PlayState(this, PLAY_STATE));
        addGameState(new TitleState(this, TITLE_STATE));
        addGameState(new DuelState(this, DUEL_STATE));
    }

    @Override
    public void init()
    {
        setGameState(TITLE_STATE);
    }
    
    public static void main(String args[]) 
    {
        TetrisGame game = new TetrisGame();
        
        game.start();
    }
}
