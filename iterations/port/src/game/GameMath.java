package game;

import java.awt.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Математические функции, используемые в игре
 * 
 */
public class GameMath {
    /**
     * Переводит угол из градусов в радианы
     * 
     * @param angle угол в градусах
     * @return угол в радианах
     */
    public static double degreesToRadians(int angle) {
        return angle * Math.PI / 180.0;
    }
    
    /**
     * Переводит угол из радианов в градусы
     * 
     * @param angle угол в радианах
     * @return угол в градусах
     */
    public static int radiansToDegrees(double angle) {
        return (int) (angle * 180.0 / Math.PI);
    }
    
    /**
     * Возвращает расстояние между двумя точками
     * 
     * @param x1 координата х первой точки
     * @param y1 координата y первой точки
     * @param x2 координата х второй точки
     * @param y2 координата y второй точки
     * @return расстояние между двумя точками
     */
    public static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    
    /**
     * Возвращает расстояние между двумя точками
     * 
     * @param p1 координаты первой точки
     * @param p2 координаты второй точки
     * @return расстояние между двумя точками
     */
    public static double distance(Point p1, Point p2) {
        return GameMath.distance(p1.x, p1.y, p2.x, p2.y);
    }
    
    /**
     * Возвращает угол между двумя точками относительно восточного направления
     * 
     * @param p1 первая точка
     * @param p2 вторая точка
     * @return угол между точками в градусах
     */
    public static int angle(Point p1, Point p2) {
        int angle = radiansToDegrees(Math.atan((p2.y - p1.y) / (double)(p2.x - p1.x)));
        if (p2.x < p1.x)
            angle += 180;
        return angle;
    }
    
    /**
     * Возвращает точку на том же расстоянии от центра, что и переданая,
     * но в противоположном от центра направлении
     * 
     * @param p точка, для которой надо определить противоположную
     * @param center центр, относительно которого будет строиться противоположная точка
     * @return противоположная точка
     */
    public static Point getOppositePoint(Point p, Point center) {
        int newX = p.x - 2 * (p.x - center.x);
        int newY = p.y - 2 * (p.y - center.y);
        return new Point(newX, newY);
    }
    
    /**
     * Возвращает центр спрайта
     * @param sprite - спрайт
     * @return центр спрайта (Point)
     */
    public static Point getCenter(lib.Sprite sprite) {
        return new Point (
            (int)(sprite.getX() + sprite.getWidth()/2),
            (int)(sprite.getY() + sprite.getHeight()/2)
        );
    }
    
    /**
     * Определяет, сталкиваются ли два спрайта
     * @param sprite1 - спрайт 1
     * @param sprite2 - спрайт 2
     * @return результат проверки (boolean)
     */
    public static boolean collide(
        lib.Sprite sprite1,
        lib.Sprite sprite2
    ){
        // Определяем центры спрайтов
        Point center1 = GameMath.getCenter(sprite1);
        Point center2 = GameMath.getCenter(sprite2);
        
        // Определяем расстояние между двумя центрами
        double distance = GameMath.distance(center1, center2);
        
        // Определяем сумму радиусов этих спрайтов
        double radiusSum = sprite1.getWidth()/2.0 + sprite2.getWidth()/2.0;
        
        return radiusSum >= distance;
    }
    
    /**
     * Определяет, поглотил ли первый спрайт второй
     * @param sprite1 - спрайт 1
     * @param sprite2 - спрайт 2
     * @return результат проверки (boolean)
     */
    public static boolean absorbe(
        lib.Sprite sprite1,
        lib.Sprite sprite2
    ){
        // Определяем центры спрайтов
        Point center1 = GameMath.getCenter(sprite1);
        Point center2 = GameMath.getCenter(sprite2);
        
        // Определяем расстояние между двумя центрами
        double distance = GameMath.distance(center1, center2);
        
        // Определяем радиусы этих спрайтов
        double radius1 = sprite1.getWidth()/2.0;
        double radius2 = sprite2.getWidth()/2.0;
        
        return radius1 > radius2 && distance <= radius1/2.0 + radius2/2.0;
    }
    
    /**
     * Возвращает степень, в которую возведена двойка
     * 
     * @param num - результат возведения
     * @return степень (int)
     */
    public static int degreeOfTwo(int num) {
        if (num == 0) return -1;

        int pow = 0;
        
        while (num > 0){
            num >>= 1;
            pow++;
        }

        pow--;

        return pow;
    }
}
