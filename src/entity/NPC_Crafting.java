package entity;

import main.GamePanel;
import object.*;

public class NPC_Crafting extends Entity{

    GamePanel gp;
    public NPC_Crafting(GamePanel gp, int col, int row) {
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
        dialogues[0][0] = "What did you want to do?";
        dialogues[1][0] = "You know you'll come back";
        dialogues[2][0] = "You need more objects to crafting that!";
        dialogues[3][0] = "You cannot carry any more!";
        dialogues[4][0] = "You cannot dismantle an equipped item!";
        dialogues[5][0] = "You cannot dismantle this item!";

    }

    public void setItems() {
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Lantern(gp));
        inventory.add(new OBJ_Shield_Blue(gp));
    }

    public void speak() {
        gp.gameState = gp.craftingState;
        gp.ui.npc = this;
    }

}
