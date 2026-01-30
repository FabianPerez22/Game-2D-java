package object;

import entity.Entity;
import EntityFactory.EntityFactory;
import main.GamePanel;

public class OBJ_Key  extends Entity{
    public static final String objName = "Key";

    GamePanel gp;
    public OBJ_Key(GamePanel gp, int col, int row) {
        super(gp,col, row);
        this.gp = gp;

        stackable = true;
        type = type_consumable;
        price = 3;
        name = objName;
        description = "[" + name +"]\n It open's a door";
        down1 = setup("objects/key", gp.tileSize, gp.tileSize);
    }
    public OBJ_Key(GamePanel gp) {
        super(gp);
        this.gp = gp;

        stackable = true;
        type = type_consumable;
        price = 3;
        name = objName;
        description = "[" + name +"]\n It open's a door";
        down1 = setup("objects/key", gp.tileSize, gp.tileSize);
        setDialog();
    }
    public boolean use(Entity entity) {

        gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.obj, "Door");

        if (objIndex != 999) {
            startDialogue(this, 0);
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;

        } else {
            startDialogue(this, 1);
            return  false;
        }
    }
    public void setDialog() {
        dialogues[0][0] = "You use the " + name + " and open the door";

        dialogues[1][0] = "What are you doing?";
    }
}
