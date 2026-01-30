package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;

import java.util.Random;

public class MON_SkeletonBoss extends Entity {
    GamePanel gp;
    public static final String momName = "Skeleton Lord Boss";

    public MON_SkeletonBoss(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        type = type_monster;
        name = momName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 50;
        life = maxLife;
        attack = 6;
        defense = 3;
        exp = 50;
        knockBackPower = 5;
        boss = true;

        int size = gp.tileSize*5;

        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size-42*2;
        solidArea.height = size-48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 170;
        attackArea.height = 170;
        motion1_duration = 25;
        motion2_duration = 50;

        getImage();
        getAttackImage();
    }

    public void getImage() {

        int i = 5;

        up1 = setup("monster/skeletonlord_up_1", gp.tileSize*i, gp.tileSize*i);
        up2 = setup("monster/skeletonlord_up_2", gp.tileSize*i, gp.tileSize*i);
        down1 = setup("monster/skeletonlord_down_1", gp.tileSize*i, gp.tileSize*i);
        down2 = setup("monster/skeletonlord_down_2", gp.tileSize*i, gp.tileSize*i);
        left1 = setup("monster/skeletonlord_left_1", gp.tileSize*i, gp.tileSize*i);
        left2 = setup("monster/skeletonlord_left_2", gp.tileSize*i, gp.tileSize*i);
        right1 = setup("monster/skeletonlord_right_1", gp.tileSize*i, gp.tileSize*i);
        right2 = setup("monster/skeletonlord_right_2", gp.tileSize*i, gp.tileSize*i);
    }
    public void getAttackImage() {
        int i = 5;
        attackUp1 = setup("monster/skeletonlord_attack_up_1", gp.tileSize*i, gp.tileSize*2*i);
        attackUp2 = setup("monster/skeletonlord_attack_up_2", gp.tileSize*i, gp.tileSize*2*i);
        attackDown1 = setup("monster/skeletonlord_attack_down_1", gp.tileSize*i, gp.tileSize*2*i);
        attackDown2 = setup("monster/skeletonlord_attack_down_2", gp.tileSize*i, gp.tileSize*2*i);
        attackLeft1 = setup("monster/skeletonlord_attack_left_1", gp.tileSize*2*i, gp.tileSize*i);
        attackLeft2 = setup("monster/skeletonlord_attack_left_2", gp.tileSize*2*i, gp.tileSize*i);
        attackRight1 = setup("monster/skeletonlord_attack_right_1", gp.tileSize*2*i, gp.tileSize*i);
        attackRight2 = setup("monster/skeletonlord_attack_right_2", gp.tileSize*2*i, gp.tileSize*i);

    }

    public void setAction() {
        if (getTileDistance(gp.player) < 10) {
            moveTowardPlayer(60);
        } else {
            // Go a random direction
            getRandomDirection(120);
        }
        // check if it attacks
        if (!attacking) {
            checkAttackOrNot(60,gp.tileSize*10, gp.tileSize*5);
        }
    }
    public void damageReaction() {
        actionLockCounter = 0;
    }
    public void checkDrop() {
        // CAST A DIE
        int i = new Random().nextInt(100) +1;

        // SET THE MONSTER DROP
        if (i < 60) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
    }

}