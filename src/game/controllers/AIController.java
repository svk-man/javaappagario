package game.controllers;


import game.Game;
import game.GameMath;
import game.models.Sprite;
import java.awt.Point;

/**
 * Контроллер игрока
 */
public class AIController extends Controller {
    
    public AIController(Game game, Sprite s, Sprite p) {
        super(game, s);
        this.player = p;
        int angle = GameMath.angle(sprite.getPosition(), player.getPosition());
        s.setDirection(angle);
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
        if (dist > AIController.MAX_DISTANCE) {
            int angle = GameMath.angle(sprite.getPosition(), player.getPosition());
            sprite.setDirection(angle);
        }
    }
    /**
     * Игрок
     */
    Sprite player;
    /**
     * Максимальное расстояние
     */
    static final int MAX_DISTANCE = 240;
}
