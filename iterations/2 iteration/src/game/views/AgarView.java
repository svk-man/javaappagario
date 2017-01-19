/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Представление агара
 * @author mypc
 */
public class AgarView {
    
    /**
     * Кэшированное изображение
     */
    private static BufferedImage image = null;
    
    /**
     * Размер представления агара
     */
    private static final int size = 20;
    
    public static BufferedImage image() {
        if (image == null) {
            // TYPE_INT_ARGB - Представляет изображение с 8-разрядными компонентами цвета RGBA,
            // упакованными в целочисленные пиксели.
            image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            
            Color color = new Color(220, 220, 220);
            Graphics2D g2d = image.createGraphics();
            
            // Заполнить овал
            g2d.setColor(color);
            g2d.fillOval(0, 0, size, size);
            
            // Обозначить границу нарисованного овала
            g2d.setColor(color.darker());
            g2d.setStroke(new BasicStroke(2));
            
            // Отрисовать овал
            g2d.drawOval(0, 0, size, size);
        }
        
        return image;
    }
}
