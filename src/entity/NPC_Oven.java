package entity;

import main.GamePanel;
import object.*;

public class NPC_Oven extends Entity{

    GamePanel gp;
    public NPC_Oven(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        direction = "down";

        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
        setItems();
    }

    public void setDialogue() {
        dialogues[0][0] = "What did you want to melt?";
        dialogues[1][0] = "You know you'll come back";
        dialogues[2][0] = "You need more objects to melt that!";
        dialogues[3][0] = "You cannot carry any more!";
    }

    public void setItems() {
        inventory.add(new OBJ_IronIngot(gp));
    }

    public void speak() {
        gp.gameState = gp.ovenState;
        gp.ui.npc = this;
    }

}
