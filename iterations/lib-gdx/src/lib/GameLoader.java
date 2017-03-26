/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import java.awt.Dimension;

/**
 * Загрузчик игрового окна
 * 
 * @author mypc
 */
public class GameLoader {
    /**
     * Параметры конфигурации
     */
    private LwjglApplicationConfiguration cfg;
    
    /**
     * Приложение
     */
    private LwjglApplication app;
    
    /**
     * Игра для выполнения
     */
    public Game game;
    
    /**
     * Конструктор игрового загрузчика
     */
    public GameLoader() {
        cfg = new LwjglApplicationConfiguration();
    }
    
    /**
     * Устанавливает игру заданного размера в полноэкранном режиме (если задано)
     * @param game - игра
     * @param windowSize - размеры окна
     * @param fullscreen - полноэкранный режим?
     */
    public void setup(Game game, Dimension windowSize, boolean fullscreen) {
        cfg = new LwjglApplicationConfiguration();
        cfg.width = windowSize.width;
        cfg.height = windowSize.height;
        cfg.fullscreen = fullscreen;
        cfg.foregroundFPS = 60;
        cfg.resizable = false;
        
        this.game = game;
    }
    
    public void start() {
        app = new LwjglApplication(game, cfg);
    }
}
