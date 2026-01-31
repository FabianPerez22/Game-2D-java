package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;

import java.util.Random;

public class MON_Bat extends Entity {

    GamePanel gp;

    public MON_Bat(GamePanel gp, int col, int row) {

        super(gp);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        type = type_monster;
        name = "Bat";
        defaultSpeed = 4;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 1;
        defense = 0;
        exp = 2;

        solidArea.x = 3;
        solidArea.y = 15;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("monster/bat_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/bat_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/bat_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/bat_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/bat_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/bat_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("monster/bat_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/bat_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        if (onPath) {
        } else {

            // Go a random direction
            getRandomDirection(10);
        }
    }
    public void damageReaction() {

        actionLockCounter = 0;
    }
    public void checkDrop() {
        // CAST A DIE
        int i = new Random().nextInt(100) +1;

        // SET THE MONSTER DROP
        if (i < 10) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
    }

}

