package game.tiles;

import game.gfx.Assets;

public class BridgeTile extends Tile {

    public BridgeTile(int id) {
        super(Assets.bridge, id);
        solid = false;
    }
    
}
