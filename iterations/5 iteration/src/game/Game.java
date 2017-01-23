package game;

import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.GameFontManager;
import game.models.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import game.controllers.AIAgarController;
import game.controllers.AISpriteController;
import game.controllers.BasicSpriteController;
import game.controllers.PlayerSpriteController;
import game.models.Agar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
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
     * Запущена игра или нет
     */
    boolean isRunning = false;
    
    /**
     * Объявление конца игры
     */
    boolean isGameOver = false;
    
    /**
     * Полная ширина поля
     */
    public static int totalWidth = 3000;
    /**
     * Полная высота поля
     */
    public static int totalHeight = 1500;
    
    /**
     * Игровой фон
     */
    private ImageBackground bg;
    
    /**
     * Получить ширину игрового фона, т.е. полную ширину игры
     * 
     * @return ширина (int)
     */
    //public int getBgWidth() {
    //    return bg.getWidth();
    //}
    
    /**
     * Получить высоту игрового фона, т.е. полную высоту игры
     * 
     * @return высота (int)
     */
    //public int getBgHeight() {
    //    return bg.getHeight();
    //}
    
    /**
     * Группа спрайтов, участвующих в коллизиях
     */
    private SpriteGroup spriteGroup = new SpriteGroup("Objects");
    
    /**
     * Группа препятствий, участвующих в коллизиях
     */
    private final SpriteGroup obstacleGroup = new SpriteGroup("Obstacles");
    
    /**
     * Группа агар, участвующих в коллизиях
     */
    private SpriteGroup agarGroup = new SpriteGroup("Agars");
       
    /**
     * Спрайт игрока
     */
    private Sprite playerSprite;
    
    /**
     * Возвращает спрайт игрока
     * 
     * @return спрайт игрока (Sprite)
     */
    public Sprite playerSprite() {
        return playerSprite;
    }
    
    /**
     * Список контроллеров
     */
    private final List<BasicSpriteController> controllers = new ArrayList<>();
    
    /**
     * Возвращает контроллер по заданному индексу
     * 
     * @param index - индекс
     * @return контроллер (BasicSpriteController)
     */
    public BasicSpriteController controller(int index) {
        return controllers.get(index);
    }
    
    /**
     * Возвращает список контроллеров
     * 
     * @return список контроллеров (List<BasicSpriteController> )
     */
    public List<BasicSpriteController> controllers() {
        return controllers;
    }
    
    /**
     * Список контроллеров агара
     */
    private final List<AIAgarController> agarControllers = new ArrayList<>();
    
    /**
     * Возвращает контроллер агара по заданному индексу
     * 
     * @param index - индекс
     * @return контроллер агара (AIAgarController)
     */
    public AIAgarController agarController(int index) {
        return agarControllers.get(index);
    }
    
    /**
     * Возвращает список контроллеров агара
     * 
     * @return список контроллеров агара (List<AIAgarController> )
     */
    public List<AIAgarController> agarControllers() {
        return agarControllers;
    }
    
    /**
     * Представление бота
     */
    BufferedImage botImage = null;
    
    /**
     * Число ботов
     */
    private int botsCount = 3;
    
    /**
     * Представление агара
     */
    BufferedImage agarImage = null;
    
    /**
     * Лимит по количеству агара на поле
     */
    private final int agarRequiredQuantity = 400;
    
    /**
     * Время, спустя которое появляется агар
     */
    private final int agarRespawnPeriod = 2000;
    
    /**
     * Время последнего появления
     */
    private long lastRespawnTime = 0;
    
    /**
     * Число агара, появляемое за раз
     */
    private final int agarRespawnQuantity = 25;
    
    /**
     * Время последнего изменения направления движения агара
     */
    private long agarLastChangedDirectionTime = 0;
    
    /**
     * Время, спустя которое должно измениться направление движения агара
     */
    private int agarChagedDirectionPeriod = 2000;
    
    /**
     * Список агар
     */
    private final List<Agar> agarsList = new ArrayList<>();
    
    /**
     * Возвращает агар по заданному индексу
     * 
     * @param index - индекс
     * @return агар (Agar)
     */
    public Agar agar(int index) {
        return agarsList.get(index);
    }
    
    /**
     * Возвращает список агар
     * 
     * @return список агар (List<Agar>)
     */
    public List<Agar> agarsList() {
        return agarsList;
    }
    
    /**
     * Список ботов
     */
    private final List<Sprite> botsSpriteList = new ArrayList<>();
    
    /**
     * Возвращает бота по заданному индексу
     * 
     * @param index - индекс
     * @return бот (Sprite)
     */
    public Sprite botSprite(int index) {
        return botsSpriteList.get(index);
    }
    
    /**
     * Возвращает список ботов
     * 
     * @return список ботов (List<Sprite>)
     */
    public List<Sprite> botsSpriteList() {
        return botsSpriteList;
    }
    
    /**
     * Менеджер коллизий
     */
    private CollisionManager manager; 
    
    /**
     * Инициализация игровых переменных
     */
    @Override
    public void initResources() { 
        try {
            if (isRunning) {
                // Загрузка изображений игровых сущностей
                BufferedImage playerImage = ImageIO.read(new File("resources/PRIMITIVE_PLANT.png"));
                botImage = ImageIO.read(new File("resources/PRIMITIVE_ANIMAL.png"));
                BufferedImage obstacleImage = ImageIO.read(new File("resources/OBSTACLE.png"));
                agarImage = game.views.AgarView.image();
            
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
                trySpawnBot();
                
                // Создание агар
                trySpawnAgar();
            
                // Генерация игрового фона
                BufferedImage tile = ImageIO.read(new File("resources/background.jpg"));
                BufferedImage bi = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = bi.createGraphics();
                int tileWidth = tile.getWidth();
                int tileHeight = tile.getHeight();
            
                for (int y = 0; y < totalHeight; y += tileHeight) {
                    for (int x = 0; x < totalWidth; x += tileWidth) {
                        g2d.drawImage(tile, x, y, null);
                    }
                }
            
                g2d.dispose();
                bg = new ImageBackground(bi);

                // Установка размеров viewport
                bg.setClip(0, 0, this.dimensions().width, this.dimensions().height);

                // Прикрепить спрайтовую группу к игровому фону
                spriteGroup.setBackground(bg);
            
                // Добавить спрайт игрока в спрайтовую группу
                spriteGroup.add(playerSprite);
            
                // Добавить контроллер игрока для спрайта игрока
                controllers.add(new PlayerSpriteController(this, playerSprite));
            
                // Сгенерировать препятствия вокруг игрока
                SpriteGroup[] groupsForObstacle = { spriteGroup };
                int size = totalWidth > totalHeight ? totalWidth : totalHeight;
                this.generateSpritesAroundPlayer(obstacleImage, playerSprite, size, 15, obstacleGroup, groupsForObstacle);
            
                // Прикрепить группу препятствий к игровому фону
                obstacleGroup.setBackground(bg);
            
                // Прикрепить группу агар к игровому фону
                agarGroup.setBackground(bg);
            
                manager = new CollisionManager(this, spriteGroup, obstacleGroup, agarGroup);
            }
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
        if (isRunning) {
            // Обновить все контроллеры
            for(int i = 0; i < controllers.size(); i++) {
                controllers.get(i).update(elapsedTime);
            }
            
            // Обновить контроллеры агар
            for(int i = 0; i < agarControllers.size(); i++) {
                agarControllers.get(i).update(elapsedTime);
            }
            
            // Обновить группу агар
            agarGroup.update(elapsedTime);
        
            // Обновить спрайтовую группу
            spriteGroup.update(elapsedTime);
            
            // Сменить направление движения у всех агар
            long curTime = System.nanoTime();
            Random r1 = new Random();
            Random r2 = new Random();
            if ((curTime - agarLastChangedDirectionTime) / 1.0E+6 >= agarChagedDirectionPeriod) {
                agarLastChangedDirectionTime = System.nanoTime();
                for(int i = 0; i < agarsList.size(); i++) {
                    int angle = GameMath.angle(agarsList.get(i).getPosition(), new Point(r1.nextInt(totalWidth), r2.nextInt(totalHeight)));
                    agarsList.get(i).setDirection(angle);
               }
            }
        
            // Обновить игровой фон
            bg.update(elapsedTime);

            // Попытаться добавить бота
            this.trySpawnBot();
            
            // Попытаться добавить агар
            curTime = System.nanoTime();
            if ((curTime - lastRespawnTime) / 1.0E+6 >= agarRespawnPeriod) {
                this.trySpawnAgar();
            }
            
            // Проверить коллизии
            manager.checkCollision();
        } else {
            if (isGameOver) {
                if (keyPressed(KeyEvent.VK_SPACE)) {
                    isGameOver = false;
                }
            } else {
                if (keyPressed(KeyEvent.VK_SPACE)) {
                    this.initiateStartGame();
                }
            }
        }
    }

    /**
     * Рендеринг игровых объектов на экран
     * 
     * @param g - графический объект рендеринга игры
     */
    @Override
    public void render(Graphics2D g) {
        if (isRunning) {
            bg.render(g);                   // Рендеринг игрового фона         
            spriteGroup.render(g);          // Рендеринг спрайтовой группы
            obstacleGroup.render(g);        // Рендеринг группы препятствий
            agarGroup.render(g);            // Рендеринг группы агар
        
            // Установка спрайта в центр игрвого фона
            if (playerSprite != null)
            {
                bg.setToCenter(playerSprite);
            }
        
            // Вывод на экран числа съеденного агара игроком
            GameFontManager gfm = new GameFontManager();
            Font font = new Font("Dialog", Font.PLAIN, 27);
            GameFont f = gfm.getFont(font);
            f.drawString(g, "Число съеденного агара: " + String.valueOf(playerSprite.agarCollected()), 0, 0);
        
            // Вывод на экран числа съеденных агар врагами
            if (botsSpriteList.size() > 0) {
                f.drawString(g, "У врагов:", 0, 50);
            }
        
            for(int i = 0; i < botsSpriteList.size(); i++) {
                String text = "Враг " + String.valueOf(i) + ": " + String.valueOf(botsSpriteList.get(i).agarCollected());
                f.drawString(g, text, 0, i * 50 + 100);
            }
        } else {
            if (isGameOver) {
                this.renderGameOverScene(g);
            } else {
                this.renderStartScene(g);
            }
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
     * Сгенерировать спрайты вокруг игрока при заданном радиусе
     * 
     * @param spriteImage - изображение спрайта
     * @param player - игрок
     * @param radius - радиус порождения
     * @param quantity - количество спрайтов, которые нужно породить
     * @param targetSpriteGroup - группа спрайтов, в которую в конечном итоге нужно будет положить спрайты
     * @param spriteGroups - группы спрайтов, с которыми нельзя пересекаться 
     */
    public void generateSpritesAroundPlayer(
        BufferedImage spriteImage,
        Sprite player,
        int radius,
        int quantity,
        SpriteGroup targetSpriteGroup,
        SpriteGroup[] spriteGroups
    ) {
        int generatedCount = 0;                              // Число сгенерированных спрайтов
        // Сгенерированные спрайты
        List <com.golden.gamedev.object.Sprite> generatedSprites = new ArrayList <com.golden.gamedev.object.Sprite>();
        
        Random r1 = new Random();
        Random r2 = new Random();
        while (generatedCount < quantity) {
            int x = r1.nextInt(radius);
            int y = r2.nextInt(radius);
            
            if (x >= 0 && x <= Game.totalWidth && y >= 0 && y <= Game.totalHeight) {
                com.golden.gamedev.object.Sprite generatedSprite = new com.golden.gamedev.object.Sprite(spriteImage, x, y);
                
                // Определить, пересекается ли сгенерированный спрайт хотя бы
                // с одним спрайтом из групп спрайтов, с которыми нельзя пересекаться
                boolean collide = false;
                for (SpriteGroup spriteGroup : spriteGroups) {
                    // Получить спрайты очередной спрайтовой группы
                    com.golden.gamedev.object.Sprite[] sprites = spriteGroup.getSprites();
                    
                    for (com.golden.gamedev.object.Sprite sprite : sprites) {
                        if (sprite != null) {
                            collide = collide || GameMath.collide(sprite, generatedSprite);
                        }
                    }
                }
                
                // Определить, пересекается ли сгенерированный спрайт с ранее сгенерированными спрайтами
                for (com.golden.gamedev.object.Sprite sprite : generatedSprites) {
                    collide = collide || GameMath.collide(sprite, generatedSprite);
                }
                if (!collide) {
                    generatedCount++;
                    targetSpriteGroup.add(generatedSprite);
                    generatedSprites.add(generatedSprite);
                }
            }
            
        }
    }
    
    /**
     * Добавление агар, если это возможно
     */
    private void trySpawnAgar() {
        lastRespawnTime = System.nanoTime();
        if (agarGroup.getSize() < this.agarRequiredQuantity) {
            SpriteGroup[] groupsForAgar = { spriteGroup, obstacleGroup };
            this.generateSpritesAroundPlayer(agarImage, playerSprite, 3000, this.agarRespawnQuantity, agarGroup, groupsForAgar);
        
            // Получить спрайты группы агар
            com.golden.gamedev.object.Sprite[] sprites = agarGroup.getSprites();
            
            // Сформировать временную группу агар
            SpriteGroup newAgarGroup = agarGroup;
            newAgarGroup.clear();
            
            for (com.golden.gamedev.object.Sprite sprite : sprites) {
                if (sprite != null) {
                    // Создание агара
                    Agar agar = new Agar();
                    // Установка параметров
                    agar.setSpeed(0.1);
                    // Установка представления
                    agar.setImage(agarImage);
                    // Установка позиции
                    agar.setPosition(new Point((int)sprite.getX(), (int)sprite.getY()));
                    // Добавление агара в группу агар, участвующих в коллизиях
                    newAgarGroup.add(agar);
                    // Добавление контроллера ИИ агару
                    agarControllers.add(new AIAgarController(this, agar));
                    // Добавление агара в список агар
                    agarsList.add(agar);
                }
            }
            
            agarGroup = newAgarGroup;
        }
    }
    
    /**
     * Добавление бота, если это возможно
     */
    private void trySpawnBot() {
        if (botsSpriteList.size() < botsCount) {
            SpriteGroup[] groupsForBots = { spriteGroup, obstacleGroup };
            this.generateSpritesAroundPlayer(botImage, playerSprite, 3000, botsCount - botsSpriteList.size(), spriteGroup, groupsForBots);
            
            // Получить спрайты спрайтовой группы
            com.golden.gamedev.object.Sprite[] sprites = spriteGroup.getSprites();
            
            // Сформировать временную прайтовую группу
            SpriteGroup newSpriteGroup = spriteGroup;
            newSpriteGroup.clear();
            
            for (com.golden.gamedev.object.Sprite sprite : sprites) {
                if (sprite != null && sprite != playerSprite && !botsSpriteList.contains(sprite)) {
                    // Создание спрайта бота
                    Sprite botSprite = new Sprite();
                    // Установка параметров
                    botSprite.setSpeed(0.1);
                    // Установка представления
                    botSprite.getSpriteView().setColor(Color.BLUE);
                    botSprite.getSpriteView().setIcon(botImage);
                    // Установка позиции
                    botSprite.setPosition(new Point((int)sprite.getX(), (int)sprite.getY()));
                    // Добавление спрайта бота в группу спрайтов, участвующих в коллизиях
                    newSpriteGroup.add(botSprite);
                    // Добавление контроллера ИИ спрайту бота
                    controllers.add(new AISpriteController(this, botSprite, playerSprite));
                    // Добавление бота в список ботов
                    botsSpriteList.add(botSprite);
                } else if (sprite != null){
                    newSpriteGroup.add(sprite);
                }
            }
            
            spriteGroup = newSpriteGroup;
        }
    }
    
    /**
     * Возвращает индекс спрайта из спрайтовой группы
     * 
     * @param searchSprite - искаемый спрайт
     * @return индекс найденного спрайта, либо -1 в случае неудачи (int)
     */
    public int spriteInSpriteGroup(com.golden.gamedev.object.Sprite searchSprite) {
        // Получить спрайты спрайтовой группы
        com.golden.gamedev.object.Sprite[] sprites = spriteGroup.getSprites();
            
        // Определить индекс спрайта
        boolean isFound = false;
        int index = -1;
        for (com.golden.gamedev.object.Sprite sprite : sprites) {
            if (!isFound && sprite != null && sprite != playerSprite) {
                index++;
                isFound = isFound || sprite == searchSprite;
            }
        }
        
        return index;
    }
    
    /**
     * Рендеринг стартовой сцены
     * 
     * @param g - графический объект рендеринга игры
     */
    private void renderStartScene(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        GameFontManager gfm = new GameFontManager();
        Font font = new Font("Monospaced", Font.CENTER_BASELINE, 72);
        GameFont f = gfm.getFont(font);
        g.setColor(Color.BLUE);
        f.drawString(g, "AGARIO", 200, 100);
        font = new Font("Monospaced", Font.CENTER_BASELINE, 60);
        f = gfm.getFont(font);
        f.drawString(g, "PLAY GAME", 165, 200);
        font = new Font("Monospaced", Font.CENTER_BASELINE, 48);
        f = gfm.getFont(font);
        f.drawString(g, "НАЖМИТЕ ПРОБЕЛ", 120, 300);
    }
    
    /**
     * Рендеринг конца игры
     * 
     * @param g - графический объект рендеринга игры
     */
    private void renderGameOverScene(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        GameFontManager gfm = new GameFontManager();
        Font font = new Font("Monospaced", Font.CENTER_BASELINE, 72);
        GameFont f = gfm.getFont(font);
        g.setColor(Color.BLUE);
        f.drawString(g, "AGARIO", 200, 100);
        font = new Font("Monospaced", Font.CENTER_BASELINE, 60);
        f = gfm.getFont(font);
        f.drawString(g, "GAME OVER", 165, 200);
        font = new Font("Monospaced", Font.CENTER_BASELINE, 48);
        f = gfm.getFont(font);
        f.drawString(g, "ИТОГОВЫЙ СЧЕТ: " + playerSprite.agarCollected(), 90, 280);
        font = new Font("Monospaced", Font.CENTER_BASELINE, 48);
        f = gfm.getFont(font);
        f.drawString(g, "НАЖМИТЕ ПРОБЕЛ", 120, 360);
    }
    
    /**
     * Инициировать старт игры
     */
    public void initiateStartGame() {
        isRunning = true;
        isGameOver = false;
        this.initResources();
    }
    
    /**
     * Инициировать конец игры
     */
    public void initiateGameOver() {
        isRunning = false;
        isGameOver = true;
        spriteGroup.reset();
        agarGroup.reset();
        obstacleGroup.reset();
        botsSpriteList.clear();
        controllers.clear();
        agarControllers.clear();
        agarsList.clear();
    }
    
    /**
     * Возвращает размеры окна для изображения
     * 
     * @return размеры окна (Dimension)
     */
    public Dimension dimensions() {
        return new Dimension(640, 480);
    }
}
