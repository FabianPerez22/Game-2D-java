package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Rock;

import java.util.Random;

public class MON_RedSlime extends Entity {

    GamePanel gp;

    public MON_RedSlime(GamePanel gp, int col, int row) {

        super(gp);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        type = type_monster;
        name = "Red Slime";
        defaultSpeed = 4;
        speed = defaultSpeed;
        maxLife = 5;
        life = maxLife;
        attack = 2;
        defense = 1;
        exp = 2;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    public void getImage() {
        up1 = setup("monster/redslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/redslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/redslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/redslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/redslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/redslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("monster/redslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/redslime_down_2", gp.tileSize, gp.tileSize);
    }
    public void setAction() {
        if (onPath) {
            // Check if it stops chasing
            //checkStopChasingOrNot(gp.player, 10, 100);

            // Search the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

            // Check if it shot projectiles
            checkShootOrNot(200, 30);
        } else {
            // Check if it start charsing
            checkStartChasingOrNot(gp.player, 10, 50);

            // Go a random direction
            getRandomDirection(120);
        }
    }
    public void damageReaction() {

        actionLockCounter = 0;
        //direction = gp.player.direction;
        onPath = true;
    }
    public void checkDrop() {
        // CAST A DIE
        int i = new Random().nextInt(100) +1;

        // SET THE MONSTER DROP
        if (i < 70) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
    }

}
