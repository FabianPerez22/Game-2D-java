package object;

import entity.Entity;
import EntityFactory.EntityFactory;
import main.GamePanel;

public class OBJ_Shield_Blue extends Entity  {

    public static final String objName = "Blue Shield";

    public OBJ_Shield_Blue(GamePanel gp, int col, int row) {
        super(gp, col, row);
        price = 19;
        type = type_shield;
        price_OBJ = 5;
        name = objName;
        description = "["+ name +"]\n A shiny blue shield";
        down1 = setup("objects/shield_blue", gp.tileSize, gp.tileSize);
        defenseValue = 2;
    }
    public OBJ_Shield_Blue(GamePanel gp) {
        super(gp);
        price = 19;
        price_OBJ = 5;
        type = type_shield;
        name = objName;
        description = "["+ name +"]\n A shiny blue shield";
        down1 = setup("objects/shield_blue", gp.tileSize, gp.tileSize);
        defenseValue = 2;
    }
}
