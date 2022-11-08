package game.items;

import game.Handler;
import game.entities.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {
    private Handler handler;
    private Player player;



    private ArrayList<Item> items;

    public ItemManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        items = new ArrayList<Item>();
    }



    public void tick(){
        Iterator<Item> it = items.iterator();
        while(it.hasNext()){
            Item i = it.next();
            i.tick();
            if(i.getCount() == Item.PICKED_UP){ // neu so luong bang so am thi xoa no khoi list
                it.remove();
                if(i.id == 0){
                    handler.getWorld().getItemManager().getPlayer().setScore(100);
                }else if(i.id == 2){
                    handler.getWorld().getItemManager().getPlayer().healing(1);
                }else if(i.id == 1){
                    handler.getWorld().getEntityManager().getPlayer().setBullet_number(10);
                }else if(i.id==3) {
                	handler.getWorld().setNumberOfKey(-1);
                }
            }
        }
    }
    public void render(Graphics g){
        for(Item i:items){
            i.render(g);
        }

    }



    public void addItem(Item i){
        i.setHandler(handler);
        items.add(i);
    }
    // get and set
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
