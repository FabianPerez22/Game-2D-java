package EntityFactory.Objects;

import EntityFactory.EntityFactory;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Mirror;

public class OBJ_MirrorFactory implements EntityFactory {
    @Override
    public Entity create(GamePanel gp) {
        return new OBJ_Mirror(gp);
    }
}
