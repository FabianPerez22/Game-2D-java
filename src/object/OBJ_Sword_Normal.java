package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gp, int col, int row) {
        super(gp, col, row);

        type = type_sword;
        name = "Normal Sword";
        description = "[" + name +"]\n An old Sword";
        down1 = setup("objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
    }
    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        price = 2;

        type = type_sword;
        name = "Normal Sword";
        description = "[" + name +"]\n An old Sword";
        down1 = setup("objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
    }
}
