package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity {
    public static final String objName = "Pickaxe";
    public OBJ_Pickaxe(GamePanel gp, int col, int row) {
        super(gp, col, row);

        type = type_picaxe;
        name = objName;
        description = "["+ name +"]\nYou will dig it!";
        down1 = setup("objects/pickaxe", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 30;
        attackArea.height = 30;
        price = 5;
        knockBackPower = 10;
        motion1_duration = 20;
        motion2_duration = 30;
    }
    public OBJ_Pickaxe(GamePanel gp) {
        super(gp);

        price = 5;
        type = type_picaxe;
        name = objName;
        description = "["+ name +"]\nYou will dig it!";
        down1 = setup("objects/pickaxe", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 30;
        attackArea.height = 30;
        motion1_duration = 10;
        motion2_duration = 20;
    }
}
