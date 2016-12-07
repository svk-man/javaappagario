package game.controllers;

import game.Game;
import game.models.Sprite;

/**
 * Базовый контроллер для спрайта - отвечает за внутреннюю его логику
 */
public class Controller {
    
    public Controller(Game game, Sprite s) {
        this.sprite = s;
        this.game = game;
    }
    
    /**
     * Базовая реализация лишь проверяет, что спрайт не вышел за поля
     * @param elapsedTime 
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
    
    
    Sprite sprite;
    
    Game game;
}
