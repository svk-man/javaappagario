package game;

import game.models.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import game.controllers.AISpriteController;
import game.controllers.BasicSpriteController;
import game.controllers.PlayerSpriteController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Игра
 * 
 * @author mypc
 */
public class Game extends com.golden.gamedev.Game {
    /**
     * Игровой фон
     */
    private ImageBackground bg;
    
    /**
     * Группа спрайтов, участвующих в коллизиях
     */
    private final SpriteGroup spriteGroup = new SpriteGroup("Objects");
    
    /**
     * Спрайт игрока
     */
    private Sprite playerSprite;
    
    /**
     * Список контроллеров
     */
    private final List<BasicSpriteController> controllers = new ArrayList<>();
    
    /**
     * Число ботов
     */
    private int botsCount = 3;
    
    /**
     * Инициализация игровых переменных
     */
    @Override
    public void initResources() { 
        try {
            // Загрузка изображений игрока и ботов
            BufferedImage playerImage = ImageIO.read(new File("resources/PRIMITIVE_PLANT.png"));
            BufferedImage botImage = ImageIO.read(new File("resources/PRIMITIVE_ANIMAL.png"));
            
            // Создание спрайта игрока
            playerSprite = new Sprite();
            // Настройка параметров
            playerSprite.setSpeed(0.1);
            // Установка представления
            playerSprite.getSpriteView().setColor(Color.GREEN);
            playerSprite.getSpriteView().setIcon(playerImage);
            // Установка позиции
            playerSprite.setPosition(new Point(320, 240));

            // Создание спрайтов ботов
            Random r = new Random();
            for(int i = 0; i < 3; i++)
            {
                // Создание спрайта бота
                Sprite botSprite = new Sprite();
                // Установка параметров
                botSprite.setSpeed(0.1);
                // Установка представления
                botSprite.getSpriteView().setColor(Color.BLUE);
                botSprite.getSpriteView().setIcon(botImage);
                // Установка позиции
                botSprite.setPosition(new Point(160 + r.nextInt(320), 120 + r.nextInt(240)));
                // Добавление спрайта бота в группу спрайтов, участвующих в коллизиях
                spriteGroup.add(botSprite);
                // Добавление контроллера ИИ спрайту бота
                controllers.add(new AISpriteController(this, botSprite, playerSprite));
            }
            
            // Загрузка изображения для игрового фона
            bg = new ImageBackground(ImageIO.read(new File("resources/background.jpg")));
            // Установка размеров viewport
            bg.setClip(0, 0, this.dimensions().width, this.dimensions().height);

            // Прикрепить спрайтовую группу к игровому фону
            spriteGroup.setBackground(bg);
            
            // Добавить спрайт игрока в спрайтовую группу
            spriteGroup.add(playerSprite);
            
            // Добавить контроллер игрока для спрайта игрока
            controllers.add(new PlayerSpriteController(this, playerSprite));
        } catch (IOException ex) {
            Logger.getLogger("main").log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Обновление игровых переменных
     * 
     * @param elapsedTime - время, прошедшее с момента последнего обновления
     */
    @Override
    public void update(long elapsedTime) {
        // Обновить все контроллеры
        for(int i = 0; i < controllers.size(); i++) {
            controllers.get(i).update(elapsedTime);
        }
        
        // Обновить спрайтовую группу
        spriteGroup.update(elapsedTime);
        
        // Обновить игровой фон
        bg.update(elapsedTime);
    }

    /**
     * Рендеринг игровых объектов на экран
     * 
     * @param g - графический объект рендеринга игры
     */
    @Override
    public void render(Graphics2D g) {
        bg.render(g);                   // Рендеринг игрового фона         
        spriteGroup.render(g);          // Рендеринг спрайтовой группы
        
        // Установка спрайта в центр игрвого фона
        if (playerSprite != null)
        {
            bg.setToCenter(playerSprite);
        }
    }
    
    /**
     * Текущая позиция координат мыши
     * 
     * @return - точка (Point) с координатами x и y
     */
    public Point mousePosition() {
        Point p = new Point(this.getMouseX(), this.getMouseY());
        p.x += bg.getX();
        p.y += bg.getY();
        return p;
    }
    
    /**
     * Возвращает размеры окна для изображения
     * 
     * @return размеры окна (Dimension)
     */
    public Dimension dimensions() {
        return new Dimension(640, 480);
    }
    
    /**
     * Полная ширина игрового поля
     */
    public static int totalWidth = 6000;
    
    /**
     * Полная высота игрового поля
     */
    public static int totalHeight = 6000;
}
