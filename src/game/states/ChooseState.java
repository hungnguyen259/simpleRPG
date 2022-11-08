package game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import game.Handler;
import game.entities.Entity;
import game.gfx.Assets;
import game.ui.UIImageButton;
import game.ui.UIManager;

public class ChooseState extends State{
	private UIManager uiManager;
    private ChooseCharacterState a;
    public ChooseState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        
        uiManager.addObject(new UIImageButton(100, 200, 600, 128, Assets.easy, () -> {
        	if(isActive) {
        		handler.getGame().setLevel(0);
                Iterator<Entity> it = this.handler.getWorld().getEntityManager().getEntities().iterator();
                while(it.hasNext()){
                    Entity entity = it.next();
                    entity.setLevel(0);              
                }
                a = new ChooseCharacterState(handler);
                a.setActive(true);
                State.setState(a);
                ;
        	}}));
        
        uiManager.addObject(new UIImageButton(100, 328, 600, 128, Assets.normal, () ->{
        	if(isActive) {
        		handler.getGame().setLevel(1);
                Iterator<Entity> it = this.handler.getWorld().getEntityManager().getEntities().iterator();
                while(it.hasNext()){
                    Entity entity = it.next();
                    entity.setLevel(1);
                }
                a = new ChooseCharacterState(handler);
                a.setActive(true);
                State.setState(a);
                
        }}));
        
        uiManager.addObject(new UIImageButton(100, 456, 600, 128, Assets.hard, () ->{
        	if(isActive) {
        		this.handler.getGame().setLevel(2);
                Iterator<Entity> it = this.handler.getWorld().getEntityManager().getEntities().iterator();
                while(it.hasNext()){
                    Entity entity = it.next();
                    entity.setLevel(2);              
                }
                a = new ChooseCharacterState(handler);
                a.setActive(true);
                State.setState(a);
                
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
	        g.drawString("Choose the difficulty ", 250, 150);
	}    


}
