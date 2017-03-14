/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.controllers;

import game.Game;
import game.GameMath;
import game.models.Agar;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author mypc
 */
public class AIAgarController {
    /**
     * Агар
     */
    Agar agar;
    
    /**
     * Игра
     */
    Game game;
    
    /**
     * Конструктор
     * 
     * @param game - игра
     * @param a - агар
     */
    public AIAgarController(Game game, Agar a) {
        this.agar = a;
        this.game = game;
        Random r1 = new Random(Game.totalWidth);
        Random r2 = new Random(Game.totalHeight);
        int angle = GameMath.angle(a.getPosition(), new Point(r1.nextInt(), r2.nextInt()));
        a.setDirection(angle);
    }
    
    /**
     * Проверка, что агар не вышел за границы поля
     * 
     * @param elapsedTime - время, прошедшее с момента последнего обновления
     */
    public void update(long elapsedTime) {
        // Проверить на выход за границы поля
        if (agar.getX() <= 0 && agar.getHorizontalSpeed() < 0)
            agar.setHorizontalSpeed(0);
        if (agar.getX() + agar.getWidth() >= Game.totalWidth && agar.getHorizontalSpeed() > 0)
            agar.setHorizontalSpeed(0);
        if (agar.getY() <= 0 && agar.getVerticalSpeed() < 0)
            agar.setVerticalSpeed(0);
        if (agar.getY() + agar.getHeight() >= Game.totalHeight && agar.getVerticalSpeed() > 0)
            agar.setVerticalSpeed(0);
    }
}
