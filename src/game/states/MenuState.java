package game.states;

import java.awt.*;

import game.Handler;
import game.gfx.Assets;
import game.ui.UIImageButton;
import game.ui.UIManager;
import game.worlds.ScoreBoard;

public class MenuState extends State {

    private UIManager uiManager;
    private ChooseState c;
    private HowToPlayState h;
    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        
        uiManager.addObject(new UIImageButton(336, 300, 128, 64, Assets.btn_start, () -> {
        	if(isActive) {
        	c = new ChooseState(handler); 
        	State.setState(c);
        	c.setActive(true);
        	handler.getGame().gameState = new GameState(handler,handler.getGame().getPlayer(),"res/world/world1.txt");
        	handler.getGame().menuState.setActive(false);           
        	}}));
        
        uiManager.addObject(new UIImageButton(336, 364, 128, 64, Assets.how_to_play_button, () ->{
        	if(isActive) {
            h = new HowToPlayState(handler);
            State.setState(h);
            h.setActive(true);
            handler.getGame().menuState.setActive(false);   
		
        }}));
        
        uiManager.addObject(new UIImageButton(336, 428, 128, 64, Assets.quit_button, () ->{
        	if(isActive)
            System.exit(0);
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();        
       
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.backgroundMenu,0,0,800,608,null);
        g.drawImage(Assets.logoMenu,225,112,371,76,null);

        uiManager.render(g);
        new ScoreBoard(handler);
        long highestScore = ScoreBoard.getHighestScoreFromFile();
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString("HighScore: "+ highestScore, 275, 250);
    }

    public UIManager getUiManager() {
        return uiManager;
    }
    
    
}

