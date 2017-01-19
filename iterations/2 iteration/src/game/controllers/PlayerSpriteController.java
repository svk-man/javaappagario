package game.controllers;

import game.Game;
import game.GameMath;
import game.models.Sprite;

/**
 * Контроллер спрайта игрока
 * 
 * @author mypc
 */
public class PlayerSpriteController extends BasicSpriteController {
    /**
     * Конструктор
     * 
     * @param game - игра
     * @param player - спрайт игрока
     */
    public PlayerSpriteController(Game game, Sprite player) {
        super(game, player);
    }
    
    /**
     * Обновление спрайта игрока
     * 
     * @param elapsedTime - время, прошедшее с момента последнего обновления
     */
    @Override
    public void update(long elapsedTime) {
        // Определить направление движения
        int angle = GameMath.angle(sprite.getPosition(), game.mousePosition());
        sprite.setDirection(angle);
    }
}
