package object;

import entity.Entity;
import EntityFactory.EntityFactory;
import main.GamePanel;

public class OBJ_Tent extends Entity {

    GamePanel gp;

    public OBJ_Tent(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        type = type_consumable;
        name = "Tent";
        down1 = setup("objects/tent", gp.tileSize, gp.tileSize);
        description = "[Tent]\nYou can sleep until next\nmorning.";
        price = 25;
        stackable = true;
    }

    public OBJ_Tent(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Tent";
        down1 = setup("objects/tent", gp.tileSize, gp.tileSize);
        description = "[Tent]\nYou can sleep until next\nmorning.";
        price = 25;
        stackable = true;
    }

    public boolean use(Entity entity) {

        gp.gameState = gp.sleepState;
        gp.playSE(16);
        gp.player.life = gp.player.maxLife;
        gp.player.mana = gp.player.maxMana;
        gp.player.getSleepingImage(down1);
        return true;
    }

}
