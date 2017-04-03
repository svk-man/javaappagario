/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 * Группа для поиска пересечений
 * 
 * @author mypc
 */
public class BasicCollisionGroup extends com.golden.gamedev.object.collision.BasicCollisionGroup {

    /**
     * Инициализация коллизий
     */
    public BasicCollisionGroup() {
        super();
        this.pixelPerfectCollision = true;
    }
    
    /**
     * Запускает проверку коллизий для спрайтов
     */
    @Override
    public void checkCollision() {
        super.checkCollision();
    }
    
    /**
     * Устанавливает коллизию между двумя группами спрайтов
     * 
     * @param s1 - первая спрайтовая группа
     * @param s2 - вторая спрайтовая группа
     */
    public void setCollisionGroup(SpriteGroup s1, SpriteGroup s2) {
        super.setCollisionGroup(s1, s2);
    }
    
    /**
     * Обрабатывает коллизии. Не переносить!
     * 
     * @param first - первый спрайт GTGE
     * @param second - второй спрайт GTGE
     */
    @Override
    public void collided(com.golden.gamedev.object.Sprite first, com.golden.gamedev.object.Sprite second) {
        this.collided((Sprite)first, (Sprite)second);
    }
    
    /**
     * Обрабатывает коллизии
     * 
     * @param first - первый спрайт
     * @param second - второй спрайт
     */
    public void collided(Sprite first, Sprite second) {
        
    }
}
