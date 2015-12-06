package nh.tetris.core;

import nh.tetris.gui.TitleWindow;

public class TitleState extends GameState
{
    private TitleWindow window;
    
    public TitleState(TetrisGame game, int id)
    {
        super(game, id);
        
        window = new TitleWindow(this);
    }

    @Override
    public void reset()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void update()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void render()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onLeave()
    {
        window.setVisible(false);
    }

    @Override
    public void onJoin()
    {
        window.setVisible(true);
    }

    public void playSoloGame()
    {
        game.setGameState(TetrisGame.PLAY_STATE);
    }

    public void playDuelGame()
    {
        game.setGameState(TetrisGame.DUEL_STATE);
    }
}
