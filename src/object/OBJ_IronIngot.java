package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_IronIngot extends Entity {
    public static final String objName = "Iron ingot";

    GamePanel gp;
    public OBJ_IronIngot(GamePanel gp, int col, int row) {
        super(gp, col, row);

        this.gp = gp;
        stackable = true;
        type = type_consumable;
        price = 4;
        coal = 3;
        ironOre = 2;
        name = objName;
        description = "["+ name +"]\nThis can be use for crafting\nsomething or repair.";
        down1 = setup("objects/iron-ingot", gp.tileSize, gp.tileSize);

    }
    public OBJ_IronIngot(GamePanel gp) {
        super(gp);
        this.gp = gp;

        stackable = true;
        type = type_consumable;
        price = 4;
        coal = 3;
        ironOre = 2;
        name = objName;
        description = "["+ name +"]\nThis can be use for crafting\nsomething or repair.";
        down1 = setup("objects/iron-ingot", gp.tileSize, gp.tileSize);
    }

}
