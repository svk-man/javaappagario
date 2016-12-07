package main;

import com.golden.gamedev.GameLoader;
import game.Game;
import java.awt.Dimension;

/**
 * Main class
 */
public class Main {
    /**
     * Runs a game
     * @param args arguments 
     */
    public static void main(String[] args) {
        Game game = new Game();
        GameLoader loader = new GameLoader();
        loader.setup(game, game.dimensions(), false);
        loader.start();
    }
}
