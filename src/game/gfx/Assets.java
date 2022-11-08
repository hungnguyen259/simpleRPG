package game.gfx;

import java.awt.image.BufferedImage;

public class Assets {
    private static final int width = 32, height = 32;
    public static BufferedImage dirt, grass, wall, tree,  stone, water, bridge,gameover,
                                flower,  tutorial,  scoreImage, door,backgroundMenu,logoMenu, item;
    public static BufferedImage[] player1_down, player1_up, player1_left, player1_right, bullet;
    public static BufferedImage exitGame= ImageLoader.loadImage("/textures/return to menu.png");
    public static BufferedImage[] player2_down, player2_up, player2_left, player2_right;


    public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
    public static BufferedImage[] zombie1_down, zombie1_up, zombie1_left, zombie1_right;
    public static BufferedImage[] monster_down, monster_up, monster_left, monster_right;
    public static BufferedImage[] boss_down, boss_up, boss_left, boss_right;

    public static BufferedImage btn_start, how_to_play_button, quit_button;
    public static BufferedImage easy,normal,hard;
    public static BufferedImage gameOver= ImageLoader.loadImage("/textures/gameover.jpg");
    public static BufferedImage gameWin= ImageLoader.loadImage("/textures/win.png");
    public static BufferedImage tym= ImageLoader.loadImage("/textures/heart.png");
    public static BufferedImage key= ImageLoader.loadImage("/textures/key.png");
    public static void init() {
        SpriteSheet Monster = new SpriteSheet(ImageLoader.loadImage("/textures/Monster.png"));
        SpriteSheet mapIcon = new SpriteSheet(ImageLoader.loadImage("/textures/mapchip.png"));
        SpriteSheet items = new SpriteSheet(ImageLoader.loadImage("/textures/10oop.png"));
        SpriteSheet doors = new SpriteSheet(ImageLoader.loadImage("/textures/door.png"));
		SpriteSheet hust = new SpriteSheet(ImageLoader.loadImage("/textures/character.png"));
        SpriteSheet quit = new SpriteSheet(ImageLoader.loadImage("/textures/Newbutton.png"));
        SpriteSheet scoreImages = new SpriteSheet(ImageLoader.loadImage("/textures/score.png"));
        SpriteSheet bullets = new SpriteSheet(ImageLoader.loadImage("/textures/bomb.png"));
        SpriteSheet bullets2 = new SpriteSheet(ImageLoader.loadImage("/textures/bomb2.png"));
        SpriteSheet bullets3 = new SpriteSheet(ImageLoader.loadImage("/textures/bomb3.png"));
        SpriteSheet bullets4 = new SpriteSheet(ImageLoader.loadImage("/textures/bomb4.png"));
        SpriteSheet background = new SpriteSheet(ImageLoader.loadImage("/textures/backgroud1.jpg"));
        SpriteSheet logo = new SpriteSheet(ImageLoader.loadImage("/textures/logo.png"));
        SpriteSheet difficulty = new SpriteSheet(ImageLoader.loadImage("/textures/difficulty.png"));

        logoMenu = logo.crop(0,0,371,76);
        backgroundMenu = background.crop(0,0,800,608);
        scoreImage = scoreImages.crop(0, 0, 108, height);

        tutorial = ImageLoader.loadImage("/textures/Tutorial.png");

        btn_start = quit.crop(0, 0, 270, 110);
      
        how_to_play_button = quit.crop(0,112,270,110);
        
        quit_button = quit.crop(0, 230, 270, 110);
        easy = difficulty.crop(0, 0, 799, 170);
        normal = difficulty.crop(0, 225, 799, 170);
        hard = difficulty.crop(0, 450, 799, 166);
        player1_down = new BufferedImage[2];
        player1_up = new BufferedImage[2];
        player1_left = new BufferedImage[2];
        player1_right = new BufferedImage[2];

        player1_down[0] = hust.crop(0, 4*height, width, height);
        player1_down[1] = hust.crop(width , 4*height, width, height);
        player1_up[0] = hust.crop(0, height*7, width, height);
        player1_up[1] = hust.crop(width , height*7, width, height);
        player1_left[0] = hust.crop(0, height*5, width, height);
        player1_left[1] = hust.crop(width , height*5, width, height);
        player1_right[0] = hust.crop(0, height*6, width, height);
        player1_right[1] = hust.crop(width, height*6, width, height);

        player2_down = new BufferedImage[2];
        player2_up = new BufferedImage[2];
        player2_left = new BufferedImage[2];
        player2_right = new BufferedImage[2];

        player2_down[0] = hust.crop(9*width, 4*height, width, height);
        player2_down[1] = hust.crop(10*width , 4*height, width, height);
        player2_up[0] = hust.crop(9*width, height*7, width, height);
        player2_up[1] = hust.crop(10*width , height*7, width, height);
        player2_left[0] = hust.crop(9*width, height*5, width, height);
        player2_left[1] = hust.crop(width *10, height*5, width, height);
        player2_right[0] = hust.crop(9*width, height*6, width, height);
        player2_right[1] = hust.crop(width*10, height*6, width, height);


        
        zombie_down = new BufferedImage[2];
        zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];
	
        zombie_down[0] 	= Monster.crop(width*6, 4*height, width, height);
		zombie_down[1] 	= Monster.crop(width*7, 4*height, width, height);
        zombie_up[0]   	= Monster.crop(width * 6, height * 7, width, height);
		zombie_up[1]    = Monster.crop(width * 7, height * 7, width, height);
		zombie_right[0] = Monster.crop(width * 6, height * 6, width, height);
		zombie_right[1] = Monster.crop(width * 7, height * 6, width, height);
		zombie_left[0] 	= Monster.crop(width * 6, height * 5, width, height);
		zombie_left[1] 	= Monster.crop(width * 7, height * 5, width, height);


		zombie1_down = new BufferedImage[2];
		zombie1_up = new BufferedImage[2];
		zombie1_left = new BufferedImage[2];
		zombie1_right = new BufferedImage[2];

		zombie1_down[0] 	= Monster.crop(width*0, 4*height, width, height);
		zombie1_down[1] 	= Monster.crop(width*1, 4*height, width, height);
        zombie1_up[0]   	= Monster.crop(width * 0, 7*height, width, height);
		zombie1_up[1]    = Monster.crop(width * 1, 7*height , width, height);
		zombie1_right[0] = Monster.crop(width * 0, height * 6, width, height);
		zombie1_right[1] = Monster.crop(width * 1, height * 6, width, height);
		zombie1_left[0] 	= Monster.crop(width * 0, height * 5, width, height);
		zombie1_left[1] 	= Monster.crop(width * 1, height * 5, width, height);
		

		monster_down = new BufferedImage[3];
		monster_up = new BufferedImage[3];
		monster_left = new BufferedImage[3];
		monster_right = new BufferedImage[3];
		
		monster_down[0] = Monster.crop(width*9, height * 4, width , height );
		monster_down[1] = Monster.crop(width * 10, height * 4, width , height );
		monster_down[2] = Monster.crop(width * 11, height * 4, width , height );
		monster_up[0] = Monster.crop(width * 9, height * 7, width , height );
		monster_up[1] = Monster.crop(width * 10, height * 7, width , height );
		monster_up[2] = Monster.crop(width*11, height *7, width , height );
		monster_right[0] = Monster.crop(width * 9, height * 6, width , height );
		monster_right[1] = Monster.crop(width * 10, height * 6, width , height );
		monster_right[2] = Monster.crop(width * 11, height * 6, width , height );
		monster_left[0] = Monster.crop(width * 9, height * 5, width , height );
		monster_left[1] = Monster.crop(width * 10, height * 5, width , height );
		monster_left[2] = Monster.crop(width * 11, height * 5, width , height );

        boss_down = new BufferedImage[3];
        boss_up = new BufferedImage[3];
        boss_left = new BufferedImage[3];
        boss_right = new BufferedImage[3];

        boss_down[0] = Monster.crop(width*9, height * 0, width , height );
        boss_down[1] = Monster.crop(width * 10, height * 0, width , height );
        boss_down[2] = Monster.crop(width * 11, height * 0, width , height );
        boss_up[0] = Monster.crop(width * 9, height * 3, width , height );
        boss_up[1] = Monster.crop(width * 10, height * 3, width , height );
        boss_up[2] = Monster.crop(width*11, height *3, width , height );
        boss_right[0] = Monster.crop(width * 9, height * 2, width , height );
        boss_right[1] = Monster.crop(width * 10, height * 2, width , height );
        boss_right[2] = Monster.crop(width * 11, height * 2, width , height );
        boss_left[0] = Monster.crop(width * 9, height * 1, width , height );
        boss_left[1] = Monster.crop(width * 10, height * 1, width , height );
        boss_left[2] = Monster.crop(width * 11, height * 1, width , height );
        // solid
		door = doors.crop(0,0,width,height);
		tree = mapIcon.crop(width * 7, height * 0, width, height);
        dirt = mapIcon.crop(width*2,height, width, height);
        grass = mapIcon.crop(3*32, height * 0, width, height);
        wall = mapIcon.crop(32, 0, width, height);
        stone = mapIcon.crop(0, height * 1 , width , height);
        water = mapIcon.crop(width * 5, height * 0, width, height);
        flower = mapIcon.crop(width * 4, 0, width, height);
        bridge =   mapIcon.crop(width * 6, 32, width, height);

        // bullet
        bullet = new BufferedImage[4];
        bullet[0] = bullets.crop(0, 0, width, height);
        bullet[1]= bullets2.crop(0, 0, width, height);
        bullet[2]= bullets3.crop(0, 0, width, height);
        bullet[3]= bullets4.crop(0, 0, width, height);

        //item
        item = items.crop(0, 0, width-1, height);





    }
}
