package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Mirror extends Entity {
    public static final String objName = "Mirror";
    int prevWordX = 0;
    int prevWordY = 0;
    int prevMap = 0;
    int prevArea = 0;

    GamePanel gp;
    public OBJ_Mirror(GamePanel gp, int col, int row) {
        super(gp, col, row);

        this.gp = gp;
        repair_cost = 2;
        stackable = true;
        maxDurability = 100;
        durabilidy = maxDurability;
        type = type_consumable;
        name = objName;
        getImage();
        setDialog();
    }
    public OBJ_Mirror(GamePanel gp) {
        super(gp);
        this.gp = gp;
        maxDurability = 100;
        durabilidy = maxDurability;
        stackable = true;
        repair_cost = 2;
        type = type_consumable;
        name = objName;
        getImage();
        setDialog();
    }

    public void getImage() {
        if (durabilidy > 0) {
            down1 = setup("objects/mirror", gp.tileSize, gp.tileSize);
            description = "["+ name +"]\nLook like will be usefully.\n Durability: "+durabilidy;
        } else {
            down1 = setup("objects/mirrorEmpty", gp.tileSize, gp.tileSize);
            description = "["+ name +"]\nLook like will be usefully.\n Durability: "+durabilidy;
        }
    }
    public boolean use(Entity entity) {

        if (prevWordX == 0 && prevWordY == 0 && durabilidy > 0) {
            durabilidy -= 20;
            prevMap = gp.currentMap;
            prevWordX = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            prevWordY = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            prevArea = gp.currentArea;
            gp.eHandler.teleport(1,49,53, gp.indoor);
        } else  {
            if (durabilidy > 0) {
                durabilidy -= 20;
                gp.eHandler.teleport(prevMap,prevWordX,prevWordY, prevArea);
                prevMap = 0;
                prevWordX = 0;
                prevWordY = 0;
                prevArea = 0;
            } else {
                gp.gameState = gp.dialogueState;
                startDialogue(this, 0);
            }
        }
        getImage();
        return  false;
    }
    public void setDialog() {
        dialogues[0][0] = "Cannot be use any more, looks like empty or broken. \nMaybe if u repair it, can be use again.";
    }
}