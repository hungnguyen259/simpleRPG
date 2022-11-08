package game.states;


import game.Handler;
import game.gfx.Assets;
import game.gfx.ImageLoader;
import game.ui.UIImageButton;
import game.ui.UIManager;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class PauseState extends State{
    private UIManager uiManager;
    public static BufferedImage continueGame= ImageLoader.loadImage("/textures/continue.png");
    public PauseState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        
        uiManager.addObject(new UIImageButton(144, 176, 512, 128, continueGame, () -> {
        	if(isActive) {
        	State.setState(handler.getGame().gameState);
            handler.getGame().gameState.setActive(true);         
            this.isActive=false;
        	}}));             
        
        uiManager.addObject(new UIImageButton(144, 304, 512, 128, Assets.exitGame, () ->{
        	if(isActive) {
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
		handler.getGame().gameState.render(g);
		g.setColor(Color.GRAY);
		
		uiManager.render(g);
	}


}