package game.worlds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import game.Handler;
import game.entities.Player;
import game.gfx.ImageLoader;

public class ScoreBoard {
	private Handler handler;
	private static long score = Player.score;
	private static int width = 32, height = 32;
	private static BufferedImage[] numberImager = new BufferedImage[50];
	private static BufferedImage board = ImageLoader.loadImage("/textures/yellownumber.png");
	private static long highestScore;
	private static BufferedImage scoreImagers = ImageLoader.loadImage("/textures/score.png");
	private static BufferedImage x= ImageLoader.loadImage("/textures/heart.png");
	private static BufferedImage bomb = ImageLoader.loadImage("/textures/bomb.png");
	private static int health;
	private static int bullet_number;
	public ScoreBoard(Handler handler) {
		score = 0;
		bullet_number=0;
		health=0;
		for (int i = 0; i <= 9; ++i) {
			setNumberImage(i);
		}		
	}

	private static void setNumberImage(int i) {
		numberImager[i] = board.getSubimage(i*width, 0, width, height);
        }
	public static void tick() {
		getHighestScoreFromFile();
		setScore(Player.score);
		if (score > highestScore) {
			highestScore = score;
			updateHighestScore(highestScore);
		}
		
		setHealth(Player.getPlayer_health());
		setBullet_number(Player.getBullet_number());
	}
	public static void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 33);
		int van =(int)(score/10000)%10;
        int nghin = (int)(score/1000) %10;
		int tram = (int)(score/100) %10;
		int chuc = (int)(score/10) %10 ;
		int donVi = (int) score%10;
		g.drawImage(scoreImagers, 0, 0,108, height, null);
        g.drawImage(numberImager[van], 108, 3,width,height, null);
		g.drawImage(numberImager[nghin], 108 + 32, 3,width,height, null);
		g.drawImage(numberImager[tram], 108 + 64, 3, width, height, null);
		g.drawImage(numberImager[chuc], 108 + 96, 3, width, height, null);
		g.drawImage(numberImager[donVi], 108+128, 3,width,height, null);
		g.drawImage(x,580,0,width,height,null);
		g.drawImage(numberImager[health],612,0, width,height,null);

		g.drawImage(bomb, 664, 0, width, height, null);
		g.drawImage(numberImager[(int)(bullet_number/10)], 696, 0, width, height, null );
		g.drawImage(numberImager[(int)(bullet_number%10)], 696+32, 0, width, height, null );
	}
	
	public static void setScore(long score) {
		ScoreBoard.score = score;
	}
	public static void setHealth(int health) {
		ScoreBoard.health = health;
	}

	public static void setBullet_number(int bullet_number) {
		ScoreBoard.bullet_number = bullet_number;
	}

	public static long getHighestScoreFromFile() {
		try {
			FileReader in = new FileReader("res/world/highestScore.txt");
			BufferedReader br = new BufferedReader(in);
			
			ScoreBoard.highestScore = Long.parseLong(br.readLine());
			br.close();
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		return ScoreBoard.highestScore;
	}
	
	private static void updateHighestScore(long highestScore) {
		try {
			FileWriter out = new FileWriter("res/world/highestScore.txt");
			out.write(Long.toString(highestScore));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static long getHighestScore() {
		return ScoreBoard.highestScore;
	}
}