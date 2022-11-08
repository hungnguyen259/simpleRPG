package game.tiles;

import game.gfx.Assets;

public class DoorTile extends Tile {

    public DoorTile(int id) {
        super(Assets.door, id);
        solid = false;
    }
}