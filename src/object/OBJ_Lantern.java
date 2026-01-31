package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity {
    public static final String objName = "Lantern";
    public OBJ_Lantern(GamePanel gp, int col, int row) {
        super(gp, col, row);

        type = type_light;
        name = objName;
        down1 = setup("objects/lantern", gp.tileSize, gp.tileSize);
        description = "[Lantern]n\nIlluminates your surroundings.";
        price = 3;
        price_OBJ = 2;
        lightRadius = 250;
    }


    public OBJ_Lantern(GamePanel gp) {
        super(gp);

        type = type_light;
        name = objName;
        down1 = setup("objects/lantern", gp.tileSize, gp.tileSize);
        description = "[Lantern]n\nIlluminates your \nsurroundings.";
        price = 3;
        price_OBJ = 2;
        lightRadius = 250;
    }

    public boolean use (Entity entity) {
        return  true;
    }
}
