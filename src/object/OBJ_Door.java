package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {
    public static final String objName = "Door";
    GamePanel gp;
    public OBJ_Door(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        down1 = setup("objects/door", gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialog();
    }
    public OBJ_Door(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        down1 = setup("objects/door", gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialog();
    }
    public void interact() {
        setDialog();
        startDialogue(this, 0);
    }
    public void setDialog() {
        dialogues[0][0] = "You need a key to open this";
    }
}
