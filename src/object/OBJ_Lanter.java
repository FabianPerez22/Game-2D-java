package object;

import entity.Entity;
import EntityFactory.EntityFactory;
import main.GamePanel;

public class OBJ_Lanter extends Entity {


    public OBJ_Lanter(GamePanel gp, int col, int row) {
        super(gp, col, row);

        type = type_light;
        name = "Lanter";
        down1 = setup("objects/lantern", gp.tileSize, gp.tileSize);
        description = "[Lantern]n\nIlluminates your surroundings.";
        price = 200;
        lightRadius = 400;
        lightDuration = 120;
    }


    public OBJ_Lanter(GamePanel gp) {
        super(gp);

        type = type_light;
        name = "Lantern";
        down1 = setup("objects/lantern", gp.tileSize, gp.tileSize);
        description = "[Lantern]n\nIlluminates your \nsurroundings.";
        price = 200;
        lightRadius = 400;
        lightDuration = 120;
    }

    public boolean use (Entity entity) {
        return  true;
    }
}
