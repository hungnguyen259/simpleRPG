package game.gfx;

import game.Handler;
import game.entities.Entity;
import game.tiles.Tile;

public class GameCamera {
    private Handler handler;
    private double xOffset;
    private double yOffset;

    public GameCamera(Handler handler, double xOffset, double yOffset) {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    public void checkBlankSpace(){
        if(xOffset<0){
            xOffset = 0;

        }else if(xOffset > handler.getWorld().getWidth()* Tile.TILEWIDTH - handler.getWidth()){
            xOffset = handler.getWorld().getWidth()* Tile.TILEWIDTH - handler.getWidth();
        }
        if(yOffset<0){
            yOffset = 0;
        }else if(yOffset > handler.getWorld().getHeight()*Tile.TILEHEIGHT - handler.getHeight()){
            yOffset = handler.getWorld().getHeight()*Tile.TILEHEIGHT - handler.getHeight();
        }
    }
    public void move(double xAmt, double yAmt){
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void centerOnEntity(Entity e){
        xOffset = e.getX() - handler.getGame().getWidth()*1.0/2 +e.getWidth()*1.0/2;
        yOffset = e.getY() - handler.getGame().getHeight()*1.0/2 + e.getHeight()*1.0/2;
        checkBlankSpace();
    }

    public double getxOffset() {
        return xOffset;
    }

    public void setxOffset(double xOffset) {
        this.xOffset = xOffset;
    }

    public double getyOffset() {
        return yOffset;
    }

    public void setyOffset(double yOffset) {
        this.yOffset = yOffset;
    }
}
