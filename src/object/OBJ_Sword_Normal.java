package object;

import entity.Entity;
import EntityFactory.EntityFactory;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity{
    public static final String objName = "Normal Sword";
    GamePanel gp;

    public OBJ_Sword_Normal(GamePanel gp, int col, int row) {
        super(gp, col, row);

        this.gp = gp;
        type = type_sword;
        name = objName;
        down1 = setup("objects/sword_normal", gp.tileSize, gp.tileSize);
        price_OBJ = 2;
        price = 2;
        maxDurability = 100;
        durabilidy = maxDurability;
        repair_cost = 2;
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        knockBackPower = 2;
        motion1_duration = 10;
        motion2_duration = 25;
        getImage();
    }
    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);
        this.gp = gp;
        maxDurability = 100;
        durabilidy = maxDurability;
        price_OBJ = 2;
        price = 2;
        type = type_sword;
        repair_cost = 2;
        name = objName;
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        knockBackPower = 2;
        motion1_duration = 10;
        motion2_duration = 25;
        getImage();
    }
    public void getImage() {
        down1 = setup("objects/sword_normal", gp.tileSize, gp.tileSize);
        description = "[" + name +"]\n An old Sword\nDurability: " + (int)(durabilidy) + " Attack: " + attackValue;
    }
}
