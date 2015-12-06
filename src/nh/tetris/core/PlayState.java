package nh.tetris.core;

import java.awt.event.KeyEvent;

import nh.tetris.Controls;
import nh.tetris.Tetris;
import nh.tetris.gui.TetrisWindow;

/*
 * Solo Mode
 */
public class PlayState extends GameState
{
    private TetrisWindow window;
    private Tetris tetris;
    
    private Controls player1;
    
    public PlayState(TetrisGame game, int id)
    {
        super(game, id);
        
        this.tetris = new Tetris();
        this.window = new TetrisWindow(tetris);
        
        player1 = new Controls(window.getKeyboard());
        
        player1.up = KeyEvent.VK_UP;
        player1.down = KeyEvent.VK_DOWN;
        player1.left = KeyEvent.VK_LEFT;
        player1.right = KeyEvent.VK_RIGHT;
        player1.hardDown = KeyEvent.VK_SPACE;
        player1.rotLeft = KeyEvent.VK_Z;
        player1.rotRight = KeyEvent.VK_X;
        player1.repeatWait = 20;
        player1.repeatDelay = 4;
    }

    @Override
    public void reset()
    {
        tetris.reset();
    }

    @Override
    public void update()
    {
        window.getKeyboard().update();
        
        player1.update(tetris);
        
        tetris.update();
        
        if (!window.isVisible()) 
        {
            reset();
            game.setGameState(TetrisGame.TITLE_STATE);
        }
    }

    @Override
    public void render()
    {
        window.render();
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
}
