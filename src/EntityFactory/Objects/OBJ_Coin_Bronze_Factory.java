package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;

public class OBJ_Coin_Bronze_Factory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Coin_Bronze(gp);
    }
}
