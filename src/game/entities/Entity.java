package game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import game.Handler;
import game.gfx.Animation;
import game.tiles.Tile;

public abstract class Entity {
    
    public static final int[] DEFAULT_HEALTH = {3, 5,7};

    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected int  health, level;
    protected boolean active = true;
    protected Rectangle bounds;
    public static final float DEFAULT_SPEED = 2.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 32, 
                            DEFAULT_CREATURE_HEIGHT = 32;
    protected float speed;
    protected float xMove, yMove;
    
    protected Animation animDown, animUp, animLeft, animRight;
    protected Animation currentImage;
    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.level = handler.getGame().getLevel();
        health = DEFAULT_HEALTH[level];
        bounds = new Rectangle(0, 0, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }
        
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    public abstract void die();
    
    public void hurt(int amt) {
        health -= amt;
        if(health <= 0) {
            active = false;
            die();
        }
    }
    public void move() {
        if(!checkEntityCollision(xMove, 0f))
            moveX();
        if(!checkEntityCollision(0f, yMove))
            moveY();
    }
    public void moveX(){
        if(xMove > 0){ //Sang phải 
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            } else{
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }
        } else if (xMove < 0) { // Sang trái 
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x + 1;
            }
        }
    }
    public void moveY(){
        if(yMove < 0) { // Lên trên 
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y + 1;
            }
        } else if(yMove > 0){ // Xuống dưới 
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }
    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x,y).isSolid();
    }
    
    public boolean checkEntityCollision(float xOffset, float yOffset) { 
        for(Entity e: handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this)){
                continue;
            }
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
                return true;
            }
        }
        return false;
    }
    
    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    public boolean isLeft() {
        return currentImage==animLeft;
    }
    
    public boolean isUp() {
        return currentImage==animUp;
    }
    
    public boolean isRight() {
        return currentImage==animRight;
    }
    
    public boolean isDown() {
        return currentImage==animDown;
    }
}
