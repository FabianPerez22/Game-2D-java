package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door_Iron extends Entity {
    public static final String objName = "Iron Door";
    GamePanel gp;
    public OBJ_Door_Iron(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        down1 = setup("objects/door_iron", gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialog();
    }
    public OBJ_Door_Iron(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        down1 = setup("objects/door_iron", gp.tileSize, gp.tileSize);
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
        dialogues[0][0] = "This iron door looks so strong to be destroyer.";
        dialogues[0][1] = "Maybe if you complete the puzzle, it'll open.";
    }
}
