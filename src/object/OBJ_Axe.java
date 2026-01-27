package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {
    public OBJ_Axe(GamePanel gp, int col, int row) {
        super(gp, col, row);

        type = type_axe;
        name = "Woodcutter's Axe";
        description = "["+ name +"]\nA bit rusty but still can cut some \ntrees.";
        down1 = setup("objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
    }
    public OBJ_Axe(GamePanel gp) {
        super(gp);

        knockBackPower = 10;
        price = 5;
        type = type_axe;
        name = "Woodcutter's Axe";
        description = "["+ name +"]\nA bit rusty but still can cut some \ntrees.";
        down1 = setup("objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
    }
}
