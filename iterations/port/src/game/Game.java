package game;

import lib.GameFont;
//import lib.GameFontManager;
import game.models.Sprite;
import lib.SpriteGroup;
import lib.ImageBackground;
import game.controllers.AIAgarController;
import game.controllers.AISpriteController;
import game.controllers.BasicSpriteController;
import game.controllers.PlayerSpriteController;
import game.models.Agar;
import java.awt.BasicStroke;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Игра
 * 
 * @author mypc
 */
public class Game extends lib.Game {
    /**
     * Запущена игра или нет
     */
    boolean isRunning = false;
    
    /**
     * Объявление конца игры
     */
    boolean isGameOver = false;
    
    /**
     * Показывается ли список особенностей игры или нет
     */
    boolean isFeatureList = true;
    
    /**
     * Цвет персонажа игрока
     */
    Color playerColor = Color.green;
    
    /**
     * Список возможных цветов персонажа
     */
    ArrayList<Color> playerColorList = new ArrayList<>(Arrays.asList(
        Color.black,
        Color.blue,
        Color.cyan,
        Color.gray,
        Color.green,
        Color.lightGray,
        Color.magenta,
        Color.orange,
        Color.pink,
        Color.red,
        Color.white,
        Color.yellow,
        new Color(153, 51, 153),
        new Color(0, 255, 0)
    ));
    
    GameFont font72Black;
    GameFont font72Blue;
    GameFont font24Blue;
    GameFont font18Blue;
    GameFont font60Blue;
    GameFont font48Blue;
    GameFont font27Black;
    GameFont font14Black;
    
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
                playerSprite.getSpriteView().setColor(playerColor);
                playerSprite.getSpriteView().setIcon(playerImage);
                // Установка позиции
                playerSprite.setPosition(new Point(320, 240));

                // Создание спрайтов ботов
                trySpawnBot();
                
                // Создание агар
                trySpawnAgar();
            
                // Генерация игрового фона
                bg = new ImageBackground(ImageIO.read(new File("resources/background.jpg")), totalWidth, totalHeight);
                bg.setClip(0, 0, this.dimensions().width, this.dimensions().height);
                bg.setTotalClip(totalWidth, totalHeight);

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
            font72Black = new GameFont(72, Color.BLACK);
            font72Blue = new GameFont(72, Color.BLUE);
            font24Blue = new GameFont(24, Color.BLUE);
            font18Blue = new GameFont(18, Color.BLUE);
            font60Blue = new GameFont(60, Color.BLUE);
            font48Blue = new GameFont(48, Color.BLUE);
            font27Black = new GameFont(27, Color.BLACK);
            font14Black = new GameFont(14, Color.BLACK);
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
                    if (!isFeatureList) {
                        this.initiateStartGame();
                    } else {
                        isFeatureList = false;
                    }
                }
                if (click()) {
                    playerColor = this.identifyColorByClickOnScene(getMouseX(), getMouseY(), 220, 220, 220, 20, playerColorList);
                }
            }
        }
    }

    boolean[] sceneRendered = {false, false, false};
    /**
     * Рендеринг игровых объектов на экран
     * 
     * @param g - графический объект рендеринга игры
     */
    

    public void renderInContext(lib.Graphics2D g) {
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
            //GameFontManager gfm = new GameFontManager();
            //GameFont f = gfm.getFont(font);
            font27Black.drawString(g, "Число съеденного агара: " + String.valueOf(playerSprite.agarCollected()), 0, 0);
        
            // Вывод на экран числа съеденных агар врагами
            if (botsSpriteList.size() > 0) {
                font27Black.drawString(g, "У врагов:", 0, 50);
            }
        
            for(int i = 0; i < botsSpriteList.size(); i++) {
                String text = "Враг " + String.valueOf(i) + ": " + String.valueOf(botsSpriteList.get(i).agarCollected());
                font27Black.drawString(g, text, 0, i * 50 + 100);
            }
        } else {
            /*
            if (isGameOver) {
                if (!sceneRendered[0]){
                    this.renderGameOverScene(g);
                    sceneRendered[0] = true;
                    sceneRendered[1] = false;
                    sceneRendered[2] = false;
                }
            } else if(isFeatureList) {
                if (!sceneRendered[1]){                
                    this.renderFeatureScene(g);
                    sceneRendered[0] = false;
                    sceneRendered[1] = true;
                    sceneRendered[2] = false;
                }
            } else {
                if (!sceneRendered[2]){ 
                    this.renderStartScene(g);
                    sceneRendered[0] = false;
                    sceneRendered[1] = false;
                    sceneRendered[2] = true;
                }
            }*/
            if (isGameOver) {
                this.renderGameOverScene(g);
            } else if(isFeatureList) {  
                this.renderFeatureScene(g);
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
        return bg.getTotalPosition(p);
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
        List <lib.Sprite> generatedSprites = new ArrayList <lib.Sprite>();
        
        Random r1 = new Random();
        Random r2 = new Random();
        while (generatedCount < quantity) {
            int x = r1.nextInt(radius);
            int y = r2.nextInt(radius);
            
            if (x >= 0 && x <= Game.totalWidth && y >= 0 && y <= Game.totalHeight) {
                lib.Sprite generatedSprite = new lib.Sprite(spriteImage, x, y);
                
                // Определить, пересекается ли сгенерированный спрайт хотя бы
                // с одним спрайтом из групп спрайтов, с которыми нельзя пересекаться
                boolean collide = false;
                for (SpriteGroup spriteGroup : spriteGroups) {
                    // Получить спрайты очередной спрайтовой группы
                    lib.Sprite[] sprites = spriteGroup.getSprites();
                    
                    for (lib.Sprite sprite : sprites) {
                        if (sprite != null) {
                            collide = collide || GameMath.collide(sprite, generatedSprite);
                        }
                    }
                }
                
                // Определить, пересекается ли сгенерированный спрайт с ранее сгенерированными спрайтами
                for (lib.Sprite sprite : generatedSprites) {
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
            lib.Sprite[] sprites = agarGroup.getSprites();
            
            // Сформировать временную группу агар
            SpriteGroup newAgarGroup = agarGroup;
            newAgarGroup.clear();
            
            for (lib.Sprite sprite : sprites) {
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
            lib.Sprite[] sprites = spriteGroup.getSprites();
            
            // Сформировать временную прайтовую группу
            SpriteGroup newSpriteGroup = spriteGroup;
            newSpriteGroup.clear();
            
            for (lib.Sprite sprite : sprites) {
                if (sprite != null && !Objects.equals(sprite, playerSprite) && !botsSpriteList.contains(sprite)) {
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
    public int spriteInSpriteGroup(lib.Sprite searchSprite) {
        // Получить спрайты спрайтовой группы
        lib.Sprite[] sprites = spriteGroup.getSprites();
            
        // Определить индекс спрайта
        boolean isFound = false;
        int index = -1;
        for (lib.Sprite sprite : sprites) {
            if (!isFound && sprite != null && !Objects.equals(sprite, playerSprite)) {
                index++;
                isFound = isFound || Objects.equals(sprite, searchSprite);
            }
        }
        
        return index;
    }
    

    /**
     * Рендеринг стартовой сцены
     * 
     * @param g - графический объект рендеринга игры
     */
    public void renderStartScene(lib.Graphics2D g) {
        
        //g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight(), Color.YELLOW);
        //GameFontManager gfm = new GameFontManager();
        //GameFont f = gfm.getFont(font);

        font72Blue.drawString(g, "AGARIO", 200, 20);
        //f = gfm.getFont(font);
        font24Blue.drawString(g, "Цвет вашего персонажа", 170, 110);
        
        //g.setColor(playerColor);
        g.fillRect(310, 150, 30, 30, playerColor);
        //g.setColor(playerColor.darker());
        //g.setStroke(new BasicStroke(2));
        //g.drawRect(310, 150, 30, 30);
        
        //g.setColor(Color.BLUE);
        font18Blue.drawString(g, "Выбрать другой цвет", 213, 190);
        
        this.drawColorRectangles(g, 220, 220, 220, 20, playerColorList);
        
        //g.setColor(Color.BLUE);
        font18Blue.drawString(g, "КЛИК ПО ЦВЕТУ", 257, 290);
        font60Blue.drawString(g, "PLAY GAME", 155, 320);
        font48Blue.drawString(g, "НАЖМИТЕ ПРОБЕЛ", 110, 390);
    }
    
    /**
     * Отрисовать прямоугольники, заполненные цветом, в заданной области
     * 
     * @param g - графический объект, на котором будем рисовать
     * @param x - начальная точка отсчета (координата x)
     * @param y - начальная точка отсчета (координата y)
     * @param areaWidth - ширина области, которую нужно заполнить прямоугольниками
     * @param rectWidth - ширина заполняемого прямоугольника
     * @param colors - список цветов для заполнения
     */
    private void drawColorRectangles(lib.Graphics2D g, int x, int y, int areaWidth, int rectWidth, ArrayList<Color> colors) {
        int cols = areaWidth / (rectWidth + 10);
        int rows = colors.size() / cols + 1;
        int padding = (areaWidth - (cols * rectWidth)) / cols;
        
        int i = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (i < colors.size()) {
                    //g.setColor(colors.get(i));
                    g.fillRect(x + padding/2 + col * rectWidth + padding * col, y + padding/2 + row * rectWidth + padding * row, rectWidth, rectWidth, colors.get(i));
                    //g.setColor(colors.get(i).darker());
                    //g.setStroke(new BasicStroke(2));
                    //g.drawRect(x + padding/2 + col * rectWidth + padding * col, y + padding/2 + row * rectWidth + padding * row, rectWidth, rectWidth);
                    i++;
                }
            }
        }
    }
    
    /**
     * Определить цвет прямоугольника по клику в области сцены
     * 
     * @param searchX - координата X, полученная кликом мыши
     * @param searchY - координата Y, полученная кликом мыши
     * @param x - начальная точка отсчета (координата x)
     * @param y - начальная точка отсчета (координата y)
     * @param areaWidth - ширина области, которая заполнена прямоугольниками
     * @param rectWidth - ширина заполненного прямоугольника
     * @param colors - список цветов, который был задан для заполнения прямоугольников
     * @return найденный цвет (Color)
     */
    private Color identifyColorByClickOnScene(int searchX, int searchY, int x, int y, int areaWidth, int rectWidth, ArrayList<Color> colors) {
        int cols = areaWidth / (rectWidth + 10);
        int rows = colors.size() / cols + 1;
        int padding = (areaWidth - (cols * rectWidth)) / cols;
        
        int i = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (i < colors.size()) {
                    if ((searchX >= x + padding/2 + col * rectWidth + padding * col && searchX <= rectWidth + x + padding/2 + col * rectWidth + padding * col) && 
                        (searchY >= y + padding/2 + row * rectWidth + padding * row && searchY <= rectWidth + y + padding/2 + row * rectWidth + padding * row)
                    ) {
                        return colors.get(i);
                    }
                    
                    i++;
                }
            }
        }
        
        return Color.green;
    }
    
    /**
     * Рендеринг конца игры
     * 
     * @param g - графический объект рендеринга игры
     */
    public void renderGameOverScene(lib.Graphics2D g) {
        resetCamera();
        g.fillRect(0, 0, getWidth(), getHeight(), Color.LIGHT_GRAY);
        //GameFontManager gfm = new GameFontManager();
        font72Blue.drawString(g, "AGARIO", 200, 100);
        //f = gfm.getFont(font);
        font60Blue.drawString(g, "GAME OVER", 165, 200);
        font48Blue.drawString(g, "ИТОГОВЫЙ СЧЕТ: " + playerSprite.agarCollected(), 90, 280);
        //f = gfm.getFont(font);
        font48Blue.drawString(g, "НАЖМИТЕ ПРОБЕЛ", 120, 360);
    }
    
    /**
     * Рендеринг списка особенностей игры
     * 
     * @param g - графический объект рендеринга игры
     */
    public void renderFeatureScene(lib.Graphics2D g) {
        //g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight(), Color.WHITE);
        //GameFontManager gfm = new GameFontManager();
        //GameFont f = gfm.getFont(font);
        //g.setColor(Color.BLUE);
        font24Blue.drawString(g, "Особенности игры \"AGARIO\"", 150, 0);

        //g.setColor(Color.BLACK);
        //g.setStroke(new BasicStroke(2));
        //g.drawRect(10, 35, this.dimensions().width - 10, 355);
        
        //font = new Font("Monospaced", Font.CENTER_BASELINE, 14);
        //f = gfm.getFont(font);
        font14Black.drawString(g, "1 итерация: по конечному полю двигается бактерия в направлении мыши с", 15, 35);
        font14Black.drawString(g, "константной скоростью и находится в центре экрана.", 15, 50);
        font14Black.drawString(g, "2 итерация:", 15, 65);
        font14Black.drawString(g, "- Размер поля - произвольный.", 15, 80);
        font14Black.drawString(g, "- Реализация препятствий и их случайной расстановки на поле.", 15, 95);
        font14Black.drawString(g, "- Реализация объекта агара и его случайного появления на поле. У агара есть", 15, 110);
        font14Black.drawString(g, "лимит по количеству объектов на поле. Агар появляется спустя определенный", 15, 125);
        font14Black.drawString(g, "промежуток времени и появляется на части поля, но не под игроком.", 15, 140);
        font14Black.drawString(g, "- Реализация поглощения агара игроком.", 15, 155);
        font14Black.drawString(g, "3 итерация:", 15, 170);
        font14Black.drawString(g, "- Реализация изменения размера игрока и врагов в нелинейной зависимости", 15, 185);
        font14Black.drawString(g, "от числа съеденного агара.", 15, 200);
        font14Black.drawString(g, "- Реализация счетчика агара и его отображения.", 15, 215);
        font14Black.drawString(g, "4 итерация:", 15, 230);
        font14Black.drawString(g, "- Реализация появления новых врагов по мере исчезания старых.", 15, 245);
        font14Black.drawString(g, "- Реализация поглощения врагов.", 15, 260);
        font14Black.drawString(g, "- Реализация экрана поражения в игре и возможности начать новую игру (по", 15, 275);
        font14Black.drawString(g, "нажатию на клавишу клавиатуры).", 15, 290);
        font14Black.drawString(g, "5 итерация: еда может двигаться.", 15, 305);
        font14Black.drawString(g, "6 итерация:", 15, 320);
        font14Black.drawString(g, "- Выбор цвета игрока перед началом игры из списка заданных цветов.", 15, 335);
        font14Black.drawString(g, "- Вывод списка возможностей игры и выбранных модификаций при запуске игры.", 15, 350);
        font14Black.drawString(g, "- Установлен предельный размер клетки, больше которого нельзя набрать.", 15, 365);

        font24Blue.drawString(g, "НАЖМИТЕ ПРОБЕЛ", 200, 390);
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
        playerColor = Color.GREEN;
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
