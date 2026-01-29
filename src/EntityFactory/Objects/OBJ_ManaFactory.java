package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Mana;

public class OBJ_ManaFactory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Mana(gp);
    }
}
