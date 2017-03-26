/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import com.golden.gamedev.Game;
import java.awt.Dimension;

/**
 * Загрузчик игрового окна
 * 
 * @author mypc
 */
public class GameLoader {
    /**
     * Игровой загрузчик
     */
    private com.golden.gamedev.GameLoader loader;
    
    /**
     * Конструктор игрового загрузчика
     */
    public GameLoader() {
        loader = new com.golden.gamedev.GameLoader();
    }
    
    /**
     * Устанавливает игру заданного размера в полноэкранном режиме (если задано)
     * @param game - игра
     * @param windowSize - размеры окна
     * @param fullscreen - полноэкранный режим?
     */
    public void setup(Game game, Dimension windowSize, boolean fullscreen) {
        loader.setup(game, windowSize, fullscreen);
    }
    
    /**
     * Осуществляет запуск игры
     */
    public void start() {
        loader.start();
    }
}

