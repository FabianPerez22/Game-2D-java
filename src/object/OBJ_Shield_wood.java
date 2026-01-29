package object;

import entity.Entity;
import EntityFactory.EntityFactory;
import main.GamePanel;

public class OBJ_Shield_wood extends Entity {
    public static final String objName = "Wood Shield";

    public OBJ_Shield_wood(GamePanel gp, int col, int row) {
        super(gp, col, row);

        type = type_shield;
        name = objName;
        description = "[" + name +"]\n An Wood shield";
        down1 = setup("objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
    }
    public OBJ_Shield_wood(GamePanel gp) {
        super(gp);

        price = 1;
        type = type_shield;
        name = objName;
        description = "[" + name +"]\n An Wood shield";
        down1 = setup("objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
    }

}
