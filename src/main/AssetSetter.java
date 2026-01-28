package main;

import entity.NPC_Merchant;
import entity.NPC_OldMan;
import main.tile_interactive.IT_DryTree;
import monster.MOM_Orc;
import monster.MON_GreenSlime;
import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;

    }

    public void setObject() {
        int i = 0;
        int mapNum = 0;



        gp.obj[mapNum][i] = new OBJ_Door(gp, 14,28); i++;
        gp.obj[mapNum][i] = new OBJ_Axe(gp, 71,90); i++;

        gp.obj[mapNum][i] = new OBJ_Tent(gp, 70,91); i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp, 71,92); i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp, 71,88); i++;
        gp.obj[mapNum][i] = new OBJ_Shield_Blue(gp, 70,88); i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp, 70,89);
        gp.obj[mapNum][i].loot = new OBJ_Key(gp);
        i++;


    }
    public void setNPC() {
        int i = 0;
        int mapNum = 0;

        gp.npc[mapNum][i] = new NPC_OldMan(gp, 21, 21);

        mapNum = 1;
        gp.npc[mapNum][i] = new NPC_Merchant(gp, 12, 7);
    }

    public void setMonster() {

        int i = 0;
        int mapNum = 0;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp,23,36); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,23,37); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,23,35); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,22,35); i++;


        //gp.monster[mapNum][i] = new MOM_Orc(gp,71,90); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,71,90); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,73,90); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,72,90); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,73,91); i++;
    }

    public void setInteractiveTile() {
        int i = 0;
        int mapNum = 0;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 28, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 32, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 20); i++;


        gp.iTile[mapNum][i] = new IT_DryTree(gp, 10, 41); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 11, 41); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 12, 41); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 13, 41); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 13, 40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 14, 40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 15, 40); i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 21); i++;
    }

}
