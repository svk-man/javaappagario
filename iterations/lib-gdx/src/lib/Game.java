/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Главный класс игры
 * 
 * @author mypc
 */
public class Game extends ApplicationAdapter {
    /**
     * Камера
     */
    OrthographicCamera camera;
    
    /**
     * Глобальная камера игры для того, чтобы фон мог получить текущее смещение 
     * камеры
     */
    static Camera currentCamera;
    
    /**
     * Batch для рисования
     */
    SpriteBatch batch;
    
    /**
     * Псевдо-графический контекст. В данном случае - контейнер длв Batch
     */
    Graphics2D  ctx;
    
    /**
     * Инициализация ресурсов игры
     */
    @Override
    public void create() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        camera.update();
        currentCamera = camera;
        
        batch = new SpriteBatch();    
        ctx = new lib.Graphics2D(batch);
        
        this.initResources();
    }
    
    /**
     * Инициализация ресурсов игры
     */
    public void initResources() { 
        
    }
    
    /**
     * Обновление игровых ресурсов
     * 
     * @param elapsedTime время, прошедшее с предыдущего обновления
     */
    public void update(long elapsedTime) {
        
    }
    
    /**
     * Рендеринг игры
     */
    @Override
    public void render() {
        camera.update();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        update((long) (Gdx.graphics.getDeltaTime() * 1000.0));
        
        ctx.getBatch().setProjectionMatrix(camera.combined);
        
        ctx.begin();
        ctx.batch.setColor(Color.WHITE);
        renderInContext(ctx);
        ctx.end();
    }
    
    /**
     * Отрисовывает состояние игры
     * 
     * @param g контекст
     */    
    public void renderInContext(lib.Graphics2D g) {
        
    }

    /**
     * Освобождение ресурсов игры
     */
    @Override
    public void dispose () {
        TextureManager.disposeTextures();
    }
    
    /**
     * Получение координаты X курсора мыши в окне
     * 
     * @return координата X курсора в окне
     */
    public int getMouseX() {
        return Gdx.input.getX() - Gdx.graphics.getWidth() / 2;
    }

    /**
     * Получение координаты Y курсора мыши в окне
     * 
     * @return координата Y курсора в окне 
     */    
    public int getMouseY() {
        return (Gdx.graphics.getHeight() / 2 - Gdx.input.getY());
    }
    
    /**
     * Получение ширины окна
     * 
     * @return 
     */
    public int getWidth() {
        return (Gdx.graphics.getWidth());
    }
    
    /**
     * Получение высоты окна
     * 
     * @return 
     */
    public int getHeight() {
        return (Gdx.graphics.getHeight());
    }
    
    /**
     * Определяет нажатие указанной кнопки
     * 
     * @param key - ключ кнопки
     * @return - нажата ли?
     */
    public boolean keyPressed(int key) {
        if (key == 32) // SPACE
            return Gdx.input.isKeyPressed(62);
        
        return false;
    }
    
    /**
     * Определяет клик мыши
     * 
     * @return - был ли клик?
     */
    public boolean click() {
        return Gdx.input.isButtonPressed(Buttons.LEFT);
    }
}
