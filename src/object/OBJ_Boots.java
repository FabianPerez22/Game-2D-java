package object;

import entity.Entity;
import EntityFactory.EntityFactory;
import main.GamePanel;

public class OBJ_Boots extends Entity {
    public static final String objName = "Boots";

    public OBJ_Boots(GamePanel gp, int col, int row) {
        super(gp, col, row);
        name = objName;
        price = 5;
        speed = 2;
        down1 = setup("objects/boots", gp.tileSize, gp.tileSize);
    }
    public OBJ_Boots(GamePanel gp) {
        super(gp);
        name = objName;
        price = 5;
        speed = 2;
        down1 = setup("objects/boots", gp.tileSize, gp.tileSize);
    }
}
