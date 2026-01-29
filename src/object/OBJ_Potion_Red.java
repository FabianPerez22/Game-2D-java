package object;

import entity.Entity;
import EntityFactory.EntityFactory;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {
    public static final String objName = "Red Potion";

    GamePanel gp;
    public OBJ_Potion_Red(GamePanel gp, int col, int row) {
        super(gp, col, row);

        this.gp = gp;
        stackable = true;
        type = type_consumable;
        value = 5;
        price = 25;
        name = objName;
        description = "["+ name +"]\nHeals your life by " + value+".";
        down1 = setup("objects/potion_red", gp.tileSize, gp.tileSize);
    }
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);
        this.gp = gp;

        stackable = true;
        type = type_consumable;
        price = 25;
        value = 5;
        name = objName;
        description = "["+ name +"]\nHeals your life by " + value+".";
        down1 = setup("objects/potion_red", gp.tileSize, gp.tileSize);
        setDialog();
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.dialogueState;
        startDialogue(this, 0);
        entity.life += value;
        gp.playSE(2);
        return true;
    }
    public void setDialog() {
        dialogues[0][0] = "You drink the " + name+"!\nYour life has been recovered by " +value+".";
    }
}
