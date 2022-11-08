package game;

import game.input.KeyManager;
import game.input.MouseManager;
import game.worlds.World;

public class Handler {
    
    private Game game;
    private World world;
    
    public Handler(Game game) {
        this.game = game;
    }
    
    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }
    
    public int getWidth() {
        return game.getWidth();
    }
    
    public int getHeight() {
        return game.getHeight();
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public Game getGame() {
        return game;
    }
    
}
