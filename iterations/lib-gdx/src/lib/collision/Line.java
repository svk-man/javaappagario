/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.collision;

/**
 * Прямая в виде уравнения k1*x + k2*y = k3
 * 
 * @author mypc
 */
public class Line {
    
    /**
     * Коэффициент прямой при X
     */
    public double k1;
    
    /**
     * Коэффициент прямой при Y
     */
    public double k2;
    
    /**
     * Третий коэффициент
     */    
    public double k3;
    
    /**
     * Конструирует прямую
     * 
     * @param x1 X-координата первой точки
     * @param y1 Y-координата первой точки
     * @param x2 X-координата второй точки
     * @param y2 Y-координата второй точки
     */
    public Line(double x1, double y1, double x2, double y2) {
        boolean x_are_equal = java.lang.Math.abs(x1 - x2) < 0.001;
        boolean y_are_equal = java.lang.Math.abs(y1 - y2) < 0.001;
        if (y_are_equal) {
            if (x_are_equal) {
                k1 = 1;
                k2 = 0;
                k3 = x1;
            } else {
                k1 = 0;
                k2 = 1;
                k3 = y1;
            }
        } else {
            k1 = 1;
            k2 = (x2 - x1) / (y1 - y2);
            k3 = k1 * x1 + k2 * y1; 
        }
    }
}
