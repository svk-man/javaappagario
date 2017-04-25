/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 * Главный класс игры
 * 
 * @author mypc
 */
public class Game extends com.golden.gamedev.Game {
    
    /**
     * Инициализация ресурсов игры
     */
    @Override
    public void initResources() { 
        
    }
    
    /**
     * Обновляет состояние игры
     * 
     * @param elapsedTime время, прошедшее с предыдущего обновления
     */
    @Override
    public void update(long elapsedTime) {
        
    }
    
    /**
     * Отрисовывает состояние игры (не портировать)
     * 
     * @param g 
     */
    @Override
    public void render(java.awt.Graphics2D g) {
        lib.Graphics2D ctx = new lib.Graphics2D(g);
        renderInContext(ctx);
    }
    
    /**
     * Отрисовывает состояние игры
     * 
     * @param g 
     */    
    public void renderInContext(lib.Graphics2D g) {
        
    }
    
    /**
     * Получение координаты X курсора мыши в окне
     * 
     * @return 
     */
    @Override
    public int getMouseX() {
        return super.getMouseX();
    }

    /**
     * Получение координаты Y курсора мыши в окне
     * 
     * @return 
     */    
    @Override
    public int getMouseY() {
        return super.getMouseY();
    }
    
    /**
     * Получение ширины окна
     * 
     * @return 
     */
    public int getWidth() {
        return super.getWidth();
    }
    
    /**
     * Получение высоты окна
     * 
     * @return 
     */
    public int getHeight() {
        return super.getHeight();
    }
    
    /**
     * Определяет нажатие указанной кнопки
     * 
     * @param key - ключ кнопки
     * @return - нажата ли?
     */
    public boolean keyPressed(int key) {
        return super.keyPressed(key);
    }
    
    /**
     * Определяет клик мыши
     * 
     * @return - был ли клик?
     */
    public boolean click() {
        return super.click();
    }
    
    /**
     * Осуществляет сброс камеры (Пустышка)
     */
    public void resetCamera() {
        
    }
}
