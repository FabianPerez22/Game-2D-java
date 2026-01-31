package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_IronOre;

public class OBJ_IronOreFactory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_IronOre(gp);
    }
}
