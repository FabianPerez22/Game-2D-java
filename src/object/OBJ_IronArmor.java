package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_IronArmor extends Entity {
    public static final String objName = "Normal Armor of Iron";
    GamePanel gp;

    public OBJ_IronArmor(GamePanel gp, int col, int row) {
        super(gp, col, row);

        this.gp = gp;
        type = type_armor;
        name = objName;
        down1 = setup("objects/armor", gp.tileSize, gp.tileSize);
        price_OBJ = 2;
        price = 2;
        maxDurability = 100;
        defenseValue = 4;
        durabilidy = maxDurability;
        avoidWet = true;
        repair_cost = 2;
        getImage();
    }
    public OBJ_IronArmor(GamePanel gp) {
        super(gp);
        this.gp = gp;
        maxDurability = 100;
        durabilidy = maxDurability;
        price_OBJ = 2;
        price = 4;
        type = type_armor;
        repair_cost = 2;
        defenseValue = 2;
        avoidWet = true;
        name = objName;
        getImage();
    }
    public void getImage() {
        down1 = setup("objects/armor", gp.tileSize, gp.tileSize);
        description = "[" + name +"]\nIt's better than nothing.\nDurability: " + (int)(durabilidy)  + " Def: " + defenseValue;
        speciallyAttribute = "Can avoid the debuff wet";
    }
}
