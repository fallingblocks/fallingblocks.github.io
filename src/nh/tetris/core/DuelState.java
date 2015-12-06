package nh.tetris.core;

import java.awt.event.KeyEvent;

import nh.tetris.Controls;
import nh.tetris.Tetris;
import nh.tetris.gui.TetrisWindow;

public class DuelState extends GameState
{
    private TetrisWindow window;
    private Tetris tetris, tetris2;
    
    private Controls player1, player2;
    
    
    public DuelState(TetrisGame game, int id)
    {
        super(game, id);

        this.tetris = new Tetris();
        this.tetris2 = new Tetris();
        this.window = new TetrisWindow(tetris, tetris2);
        
        player1 = new Controls(window.getKeyboard());
        
        player1.up = KeyEvent.VK_W;
        player1.down = KeyEvent.VK_S;
        player1.left = KeyEvent.VK_A;
        player1.right = KeyEvent.VK_D;
        player1.hardDown = KeyEvent.VK_Q;
        player1.rotLeft = KeyEvent.VK_Z;
        player1.rotRight = KeyEvent.VK_X;
        player1.repeatWait = 20;
        player1.repeatDelay = 4;
        
        player2 = new Controls(window.getKeyboard());
        
        player2.up = KeyEvent.VK_UP;
        player2.down = KeyEvent.VK_DOWN;
        player2.left = KeyEvent.VK_LEFT;
        player2.right = KeyEvent.VK_RIGHT;
        player2.hardDown = KeyEvent.VK_END;
        player2.rotLeft = KeyEvent.VK_DELETE;
        player2.rotRight = KeyEvent.VK_PAGE_DOWN;
        player2.repeatWait = 20;
        player2.repeatDelay = 4;
    }

    @Override
    public void reset()
    {
        tetris.reset();
        
        tetris2.reset();
    }

    @Override
    public void update()
    {
        window.getKeyboard().update();
        
        player1.update(tetris);
        player2.update(tetris2);
        
        tetris.update();
        tetris2.update();
        
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
