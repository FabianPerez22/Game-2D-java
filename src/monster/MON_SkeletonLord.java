package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;

import java.util.Random;

public class MON_SkeletonLord extends Entity {
    GamePanel gp;
    public static final String momName = "Skeleton Lord";

    public MON_SkeletonLord(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        type = type_monster;
        name = momName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 3;
        defense = 2;
        exp = 15;
        knockBackPower = 5;

        int size = gp.tileSize*5;


        solidArea.x = 40;
        solidArea.y = 40;
        solidArea.width = 48;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;


        solidAreaDefaultY = solidArea.y;
        attackArea.width = 35*2;
        attackArea.height = 35*2;
        motion1_duration = 25;
        motion2_duration = 50;


        // Size to be a boss fight
        //solidArea.x = 48;
        //solidArea.y = 48;
        //solidArea.width = size-42*2;
        //solidArea.height = size-48;
        //solidAreaDefaultX = solidArea.x;
        //solidAreaDefaultY = solidArea.y;
        //attackArea.width = 170;
        //attackArea.height = 170;
        //motion1_duration = 25;
        //motion2_duration = 50;

        getImage();
        getAttackImage();
    }

    public void getImage() {
        // +5 to be a boss
        double i = 2;

        if (!inRage) {
            up1 = setup("monster/skeletonlord_up_1", (int)(gp.tileSize*i), (int)(gp.tileSize*i));
            up2 = setup("monster/skeletonlord_up_2", (int)(gp.tileSize*i), (int)(gp.tileSize*i));
            down1 = setup("monster/skeletonlord_down_1", (int)(gp.tileSize*i), (int)(gp.tileSize*i));
            down2 = setup("monster/skeletonlord_down_2", (int)(gp.tileSize*i), (int)(gp.tileSize*i));
            left1 = setup("monster/skeletonlord_left_1", (int)(gp.tileSize*i), (int)(gp.tileSize*i));
            left2 = setup("monster/skeletonlord_left_2", (int)(gp.tileSize*i), (int)(gp.tileSize*i));
            right1 = setup("monster/skeletonlord_right_1", (int)(gp.tileSize*i), (int)(gp.tileSize*i));
            right2 = setup("monster/skeletonlord_right_2", (int)(gp.tileSize*i), (int)(gp.tileSize*i));
        }

        if (inRage){
            up1 = setup("monster/skeletonlord_phase2_up_1", (int)(gp.tileSize*i), (int)(gp.tileSize*i));
            up2 = setup("monster/skeletonlord_phase2_up_2", (int)(gp.tileSize*i), (int)(gp.tileSize*i));
            down1 = setup("monster/skeletonlord_phase2_down_1", (int)(gp.tileSize*i), (int)(gp.tileSize*i));
            down2 = setup("monster/skeletonlord_phase2_down_2", (int)(gp.tileSize*i), (int)(gp.tileSize*i));
            left1 = setup("monster/skeletonlord_phase2_left_1", (int)(gp.tileSize*i), (int)(gp.tileSize*i));
            left2 = setup("monster/skeletonlord_phase2_left_2", (int)(gp.tileSize*i), (int)(gp.tileSize*i));
            right1 = setup("monster/skeletonlord_phase2_right_1", (int)(gp.tileSize*i), (int)(gp.tileSize*i));
            right2 = setup("monster/skeletonlord_phase2_right_2", (int)(gp.tileSize*i), (int)(gp.tileSize*i));
        }

    }
    public void getAttackImage() {
        // +5 to be a boss
        double i = 2;
        if (!inRage) {
            attackUp1 = setup("monster/skeletonlord_attack_up_1", (int)(gp.tileSize*i), (int)(gp.tileSize*i*2));
            attackUp2 = setup("monster/skeletonlord_attack_up_2", (int)(gp.tileSize*i), (int)(gp.tileSize*i*2));
            attackDown1 = setup("monster/skeletonlord_attack_down_1", (int)(gp.tileSize*i), (int)(gp.tileSize*i*2));
            attackDown2 = setup("monster/skeletonlord_attack_down_2", (int)(gp.tileSize*i), (int)(gp.tileSize*i*2));
            attackLeft1 = setup("monster/skeletonlord_attack_left_1", (int)(gp.tileSize*i*2), (int)(gp.tileSize*i));
            attackLeft2 = setup("monster/skeletonlord_attack_left_2", (int)(gp.tileSize*i*2), (int)(gp.tileSize*i));
            attackRight1 = setup("monster/skeletonlord_attack_right_1", (int)(gp.tileSize*i*2), (int)(gp.tileSize*i));
            attackRight2 = setup("monster/skeletonlord_attack_right_2", (int)(gp.tileSize*i*2), (int)(gp.tileSize*i));
        }
        if (inRage) {
            attackUp1 = setup("monster/skeletonlord_attack_up_1", (int)(gp.tileSize*i), (int)(gp.tileSize*i*2));
            attackUp2 = setup("monster/skeletonlord_attack_up_2", (int)(gp.tileSize*i), (int)(gp.tileSize*i*2));
            attackDown1 = setup("monster/skeletonlord_attack_down_1", (int)(gp.tileSize*i), (int)(gp.tileSize*i*2));
            attackDown2 = setup("monster/skeletonlord_attack_down_2", (int)(gp.tileSize*i), (int)(gp.tileSize*i*2));
            attackLeft1 = setup("monster/skeletonlord_attack_left_1", (int)(gp.tileSize*i*2), (int)(gp.tileSize*i));
            attackLeft2 = setup("monster/skeletonlord_attack_left_2", (int)(gp.tileSize*i*2), (int)(gp.tileSize*i));
            attackRight1 = setup("monster/skeletonlord_attack_right_1", (int)(gp.tileSize*i*2), (int)(gp.tileSize*i));
            attackRight2 = setup("monster/skeletonlord_attack_right_2", (int)(gp.tileSize*i*2), (int)(gp.tileSize*i));
        }

    }

    public void setAction() {
        if (!inRage && life < maxLife/2) {
            inRage = true;
            getImage();
            getAttackImage();
            defaultSpeed++;
            speed = defaultSpeed;
            attack += 2;
            defense -=4;
        }
        if (getTileDistance(gp.player) < 10) {
            moveTowardPlayer(60);
        } else {
            // Go a random direction
            getRandomDirection(120);
        }
        // check if it attacks
        if (!attacking) {
            // 60, x10 x5 to be a boss
            checkAttackOrNot(30,gp.tileSize*2, gp.tileSize*2);
        }
    }
    public void damageReaction() {
        actionLockCounter = 0;
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
