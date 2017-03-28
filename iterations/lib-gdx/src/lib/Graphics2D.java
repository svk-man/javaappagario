/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.awt.Color;

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
    
    /**
     * Установка цвета
     * 
     * @param color 
     */
    public void setColor(Color color) {
        batch.setColor(new com.badlogic.gdx.graphics.Color(
                                                            color.getRed() / 255.0f, 
                                                            color.getGreen() / 255.0f,
                                                            color.getBlue() / 255.0f,
                                                            color.getAlpha() / 255.0f));
    }
    
    Texture texture;
    public void fillRect(int x, int y, int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(new com.badlogic.gdx.graphics.Color(
                                                            color.getRed() / 255.0f, 
                                                            color.getGreen() / 255.0f,
                                                            color.getBlue() / 255.0f,
                                                            color.getAlpha() / 255.0f));
        pixmap.fillRectangle(0, 0, width, height);
        if (texture != null)
            texture.dispose();
        texture = new Texture(pixmap);
        pixmap.dispose();
        batch.draw(texture, x, y, width, height);
        //texture.dispose();
    }
}
