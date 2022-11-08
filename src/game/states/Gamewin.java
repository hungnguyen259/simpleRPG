package game.states;

import java.io.IOException;

import game.Game;
import game.Handler;
import game.gfx.Assets;
import game.gfx.ImageLoader;
import game.ui.UIImageButton;
import game.ui.UIManager;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Gamewin extends State{
	 private UIManager uiManager;
	 public Gamewin(Handler handler) {
	    	super(handler);
	        uiManager = new UIManager(handler);
	        handler.getMouseManager().setUiManager(uiManager);
	        uiManager.addObject(new UIImageButton(336, 550, 128, 32, Assets.exitGame, () ->{
	        	if(isActive) {
	        		handler.getGame().setWon(true);
	        		handler.getGame().menuState=new MenuState(handler);
	        		State.setState(handler.getGame().menuState);
	                handler.getGame().menuState.setActive(true);
	                this.isActive=false;
	        	}}));    
	        
    }

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.gameWin, 0, 0, 800, 608, null);
		g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString("Score "+ handler.getGame().getPlayer().getScore(), 300, 500);
        uiManager.render(g);
	}
}
