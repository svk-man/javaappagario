/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.awt.Font;

/**
 * Игровой шрифт для отрисовки надписей
 * 
 * @author mypc
 */
public class GameFont extends com.golden.gamedev.object.font.SystemFont {

    /**
     * Создает новый игровой шрифт
     * 
     * @param size размер шрифта
     * @param clr цвет
     */
    public GameFont(int size, java.awt.Color clr) {
        super(new Font("Monospaced", Font.CENTER_BASELINE, size), clr);
    }
    
    /**
     * Рисует строку с заданным значением строки
     * 
     * @param g контекст
     * @param data данные
     * @param x координата X
     * @param y координата Y
     */
    public void drawString(Graphics2D g, String data, int x, int y) {
        super.drawString(g.get(), data, x, y);
    }
}
