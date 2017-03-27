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
public class SpriteGroup {
    
    /**
     * Имя спрайта
     */
    String name;
    
    /**
     * Список спрайтов
     */
    ArrayList<Sprite> list;
    
    /**
     * Создает новую именованную группу спрайтов
     * 
     * @param string название группы
     */
    public SpriteGroup(String string) {
        name = string;
        list = new ArrayList<>();
    }
    
    /**
     * Добавляет новый спрайт в группу
     * 
     * @param s спрайт
     */
    public void add(Sprite s) {
        list.add(s);
    }
    
    /**
     * Удаляет группу из спрайтов
     * 
     * @param s спрайт
     */
    public void remove(Sprite s) {
        list.remove(s);
    }
    
    /**
     * Устанавливает фон для группы спрайтов
     * 
     * @param bg фон для группы
     */
    public void setBackground(ImageBackground bg) {
        
    }
    
    /**
     * Обновляет состояние группы спрайтов
     * 
     * @param elapsed прошедшее время в мс
     */
    public void update(long elapsed) {
        list.stream().forEach((s) -> {
            s.update(elapsed);
        });
    }
    
    /**
     * Рендерит группу в контексте
     * 
     * @param g графический контекст
     */
    public void render(Graphics2D g) {
        list.stream().forEach((s) -> {
            s.render(g);
        });
    }
    
    /**
     * Возвращает список из всех спрайтов
     * 
     * @return список спрайтов
     */
    public List<Sprite> toList() {
        return list;
    }
    
    /**
     * Возвращает массив всех спрайтов
     * 
     * @return массив спрайтов
     */
    public Sprite[] getSprites() {
        Sprite[] spriteArr = new Sprite[list.size()];
        spriteArr = list.toArray(spriteArr);
        return spriteArr;
    }
    
    /**
     * Очистить список спрайтов
     */
    public void clear() {
        list.clear();
    }
    
    /**
     * Определить размер списка
     * 
     * @return размер списка
     */
    public int getSize() {
        return list.size();
    }
}
