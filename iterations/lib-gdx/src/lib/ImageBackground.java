/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * Фон для игры
 * 
 * @author mypc
 */
public class ImageBackground {
    
    /**
     * Ширина всего поля игры
     */
    int totalWidth = 0;
    
    /**
     * Высота всего поля игры
     */
    int totalHeight = 0;
    
    /**
     * Ширина вьюпорта
     */
    int viewportWidth = 0;
    
    /**
     * Высота вьюпорта
     */
    int viewportHeight = 0;
    
    /**
     * Регион текстуры для отрисовки
     */
    TextureRegion region;
    
    /**
     * Текстура для отрисовки
     */
    Texture texture;
    
    /**
     * Создает новый фон с заданным изображением
     * 
     * @param bi изображение
     */
    public ImageBackground(BufferedImage bi) {
        if (texture != null){
            texture.dispose();
        }
        texture = TextureManager.getTexture(bi);
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }
    
    /**
     * Устанавливает обрезку фона - здесь не делает ничего, только устанавливает
     * внутренние параметры
     * 
     * @param x координата X начала
     * @param y координата Y начала
     * @param width ширина порта
     * @param height высорта порта
     */
    public  void setClip(int x, int y, int width, int height) {
        viewportWidth = width;
        viewportHeight = height;
    }
    
    /**
     * Устанавливает глобальные пределы смещения камеры
     * 
     * @param totalWidth ширина поля
     * @param totalHeight высота поля
     */
    public void setTotalClip(int totalWidth, int totalHeight) {
        this.totalWidth = totalWidth;
        this.totalHeight = totalHeight;
        region = new TextureRegion(texture);
    }
    
    /**
     * Обновление состояния фона
     * 
     * @param elapsed прошедшее время в мс
     */
    public void update(long elapsed) {

    }
    
    /**
     * Отрисовка фона
     * 
     * @param g графический контекст
     */
    public void render(Graphics2D g) {
       if (region != null) {
           g.getBatch().draw(region, 0, 0, this.totalWidth, this.totalHeight);
       }
    }
    
    /**
     * Устанавливает центра фона в определенную позицию
     * 
     * @param s спрайт, в центр которого устанавливается позиция камеры
     */
    public void setToCenter(Sprite s) {
        Point center = s.getCenter();
        float x = center.x;
        float y = center.y;
        if ((x  - viewportWidth / 2) < 0) {
            x = viewportWidth / 2;
        }
        if ((y  - viewportHeight / 2) < 0) {
            y = viewportHeight / 2;
        }
        if ((x + viewportWidth / 2) > totalWidth) {
            x = totalWidth - viewportWidth / 2;
        }
        if ((y + viewportHeight / 2) > totalHeight) {
            y = totalHeight - viewportHeight / 2;
        }
        
        Game.currentCamera.position.set(x, y, 0);
    }
    
    /**
     * Возвращает позицию X смещения фона
     * 
     * @return X-координата
     */
    public double getX() {
        return Game.currentCamera.position.x;
    }

    /**
     * Возвращает позицию Y смещения фона
     * 
     * @return Y-координата
     */    
    public double getY() {
        return Game.currentCamera.position.y;
    }
}
