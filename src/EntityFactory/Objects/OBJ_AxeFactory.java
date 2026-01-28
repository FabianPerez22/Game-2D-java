package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Axe;

public class OBJ_AxeFactory implements EntityFactory {
    public Entity create(GamePanel gp) {
        return new OBJ_Axe(gp);
    }
}
