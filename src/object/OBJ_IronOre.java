package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_IronOre extends Entity {
    public static final String objName = "Iron ore";

    GamePanel gp;
    public OBJ_IronOre(GamePanel gp, int col, int row) {
        super(gp, col, row);

        this.gp = gp;
        stackable = true;
        type = type_consumable;
        price = 2;
        name = objName;
        description = "["+ name +"]\nThis will be usefully.";
        down1 = setup("objects/iron-ore", gp.tileSize, gp.tileSize);

    }
    public OBJ_IronOre(GamePanel gp) {
        super(gp);
        this.gp = gp;

        stackable = true;
        type = type_consumable;
        price = 2;
        name = objName;
        description = "["+ name +"]\nThis will be usefully.";
        down1 = setup("objects/iron-ore", gp.tileSize, gp.tileSize);
    }
}
