package game.states;

import java.awt.Graphics;
import game.Handler;
import game.entities.Player;
import game.worlds.World;

public class GameState extends State {
    private World world;
    private Player player;
    private PauseState pauseState;
    private boolean isActive=false;  
    public GameState(Handler handler, Player player, String path) {
        super(handler);
        this.player = player;
        world = new World(handler, player, path);
        handler.setWorld(world);
        isActive=false;
    }

    @Override
    public void tick() {
    	
    	if(isActive) {
            world.tick();
            
    	}
    	if(handler.getKeyManager().esc ){    
        	    		
            pauseState = new PauseState(handler);
            pauseState.setActive(true);
            State.setState(pauseState);
            }
  
    	
    }
    
    @Override
    public void render(Graphics g) {
        world.render(g);
        
    }

    public Player getPlayer() {
        return player;
    }
    
    public World getWorld() {
		return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }
}
