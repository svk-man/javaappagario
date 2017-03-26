/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.collision;

/**
 * Эллипс, как основная фигура аппроксимации формы объектов
 * 
 * @author mypc
 */
public class Ellipse {
    
    /**
     * Координата X центра
     */
    public double x0;
    
    /**
     * Координата Y центра
     */    
    public double y0;
    
    /**
     * Радиус ширины
     */
    public double widthRadius;
    
    /**
     * Радиус высоты
     */
    public double heightRadius;
    
    /**
     * Конструирует новый эллипс
     * 
     * @param x0 X-координата центра
     * @param y0 Y-координата центра
     * @param wr горизонтальный радиус эллипса 
     * @param hr вертикальный радиус эллипса
     */
    public Ellipse(double x0, double y0, double wr, double hr) {
        this.x0 = x0;
        this.y0 = y0;
        this.widthRadius = wr;
        this.heightRadius = hr;
    }
    
    /**
     * Возвращает пару точек пересечения с прямой. Функция НЕ проверяет корректность
     * данных, нужно чтобы всегда точки существовали
     * 
     * @return пара точек пересечения
     */
    Math.CollisionPointPair getIntersectionPoints(Line l) {
        return Math.getCollisionPointsBetweenLineAndEllipse(
                l.k1,
                l.k2, 
                l.k3, 
                x0, 
                y0, 
                this.widthRadius, 
                this.heightRadius
        );
    }

    /**
     * Проверяет, что два эллипса пересекаются. Это очень медленный и затратный 
     * способ, который не стоит использовать для быстрых вычислений.
     * 
     * @param o другой элипс
     * @return пересекаются ли?
     */
    protected boolean narrowCollidesWith(Ellipse o ) {
        Line  l = new Line(this.x0, this.y0, o.x0, o.y0);
        // Получить точки пересечения с прямой
        Math.CollisionPointPair myPair = this.getIntersectionPoints(l);
        Math.CollisionPointPair secondPair = this.getIntersectionPoints(l);
        
        // Получить ось и спроецировать все пары точек пересечения на неё.
        Axle a = new Axle(l, this.x0, this.y0);
        double x11 = a.projectPoint(myPair.x1, myPair.y1);
        double x12 = a.projectPoint(myPair.x2, myPair.y2);

        double x21 = a.projectPoint(secondPair.x1, secondPair.y1);
        double x22 = a.projectPoint(secondPair.x2, secondPair.y2);
        
        return Math.collides1D(x11, x12, x21, x22);
    }
    
    /**
     * Использует некоторые эвристики, для того, чтобы проверить, 
     * что два эллипса пересекаютс. Эти эвристики позволяют считать сложным 
     * способом, лишь в довольно ограниченном списке случаев, что 
     * должно дать ускорение программы.
     * 
     * @param o другой эллипса
     * @return пересекаются ли?
     */
    public boolean collidesWith(Ellipse o) {
        double dx = java.lang.Math.abs(x0 - o.x0);
        double dy = java.lang.Math.abs(y0 - o.y0);
        // Эллипсы радиусами меньше 0.001 не поддерживаются
        if (dx <= 0.001 && dy <= 0.001) {
            return true;
        }
        
        double dist = java.lang.Math.sqrt(dx * dx + dy * dy);
        double max_non_collision_dist = java.lang.Math.max(this.widthRadius, this.heightRadius)
                                      + java.lang.Math.max(o.widthRadius, o.heightRadius);
        double min_non_collision_dist = java.lang.Math.min(this.widthRadius, this.heightRadius)
                                      + java.lang.Math.min(o.widthRadius, o.heightRadius);
        if (dist > max_non_collision_dist) {
            return false;
        }
        
        if (dist < min_non_collision_dist) {
            return true;
        }
        
        return this.narrowCollidesWith(o);
    }
}
