package main;

import com.golden.gamedev.GameLoader;
import game.Game;
import java.awt.Dimension;

/**
 * Основной класс
 * 
 * @author mypc
 */
public class Main {
    /**
     * Осуществление запуска игры
     * 
     * @param args arguments 
     */
    public static void main(String[] args) {
        Game game = new Game();                         // Создание игры
        GameLoader loader = new GameLoader();           // Создание игрового загрузчика
        // Установка параметров: игра заданного размера не в полноэкранном режиме
        loader.setup(game, game.dimensions(), false);
        loader.start();                                 // Запуск
    }
}
