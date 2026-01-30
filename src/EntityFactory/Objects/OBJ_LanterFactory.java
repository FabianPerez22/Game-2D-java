package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Lanter;

public class OBJ_LanterFactory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Lanter(gp);
    }
}
