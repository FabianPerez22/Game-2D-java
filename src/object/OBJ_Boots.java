package object;

import entity.Entity;
import EntityFactory.EntityFactory;
import main.GamePanel;

public class OBJ_Boots extends Entity {

    public OBJ_Boots(GamePanel gp, int col, int row) {
        super(gp, col, row);
        name = "Boots";
        price = 5;
        speed = 2;
        down1 = setup("objects/boots", gp.tileSize, gp.tileSize);
    }
    public OBJ_Boots(GamePanel gp) {
        super(gp);
        name = "Boots";
        price = 5;
        speed = 2;
        down1 = setup("objects/boots", gp.tileSize, gp.tileSize);
    }
}
