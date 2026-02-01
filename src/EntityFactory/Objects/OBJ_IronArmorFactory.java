package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_IronArmor;

public class OBJ_IronArmorFactory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_IronArmor(gp);
    }
}
