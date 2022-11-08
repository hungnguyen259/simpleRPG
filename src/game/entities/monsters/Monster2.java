package game.entities.monsters;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import game.Handler;
import game.entities.Bullet;
import game.gfx.Animation;
import game.gfx.Assets;
import game.tiles.Tile;

public class Monster2 extends Monster {    
    private long lastAttackTimer, attackCooldown = 1000, attackTimer = attackCooldown;
    private static final int[] BONUS = {200, 400,500};
    private static final float[] ZOMBIE_SPEED = {1, 1, 1.5f};
    private float xPlayer, yPlayer;
    private final float xZ, yZ;
    
    private ArrayList<Bullet> bullets;
    
    public Monster2(Handler handler, float x, float y) {
        super(handler, x, y);
        xZ=x;
        yZ=y;
        speed = ZOMBIE_SPEED[level];
        xMove = 0;
        yMove = 0;
  
        //Animation
        animDown = new Animation(200, Assets.monster_down);
        animUp = new Animation(200, Assets.monster_up);
        animLeft = new Animation(200, Assets.monster_left);
        animRight = new Animation(200, Assets.monster_right);
        
        currentImage = animDown;
        
        bullets = new ArrayList<Bullet>();
    }
    
    @Override
    public void tick() {
        //Animation
        currentImage.tick();
        move();
        attack();
        
        Iterator<Bullet> it = bullets.iterator();
        while(it.hasNext()){
            Bullet b = it.next();
            b.setSpeed(b.BULLET_SPEED[level]);
            b.tick();
            if(!b.isActive())
                it.remove();
        }
    }
    
    @Override
    public void attack() {
    	attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;
      
        int i=1;

        float xx=x+width/4;
        float yy=y+height/4;
        while(i>0) {
        	
        if(currentImage == animUp){
        	yy-=10;
        } else if(currentImage == animDown) {
        	yy+=10;
        }else if(currentImage == animLeft) {
        	xx-=10;
        }else if(currentImage == animRight) {
        	xx+=10;
        }
        bullets.add(new Bullet(handler,this, xx, yy));
        	i--;
        }
        attackTimer = 0;
    }
    
    
    @Override
    public void render(Graphics g) {
        g.drawImage(currentImage.getCurrentFrame(), (int) (x-handler.getGame().getGameCamera().getxOffset()),
                (int) (y-handler.getGame().getGameCamera().getyOffset()), width, height, null);
        bullets.forEach((b) -> {
            b.render(g);
        });
    }
    public void die() {
        Random rand = new Random();
        handler.getWorld().getItemManager().addItem(items[rand.nextInt(3)].createNew((int) x, (int) y));
        handler.getWorld().getEntityManager().getPlayer().setScore(BONUS[level]);
        handler.getWorld().setNumberOfMonster(-1);    
    }
    
    @Override
    public void move() {
    	xPlayer=handler.getWorld().getEntityManager().getPlayer().getX();
        yPlayer=handler.getWorld().getEntityManager().getPlayer().getY();
        if(Math.abs(yPlayer-y)>200  || Math.abs(xPlayer-x)>200 ) {
        	return;
        }
        if(y>yPlayer)
    		yMove=-speed;
    	else
    		yMove=+speed;
        if(x>xPlayer)
    		xMove=-speed;
    	else
    		xMove=+speed;
        //if(Math.abs(yPlayer-y)>Math.abs(xPlayer-x)) {
        	moveY();
       // }
        	
      //  else  {
        	
        	moveX();
      //  } 
       
    }
    
    @Override
    public void moveX(){
        if(xMove > 0){ //Tiếp tục di chuyển sang trái nếu không chạm vào tile 
            int tx = (int) (x + xMove + bounds.x + bounds.width) / (Tile.TILEWIDTH);
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
                currentImage=animRight;
            //} else{ //Di chuyển đến sát bound của tile và biến mất 
               // x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
               // xMove = -xMove;
               // moveY();
            }
        } else if (xMove < 0) { //Moving right
            int tx = (int) (x + xMove + bounds.x) / (Tile.TILEWIDTH);
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
                currentImage = animLeft;
            } //else {
               // x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
               // xMove = -xMove;
               // moveY();
           // }
        }
    }

    @Override
    public void moveY(){
        if(yMove < 0) {
            int ty = (int) (y + yMove + bounds.y) / (Tile.TILEHEIGHT);
            
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
                currentImage=animUp;
            } //else {
                //y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
               // yMove = -yMove;
               // moveX();
           // }
        } else if(yMove > 0){
            int ty = (int) (y + yMove + bounds.y + bounds.height) / (Tile.TILEHEIGHT);
            
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
                currentImage=animDown;
            } //else {
               // y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height;
               // yMove = -yMove;
               // moveX();
           // }
        }
    }
    public void setLevel(int level) {
        speed = ZOMBIE_SPEED[level];
        health = DEFAULT_HEALTH[level];
        this.level = level;
    }
}
