package game.models;

import game.GameMath;
import java.awt.Point;

/**
 * Мост между спрайтом GTGE и интерфейсами модели и представления
 * 
 * @author mypc
 */
public class Agar extends lib.Sprite {
    /**
     * Представление агара
     */
    //private SpriteView spriteView = new SpriteView(this);

    /**
     * Угол перемещения относительно восточного направления
     */
    private int angle = 0;
    
    /**
     * Скорость перемещения объекта
     */
    private double speed = 0;
    
    /**
     * Установка позиции агара
     * 
     * @param position - позиция (Point)
     */
    public void setPosition(Point position) {
        this.setX(position.getX() - 1 / 2);
        this.setY(position.getY() - 1 / 2);
    }

    /**
     * Получить позицию агара
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
     * Установка скорости агара
     * 
     * @param speed - скорость (double)
     */
    public void setSpeed(double speed) {
        this.speed = speed;
        setHorizontalSpeed(speed * Math.cos(GameMath.degreesToRadians(angle)));
        setVerticalSpeed(speed * Math.sin(GameMath.degreesToRadians(angle)));
    }

    /**
     * Получить скорость агара
     * 
     * @return - скорость (double)
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Установка направления движения агара
     * 
     * @param angle - угол перемещения относительно восточного направления (int)
     */
    public void setDirection(int angle) {
        this.angle = angle;
        setHorizontalSpeed(speed * Math.cos(GameMath.degreesToRadians(angle)));
        setVerticalSpeed(speed * Math.sin(GameMath.degreesToRadians(angle)));
    }

    /**
     * Получить направление движения агара
     * 
     * @return - угол перемещения относительно восточного направления (int)
     */
    public int getDirection() {
        return angle;
    }
    
    /**
     * Обновить агар
     * 
     * @param elapsedTime - время, прошедшее с момента последнего обновления
     */
    public void update(long elapsedTime) {
        super.update(elapsedTime);
        //spriteView.repaint();
    }
}
