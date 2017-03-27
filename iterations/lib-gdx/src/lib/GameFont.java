/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import lib.Game;
import lib.Graphics2D;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import java.io.File;

/**
 * Игровой шрифт
 * 
 * @author mypc
 */
public class GameFont {
    
    /**
     * Шрифт по умолчанию из GDX
     */
    BitmapFont font;
    
    final String fontChars = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";    
    
    /**
     * Создает новый игровой шрифт
     * 
     * @param fontName имя
     * @param attrs атрибуты
     * @param size размер шрифта
     * @param clr  цвет
     */
    public GameFont(String fontName, int attrs, int size, java.awt.Color clr) {
        //final String FONT_PATH = "fonts/Comfortaa-Regular.ttf";
        //FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
        //FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        //parameter.characters = fontChars;
        //parameter.size = 15;
        //parameter.color = Color.BLACK;
        //font = generator.generateFont(parameter);
        //generator.dispose();
        font = new BitmapFont();
        font.setColor(new Color(
                clr.getRed() / 255.0f, 
                clr.getGreen() / 255.0f,
                clr.getBlue() / 255.0f,
                clr.getAlpha() / 255.0f
        ));
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
        float px = x + (Game.currentCamera.position.x - Gdx.graphics.getWidth() / 2);
        float py =  (Gdx.graphics.getHeight() - y) 
                 + (Game.currentCamera.position.y - Gdx.graphics.getHeight() / 2);
        font.draw(
            g.getBatch(), 
            data, 
            px, 
            py
        );
    }
}
