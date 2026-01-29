package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Door_Iron;

public class OBJ_Iron_Door_Factory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Door_Iron(gp);
    }
}
