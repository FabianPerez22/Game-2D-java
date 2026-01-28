package data;

import EntityFactory.EntityFactory;
import EntityFactory.EntityGenerator;
import entity.Entity;
import main.GamePanel;

import java.io.*;

public class SaveLoad {
    GamePanel gp;
    EntityGenerator entityGenerator;

    public SaveLoad(GamePanel gp){
        this.gp = gp;
        this.entityGenerator = new EntityGenerator();
    }

    public Entity getObject(String itemName) {
        return entityGenerator.getObject(itemName, gp);
    }

    public void save() {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

            // PLAYER STATS
            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.strength = gp.player.strength;
            ds.maxMana = gp.player.maxMana;
            ds.mana = gp.player.mana;
            ds.dexterity = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.nexLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;

            // PLAYER INVENTORI
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmounts.add(gp.player.inventory.get(i).amount);
            }
            // PLAYER EQUIPMENT
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSlot = gp.player.getCurrentShieldlot();

            // OBJECTS ON MAP
            ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.obj[1].length; i++) {
                    if (gp.obj[mapNum][i] == null) {
                        ds.mapObjectNames[mapNum][i] = "NA";
                    } else {
                        ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;

                        if (gp.obj[mapNum][i].loot != null){
                            ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name;
                        }
                        ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
                    }
                }
            }

            // WRITE THE DATASORAGE OBJECT
            oos.writeObject(ds);
        } catch (Exception e) {
            System.out.println("Save Exception!");
        }
    }
    public void load() {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            // Read the dataStorage object
            DataStorage ds = (DataStorage)ois.readObject();

            // PLAYER STATS
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.maxMana = ds.maxMana;
            gp.player.mana = ds.mana;
            gp.player.exp = ds.exp;
            gp.player.level = ds.level;
            gp.player.nextLevelExp = ds.nexLevelExp;
            gp.player.coin = ds.coin;

            // PLAYER INVNETORY
            gp.player.inventory.clear();
            for (int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
            }

            // PLAYER EQUIPMENT
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getAttackImage();

            // OBJECTS ON MAP
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.obj[1].length; i++) {
                    if (ds.mapObjectNames[mapNum][i].equals("NA")) {
                        gp.obj[mapNum][i] = null;
                    } else {
                        gp.obj[mapNum][i] = getObject(ds.mapObjectNames[mapNum][i]);
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];

                        if (ds.mapObjectLootNames != null){
                            gp.obj[mapNum][i].loot = getObject(ds.mapObjectLootNames[mapNum][i]);
                        }
                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if (gp.obj[mapNum][i].opened) {
                            gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                        }
                    }
                }
            }


        } catch (Exception e) {
            System.out.println("Load Exception!");
        }
    }
}
