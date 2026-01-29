package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Pickaxe;

public class OBJ_PickaxeFactory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Pickaxe(gp);
    }
}
