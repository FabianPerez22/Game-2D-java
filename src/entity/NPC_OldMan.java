package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_OldMan extends Entity{


    public NPC_OldMan(GamePanel gp, int col, int row) {
        super(gp, col, row);

        direction = "down";
        speed = 1;


        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        dialogueSet = -1;

        getImage();
        setDialogue();

    }

    public void getImage() {
        up1 = setup("npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("npc/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("npc/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("npc/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("npc/oldman_right_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0][0] = "Hello, Lad.";
        dialogues[0][1] = "So, you've come to this island to find the \ntreasure?";
        dialogues[0][2] = "I used to be a great wizard but now... I'm a bit too \nold for that adventure.";
        dialogues[0][3] = "Well, good luck on you.";

        dialogues[1][0] = "If you become tired, rest at the water.";
        dialogues[1][1] = "There's so many treasure and monster, be carefull";
        dialogues[1][2] = "I need some rest after init my adventure";


        dialogues[2][0] = "Good lucky.";


    }
    public void setAction(){

        if (onPath) {

            //int goalCol = 12;
            //int goalRow = 9;

            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol, goalRow);

        } else {
            actionLockCounter++;

            if (actionLockCounter == 120){
                Random random = new Random();
                int i = random.nextInt(100)+1;
                if(i <= 25){
                    direction = "up";
                }
                if(i > 25 && i <= 50){
                    direction = "down";
                }
                if(i > 50 && i <= 75){
                    direction = "left";
                }
                if (i > 75 ){
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }
    }
    public void speak() {
        // Do this character specific stuff

        facePlayer();
        startDialogue(this, dialogueSet);

        dialogueSet++;
        if (dialogues[dialogueSet][0] == null) {
            dialogueSet = 0;
        }
       // if (gp.player.life < gp.player.maxLife/3) {
       //     dialogueSet = 1;
       // }

        //onPath = true;
    }
}
