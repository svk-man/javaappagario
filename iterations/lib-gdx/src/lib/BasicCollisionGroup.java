/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.List;

/**
 * Группа для поиска пересечений между спрайтами
 * 
 * @author mypc
 */
public class BasicCollisionGroup {
    /**
     * Первая группа для определения пересечений
     */
    SpriteGroup group1;
    
    /**
     * Вторая группа для определения пересечений
     */
    SpriteGroup group2;
    
    /**
     * Конструирует новую группу
     */
    public BasicCollisionGroup() {

    }
    
    /**
     * Запускает проверку коллизий для спрайтов
     */
    public void checkCollision() {
        if (group1 != null && group2 != null) {
            List<Sprite> sprites1 = group1.toList();
            List<Sprite> sprites2 = group2.toList();
            for (int i = 0; i < sprites1.size(); i++) {
                for (int j = 0; j < sprites2.size(); j++) {
                    Sprite s1 = sprites1.get(i);
                    Sprite s2 = sprites2.get(j);                    
                    if (s1 != s2) {
                        if (s1.getCollisionShape().collidesWith(s2.getCollisionShape())) {
                            this.collided(s1, s2);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Устанавливает группы для происка пересечений
     * 
     * @param s1 первая группа
     * @param s2 вторая группа
     */
    public void setCollisionGroup(SpriteGroup s1, SpriteGroup s2) {
        group1 = s1;
        group2 = s2;
    }

    /**
     * Обрабатывает коллизии.
     * 
     * @param first первый спрайт
     * @param second второй спрайт
     */
    public void collided(Sprite first, Sprite second) {
        
    }
}
