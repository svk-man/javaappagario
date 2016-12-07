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
 */
public class Sprite extends com.golden.gamedev.object.Sprite {
    private SpriteView spriteView = new SpriteView(this);
    
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
    
    public void setPosition(Point position) {
        this.setX(position.getX() - 1 / 2);
        this.setY(position.getY() - 1 / 2);
    }

    public Point getPosition() {
        Point position = new Point();
        position.x = (int) (getX());
        position.y = (int) (getY());
        
        return position;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
        setHorizontalSpeed(speed * Math.cos(GameMath.degreesToRadians(angle)));
        setVerticalSpeed(speed * Math.sin(GameMath.degreesToRadians(angle)));
    }

    public double getSpeed() {
        return speed;
    }

    public void setDirection(int angle) {
        this.angle = angle;
        setHorizontalSpeed(speed * Math.cos(GameMath.degreesToRadians(angle)));
        setVerticalSpeed(speed * Math.sin(GameMath.degreesToRadians(angle)));
    }

    public int getDirection() {
        return angle;
    }
}
