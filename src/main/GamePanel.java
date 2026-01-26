package main;

import entity.Entity;
import entity.Player;
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
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    int FPS = 60;
    //int FPSAtributes= 10;


    // SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound se = new Sound();
    Sound music = new Sound();
    public Collisionchecker cChecker = new Collisionchecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this,keyH);
    public Entity obj[] = new Entity[20];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];
    public InteractiveTile iTile[] = new InteractiveTile[50];
    ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int charactersState = 4;
    public final int optionState = 5;
    public final int gameOverState = 6;

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
        gameState = titleState;
    }
    public void retry() {
        player.setDefaultPositions();
        player.restoreLifeAndMana();
        aSetter.setMonster();
        aSetter.setNPC();

    }
    public void restart() {
        player.setDefaultValues();

        player.setItems();
        aSetter.setMonster();
        aSetter.setNPC();
        aSetter.setObject();
        aSetter.setInteractiveTile();

    }
    public void startGameThrea() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double drawInerval = 1000000000/FPS;
        //double drawInervalMaps = 1000000000/FPSAtributes;

        double delta = 0;
        double deltaMaps = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;
        int drawCountMaps = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInerval;
           // deltaMaps += (currentTime - lastTime) / drawInervalMaps;

            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            //if(deltaMaps >= 1) {
            //    deltaMaps--;
            //    drawCountMaps++;
            //}

            if(timer >= 1000000000){
                System.out.println("FPS user: " + drawCount);
               // System.out.println("FPS maps: " + drawCountMaps);

                //drawCountMaps = 0;
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
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null){
                    npc[i].update();
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if(monster[i] != null) {
                    if (monster[i].alive && !monster[i].dying) {
                        monster[i].update();
                    }
                    if (!monster[i].alive) {
                        monster[i].checkDrop();
                        monster[i] = null;
                    }
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if(projectileList.get(i) != null) {
                    if (projectileList.get(i).alive) {
                        projectileList.get(i).update();
                    }
                    if (!projectileList.get(i).alive) {
                        projectileList.remove(i);
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
            for (int i = 0; i < iTile.length; i++) {
                if (iTile[i] != null) {
                    iTile[i].update();
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
            for (int i = 0; i < iTile.length; i++) {
                if (iTile[i] != null) {
                    iTile[i].draw(g2);
                }
            }

            // ADD ENTITIES TO THE LIST
            entityList.add(player);

            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }
            for (int i = 0; i < obj.length; i++) {
                if(obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if(monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if(projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if(particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
                }
            }

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

            g2.drawString("Draw Time: " + passed, x, y);
        }

        g2.dispose();
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
