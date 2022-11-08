package game.tiles;

import game.gfx.Assets;

public class TreeTile extends Tile {

    public TreeTile(int id) {
        super(Assets.tree, id);
        solid = true;
    }
    
   
}
