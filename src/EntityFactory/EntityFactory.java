package EntityFactory;

import entity.Entity;
import main.GamePanel;

public interface EntityFactory {
    Entity create(GamePanel gp);
}
