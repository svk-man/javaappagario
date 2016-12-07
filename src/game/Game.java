package game;

import game.models.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import game.controllers.AIController;
import game.controllers.Controller;
import game.controllers.PlayerController;
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
 *
 */
public class Game extends com.golden.gamedev.Game {
    /**
     * Фон игры
     */
    private ImageBackground bg;
    
    /**
     * Группа объектов, участвующих в коллизиях
     */
    private final SpriteGroup spriteGroup = new SpriteGroup("Objects");
    /**
     * Спрайт игрока
     */
    private Sprite playerSprite;
    /**
     * Список контроллеров
     */
    private final List<Controller> controllers = new ArrayList<>();
    /**
     * Число ботов
     */
    private int botsCount = 3;
    
    @Override
    public void initResources() { 
        try {
            BufferedImage playerImage = ImageIO.read(new File("resources/PRIMITIVE_PLANT.png"));
            BufferedImage botImage = ImageIO.read(new File("resources/PRIMITIVE_ANIMAL.png"));
            
            playerSprite = new Sprite();
            playerSprite.setSpeed(0.1);
            playerSprite.getSpriteView().setColor(Color.GREEN);
            playerSprite.getSpriteView().setIcon(playerImage);
            playerSprite.setPosition(new Point(320, 240));

            Random r = new Random();
            for(int i = 0; i < 3; i++)
            {
                Sprite botSprite = new Sprite();
                botSprite.setSpeed(0.1);
                botSprite.getSpriteView().setColor(Color.BLUE);
                botSprite.getSpriteView().setIcon(botImage);
                botSprite.setPosition(new Point(160 + r.nextInt(320), 120 + r.nextInt(240)));
                spriteGroup.add(botSprite);
                controllers.add(new AIController(this, botSprite, playerSprite));
            }
            
            bg = new ImageBackground(ImageIO.read(new File("resources/background.jpg")));
            bg.setClip(0, 0, this.dimensions().width, this.dimensions().height);

            spriteGroup.setBackground(bg);            
            spriteGroup.add(playerSprite);
            
            controllers.add(new PlayerController(this, playerSprite));
        } catch (IOException ex) {
            Logger.getLogger("main").log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(long elapsedTime) {
        for(int i = 0; i < controllers.size(); i++) {
            controllers.get(i).update(elapsedTime);
        }        
        spriteGroup.update(elapsedTime);
        bg.update(elapsedTime);
    }

    @Override
    public void render(Graphics2D g) {
        bg.render(g);                       
        spriteGroup.render(g);
        
        if (playerSprite != null)
        {
            bg.setToCenter(playerSprite);
        }
    }
    
    /**
     * Текущая позиция координат мыши
     * @return 
     */
    public Point mousePosition() {
        Point p = new Point(this.getMouseX(), this.getMouseY());
        p.x += bg.getX();
        p.y += bg.getY();
        return p;
    }
    
    /**
     * Возвращает размеры окна для изображения
     * @return 
     */
    public Dimension dimensions() {
        return new Dimension(640, 480);
    }
    
    /**
     * Полная ширина поля
     */
    public static int totalWidth = 6000;
    /**
     * Полная высота поля
     */
    public static int totalHeight = 6000;
}
