package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Chest;

public class OBJ_ChestFactory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Chest(gp);
    }
}
