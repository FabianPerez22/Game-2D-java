package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Heart;

public class OBJ_HeartFactory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Heart(gp);
    }
}
