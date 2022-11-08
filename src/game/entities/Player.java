package game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import game.Handler;
import game.gfx.Animation;
import game.gfx.Assets;
import game.states.ChooseCharacterState;
import game.states.PauseState;

public class Player extends Entity {
    //Attack timer
    private long lastAttackTimer, attackCooldown = 300, attackTimer = attackCooldown;
    //Bullet
    private ArrayList<Bullet> bullets;
    public static final int[] BULLET_NUMBER = {40,60,80};

    private static int bullet_number = 40;
    //Score
    public static long score = 0;
    private static int player_health=3;
    //image
    public static int characterNumber = 0 ;
    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Entity.DEFAULT_CREATURE_WIDTH, Entity.DEFAULT_CREATURE_HEIGHT);
   
        bounds.x = 3;
        bounds.y = 10;
        bounds.width = 26;
        bounds.height = 20;
  
        //Animation
        if(characterNumber==0) {
            animDown = new Animation(200, Assets.player1_down);
            animUp = new Animation(200, Assets.player1_up);
            animLeft = new Animation(200, Assets.player1_left);
            animRight = new Animation(200, Assets.player1_right);
            currentImage = animDown;
        }
        else if(characterNumber==1){
        	animDown = new Animation(200, Assets.player2_down);
            animUp = new Animation(200, Assets.player2_up);
            animLeft = new Animation(200, Assets.player2_left);
            animRight = new Animation(200, Assets.player2_right);
            currentImage = animDown;
        }
        //Bullets
        bullets = new ArrayList<Bullet>();
        
        
    }
    // hồi máu khi máu thấp hơn mặc định
    public void healing(int x){
        if(player_health<DEFAULT_HEALTH[level]){
        	player_health= player_health+x;
        }
    }
    @Override
    public void tick() {
        //Animation
        currentImage.tick();
        //Movement

        getInput();
        move();
        //Attack 
        attack();

        checkAttacks();
        //Bullet 
        Iterator<Bullet> it = bullets.iterator();
        while(it.hasNext()){
            Bullet b = it.next();
            b.tick();
            if(!b.isActive()){
                it.remove();
                //bullet_number--;
            }

        }
        handler.getGame().getGameCamera().centerOnEntity(this);
        
    }
    
    public void checkAttacks() {
        Rectangle cb = getCollisionBounds(0,0);
        Rectangle ar = new Rectangle();
        int arSize = 4;
        ar.width = arSize;
        ar.height = arSize;
        
        if(currentImage == animUp){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        } else if(currentImage == animDown) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        }else if(currentImage == animLeft) {
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize;
        }else if(currentImage == animRight) {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else
            return;
      
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0, 0).intersects(ar)){                
                	this.setX(handler.getWorld().getSpawnX());
                	this.setY(handler.getWorld().getSpawnY());
                    this.hurt(1);
                    return;
                }   
            }
        }
    
    public void attack() {

            if(handler.getKeyManager().space) {
            	if(bullet_number<=0) return;
                attackTimer += System.currentTimeMillis() - lastAttackTimer;
                lastAttackTimer = System.currentTimeMillis();
                if(attackTimer < attackCooldown)
                    return;
                bullet_number--;
                if(isUp()){
                    bullets.add(new Bullet(handler,this, x + width / 4, y));

                } else if(isDown()) {
                    bullets.add(new Bullet(handler,this, x + width /4, y + height));

                }else if(isLeft()) {
                    bullets.add(new Bullet(handler,this, x - width / 2, y + height / 4));

                }else if(isRight()) {
                    bullets.add(new Bullet(handler,this, x + width, y + height / 4));
                }
            }
            attackTimer = 0;
        
    }
    public void hurt(int amt) {
        player_health -= amt;
        if(player_health <= 0) {
            active = false;
            die();
        }
    }
    @Override
    public void die() {
    	handler.getGame().setDie_player(true);
        
    }
    
    private void getInput() {

        xMove = 0;
        yMove = 0;
        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
        if(handler.getKeyManager().up &&handler.getKeyManager().shift) 
        	yMove= -speed*(float)1.5;
        if(handler.getKeyManager().down &&handler.getKeyManager().shift) 
        	yMove= speed*(float)1.5;
        if(handler.getKeyManager().left &&handler.getKeyManager().shift) 
        	xMove= -speed*(float)1.5;
        if(handler.getKeyManager().right &&handler.getKeyManager().shift) 
        	xMove= speed*(float)1.5;


    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x-handler.getGame().getGameCamera().getxOffset()),
                (int) (y-handler.getGame().getGameCamera().getyOffset()), width, height, null);
        bullets.forEach((b) -> {
            b.render(g);
        });
    }
    
    private BufferedImage getCurrentAnimationFrame() {
        if(xMove < 0){
            currentImage = animLeft;
        } else if (xMove > 0) {
            currentImage = animRight;
        } else if (yMove < 0) {
            currentImage = animUp;
        } else if (yMove > 0) {
            currentImage = animDown;
        }
        return currentImage.getCurrentFrame();
    }

    public void setAnimation(){
        if (characterNumber == 0) {
        	animDown = new Animation(200, Assets.player1_down);
            animUp = new Animation(200, Assets.player1_up);
            animLeft = new Animation(200, Assets.player1_left);
            animRight = new Animation(200, Assets.player1_right);
            currentImage = animDown;
        }
        else if (characterNumber  == 1){
            animDown = new Animation(200, Assets.player2_down);
            animUp = new Animation(200, Assets.player2_up);
            animLeft = new Animation(200, Assets.player2_left);
            animRight = new Animation(200, Assets.player2_right);
            currentImage = animDown;
        }

    }



    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score += score;
    }
    

    public static int getBullet_number() {
        return bullet_number;
    }
    public void initBullet_number(int bullet_number) {
    	this.bullet_number = bullet_number;
    }
    public void setBullet_number(int bullet_number) {
        this.bullet_number += bullet_number;
    }
	public static int getPlayer_health() {
		return player_health;
	}
	public  void setPlayer_health(int player_health) {
		this.player_health = player_health;
	}

   
}