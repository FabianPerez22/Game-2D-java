package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key  extends Entity {

    public OBJ_Key(GamePanel gp, int col, int row) {
        super(gp,col, row);

        name = "Key";
        description = "[" + name +"]\n It open's a door";
        down1 = setup("objects/key", gp.tileSize, gp.tileSize);
    }
    public OBJ_Key(GamePanel gp) {
        super(gp);
        price = 3;
        name = "Key";
        description = "[" + name +"]\n It open's a door";
        down1 = setup("objects/key", gp.tileSize, gp.tileSize);
    }
}
