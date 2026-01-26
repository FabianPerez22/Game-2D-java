package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {

    public OBJ_Chest(GamePanel gp, int col, int row) {
        super(gp, col, row);
        name = "Chest";
        down1 = setup("objects/boots", gp.tileSize, gp.tileSize);
    }
}
