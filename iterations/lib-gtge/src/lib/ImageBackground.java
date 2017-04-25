/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * Фон для игры
 * 
 * @author mypc
 */
public class ImageBackground extends com.golden.gamedev.object.background.ImageBackground {

    /**
     * Конструктор игрового фона
     * 
     * @param bi - фоновое изображение
     * @param width - ширина игрового фона
     * @param height - высота игрового фона
     */
    public ImageBackground(BufferedImage bi, int width, int height) {
        super(bi);

        BufferedImage tile = bi;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g2d = img.createGraphics();
        int tileWidth = tile.getWidth();
        int tileHeight = tile.getHeight();
            
        for (int y = 0; y < height; y += tileHeight) {
            for (int x = 0; x < width; x += tileWidth) {
                g2d.drawImage(tile, x, y, null);
            }
        }
            
        g2d.dispose();

        super.setImage(img);
    }
    
    /**
     * Устанавливает обрезку фона
     * 
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    @Override
    public  void setClip(int x, int y, int width, int height) {
        super.setClip(x, y, width, height);
    }
    
    /**
     * Устанавливает глобальные пределы смещения камеры
     * 
     * @param totalWidth
     * @param totalHeight 
     */
    public void setTotalClip(int totalWidth, int totalHeight) {
        
    }
    
    /**
     * Обновление состояния фона
     * 
     * @param elapsed 
     */
    @Override
    public void update(long elapsed) {
        super.update(elapsed);
    }
    
    /**
     * Отрисовка фона
     * 
     * @param g 
     */
    public void render(Graphics2D g) {
        super.render(g.get());
    }
    
    /**
     * Устанавливает центр фона в определенную позицию
     * 
     * @param s 
     */
    public void setToCenter(Sprite s) {
        super.setToCenter(s);
    }
    
    /**
     * Возвращает позицию X смещения фона
     * 
     * @return 
     */
    @Override
    public double getX() {
        return super.getX();
    }

    /**
     * Возвращает позицию Y смещения фона
     * 
     * @return 
     */    
    @Override
    public double getY() {
        return super.getY();
    }

    public Point getTotalPosition(Point p) {
        return new Point ((int) (p.x + getX()), (int) (p.y + getY()));
    }
}
