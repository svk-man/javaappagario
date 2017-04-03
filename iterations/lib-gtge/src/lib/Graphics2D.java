/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.awt.Color;

/**
 * Графический контекст для рисования
 * 
 * @author mypc
 */
public class Graphics2D {
    
    /**
     * Графический объект для рисования
     */
    java.awt.Graphics2D g;
    
    /**
     * Графика
     * 
     * @param g 
     */
    public Graphics2D(java.awt.Graphics2D g) {
        this.g = g;
    }

    /**
     * Возвращает реальный объект для рисования. Не переносить!
     * 
     * @return объект
     */
    public java.awt.Graphics2D get() {
        return g;
    }
    
    /**
     * Отрисовка заполненного прямоугольника
     * 
     * @param x - координата x
     * @param y - координата y
     * @param width - ширина прямоугольника
     * @param height - высота прямоугольника
     * @param color - цвет
     */
    public void fillRect(int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
