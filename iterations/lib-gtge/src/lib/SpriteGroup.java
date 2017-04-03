/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.ArrayList;
import java.util.List;

/**
 * Группа спрайтов
 * 
 * @author mypc
 */
public class SpriteGroup extends com.golden.gamedev.object.SpriteGroup {

    /**
     * Создает новую именованную группу спрайтов
     * 
     * @param string 
     */
    public SpriteGroup(String string) {
        super(string);
    }
    
    /**
     * Добавляет новый спрайт в группу
     * 
     * @param s 
     */
    public void add(Sprite s) {
        super.add(s);
    }
    
    /**
     * Удаляет группу из спрайтов
     * 
     * @param s 
     */
    public void remove(Sprite s) {
        super.remove(s);
    }
    
    /**
     * Возвращает массив всех спрайтов
     * 
     * @return массив спрайтов
     */
    @Override
    public lib.Sprite[] getSprites() {
        return (lib.Sprite[]) super.getSprites();
    }
    
    /**
     * Устанавливает фон для группы спрайтов
     * 
     * @param bg 
     */
    public void setBackground(ImageBackground bg) {
        super.setBackground(bg);
    }
    
    /**
     * Обновляет состояние группы спрайтов
     * 
     * @param elapsed 
     */
    @Override
    public void update(long elapsed) {
        super.update(elapsed);
    }
    
    /**
     * Рендерит группу в контексте
     * 
     * @param g 
     */
    public void render(Graphics2D g) {
        super.render(g.get());
    }
    
    /**
     * Возвращает список из всех спрайтов
     * 
     * @return 
     */
    public List<Sprite> toList() {
        List<Sprite> result = new ArrayList<>();
        com.golden.gamedev.object.Sprite[] local = this.getSprites();
        for (com.golden.gamedev.object.Sprite sprite : local) {
            result.add((Sprite) sprite);
        }
        return result;
    }
    
    /**
     * Очистить список спрайтов
     */
    public void clear() {
        super.clear();
    }
    
    /**
     * Определить размер списка
     * 
     * @return размер списка
     */
    public int getSize() {
        return super.getSprites().length;
    }
}
