package game.controllers;

import game.Game;
import game.GameMath;
import game.models.Sprite;

/**
 * Контроллер игрока
 */
public class PlayerController extends Controller {
    
    public PlayerController(Game game, Sprite s) {
        super(game, s);
    }
    /**
     * Базовая реализация лишь проверяет, что спрайт не вышел за поля
     * @param elapsedTime 
     */
    @Override
    public void update(long elapsedTime) {
        int angle = GameMath.angle(sprite.getPosition(), game.mousePosition());
        sprite.setDirection(angle);
    }
}
