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
     * Модуль обнаружения коллизий объект - спрайт
     */
    private final ObjectToSpriteCollisionGroup o2s;
    
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
        o2s = new ObjectToSpriteCollisionGroup(spriteGroup, spriteGroup);
    }
    
    /**
     * Проверить коллизии
     */
    public void checkCollision() {
        o2o.checkCollision();
        o2a.checkCollision();
        o2s.checkCollision();
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
            // Определить индекс спрайта
            int index = game.spriteInSpriteGroup(first);

            if (index > -1) {
                game.botSprite(index).incrementCollectedAgar();
                if (game.botSprite(index).agarCollected() == Math.pow(2, game.botSprite(index).size() + 1)) {
                    game.botSprite(index).incrementSize();
                }
            
                agarGroup.remove(second);
            }
        }
    }
    
    /**
     * Устанавливает реакцию на результат коллизии со спрайтом
     * 
     * @param first - спрайт 1 из группы 1
     * @param second - спрайт 2 из группы 2
     */
    public void collidedObjectToSprite(Sprite first, Sprite second) {
        if (first == game.playerSprite() && GameMath.absorbe(first, second)) {
            // Определить индекс 2-го спрайта
            int index = game.spriteInSpriteGroup(second);

            if (index > -1) {
                int collectedAgar = game.playerSprite().agarCollected() + game.botSprite(index).agarCollected();
                game.playerSprite().setCollectedAgar(collectedAgar);
                game.playerSprite().setSize(GameMath.degreeOfTwo(collectedAgar) == -1 ? 0 : GameMath.degreeOfTwo(collectedAgar));
                game.botsSpriteList().remove(index);
                spriteGroup.remove(second);
            }
        }
        
        if (second == game.playerSprite() && GameMath.absorbe(first, second)) {
            // Определить индекс 1-го спрайта
            int index = game.spriteInSpriteGroup(first);
            
            if (index > -1) {
                int collectedAgar = game.playerSprite().agarCollected() + game.botSprite(index).agarCollected();
                game.botSprite(index).setCollectedAgar(collectedAgar);
                game.botSprite(index).setSize(GameMath.degreeOfTwo(collectedAgar) == -1 ? 0 : GameMath.degreeOfTwo(collectedAgar));
                spriteGroup.remove(second);
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
    
    /**
     * Модуль обнаружения коллизий объект - спрайт
     */
    private class ObjectToSpriteCollisionGroup extends BasicCollisionGroup {
        /**
         * Конструктор
         * 
         * @param group1 - 1 группа спрайтов
         * @param group2 - 2 группа спрайтов
         */
        public ObjectToSpriteCollisionGroup(SpriteGroup group1, SpriteGroup group2) {
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
            CollisionManager.this.collidedObjectToSprite(first, second);
        }  
    }
}
