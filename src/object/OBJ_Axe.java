package object;

import entity.Entity;
import EntityFactory.EntityFactory;
import main.GamePanel;

public class OBJ_Axe extends Entity{
    public static final String objName = "Woodcutter's Axe";
    public OBJ_Axe(GamePanel gp, int col, int row) {
        super(gp, col, row);

        type = type_axe;
        name = objName;
        description = "["+ name +"]\nA bit rusty but still can cut some \ntrees.";
        down1 = setup("objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 30;
        attackArea.height = 30;
        price = 5;
        knockBackPower = 10;
        motion1_duration = 20;
        motion2_duration = 40;
    }
    public OBJ_Axe(GamePanel gp) {
        super(gp);

        price = 5;
        type = type_axe;
        name = objName;
        description = "["+ name +"]\nA bit rusty but still can cut some \ntrees.";
        down1 = setup("objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        motion1_duration = 20;
        motion2_duration = 40;
    }
}
