package game.worlds;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import game.entities.EntityManager;
import game.entities.Player;
import game.entities.monsters.Monster;
import game.entities.monsters.Monster1;
import game.entities.monsters.Monster2;
import game.entities.monsters.Monster3;
import game.items.Item;
import game.items.ItemManager;
import game.tiles.Tile;
import game.utils.Utils;

public class World {
    
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY, exitX, exitY;
    private int numberOfMonster, numberOfKey;
    private int[][] tile;
    private int[] monsters, keys;
    private EntityManager entityManager;
    public Rectangle bounds;
    private ItemManager itemManager;
    private Item  key=Item.key;
    
    public World(Handler handler, Player player, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler, player);
        itemManager = new ItemManager(handler, player);
        loadWorld(path);
        //Add monster
        for(int i = 0; i < monsters.length; i += 3) {
            
            if(monsters[i] == 1)
                entityManager.addEntity(new Monster(handler, monsters[i+1], monsters[i+2]));
            else if(monsters[i] == 2)
                entityManager.addEntity(new Monster1(handler, monsters[i+1], monsters[i+2]));
            else if(monsters[i] == 3)
                entityManager.addEntity(new Monster2(handler, monsters[i+1], monsters[i+2]));
            else if(monsters[i] == 4)
                entityManager.addEntity(new Monster3(handler, monsters[i+1], monsters[i+2]));
        }
        
        //Add Food
        for(int i = 0; i < keys.length; i += 2) {
          
                itemManager.addItem(key.createNew(keys[i], keys[i+1]));
           
        }

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);

    }
    
    public void tick() {
        itemManager.tick();
        entityManager.tick();
        ScoreBoard.tick();
    }



    public void render(Graphics g) {


        int xStart = (int) Math.max(0, handler.getGame().getGameCamera().getxOffset()/Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGame().getWidth() + handler.getGame().getGameCamera().getxOffset())/Tile.TILEWIDTH+1);
        int yStart = (int)Math.max(0, handler.getGame().getGameCamera().getyOffset()/Tile.TILEWIDTH);
        int yEnd = (int) Math.min(height, (handler.getGame().getHeight() + handler.getGame().getGameCamera().getyOffset())/Tile.TILEHEIGHT+1);

        for(int y = yStart; y < yEnd; y++) {
            for(int x = xStart; x < xEnd; x++) {
                getTile(x,y).render(g,(int) (x * Tile.TILEWIDTH - handler.getGame().getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT-handler.getGame().getGameCamera().getyOffset()));
            }
        }

        //Items
        itemManager.render(g);
        //Entities
        entityManager.render(g);
        
        //Score 
        
        ScoreBoard.render(g);
    }
    
    
    
    public Tile getTile(int x, int y) {
        
        return Tile.tiles[this.tile[x][y]];
    }
    
    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        exitX = Utils.parseInt(tokens[4]);
        exitY = Utils.parseInt(tokens[5]);

        bounds = new Rectangle(exitX, exitY, 32, 32);
        //Monster
        numberOfMonster = Utils.parseInt(tokens[6]);
        monsters = new int[numberOfMonster * 3];
        for (int i = 0; i < monsters.length; i++) {
                monsters[i] = Utils.parseInt(tokens[i + 7]);
        }
        
        //Food
        numberOfKey = Utils.parseInt(tokens[7 + monsters.length]);
        keys = new int[numberOfKey * 2];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = Utils.parseInt(tokens[i + 8 + monsters.length]);
        }
        
        //Tile
        tile = new int[width][height];
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++){
                tile[x][y] = Utils.parseInt(tokens[(x + y * width) + 8 + monsters.length + keys.length]);
            }
        }
    }

    //get and set
    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public int[][] getTiles() {
        return tile;
    }

    public int getNumberOfMonster() {
        return numberOfMonster;
    }

    public void setNumberOfMonster(int numberOfMonster) {
        this.numberOfMonster += numberOfMonster;
    }

	public int getNumberOfKey() {
		return numberOfKey;
	}

	public void setNumberOfKey(int numberOfKey) {
		this.numberOfKey += numberOfKey;
	}

	public int getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}
    
}
