/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import com.badlogic.gdx.graphics.Texture;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * Класс спрайта
 * 
 * @author mypc
 */
public class Sprite {
    
    /**
     * Текстура
     */
    Texture texture;
    
    /**
     * Форма для отображения спрайта
     */
    lib.collision.Ellipse shape;
    
    /**
     * X-координата центра спрайта
     */
    double x = 0;
    
    /**
     * Y-координата центра спрайта
     */
    double y = 0;
    
    /**
     * "Старая" X-координата спрайта
     */
    double oldX = 0;
    
    /**
     * "Старая" Y-координата спрайта
     */
    double oldY = 0;
    
    /**
     * Горизональная скорость спрайта
     */
    double horizontalSpeed = 0;
    
    /**
     * Вертикальная скорость спрайта
     */
    double verticalSpeed = 0;
    
    /**
     * Создает спрайт по умолчанию
     */
    public Sprite() {
        
    }
    
    /**
     * Создает спрайт с изображением x и y
     * 
     * @param bi изображение
     * @param x X-координата спрайта
     * @param y Y-координата спрайта
     */
    public Sprite(BufferedImage bi, int x, int y) {        
        this.x = x;
        oldX = x;
        this.y = y;
        oldY = y;
        setImage(bi);
    }
    
    /**
     * Устанавливает изображение для спрайта
     * 
     * @param bi изображение
     */
    public final void setImage(BufferedImage bi) {
        if (texture != null)
            texture.dispose();
        texture = TextureManager.getTexture(bi);
        shape = new lib.collision.Ellipse(
                x + texture.getWidth() / 2, 
                y - texture.getHeight() / 2, 
                texture.getWidth() / 2, 
                texture.getHeight() / 2
        );        
    }
    
    /**
     * Обновление состояния спрайта
     * @param elapsed прошедшее время
     */
    public void update(long elapsed) {
        double nx = x + horizontalSpeed * elapsed;
        double ny = y + verticalSpeed * elapsed;
        
        oldX = x;
        oldY = y;
        
        x = nx;
        y = ny;
        
        shape.x0 = x +  texture.getWidth() / 2;
        shape.y0 = y -  texture.getHeight() / 2;
    }
    
    /**
     * Рисует спрайт
     * 
     * @param g контекст
     */
    public void render(Graphics2D g) {
        if (texture != null)  {
            g.getBatch().draw(
                texture, 
                (float)x, 
                (float)(y - texture.getHeight())
            );
 
        }
    }
    
    /**
     * Устанавливает X-координату спрайта
     * 
     * @param x координата спрайта
     */
    public void setX(double x) {
        this.x = x;
        shape.x0 = x + texture.getWidth() / 2;
    }

    /**
     * Устанавливает Y-координату спрайта
     * 
     * @param y координата спрайта
     */
    public void setY(double y) {
        this.y = y;
        shape.y0 = y - texture.getHeight() / 2;
    }


    /**
     * Возвращает X-координату спрайта
     * 
     * @return X-координата спрайта
     */    
    public double getX() {
        return x;
    }

    /**
     * Возвращает Y-координату спрайта
     * 
     * @return Y-координата спрайта
     */
    public double getY() {
        return y;
    }

    /**
     * Устанавливает горизонтальную скорость спрайта
     * 
     * @param v скорость
     */    
    public void setHorizontalSpeed(double v) {
        horizontalSpeed = v;
    }

    /**
     * Устанавливает вертикальную скорость спрайта
     * 
     * @param v скорость
     */
    public void setVerticalSpeed(double v) {
        verticalSpeed = v;
    }

    /**
     * Возвращает старую X-координату
     * 
     * @return 
     */    
    public double getOldX() {
        return oldX;
    }
    
    /**
     * Возвращает старую Y-координату
     * 
     * @return 
     */
    public double getOldY() {
       return oldY;
    }  
    
    /**
     * Возвращает центр спрайта
     * 
     * @return центр спрайта
     */
    public Point getCenter() {
        return new Point((int)shape.x0,(int)shape.y0);
    }
    
    /**
     * Возвращает фигуру для коллизии
     * 
     * @return фигура для коллизии
     */
    public lib.collision.Ellipse getCollisionShape() {
        return shape;        
    }

    /**
     * Возвращает ширину спрайта
     * 
     * @return ширина спрайта
     */
    public int getWidth() {
        return texture.getWidth();
    }

    /**
     * Возвращает высоту спрайта
     * 
     * @return высота спрайта
     */
    public int getHeight() {
        return texture.getHeight();
    }    
    
    /**
     * Возвращает горизонтальную скорость
     * 
     * @return горизонтальная скорость
     */
    public double getHorizontalSpeed() {
        return horizontalSpeed;
    }
    
    /**
     * Возвращает вертиальную скорость
     * 
     * @return вертикальная скорость
     */
    public double getVerticalSpeed() {
        return verticalSpeed;
    }
    
    /**
     * Останавливает спрайт при выходе за пределы прямоугольника [0;0;totalWidth;totalHeight]
     * 
     * @param totalWidth макс. ширина
     * @param totalHeight макс. высота
     */
    public void stopOnGoingOutOfBounds(double totalWidth, double totalHeight) {            
        if (this.getX() <= 0 && this.getHorizontalSpeed() < 0) {
            this.setHorizontalSpeed(0);
        }
        if (this.getX() + this.getWidth() >= totalWidth && this.getHorizontalSpeed() > 0) {
            this.setHorizontalSpeed(0);
        }
        if (this.getY() - this.getHeight() <= 0 && this.getVerticalSpeed() < 0) {
            this.setVerticalSpeed(0);
        }
        if (this.getY() >= totalHeight && this.getVerticalSpeed() > 0) {
            this.setVerticalSpeed(0);
        }
    }
}
