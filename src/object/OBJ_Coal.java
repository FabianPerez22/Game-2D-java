package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coal extends Entity {
    public static final String objName = "Coal";

    GamePanel gp;
    public OBJ_Coal(GamePanel gp, int col, int row) {
        super(gp, col, row);

        this.gp = gp;
        stackable = true;
        type = type_consumable;
        price = 2;
        price_OBJ = 1;
        name = objName;
        description = "["+ name +"]\nThis will be usefully.";
        down1 = setup("objects/coal", gp.tileSize, gp.tileSize);

    }
    public OBJ_Coal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        stackable = true;
        type = type_consumable;
        price_OBJ = 1;
        price = 2;
        name = objName;
        description = "["+ name +"]\nThis will be usefully.";
        down1 = setup("objects/coal", gp.tileSize, gp.tileSize);
    }
}
