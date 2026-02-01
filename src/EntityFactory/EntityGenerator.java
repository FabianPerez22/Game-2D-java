package EntityFactory;

import EntityFactory.Objects.*;
import entity.Entity;
import main.GamePanel;
import object.*;

import java.util.HashMap;
import java.util.Map;

public class EntityGenerator {
    private final Map<String, EntityFactory> factories = new HashMap<>();
    GamePanel gp;
    public EntityGenerator(GamePanel gp) {
        initFactories();
        this.gp = gp;
    }

    public void initFactories() {
        factories.put(OBJ_Axe.objName, new OBJ_AxeFactory());
        factories.put(OBJ_Boots.objName, new OBJ_BootsFactory());
        factories.put(OBJ_Chest.objName, new OBJ_ChestFactory());
        factories.put(OBJ_Coal.objName, new OBJ_CoalFactory());
        factories.put(OBJ_Coin_Bronze.objName, new OBJ_Coin_Bronze_Factory());
        factories.put(OBJ_Door.objName, new OBJ_DoorFactory());
        factories.put(OBJ_Door_Iron.objName, new OBJ_Iron_Door_Factory());
        factories.put(OBJ_Fireball.objName, new OBJ_FireballFactory());
        factories.put(OBJ_Heart.objName, new OBJ_HeartFactory());
        factories.put(OBJ_IronIngot.objName, new OBJ_IronIngotFactory());
        factories.put(OBJ_IronOre.objName, new OBJ_IronOreFactory());
        factories.put(OBJ_Key.objName, new OBJ_KeyFactory());
        factories.put(OBJ_Lantern.objName, new OBJ_LanterFactory());
        factories.put(OBJ_Mana.objName, new OBJ_ManaFactory());
        factories.put(OBJ_Mirror.objName, new OBJ_MirrorFactory());
        factories.put(OBJ_Potion_Red.objName, new OBJ_Potion_Red_Factory());
        factories.put(OBJ_Rock.objName, new OBJ_RockFactory());
        factories.put(OBJ_Shield_Blue.objName, new OBJ_Shield_Blue_Factory());
        factories.put(OBJ_Shield_wood.objName, new OBJ_Shield_woodFactory());
        factories.put(OBJ_Sword_Normal.objName, new OBJ_Sword_Normal_Factory());
        factories.put(OBJ_Tent.objName, new OBJ_TentFactory());
        factories.put(OBJ_Pickaxe.objName, new OBJ_PickaxeFactory());
    }
    public Entity getObject(String itemName) {
        EntityFactory factory = factories.get(itemName);

        if (factory == null) {
            System.out.println("Unknown item: " + itemName +" Factory's:" + factory);
        }
        return factory != null ? factory.create(gp) : null;
    }
}
