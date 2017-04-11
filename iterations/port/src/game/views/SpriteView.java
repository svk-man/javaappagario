package game.views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import game.models.Sprite;

/**
 * Представление спрайта
 * 
 * @author mypc
 */
public class SpriteView {
    /**
     * Спрайт
     */
    private Sprite sprite;
    
    /**
     * Конструктор
     * 
     * @param s - спрайт
     */
    public SpriteView(Sprite s) {
        sprite = s;
    }
    
    /**
     * Цвет объекта
     */
    private Color color = null;
    
    /**
     * Иконка объекта
     */
    private BufferedImage icon = null;
    
    /**
     * Графика для отрисовки
     */
    private Graphics2D g2d;
        
    // Перерисовать фон объекта
    public void repaint() {
        if (color != null && icon != null) {
            // Зарисовать площадь нужным цветом
            double coef = 0.5 + 0.1 * sprite.size();
            int max = (int)(Math.max(icon.getWidth(), icon.getHeight()) * coef);
            BufferedImage bi = new BufferedImage(max, max, BufferedImage.TYPE_INT_ARGB);

            if (g2d != null){
                g2d.dispose();
            }
            // Отрисовать овал
            g2d = bi.createGraphics();               
            g2d.setColor(color);
            g2d.fillOval(0, 0, bi.getWidth(), bi.getHeight());
            
            // Обозначить периметр
            g2d.setColor(color.darker().darker());
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(0, 0, bi.getWidth(), bi.getHeight());

            double dcoef = coef - 0.05;
            g2d.scale(dcoef, dcoef); 
            
            // Отрисовать иконку
            int iconHeight = (int)(icon.getHeight() * dcoef);
            int iconWidth = (int)(icon.getWidth() * dcoef);
            g2d.drawImage(icon, (max - iconWidth) / 2 + 7, (max - iconHeight) / 2, null);

            // Установка изображения спрайту
            sprite.setImage(bi);
            
        }
    }
    
    /**
     * Установка цвета представления спрайта
     * 
     * @param color - цвет (Color)
     */
    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    /**
     * Установка иконки представления спрайта
     * 
     * @param icon - иконка (BufferedImage)
     */
    public void setIcon(BufferedImage icon) {
        this.icon = icon;
        repaint();
    }
}
