/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * Менеджер коллизий
 * 
 * @author mypc
 */
public class CollisionManager {
    /**
     * Модуль обнаружения коллизий объект - препятствие
     */
    private final ObjectToObstacleCollisionGroup o2o;
    
    /**
     * Модуль обнаружения коллизий объект - агар
     */
    private final ObjectToAgarCollisionGroup o2a;
    
    /**
     * Игра
     */
    private final Game game;
    
    /**
     * Группа спрайтов
     */
    private final SpriteGroup spriteGroup;
    
    /**
     * Группа агар
     */
    private final SpriteGroup agarGroup;
    
    /**
     * Конструктор
     * 
     * @param game - игра
     * @param spriteGroup - группа спрайтов, для которых необходимо учитывать коллизии
     * @param obstacleGroup - группа препятстий, для которых необходимо учитывать коллизии
     * @param agarGroup - группа агар, для которых необходимо учитывать коллизии
     */
    public CollisionManager(Game game, SpriteGroup spriteGroup, SpriteGroup obstacleGroup, SpriteGroup agarGroup) {
        this.game = game;
        this.spriteGroup = spriteGroup;
        this.agarGroup = agarGroup;
        o2o = new ObjectToObstacleCollisionGroup(spriteGroup, obstacleGroup);
        o2a = new ObjectToAgarCollisionGroup(spriteGroup, agarGroup);
    }
    
    /**
     * Проверить коллизии
     */
    public void checkCollision() {
        o2o.checkCollision();
        o2a.checkCollision();
    }
    
    /**
     * Устанавливает реакцию на результат коллизии с препятствием
     * 
     * @param first - спрайт 1 из группы 1
     * @param second - спрайт 2 из группы 2
     */
    public void collidedObjectToObstacle(Sprite first, Sprite second) {
        first.setX(first.getOldX());
        first.setY(first.getOldY());        
    }
    
    /**
     * Устанавливает реакцию на результат коллизии с агаром
     * 
     * @param first - спрайт 1 из группы 1
     * @param second - спрайт 2 из группы 2
     */
    public void collidedObjectToAgar(Sprite first, Sprite second) {
        if (first == game.playerSprite()) {
            // Поедание агара игроком
            game.playerSprite().incrementCollectedAgar();
            if (game.playerSprite().agarCollected() == Math.pow(2, game.playerSprite().size() + 1)) {
                game.playerSprite().incrementSize();
            }
            
            agarGroup.remove(second);
        } else {
            // Поедание агара врагом
            // Получить спрайты спрайтовой группы
            Sprite[] sprites = spriteGroup.getSprites();
        
            // Определить индекс спрайта
            boolean isFound = false;
            int index = -1;
            for (Sprite sprite : sprites) {
                if (!isFound && sprite != null && sprite != game.playerSprite()) {
                    index++;
                    isFound = isFound || sprite == first;
                }
            }
        
            if (isFound) {
                game.botSprite(index).incrementCollectedAgar();
                if (game.botSprite(index).agarCollected() == Math.pow(2, game.botSprite(index).size() + 1)) {
                    game.botSprite(index).incrementSize();
                }
            
                agarGroup.remove(second);
            }
        }
    }
    
    /**
     * Модуль обнаружения коллизий объект - препятствие
     */
    private class ObjectToObstacleCollisionGroup extends BasicCollisionGroup {
        /**
         * Конструктор
         * 
         * @param group1 - 1 группа спрайтов
         * @param group2 - 2 группа спрайтов
         */
        public ObjectToObstacleCollisionGroup(SpriteGroup group1, SpriteGroup group2) {
            setCollisionGroup(group1, group2);
            pixelPerfectCollision = true;
        }
        
        /**
         * Определяет, когда спрайт 1 из группы 1 вступил в коллизию с со спрайтом 2 из группы 2
         * 
         * @param first - спрайт 1 из группы 1
         * @param second - спрайт 2 из группы 2
         */
        @Override
        public void collided(Sprite first, Sprite second) {
            CollisionManager.this.collidedObjectToObstacle(first, second);
        }
        
    }
    
    /**
     * Модуль обнаружения коллизий объект - агар
     */
    private class ObjectToAgarCollisionGroup extends BasicCollisionGroup {
        /**
         * Конструктор
         * 
         * @param group1 - 1 группа спрайтов
         * @param group2 - 2 группа спрайтов
         */
        public ObjectToAgarCollisionGroup(SpriteGroup group1, SpriteGroup group2) {
            setCollisionGroup(group1, group2);
            pixelPerfectCollision = true;
        }

        /**
         * Определяет, когда спрайт 1 из группы 1 вступил в коллизию с со спрайтом 2 из группы 2
         * 
         * @param first - спрайт 1 из группы 1
         * @param second - спрайт 2 из группы 2
         */
        @Override
        public void collided(Sprite first, Sprite second) {
            CollisionManager.this.collidedObjectToAgar(first, second);
        }
        
    }
}
