package game.models;

import game.GameMath;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import game.views.SpriteView;

/**
 * Мост между спрайтом GTGE и интерфейсами модели и представления
 * 
 * @author mypc
 */
public class Sprite extends com.golden.gamedev.object.Sprite {
    /**
     * Представление спрайта
     */
    private SpriteView spriteView = new SpriteView(this);
    
    /**
     * Получить представление спрайта
     * 
     * @return - представление спрайта
     */
    public SpriteView getSpriteView() {
        return spriteView;
    }

    /**
     * Угол перемещения относительно восточного направления
     */
    private int angle = 0;
    
    /**
     * Скорость перемещения объекта
     */
    private double speed = 0;
    
    /**
     * Установка позиции спрайта
     * 
     * @param position - позиция (Point)
     */
    public void setPosition(Point position) {
        this.setX(position.getX() - 1 / 2);
        this.setY(position.getY() - 1 / 2);
    }

    /**
     * Получить позицию спрайта
     * 
     * @return - позиция (Point)
     */
    public Point getPosition() {
        Point position = new Point();
        position.x = (int) (getX());
        position.y = (int) (getY());
        
        return position;
    }

    /**
     * Установка скорости спрайта
     * 
     * @param speed - скорость (double)
     */
    public void setSpeed(double speed) {
        this.speed = speed;
        setHorizontalSpeed(speed * Math.cos(GameMath.degreesToRadians(angle)));
        setVerticalSpeed(speed * Math.sin(GameMath.degreesToRadians(angle)));
    }

    /**
     * Получить скорость спрайта
     * 
     * @return - скорость (double)
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Установка направления движения спрайта
     * 
     * @param angle - угол перемещения относительно восточного направления (int)
     */
    public void setDirection(int angle) {
        this.angle = angle;
        setHorizontalSpeed(speed * Math.cos(GameMath.degreesToRadians(angle)));
        setVerticalSpeed(speed * Math.sin(GameMath.degreesToRadians(angle)));
    }

    /**
     * Получить направление движения спрайта
     * 
     * @return - угол перемещения относительно восточного направления (int)
     */
    public int getDirection() {
        return angle;
    }
}
