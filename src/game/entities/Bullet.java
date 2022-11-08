package game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import game.Handler;
import game.entities.monsters.Monster;
import game.gfx.Assets;
import game.tiles.Tile;

public class Bullet extends Entity {
    
    public static final float[] BULLET_SPEED = {3.0f, 6.0f,6.0f};

    private Entity owner;
    private float X,Y;
    public Bullet(Handler handler, Entity owner, float x, float y) {
        super(handler, x, y, Entity.DEFAULT_CREATURE_WIDTH, Entity.DEFAULT_CREATURE_HEIGHT);
        level = this.handler.getGame().getLevel();
        this.owner = owner;
        X=x;
        Y=y;
        if(this.owner instanceof Player){
            speed = BULLET_SPEED[0];

        } else{
            speed = BULLET_SPEED[level];
       
        } 
        
        setMove();
        bounds.x = 3;
        bounds.y = 3;
        if(owner instanceof Player) {
        	bounds.width = 2*width / 3;
            bounds.height = 2*height / 3;
        }
        bounds.width = width / 2;
        bounds.height = height / 2;
    }
    public Bullet(Handler handler, Entity owner, float x, float y,int bullerDirect) {
        super(handler, x, y, Entity.DEFAULT_CREATURE_WIDTH, Entity.DEFAULT_CREATURE_HEIGHT);
        level = this.handler.getGame().getLevel();
        this.owner = owner;
        X=x;
        Y=y;
     
        if(bullerDirect ==1){
            xMove = speed;
            yMove = 0;
        } else if(bullerDirect ==2){
            xMove = -speed;
            yMove = 0;
        }
        else if(bullerDirect ==3){
            yMove = -speed;
            xMove = 0;
        }
        else if(bullerDirect ==4){
            yMove = speed;
            xMove = 0;
        }

        bounds.x = 3;
        bounds.y = 3;
        bounds.width = width / 2;
        bounds.height = height / 2;
    }
    @Override
    public void tick() {
        move();
        checkAttacks();
    }
    
    @Override
    public void render(Graphics g) {
    	if(this.owner instanceof Player) {
    	if(((int)(x)+(int)(y))%32 <8)
            g.drawImage(Assets.bullet[0], (int) (x-handler.getGame().getGameCamera().getxOffset()),
                    (int) (y-handler.getGame().getGameCamera().getyOffset()), 2*width/3 , 2*height/3, null);
        	else if(((int)(x-handler.getGame().getGameCamera().getxOffset())+(int)(y-handler.getGame().getGameCamera().getyOffset()))%32 <16)
        	g.drawImage(Assets.bullet[3], (int) (x-handler.getGame().getGameCamera().getxOffset()),
                    (int) (y-handler.getGame().getGameCamera().getyOffset()), 2*width/3 , 2*height/3, null);
        	else if(((int)(x-handler.getGame().getGameCamera().getxOffset())+(int)(y-handler.getGame().getGameCamera().getyOffset()))%32 <24)
            g.drawImage(Assets.bullet[2], (int) (x-handler.getGame().getGameCamera().getxOffset()),
                     (int) (y-handler.getGame().getGameCamera().getyOffset()), 2*width/3 , 2*height/3, null);
        	else  
            g.drawImage(Assets.bullet[1], (int) (x-handler.getGame().getGameCamera().getxOffset()),
                     (int) (y-handler.getGame().getGameCamera().getyOffset()), 2*width/3 , 2*height/3, null);
    	}
    	else {
    	if(((int)(x)+(int)(y))%32 <8)
        g.drawImage(Assets.bullet[0], (int) (x-handler.getGame().getGameCamera().getxOffset()),
                (int) (y-handler.getGame().getGameCamera().getyOffset()), width/2 , height/2, null);
    	else if(((int)(x-handler.getGame().getGameCamera().getxOffset())+(int)(y-handler.getGame().getGameCamera().getyOffset()))%32 <16)
    	g.drawImage(Assets.bullet[1], (int) (x-handler.getGame().getGameCamera().getxOffset()),
                (int) (y-handler.getGame().getGameCamera().getyOffset()), width/2 , height/2, null);
    	else if(((int)(x-handler.getGame().getGameCamera().getxOffset())+(int)(y-handler.getGame().getGameCamera().getyOffset()))%32 <24)
        g.drawImage(Assets.bullet[2], (int) (x-handler.getGame().getGameCamera().getxOffset()),
                 (int) (y-handler.getGame().getGameCamera().getyOffset()), width/2 , height/2, null);
    	else  
        g.drawImage(Assets.bullet[3], (int) (x-handler.getGame().getGameCamera().getxOffset()),
                 (int) (y-handler.getGame().getGameCamera().getyOffset()), width/2 , height/2, null);
    	}
    }
    
    public void checkAttacks() {
        Rectangle cb = getCollisionBounds(0, 0);
        if(this.owner instanceof Monster) {
            for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
                if(e.getCollisionBounds(0, 0).intersects(cb)){
                     if(e.equals(handler.getWorld().getEntityManager().getPlayer())) {
                    	e.setX(handler.getWorld().getSpawnX());
                    	e.setY(handler.getWorld().getSpawnY());
                        e.hurt(1);
                        this.setActive(false);
                        return;
                    }
                }
            }
        }else if(this.owner instanceof Player) {
            for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
                if(e.equals(this) || e.equals(this.owner))
                    continue;
                if(e.getCollisionBounds(0, 0).intersects(cb)){                                    
                        e.hurt(1);
                        this.setActive(false);
                        return;
                    }
                }
            }
        }
    
    @Override
    public void die()  {
        
    }
    
    @Override
    public void move() {
        moveX();
        moveY();
    }
    
    public void setMove() {
        // Kiem tra hướng cua player de khoi tao xMove va yMove
        
        if(owner.isRight()){
            xMove = speed;
            yMove = 0;
        } else if(owner.isLeft()){
            xMove = -speed;
            yMove = 0;
        }
        else if(owner.isUp()){
            yMove = -speed;
            xMove = 0;
        }
        else if(owner.isDown()){
            yMove = speed;
            xMove = 0;
        }
    }
    
    @Override
    public void moveX(){
    	if(Math.abs(X-x)>150) {
    		active=false;
    	}
    	else if(xMove > 0){ //Tiếp tục di chuyển sang phai nếu không chạm vào tile 
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            } else{ //Di chuyển đến sát bound của tile và biến mất 
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width + 1;
                active = false;
            }
        } else if (xMove < 0) { //Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x - 1;
                active = false;
            }
        }
    }

    @Override
    public void moveY(){
    	if(Math.abs(Y-y)>150) {
    		active=false;
    	}
    	else if(yMove < 0) {
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y + 1;
                active = false;

            }
        } else if(yMove > 0){
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
                active = false;

            }
        }
    }
    
    @Override
    public void setLevel(int level) {
        speed = BULLET_SPEED[level];
        this.level = level;
    }
}
