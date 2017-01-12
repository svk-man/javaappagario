package game.controllers;


import game.Game;
import game.GameMath;
import game.models.Sprite;
import java.awt.Point;

/**
 * Контроллер игрока
 */
public class AISpriteController extends BasicSpriteController {
    
    public AISpriteController(Game game, Sprite ai, Sprite player) {
        super(game, ai);
        this.player = player;
        int angle = GameMath.angle(sprite.getPosition(), this.player.getPosition());
        ai.setDirection(angle);
    }
    /**
     * Базовая реализация лишь проверяет, что спрайт не вышел за поля
     * @param elapsedTime 
     */
    @Override
    public void update(long elapsedTime) {
        Point playerPos = player.getPosition();
        Point spritePos = sprite.getPosition();
        double dist = GameMath.distance(spritePos, playerPos);
        if (dist > AISpriteController.MAX_DISTANCE_BETWEEN_AI_AND_PLAYER) {
            int angle = GameMath.angle(sprite.getPosition(), player.getPosition());
            sprite.setDirection(angle);
        }
    }
    /**
     * Игрок
     */
    Sprite player;
    /**
     * Максимальное расстояние между спрайтом искусственного интеллекта(ИИ) и спрайтом игрока
     */
    static final int MAX_DISTANCE_BETWEEN_AI_AND_PLAYER = 240;
}
