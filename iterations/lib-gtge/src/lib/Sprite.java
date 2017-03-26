/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.awt.image.BufferedImage;

/**
 * Класс спрайта
 * 
 * @author mypc
 */
public class Sprite extends com.golden.gamedev.object.Sprite {
    
    public Sprite() {
        super();
    }
    
    public Sprite(BufferedImage bi, int x, int y) {
        super(bi, x, y);
    }
    
    /**
     * Устанавливает изображение
     * 
     * @param bi 
     */
    @Override
    public void setImage(BufferedImage bi) {
        super.setImage(bi);
    }
    
    /**
     * Обновление состояния спрайта
     * 
     * @param elapsed прошедшее время
     */
    @Override
    public void update(long elapsed) {
        super.update(elapsed);
    }
    
    /**
     * Рисует спрайт на изображении
     * 
     * @param g 
     */
    public void render(Graphics2D g) {
        super.render(g.get());
    }
    
    @Override
    public void setX(double x) {
        super.setX(x);
    }

    @Override
    public void setY(double y) {
        super.setY(y);
    }

    @Override
    public double getX() {
        return super.getX();
    }

    @Override
    public double getY() {
        return super.getY();
    }
    
    @Override
    public void setHorizontalSpeed(double v) {
        super.setHorizontalSpeed(v);
    }

    @Override
    public void setVerticalSpeed(double v) {
        super.setVerticalSpeed(v);
    }
    
    @Override    
    public double getOldX() {
        return super.getOldX();
    }
    
    @Override    
    public double getOldY() {
       return super.getOldY();
    }  
    
    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }
    
    @Override
    public double getHorizontalSpeed() {
        return super.getHorizontalSpeed();
    }

    @Override
    public double getVerticalSpeed() {
        return super.getVerticalSpeed();
    }
    
    /**
     * Останавливает спрайт при выходе за пределы прямоугольника [0;0;totalWidth;totalHeight]
     * 
     * @param totalWidth макс. ширина
     * @param totalHeight макс. высота
     */
    public void stopOnGoingOutOfBounds(double totalWidth, double totalHeight) {
        if (this.getX() <= 0 && this.getHorizontalSpeed() < 0)
            this.setHorizontalSpeed(0);
        if (this.getX() + this.getWidth() >= totalWidth && this.getHorizontalSpeed() > 0)
            this.setHorizontalSpeed(0);
        if (this.getY() <= 0 && this.getVerticalSpeed() < 0)
            this.setVerticalSpeed(0);
        if (this.getY() + this.getHeight() >= totalHeight && this.getVerticalSpeed() > 0)
            this.setVerticalSpeed(0);
    }
}
