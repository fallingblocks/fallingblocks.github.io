package nh.tetris.core;

public abstract class GameState
{
    protected final StateBasedGame game;
    protected final int id;
    
    public GameState(StateBasedGame game, int id) 
    {
        this.game = game;
        this.id = id;
    }
    
    public final StateBasedGame getGame() { return game; }
    
    public abstract void reset();
    
    public abstract void update();
    
    public abstract void render();
    
    public abstract void onLeave();
    
    public abstract void onJoin();
    
    public final int getID() { return id; }
}
