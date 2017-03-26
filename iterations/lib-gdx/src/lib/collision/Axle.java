/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.collision;

/**
 * Ось для проекции
 * 
 * @author mypc
 */
public class Axle {
    
    /**
     * X-координата опорной точки (0,0)
     */
    double px;
    
    /**
     * Y-координата опорной точки (0,0)
     */
    double py;
    
    /**
     * X-координата вектора оси
     */
    double x;
    
    /**
     * Y-координата вектора оси
     */
    double y;
    
    /**
     * Создает новую ось
     * 
     * @param l прямая
     * @param px X-координата точки (0,0)
     * @param py Y-координата точки (0,0)
     */
    public Axle(Line l, double px, double py) {
        if (java.lang.Math.abs(l.k1) < 0.001 && java.lang.Math.abs(l.k2) < 0.001) {
            x = 1;
            y = 0;
        } else {
            double length = java.lang.Math.sqrt(l.k1 * l.k1 + l.k2 * l.k2);
            x = l.k1 / length;
            y = l.k2 / length;
        }
        
        this.px = px;
        this.py = py;
    }

    /**
     * Проецирует точку на данную ось
     * 
     * @param x X-координата точки
     * @param y Y-координата точки
     * @return значение точки
     */
    double projectPoint(double x, double y) {
        return this.x * (x - this.px) + this.y * (y - this.py);
    }
}
