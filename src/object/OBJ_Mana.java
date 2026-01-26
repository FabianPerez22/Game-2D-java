package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Mana extends Entity {
    GamePanel gp;
    public OBJ_Mana(GamePanel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Heart";
        value = 1;
        down1 = setup("objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image = setup("objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("objects/manacrystal_blank", gp.tileSize, gp.tileSize);
    }

    public OBJ_Mana(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Heart";
        value = 1;
        down1 = setup("objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image = setup("objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("objects/manacrystal_blank", gp.tileSize, gp.tileSize);

    }

    public void use(Entity entity) {
        gp.playSE(2);
        entity.mana += value;
    }

}
