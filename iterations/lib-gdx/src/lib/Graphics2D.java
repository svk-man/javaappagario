/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Графический контекст для рисования
 * 
 * @author mypc
 */
public class Graphics2D {
    /**
     * Батч для рисования
     */
    SpriteBatch batch;
    
    /**
     * Создает новый контекст с заданным батчем
     * @param batch 
     */
    public Graphics2D(SpriteBatch batch) {
        this.batch = batch;
    }
    
    /**
     * Начинает отрисовку
     */
    public void begin() {
        batch.begin();
    }
    
    /**
     * Заканчивает отрисовку
     */
    public void end() {
        batch.end();
    }

    /**
     * Возвращает новый батч кода
     * @return
     */
    public SpriteBatch getBatch() {
        return batch;
    }
}
