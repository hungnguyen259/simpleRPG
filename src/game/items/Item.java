package game.items;

import game.Handler;
import game.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    
    public static Item[] items = new Item[256];
    public static Item muoiOOP = new Item(Assets.item, "diamond", 0);
    public static Item bomb = new Item(Assets.bullet[0], "bomb", 1);
    public static Item blood = new Item(Assets.tym, "blood", 2);
    public static Item key = new Item(Assets.key, "key",3);
    // class
    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32, PICKED_UP = -1;
    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;
    protected int x, y, count;

    protected Rectangle bounds;


    public Item(BufferedImage texture, String name, int id){
        this.texture = texture;
        this.name = name;
        this.id = id;
        this.x=x;
        this.y=y;
        count = 1;
        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;

    }

    public void tick(){
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)){
            count = PICKED_UP;

        }


    }
    public void render(Graphics g){
        if(handler == null)
            return;
        render(g, (int) (x-handler.getGame().getGameCamera().getxOffset()),
                (int)(y-handler.getGame().getGameCamera().getyOffset()));
    }
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public Item createNew(int x, int y){
        Item i = new Item(texture, name, id);
        i.setPosition(x, y);
        return i;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }


    //get and set

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
