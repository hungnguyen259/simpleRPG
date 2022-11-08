package game;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Launcher {
    
    public static void main(String[] args) {
        Game game = new Game("Grand Escape", 800, 608);
        game.start();
        try {
            FileInputStream fileInputStream = new FileInputStream("hello.mp3");

            Player player = new Player(fileInputStream);
            player.play();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }


    }
}
    
