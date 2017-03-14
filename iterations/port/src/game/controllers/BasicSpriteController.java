package game.controllers;

import game.Game;
import game.models.Sprite;

/**
 * Базовый контроллер для спрайта - отвечает за внутреннюю его логику
 * 
 * @author mypc
 */
public class BasicSpriteController {
    /**
     * Спрайт
     */
    Sprite sprite;
    
    /**
     * Получить спрайт
     * 
     * @return спрайт (Sprite)
     */
    public Sprite sprite() {
        return this.sprite;
    }
    
    /**
     * Игра
     */
    Game game;
    
    /**
     * Конструктор
     * 
     * @param game - игра
     * @param s - спрайт
     */
    public BasicSpriteController(Game game, Sprite s) {
        this.sprite = s;
        this.game = game;
    }
    
    /**
     * Базовая реализация лишь проверяет, что спрайт не вышел за границы поля
     * 
     * @param elapsedTime - время, прошедшее с момента последнего обновления
     */
    public void update(long elapsedTime) {
        // Проверить на выход за границы поля
        if (sprite.getX() <= 0 && sprite.getHorizontalSpeed() < 0)
            sprite.setHorizontalSpeed(0);
        if (sprite.getX() + sprite.getWidth() >= Game.totalWidth && sprite.getHorizontalSpeed() > 0)
            sprite.setHorizontalSpeed(0);
        if (sprite.getY() <= 0 && sprite.getVerticalSpeed() < 0)
            sprite.setVerticalSpeed(0);
        if (sprite.getY() + sprite.getHeight() >= Game.totalHeight && sprite.getVerticalSpeed() > 0)
            sprite.setVerticalSpeed(0);
    }
}
