package game.controllers;


import game.Game;
import game.GameMath;
import game.models.Sprite;
import java.awt.Point;

/**
 * Контроллер спрайта искусственного интеллекта (ИИ)
 */
public class AISpriteController extends BasicSpriteController {
    /**
     * Игрок
     */
    Sprite player;
    
    /**
     * Максимальное расстояние между спрайтом искусственного интеллекта(ИИ) и спрайтом игрока
     */
    static final int MAX_DISTANCE_BETWEEN_AI_AND_PLAYER = 240;
    
    /**
     * Конструктор
     * 
     * @param game - игра
     * @param ai - спрайт искусственного интеллекта (ИИ)
     * @param player - спрайт игрока
     */
    public AISpriteController(Game game, Sprite ai, Sprite player) {
        super(game, ai);
        this.player = player;
        int angle = GameMath.angle(sprite.getPosition(), this.player.getPosition());
        ai.setDirection(angle);
    }
    
    /**
     * Обновление контроллера спрайта искусственного интеллекта (ИИ)
     * 
     * @param elapsedTime - время, прошедшее с момента последнего обновления
     */
    @Override
    public void update(long elapsedTime) {
        Point playerPos = player.getPosition();             // Позиция спрайта игрока
        Point aiPos = sprite.getPosition();                 // Позиция спрайта ИИ
        double dist = GameMath.distance(aiPos, playerPos);  // Расстояние между спрайтом ИИ и спрайтом игрока
        
        // Изменить направление спрайта ИИ
        if (dist > AISpriteController.MAX_DISTANCE_BETWEEN_AI_AND_PLAYER) {
            int angle = GameMath.angle(aiPos, playerPos);
            sprite.setDirection(angle);
        }
    }

}
