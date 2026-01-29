package EntityFactory;

import EntityFactory.Objects.*;
import entity.Entity;
import main.GamePanel;

import java.util.HashMap;
import java.util.Map;

public class EntityGenerator {
    private final Map<String, EntityFactory> factories = new HashMap<>();

    public EntityGenerator() {
        initFactories();
    }

    public void initFactories() {
        factories.put("Woodcutter's Axe", new OBJ_AxeFactory());
        factories.put("Boots", new OBJ_BootsFactory());
        factories.put("Normal Sword", new OBJ_Sword_Normal_Factory());
        factories.put("Red Potion", new OBJ_Potion_Red_Factory());
        factories.put("Tent", new OBJ_TentFactory());
        factories.put("Wood Shield", new OBJ_Shield_woodFactory());
        factories.put("Blue Shield", new OBJ_Shield_Blue_Factory());
        factories.put("Key", new OBJ_KeyFactory());
        factories.put("Lantern", new OBJ_LanterFactory());
        factories.put("Chest", new OBJ_ChestFactory());
        factories.put("Door", new OBJ_DoorFactory());
    }
    public Entity getObject(String itemName, GamePanel gp) {
        EntityFactory factory = factories.get(itemName);

        if (factory == null) {
            System.out.println("Unknown item: " + itemName +" Factory's:" + factory);
        }
        return factory != null ? factory.create(gp) : null;
    }
}
