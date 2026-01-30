package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Fireball;

public class OBJ_FireballFactory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Fireball(gp);
    }
}
