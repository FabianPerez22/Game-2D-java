package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Sword_Normal;

public class OBJ_Sword_Normal_Factory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Sword_Normal(gp);
    }
}
