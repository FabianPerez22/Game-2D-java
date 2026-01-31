package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_IronIngot;

public class OBJ_IronIngotFactory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_IronIngot(gp);
    }
}
