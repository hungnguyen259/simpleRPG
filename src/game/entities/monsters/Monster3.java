package game.entities.monsters;

import game.Handler;
import game.entities.Bullet;
import game.gfx.Animation;
import game.gfx.Assets;
import game.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Monster3 extends Monster{
    private long lastAttackTimer, attackCooldown = 1000, attackTimer = attackCooldown;
    private long lastCreateTimer, createCooldown = 10000, createTimer = attackCooldown;

    private static final int[] BONUS = {2000, 3000,5000};
    private static final int[] BOSS_HEALTH = {10,20,30};
    private float xPlayer, yPlayer;
    private final float xZ, yZ;

    private ArrayList<Bullet> bullets;
    private ArrayList<Monster2> children; 
    public Monster3(Handler handler, float x, float y) {
        super(handler, x, y);
        xZ=x;
        yZ=y;
        speed = ZOMBIE_SPEED[level]/4;
        xMove = 0;
        yMove = 0;
        health=BOSS_HEALTH[level];
        //Animation
        animDown = new Animation(200, Assets.boss_down);
        animUp = new Animation(200, Assets.boss_up);
        animLeft = new Animation(200, Assets.boss_left);
        animRight = new Animation(200, Assets.boss_right);

        currentImage = animDown;

        bullets = new ArrayList<Bullet>();
        children = new ArrayList<Monster2>();
    }

    @Override
    public void tick() {
        //Animation
        currentImage.tick();
        move();
        attack();
        create();
        Iterator<Bullet> it = bullets.iterator();
        while(it.hasNext()){
            Bullet b = it.next();
            b.setSpeed(Bullet.BULLET_SPEED[level]);
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

        float xx=x+width/4;
        float yy=y+height/4;
        bullets.add(new Bullet(handler,this, xx, yy,1));
        bullets.add(new Bullet(handler,this, xx, yy,2));
        bullets.add(new Bullet(handler,this, xx, yy,3));
        bullets.add(new Bullet(handler,this, xx, yy,4));
        attackTimer = 0;
    }

    public void create() {
    	if(handler.getGame().gameState.getWorld().getEntityManager().getEntities().size()==8) return;
        createTimer += System.currentTimeMillis() - lastCreateTimer;
        lastCreateTimer = System.currentTimeMillis();
        if(createTimer < createCooldown)
            return;
        xPlayer=handler.getWorld().getEntityManager().getPlayer().getX();
        yPlayer=handler.getWorld().getEntityManager().getPlayer().getY();
        if(Math.abs(yPlayer-y)>200  || Math.abs(xPlayer-x)>200 ) {
        	return;
        }
        Random rand = new Random();
        int i =rand.nextInt(2);
        if(i==0) 
        handler.getGame().gameState.getWorld().getEntityManager().getSubEntities().add(new Monster2(handler,x+width,y+height));
        //handler.getGame().gameState.getWorld().getEntityManager().getSubEntities().add(new Monster1(handler,x+width,y-height));}
        else
        {handler.getGame().gameState.getWorld().getEntityManager().getSubEntities().add(new Monster1(handler,x-width,y+height));
        //handler.getGame().gameState.getWorld().getEntityManager().getSubEntities().add(new Monster2(handler,x-width,y-height));
        }
        createTimer = 0;
    }
    public void die() {       
        handler.getWorld().getEntityManager().getPlayer().setScore(BONUS[level]);
        handler.getWorld().setNumberOfMonster(-1);    
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(currentImage.getCurrentFrame(), (int) (x-handler.getGame().getGameCamera().getxOffset()),
                (int) (y-handler.getGame().getGameCamera().getyOffset()), width, height, null);
        bullets.forEach((b) -> {
            b.render(g);
        });
      
    }
    public void setLevel(int level) {
        speed = ZOMBIE_SPEED[level];
        health = BOSS_HEALTH[level];
        this.level = level;
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

	public ArrayList<Monster2> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Monster2> children) {
		this.children = children;
	}
    
}
