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

        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp, 39,16); i++;
        gp.obj[mapNum][i] = new OBJ_Heart(gp, 15,17); i++;

        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp, 73,56); i++;

        gp.obj[mapNum][i] = new OBJ_Axe(gp, 66,49); i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*39;
        gp.obj[mapNum][i].worldY = gp.tileSize*58;
        i++;




        mapNum = 2;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_Door_Iron(gp, 55,72); i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp, 42,55); i++;

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

        gp.monster[mapNum][i] = new MON_GreenSlime(gp,64, 49); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,64, 51); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,57, 67); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,62,82); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,78,61); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,66,88); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,37,81); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,21, 69); i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp,38, 34); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,39, 34); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,40, 34); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,42, 34); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,43, 34); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,44, 34); i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp,45, 34); i++;

        gp.monster[mapNum][i] = new MOM_RedSlime(gp,3,32); i++;

        i = 0;
        mapNum = 2;
        gp.monster[mapNum][i] = new MOM_Bat(gp,64,77); i++;
        gp.monster[mapNum][i] = new MOM_Bat(gp,64,75); i++;
        gp.monster[mapNum][i] = new MOM_Bat(gp,59,75); i++;
        gp.monster[mapNum][i] = new MOM_Bat(gp,42,62); i++;

        gp.monster[mapNum][i] = new MOM_Bat(gp,41,62); i++;

        gp.monster[mapNum][i] = new MOM_RedSlime(gp,69,66); i++;

        i = 0;
        mapNum = 3;
        gp.monster[mapNum][i] = new MOM_RedSlime(gp,73,71); i++;
        gp.monster[mapNum][i] = new MOM_RedSlime(gp,73,72); i++;

        gp.monster[mapNum][i] = new MOM_SkeletonLord(gp,71,46); i++;

        gp.monster[mapNum][i] = new MOM_SkeletonLord(gp,60,56); i++;

    }
    public void setInteractiveTile() {
        int i = 0;
        int mapNum = 0;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 12); i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 53, 17); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 53, 18); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 53, 19); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 53, 20); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 53, 21); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 53, 22); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 53, 23); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 54, 23); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 54, 24); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 55, 24); i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 36, 18); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 36, 19); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 36, 20); i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 15, 20); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 15, 19); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 15, 18); i++;


        gp.iTile[mapNum][i] = new IT_DryTree(gp, 24,64); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 24,63); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 24,62); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 24,61); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 24,60); i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 25,64); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 25,63); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 25,62); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 25,61); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 25,60); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 25,59); i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 37, 60); i++;

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

        mapNum = 3;
        i = 0;

    }

}
