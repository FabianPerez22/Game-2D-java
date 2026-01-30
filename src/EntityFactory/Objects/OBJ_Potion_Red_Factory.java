package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Potion_Red;

public class OBJ_Potion_Red_Factory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Potion_Red(gp);
    }
}
