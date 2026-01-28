package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Tent;

public class OBJ_TentFactory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Tent(gp);
    }
}
