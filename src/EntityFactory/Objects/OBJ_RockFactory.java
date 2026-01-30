package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Rock;

public class OBJ_RockFactory implements EntityFactory {

    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Rock(gp);
    }
}
