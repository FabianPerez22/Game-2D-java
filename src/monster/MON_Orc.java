package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;

import java.util.Random;

public class MON_Orc extends Entity {
    GamePanel gp;

    public MON_Orc(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        type = type_monster;
        name = "Green Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 15;
        life = maxLife;
        attack = 3;
        defense = 0;
        exp = 10;
        knockBackPower = 10;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 48;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        motion1_duration = 40;
        motion2_duration = 85;

        getImage();
        getAttackImage();
    }

    public void getImage() {
        up1 = setup("monster/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/orc_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/orc_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/orc_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("monster/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/orc_right_2", gp.tileSize, gp.tileSize);
    }
    public void getAttackImage() {
         attackUp1 = setup("monster/orc_attack_up_1", gp.tileSize, gp.tileSize*2);
         attackUp2 = setup("monster/orc_attack_up_2", gp.tileSize, gp.tileSize*2);
         attackDown1 = setup("monster/orc_attack_down_1", gp.tileSize, gp.tileSize*2);
         attackDown2 = setup("monster/orc_attack_down_2", gp.tileSize, gp.tileSize*2);
         attackLeft1 = setup("monster/orc_attack_left_1", gp.tileSize*2, gp.tileSize);
         attackLeft2 = setup("monster/orc_attack_left_2", gp.tileSize*2, gp.tileSize);
         attackRight1 = setup("monster/orc_attack_right_1", gp.tileSize*2, gp.tileSize);
         attackRight2 = setup("monster/orc_attack_right_2", gp.tileSize*2, gp.tileSize);

    }

    public void setAction() {
        if (onPath) {
            // Check if it stops chasing
            checkStopChasingOrNot(gp.player, 10, 100);

            // Search the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

        } else {
            // Check if it start charsing
            checkStartChasingOrNot(gp.player, 6, 100);

            // Go a random direction
            getRandomDirection(120);
        }
        // check if it attacks
        if (!attacking) {
            checkAttackOrNot(45,gp.tileSize*2, gp.tileSize);
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
        if (i < 10) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
    }

}
