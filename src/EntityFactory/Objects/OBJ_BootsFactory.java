package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Boots;

public class OBJ_BootsFactory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Boots(gp);
    }
}
