package main;

import EntityFactory.EntityGenerator;
import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import enviroment.EnviromentManager;
import main.tile.TileManager;
import main.tile_interactive.InteractiveTile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTING
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile

    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public boolean fullScreenOn = false;

    //WORD SETTINGS
    public int maxWorldCol;
    public int maxWorldRow;
    public final int maxMap = 10;
    public int currentMap = 0;

    // FPS
    final int UPS = 60;
    final int FPS = 30;

    // SYSTEM
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound se = new Sound();
    Sound music = new Sound();
    public Collisionchecker cChecker = new Collisionchecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    public PathFinder pFinder = new PathFinder(this);
    SaveLoad saveLoad = new SaveLoad(this);
    EnviromentManager eManager = new EnviromentManager(this);
    public EntityGenerator eGenerator = new EntityGenerator(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this,keyH);
    public Entity obj[][] = new Entity[maxMap][30];
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity monster[][] = new Entity[maxMap][30];
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
    ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    public Entity projectileList[][] = new Entity[maxMap][20];

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int charactersState = 4;
    public final int optionState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    public final int sleepState = 9;
    public final int craftingState = 10;
    public final int ovenState = 11;

    // AREA
    public int currentArea;
    public int nextArea;
    public final int outside = 50;
    public final int indoor = 51;
    public final int dungeon = 52;

    public GamePanel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame() {

        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        eManager.setup();

        gameState = titleState;
        currentArea = outside;
    }
    public void resetGame(boolean restart) {

        currentArea = outside;
        player.setDefaultPositions();
        player.restoreStatus();
        player.resetCounter();
        aSetter.setMonster();
        aSetter.setNPC();
        if (restart) {
            player.setDefaultValues();
            aSetter.setObject();
            aSetter.setInteractiveTile();
            eManager.lighting.resetDay();
        }
    }
    public void startGameThrea() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double updateInterval = 1000000000.0 / UPS;
        double drawInterval   = 1000000000.0 / FPS;

        double deltaUpdate = 0;
        double deltaDraw   = 0;

        long lastTime = System.nanoTime();

        long timer = 0;
        int UPSCount = 0;
        int drawCount = 0;

        while (gameThread != null) {

            long currentTime = System.nanoTime();
            long elapsed = currentTime - lastTime;

            deltaUpdate += elapsed / updateInterval;
            deltaDraw   += elapsed / drawInterval;
            lastTime = currentTime;

            timer += (currentTime - lastTime);
            lastTime = currentTime;


            // UPDATE → 60 veces por segundo
            if (deltaUpdate >= 1) {
                update();
                deltaUpdate--;
                UPSCount++;
                timer++;
            }
            // DRAW → 30 veces por segundo
            if (deltaDraw >= 1) {
                repaint();
                deltaDraw--;
                drawCount++;
            }

            if(timer >= 60){
                System.out.println("UPS FPS: " + UPSCount);
                System.out.println("DRAW FPS: " + drawCount);
                UPSCount = 0;
                drawCount = 0;
                timer = 0;
            }

        }
    }
    public void update() {
        if(gameState == playState){
            // PLAYER
            player.update();

            //NPC
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null){
                    npc[currentMap][i].update();
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if(monster[currentMap][i] != null) {
                    if (monster[currentMap][i].alive && !monster[currentMap][i].dying) {
                        monster[currentMap][i].update();
                    }
                    if (!monster[currentMap][i].alive) {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }
            for (int i = 0; i < projectileList[1].length; i++) {
                if(projectileList[currentMap][i] != null) {
                    if (projectileList[currentMap][i].alive) {
                        projectileList[currentMap][i].update();
                    }
                    if (!projectileList[currentMap][i].alive) {
                        projectileList[currentMap][i] = null;
                    }
                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if(particleList.get(i) != null) {
                    if (particleList.get(i).alive) {
                        particleList.get(i).update();
                    }
                    if (!particleList.get(i).alive) {
                        particleList.remove(i);
                    }
                }
            }
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].update();
                }
            }
        }
        if(gameState == pauseState){
            // nothing for now
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart = 0;
        if(keyH.showDebugText){
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN
        if(gameState == titleState) {
            ui.draw(g2);
        }

        // OTHERS
        else {

            // TILE
            tileM.draw(g2);

            // INTERACTIVE TILE
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2);
                }
            }

            // ADD ENTITIES TO THE LIST
            entityList.add(player);

            for (int i = 0; i < npc[1].length; i++) {
                if(npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0; i < obj[1].length; i++) {
                if(obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if(monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (int i = 0; i < projectileList[1].length; i++) {
                if(projectileList[currentMap][i] != null) {
                    entityList.add(projectileList[currentMap][i]);
                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if(particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
                }
            }

            eManager.update();

            // SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    return Integer.compare(e1.worldY, e2.worldY);
                }
            });

            // DRAW ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            // EMPTY ENTITY LIST
            entityList.clear();

            // ENVIROMENT
            eManager.draw(g2);

            // UI
            ui.draw(g2);
        }

        // DEBUG
        if(keyH.showDebugText){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial",Font.PLAIN, 20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX " + player.worldX, x, y); y += lineHeight;
            g2.drawString("WorldY " + player.worldY, x, y); y += lineHeight;
            g2.drawString("Col " + (player.worldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
            g2.drawString("Row " + (player.worldY + player.solidArea.y)/tileSize, x, y); y += lineHeight;
            g2.drawString("Draw Time: " + passed, x, y); y += lineHeight;
            g2.drawString("God mode:" + keyH.godModeOn, x, y);

        }

        g2.dispose();
    }
    public void changeArea() {

        if (nextArea != currentArea){
            stopMusic();

            if (nextArea == outside) {
               playMusic(0);
            }
            if (nextArea == indoor) {
                playMusic(20);
            }
            if (nextArea == dungeon) {
                playMusic(21);
            }
            aSetter.setNPC();
        }

        currentArea = nextArea;
        aSetter.setMonster();
    }
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
