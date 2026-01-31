package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Coal;

public class OBJ_CoalFactory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Coal(gp);
    }
}
