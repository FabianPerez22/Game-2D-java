package main.tile_interactive;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coal;
import object.OBJ_IronOre;

import java.awt.*;

public class IT_coalWall extends InteractiveTile{
    GamePanel gp;
    public IT_coalWall(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("tiles_interactive/coalWall", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 5;
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
        Color color = new Color(0,0,0);
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
        dropItem(new OBJ_Coal(gp));
    }
}
