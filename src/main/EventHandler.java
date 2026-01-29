package main;

import entity.Entity;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][][];
    Entity eventMaster;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventMaster = new Entity(gp);

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;
        while ( map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {

            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;

            if (col == gp.maxWorldCol){
                col = 0;
                row++;
                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
        setDialogue();
    }

    public void checkEvent() {
       // if(hit(27, 16, "right")) damagePit(gp.dialogueState);

        // Check if the player character is more than 1 tile away from the last event

        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {
            if(hit(0,27, 16, "right")) damagePit(gp.dialogueState);
            else if(hit(0,88,74, "up")) healingPool(gp.dialogueState);
            else if(hit(0,23,16,"any")) wetGround();
            else if(hit(0,53,15, "any")) teleport(1,51,44, gp.indoor); // Merchant hause go in
            else if(hit(1,51,45, "any")) teleport(0,53,15, gp.outside);
            else if(hit(1,51,42, "up")) speak(gp.npc[1][1]);
            else if(hit(1,53, 44,"any")) messageConvenient(gp.dialogueState);


            else if(hit(0,1,32, "any")) teleport(2,40,77, gp.dungeon); // first mine, go in
            else if(hit(2,39,77, "any")) teleport(0,1,32, gp.outside); // go outside

            else if(hit(2,78,62, "any")) teleport(3,58,72,gp.dungeon); // second mine go in
            else if(hit(3,57,72, "any")) teleport(2,78,62,gp.dungeon); // go outside the second mine

            else if(hit(3,59,72,"any")) wetGround();
            else if(hit(3,59,73,"any")) wetGround();
            else if(hit(3,59,74,"any")) wetGround();
            else if(hit(3,59,75,"any")) wetGround();

            else if(hit(3,57,59,"any")) wetGround();
            else if(hit(3,57,58,"any")) wetGround();
            else if(hit(3,57,57,"any")) wetGround();
            else if(hit(3,58,59,"any")) wetGround();
            else if(hit(3,58,58,"any")) wetGround();
            else if(hit(3,58,57,"any")) wetGround();

            else if(hit(3,65,48,"any")) wetGround();
            else if(hit(3,66,44,"any")) wetGround();
            else if(hit(3,76,46,"any")) wetGround();

            else if(hit(3,82, 60,"any")) wetGround();

            else if(hit(3,85,72,"any")) wetGround();

            else if(hit(3,68, 74,"any")) wetGround();
            else if(hit(3,69, 74,"any")) wetGround();

            else if(hit(3,61, 77,"any")) wetGround();
            else if(hit(3,62, 77,"any")) wetGround();

            else if(hit(3,87, 62,"any")) messageConvenient(gp.dialogueState);

        }
    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone ) {
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }
    public void setDialogue() {

        eventMaster.dialogues[0][0] = "You fall into a pit!";
        eventMaster.dialogues[1][0] = "The game was save, all status has been recovered";
        eventMaster.dialogues[1][1] = "Damn, this is good water";
        eventMaster.dialogues[2][0] = "What are u doing here? This's not done and idk if it'll be..";

    }
    public void teleport(int map, int col, int row, int area) {

        gp.gameState = gp.transitionState;
        gp.nextArea = area;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        gp.playSE(15);
    }
    public void damagePit(int gameState) {

        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster, 0);
        gp.playSE(6);
        gp.player.life -= 1;
        canTouchEvent = false;
    }
    public void messageConvenient(int gameState) {

        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster, 2);
        gp.playSE(6);
        canTouchEvent = false;
    }
    public void healingPool(int gameState) {
        if(gp.keyH.enterPressed) {
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            eventMaster.startDialogue(eventMaster, 1);
            gp.playSE(2);
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.aSetter.setMonster();
            gp.saveLoad.save();
        }
    }
    public void speak(Entity entity) {
        if (gp.keyH.enterPressed) {
            gp.gameState = gp.dialogueState;
            gp.player.attackCanceled = true;
            entity.speak();
        }
    }
    public void wetGround() {
        if (gp.player.wet && gp.player.wetCounter > 30){
            gp.player.wetCounter = 30;
        }
        gp.player.wet = true;
    }
}
