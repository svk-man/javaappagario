package game.views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import game.models.Sprite;

/**
 *
 * @author mypc
 */
public class SpriteView {
    private Sprite sprite;
    
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
    private void repaint() {
        if (color != null && icon != null) {
            // Зарисовать площадь нужным цветом
            double coef = 0.5;
            int max = (int)(Math.max(icon.getWidth(), icon.getHeight()) * coef);
            BufferedImage bi = new BufferedImage(max, max, BufferedImage.TYPE_INT_ARGB);

            g2d = bi.createGraphics();               
            g2d.setColor(color);
            g2d.fillOval(0, 0, bi.getWidth(), bi.getHeight());
            
            // Обозначить периметр
            g2d.setColor(color.darker().darker());
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(0, 0, bi.getWidth(), bi.getHeight());

            double dcoef = coef - 0.05;
            g2d.scale(dcoef, dcoef); 
            
            int iconHeight = (int)(icon.getHeight() * dcoef);
            int iconWidth = (int)(icon.getWidth() * dcoef);
            g2d.drawImage(icon, (max - iconWidth) / 2 + 7, (max - iconHeight) / 2, null);

            sprite.setImage(bi);
        }
    }
    
    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
        repaint();
    }
}
