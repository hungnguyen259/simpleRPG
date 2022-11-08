package game.states;

import game.Handler;
import game.entities.Entity;
import game.entities.Player;
import game.gfx.Assets;
import game.ui.UIImageButton;
import game.ui.UIManager;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.Iterator;

public class ChooseCharacterState extends State {
    private UIManager uiManager;
    public ChooseCharacterState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        
        uiManager.addObject(new UIImageButton(100, 300, 300, 300, Assets.player1_down[1], () -> {
        	if(isActive) {
        		Player.characterNumber=0;
        		handler.getGame().getPlayer().setAnimation();
                handler.getGame().gameState.setActive(true);
                State.setState(handler.getGame().gameState);     
        	}}));             
        
        uiManager.addObject(new UIImageButton(400, 300, 300, 300, Assets.player2_down[1], () ->{
        	if(isActive) {
        		Player.characterNumber=1;
                handler.getGame().getPlayer().setAnimation();              
                handler.getGame().gameState.setActive(true);
                State.setState(handler.getGame().gameState);
        	}}));
        
    }
	@Override
	public void tick() {
		uiManager.tick();  
		
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.backgroundMenu,0,0,800,608,null);
		uiManager.render(g);
		g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString("Choose your character", 230, 250);
		
	}

}
