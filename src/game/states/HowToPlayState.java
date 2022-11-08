package game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import game.Handler;
import game.gfx.Assets;
import game.gfx.ImageLoader;
import game.ui.UIImageButton;
import game.ui.UIManager;
import game.worlds.ScoreBoard;

public class HowToPlayState extends State{
	private UIManager uiManager;
	 public static BufferedImage tutorial= ImageLoader.loadImage("/textures/tutorial.png");
    public HowToPlayState(Handler handler) {
    	super(handler);
    	uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.addObject(new UIImageButton(336, 450, 128, 64, Assets.exitGame, () ->{
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
		 g.drawImage(Assets.backgroundMenu,0,0,800,608,null);
	     g.drawImage(tutorial,160,112,480,280,null);
	     uiManager.render(g);
	     
	}
}
