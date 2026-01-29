package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Door;

public class OBJ_DoorFactory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Door(gp);
    }
}
