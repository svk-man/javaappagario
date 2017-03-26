/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Менеджер текстур
 * 
 * @author mypc
 */
class TextureManager {
    /**
     * Сущности
     */
    static List<Entry> entries;
    
    /**
     * Пара - текстура и изображение для поиска соответствий
     */
    static class Entry {
        /**
         * Текстура
         */
        Texture texture;
        
        /**
         * Изображение
         */
        BufferedImage image;
    }
    
    /**
     * Преборазует изображение в текстуру
     * 
     * @param img иходное изображениек
     * @return текстура
     */
    public static Texture imageToTexture(BufferedImage img) {
        Pixmap px = new Pixmap(img.getWidth(), img.getHeight(), Pixmap.Format.RGBA8888);
        Pixmap.setBlending(Pixmap.Blending.None);
        px.setColor(Color.CYAN);
        for(int i = 0; i < img.getWidth(); i++) {
            for(int j = 0; j < img.getHeight(); j++) {
                int color = img.getRGB(i, j);
                int red = (color & 0x00ff0000) >> 16;
                int green = (color & 0x0000ff00) >> 8;
                int blue = color & 0x000000ff;
                int alpha = (color >> 24) & 0xff;
                px.setColor(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f);
                px.drawPixel(i, j);
            }
        }
        
        return new Texture(px);
    }
    
    /**
     * Получает текстуру для изображения, стараясь по возможности кэшировать её
     * 
     * @param img изображение
     * @return текстура
     */
    public static Texture getTexture(BufferedImage img) {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        
        for (Entry entry : entries) {
            if (entry.image == img) {
                return entry.texture;
            }            
        }
        
        Texture tex = TextureManager.imageToTexture(img);
        Entry e = new Entry();
        e.image = img;
        e.texture = tex;
        entries.add(e);
        
        return e.texture;
    }
    
    /**
     * Освобождает набор текстур, загруженных в систему
     */
    public static void disposeTextures() {
        if (entries != null) {
            entries.stream().forEach((entry) -> {
                entry.texture.dispose();
            });            
        }
    }
}
