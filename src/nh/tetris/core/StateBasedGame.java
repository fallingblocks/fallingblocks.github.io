package nh.tetris.core;

import java.util.HashMap;

public abstract class StateBasedGame
{
    private static final int SECOND = 1_000_000_000;
    
    private HashMap<Integer, GameState> states;
    private GameState currentState;
    private volatile boolean running;
    private Thread thread;
    
    private int upsGoal, fpsGoal;
    private int sleep, fps, ups, skipTicks, skipFrames;
    
    public StateBasedGame() 
    {
        states = new HashMap<>();
        
        running = false;
        
        setFPS(60);
        setUPS(60);
    }
    
    public abstract void init();
    
    public final int getFPS() { return fps; }
    public final int getUPS() { return ups; }
    public final int getSleep() { return sleep; }
    
    public final int getFPSGoal() { return fpsGoal; }
    public final int getUPSGoal() { return upsGoal; }
    
    public final void setFPS(int fps) 
    {
        fpsGoal = fps;
        
        skipFrames = SECOND / fps;
    }
    
    public final void setUPS(int ups) 
    {
        upsGoal = ups;
        
        skipTicks = SECOND / ups;
    }
    
    public final void setSleep(int millis) 
    {
        sleep = millis;
    }
    
    public final void addGameState(GameState state) 
    {
        states.put(state.getID(), state);
        
        state.reset();
    }
    
    public final GameState setGameState(int stateID) 
    {
//        System.out.println("Changing State to " + stateID);
        
        if (currentState != null) 
        {
            if (currentState.id != stateID) currentState.onLeave();
        }
        
        currentState = states.get(stateID);
        
        currentState.onJoin();
        
        return currentState;
    }
    
    public final GameState getCurrentGameState() 
    {
        return currentState;
    }
    
    public final synchronized void start() 
    {
        if (running) return;
        
        running = true;
        
        final StateBasedGame game = this;
        thread = new Thread("Game Loop") 
        {
            public void run() 
            {
                game.run();
            }
        };
        thread.start();
    }
    
    public final synchronized void stop() 
    {
        if (running) 
        {
            running = false;
            
            try
            {
                thread.join();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    private void run() 
    {
        init();
        
        long timer, tickTimer, frameTimer;
        
        timer = tickTimer = frameTimer = System.nanoTime();
        
        int frames = 0;
        int ticks = 0;
        
        while (running) 
        {
            int loops = 0;
            while (System.nanoTime() > tickTimer && loops++ < 10) 
            {
                ticks++;
                update();
                
                tickTimer += skipTicks;
            }
            
            if (System.nanoTime() > frameTimer) 
            {
                frames++;
                render();
                
                frameTimer += skipFrames;
            }
            
            try
            {
                Thread.sleep(sleep);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            
            if (System.nanoTime() - timer >= SECOND) 
            {
                timer = System.nanoTime();
                
                fps = frames;
                ups = ticks;
                
                frames = ticks = 0;
            }
        }
    }
    
    private void update() 
    {
        if (currentState != null) currentState.update();
    }
    
    private void render() 
    {
        if (currentState != null) currentState.render();
    }
}
