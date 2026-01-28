package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Key;

public class OBJ_KeyFactory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Key(gp);
    }
}
