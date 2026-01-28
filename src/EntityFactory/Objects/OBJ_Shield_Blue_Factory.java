package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Shield_Blue;

public class OBJ_Shield_Blue_Factory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Shield_Blue(gp);
    }
}
