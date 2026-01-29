package main.tile_interactive;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;

import java.awt.*;
import java.util.Random;

public class IT_DestructibleWall extends InteractiveTile{
    GamePanel gp;
    public IT_DestructibleWall(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("tiles_interactive/destructiblewall", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 4;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;
        if (entity.currentWeapon.type == type_picaxe){
            isCorrectItem = true;
        }
        return isCorrectItem;
    }

    public void playSE() {
        gp.playSE(22);
    }

    public Color getParticleColor() {
        Color color = new Color(65,65,65);
        return color;
    }
    public int getParticleSize() {
        int size = 6;
        return size; // 6 pixels
    }
    public int getParticleSpeed() {
        int speed = 1;
        return speed;
    }
    public int getParticleMaxLife() {
        int maxLife = 20;
        return maxLife;
    }
    public void checkDrop() {
        // CAST A broke
        int i = new Random().nextInt(100) +1;

        // SET AN ITEM OF BREAK IT
        if (i < 10) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
    }

}
