package main;

import entity.NPC_BigRock;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import main.tile_interactive.IT_DestructibleWall;
import main.tile_interactive.IT_DryTree;
import main.tile_interactive.IT_MetalPlate;
import monster.*;
import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;

    }

    public void setObject() {
        int i = 0;
        int mapNum = 0;

        gp.obj[mapNum][i] = new OBJ_Door(gp, 7,32); i++;
        //gp.obj[mapNum][i] = new OBJ_Axe(gp, 71,90); i++;
//
        gp.obj[mapNum][i] = new OBJ_Tent(gp, 70,91); i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp, 71,92); i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp, 71,88); i++;
        gp.obj[mapNum][i] = new OBJ_Shield_Blue(gp, 70,88); i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp, 70,90); i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*70;
        gp.obj[mapNum][i].worldY = gp.tileSize*89;
        i++;



        mapNum = 2;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_Door_Iron(gp, 55,72); i++;


        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*56;
        gp.obj[mapNum][i].worldY = gp.tileSize*55; i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Boots(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*73;
        gp.obj[mapNum][i].worldY = gp.tileSize*76; i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*42;
        gp.obj[mapNum][i].worldY = gp.tileSize*53; i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Tent(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*77;
        gp.obj[mapNum][i].worldY = gp.tileSize*57; i++;

        gp.obj[mapNum][i] = new OBJ_Key(gp, 38,69); i++;


    }
    public void setNPC() {
        int i = 0;
        int mapNum = 0;

        gp.npc[mapNum][i] = new NPC_OldMan(gp, 70,88); i++;

        mapNum = 1;
        gp.npc[mapNum][i] = new NPC_Merchant(gp, 51,40); i++;

        mapNum = 2;
        i = 0;
        gp.npc[mapNum][i] = new NPC_BigRock(gp, 63,81); i++;
        gp.npc[mapNum][i] = new NPC_BigRock(gp, 64,73); i++;
        gp.npc[mapNum][i] = new NPC_BigRock(gp, 52,79); i++;


    }
    public void setMonster() {

        int i = 0;
        int mapNum = 0;

        //gp.monster[mapNum][i] = new MON_GreenSlime(gp,23,36); i++;
        //gp.monster[mapNum][i] = new MON_GreenSlime(gp,23,37); i++;
        //gp.monster[mapNum][i] = new MON_GreenSlime(gp,23,35); i++;
        gp.monster[mapNum][i] = new MOM_SkeletonLord(gp,22,35); i++;


        i = 0;
        mapNum = 2;
        gp.monster[mapNum][i] = new MOM_Bat(gp,64,77); i++;
        gp.monster[mapNum][i] = new MOM_Bat(gp,64,75); i++;
        gp.monster[mapNum][i] = new MOM_Bat(gp,59,75); i++;
        gp.monster[mapNum][i] = new MOM_Bat(gp,42,62); i++;

        gp.monster[mapNum][i] = new MOM_RedSlime(gp,69,66); i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp,59,62); i++;

    }
    public void setInteractiveTile() {
        int i = 0;
        int mapNum = 0;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 12); i++;

        mapNum = 2;
        i = 0;
        gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 61,73); i++;
        gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 66,81); i++;
        gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 52,73); i++;


        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 40,74); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 40,73); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 40,72); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 40,71); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 40,70); i++;

        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 67, 78); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 68, 78); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 69, 78); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 70, 78); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 71, 78); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 71, 77); i++;

        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 77, 59); i++;

    }

}
