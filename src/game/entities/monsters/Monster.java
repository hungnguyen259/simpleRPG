package game.entities.monsters;

import java.awt.Graphics;
import java.util.Random;

import game.Handler;
import game.entities.Entity;
import game.gfx.Animation;
import game.gfx.Assets;
import game.items.Item;

public class Monster extends Entity {
    
    protected static final int[] ZOMBIE_SPEED = {1, 1,2};
    private static final int[] BONUS = {50, 100, 150};
    protected Animation animDown, animUp, animLeft, animRight, currentImage;
    protected Item[] items = {Item.muoiOOP, Item.blood, Item.bomb};
    
    public Monster(Handler handler, float x, float y) {
        super(handler, x, y, Entity.DEFAULT_CREATURE_WIDTH, Entity.DEFAULT_CREATURE_HEIGHT);
        level = handler.getGame().getLevel();
        bounds.x = 3;
        bounds.y = 3;
        bounds.width = 26;
        bounds.height = 26;
        
        //Animation
        currentImage = new Animation(200, Assets.zombie_down);
    }
    
    @Override
    public void tick() {
        //Animation
        currentImage.tick();
        //Move
        move();
    }
    
    public void attack() {
    	
    }
    
    @Override
    public void die() {
        Random rand = new Random();
        handler.getWorld().getItemManager().addItem(items[rand.nextInt(3)].createNew((int) x, (int) y));
        handler.getWorld().getEntityManager().getPlayer().setScore(BONUS[level]);
        handler.getWorld().setNumberOfMonster(-1);    
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(currentImage.getCurrentFrame(), (int) (x-handler.getGame().getGameCamera().getxOffset()),
                (int)(y-handler.getGame().getGameCamera().getyOffset()) , width, height, null);
    }
    
    @Override
    public void move() {
    }
    
    @Override
    public void moveX(){
        
    }

    @Override
    public void moveY(){
        
    }
    
    @Override
    public void setLevel(int level) {
        speed = ZOMBIE_SPEED[level];
        health = DEFAULT_HEALTH[level];
        this.level = level;
    }
    
    @Override
    public boolean isLeft() {
        return currentImage==animLeft;
    }
    
    @Override
    public boolean isUp() {
        return currentImage==animUp;
    }
    
    @Override
    public boolean isRight() {
        return currentImage==animRight;
    }
    
    @Override
    public boolean isDown() {
        return currentImage==animDown;
    }
    
}
