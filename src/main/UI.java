package main;

import entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_Mana;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0: the first screen, 1: the second...
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    int subState = 0;
    public int counter = 0;
    public Entity npc;

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/res/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // CREATE HUB OBJECT
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        Entity crystal = new OBJ_Mana(gp);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;
        Entity bronzeCoin = new OBJ_Coin_Bronze(gp);
        coin = bronzeCoin.down1;

    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        // TITLE STATE
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        // PLAY STATE
        if (gp.gameState == gp.playState){
            drawPlayerLife();
            //drawMessage(); Drawmessage will be coment because they consume a lot of resources
        }
        // PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        // CHARACTER STATE
        if (gp.gameState == gp.charactersState){
            drawCharacterScreen();
            drawInventory(gp.player, true);
        }
        // OPTION STATE
        if (gp.gameState == gp.optionState) {
            drawOptionscreen();
        }
        // GAME OVER STATE
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        // TRANSITION STATE
        if (gp.gameState == gp.transitionState) {
            drawTransition();
        }
        // TRADE STATE
        if (gp.gameState == gp.tradeState) {
            drawTradeScreen();
        }

    }
    public void drawPlayerLife() {
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // DRAW MAX LIFE
        while (i < gp.player.maxLife/2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        // DRAW CURRENT LIFE
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

        // DRAW MAX MANA
        x = (gp.tileSize/2)-5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while (i < gp.player.maxMana) {
            g2.drawImage(crystal_blank, x, y , null);
            i++;
            x += 35;
        }

        // DRAW MANA
        x = (gp.tileSize/2)-5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while (i < gp.player.mana) {
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x += 35;
        }

    }
    public void drawPauseScreen() {

        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }
    public void drawMessage() {
       int messageX = gp.tileSize;
       int messageY = gp.tileSize*4;
       g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

       for (int i = 0; i < message.size(); i++) {

           if (message.get(i) != null) {

               g2.setColor(Color.black);
               g2.drawString(message.get(i), messageX+2, messageY+2);
               g2.setColor(Color.white);
               g2.drawString(message.get(i), messageX, messageY);

               int counter = messageCounter.get(i) + 1; // messageCounter++
               messageCounter.set(i, counter); // set the counter to the array
               messageY += 50;

               if (messageCounter.get(i) > 180) {
                   message.remove(i);
                   messageCounter.remove(i);
               }
           }
       }
    }
    public void drawTitleScreen() {

        if (titleScreenState == 0) {
            g2.setColor(new Color(0,0,0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Blue Boy Adventure";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;

            // SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x+5, y+5);

            // MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // BLUE BOY IMAGE
            x = gp.screenWidth/2 - (gp.tileSize*2)/2;
            y += gp.tileSize*2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if(commandNum == 0) {
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
        else if (titleScreenState == 1) {

            // CLASS SELECTION SCREEN
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select your Class!";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = getXforCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if(commandNum == 0) {
                g2.drawString(">", x-gp.tileSize, y);
            }
            text = "Archer";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Thief";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 3) {
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
    }
    public void drawDialogueScreen(){
        // WINDOW
        int x = gp.tileSize*3;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*6);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawCharacterScreen() {

        // CREATE A FRAME
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = (gp.tileSize*10)+15;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(35F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 32;

        // NAMES
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Speed", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Shield", textX, textY);
        textY += lineHeight;

        // VALUES
        int tailX = (frameX+ frameWidth) - 30;
        // Reset text Y
        textY = frameY +gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.speed );
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY -14, null);
        textY += gp.tileSize;

        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY -14, null);

    }
    public void drawInventory(Entity entity, boolean cursor) {

        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        if (entity == gp.player){
            frameX = gp.tileSize*13;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight = gp.tileSize*5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        }
        else {
            frameX = gp.tileSize*2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight = gp.tileSize*5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        // FRAME
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOT
        final int slotXstart = frameX +20;
        final int slotYstar = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstar;
        int slotSize = gp.tileSize+3;

        // DRAW PLAYER'S ITEMS
        for (int i = 0; i < entity.inventory.size(); i++) {

            // EQUIP CURSOR
            if (entity.inventory.get(i) == entity.currentWeapon ||
                    entity.inventory.get(i) == entity.currentShield) {
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX,slotY, gp.tileSize, gp.tileSize, 10, 10);
            }

            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);

            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        // CURSOR
        int cursorX = slotXstart + (slotSize*slotCol);
        int cursorY = slotYstar + (slotSize*slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        // DRAW CURSOR
        if (cursor) {
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            // DESCRIPTION FRAME
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight+10;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize*3;

            // DRAW DESCRIPTION TEXT
            int textX = dFrameX+20;
            int textY = dFrameY+gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(20F));

            int itemIndex = getItemIndexOnSlo(slotCol, slotRow);

            if (itemIndex < entity.inventory.size()) {

                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

                for (String line: entity.inventory.get(itemIndex).description.split("\n")){
                    g2.drawString(line, textX, textY);
                    textY += 32;
                }
            }
        }
    }
    public int getItemIndexOnSlo(int slotCol, int slotRow) {
        return  slotCol + (slotRow*5);
    }
    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0,0,0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    public void drawGameOverScreen() {

        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);


        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";
        // SHADOW
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        // MAIN
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        // Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-40,y);
        }


        // Back to the title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-40,y);
        }

    }
    public void drawTradeScreen() {

        switch (subState) {
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_Sell(); break;
        }
        gp.keyH.enterPressed = false;
    }
    public void trade_select() {

        drawDialogueScreen();

        // DRAW WINDOW
        int x = gp.tileSize*15;
        int y = gp.tileSize*4;
        int width = gp.tileSize*3;
        int height = (int) (gp.tileSize*3.5);
        drawSubWindow(x,y,width,height);

        // DRAW TEXTS
        x += gp.tileSize;
        y += gp.tileSize;

        g2.drawString("Buy", x,y);
        if (commandNum == 0) {
            g2.drawString(">", x-24, y);
            if (gp.keyH.enterPressed) {
                subState = 1;
            }
        }
        y += gp.tileSize;


        g2.drawString("Sell", x,y);
        if (commandNum == 1) {
            g2.drawString(">", x-24, y);
            if (gp.keyH.enterPressed) {
                subState = 2;
            }
        }
        y += gp.tileSize;

        g2.drawString("Leave", x,y);
        if (commandNum == 2) {
            g2.drawString(">", x-24, y);
            if (gp.keyH.enterPressed) {
                commandNum = 0;
                gp.gameState = gp.dialogueState;
                currentDialogue = "Come again, hehe!";

            }
        }

    }
    public void trade_buy() {

        // DRAW PLAYER INVNETORY
        drawInventory(gp.player, false);
        // DRAW INVENTORY NPC
        drawInventory(npc, true);

        //DRAW HINT WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize*9+15;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2-10;
        drawSubWindow(x,y,width,height);
        g2.setFont(g2.getFont().deriveFont(30F));
        g2.drawString("[ESC] Back", x+24, y+60 );

        // DRAW PLAYER COIN WINDOW
         x = gp.tileSize*13;
        drawSubWindow(x,y,width,height);
        g2.drawString("Your Coin:" + gp.player.coin, x+24, y+60 );

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlo(npcSlotCol, npcSlotRow);
        if (itemIndex < npc.inventory.size()) {
            x = (int)  (gp.tileSize*5.5);
            y = (int)  (gp.tileSize*5.5);
            width = (int)  (gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+ 8,32, 32, null);
            int price = npc.inventory.get(itemIndex).price;
            String text = ""+price;
            x = getXforAlignToRightText(text, gp.tileSize*8-20);
            g2.drawString(text,x, y+ 34);
        }

        // BUY AN ITEM
        if (gp.keyH.enterPressed) {
            if (npc.inventory.get(itemIndex).price > gp.player.coin) {
                subState = 0;
                gp.gameState = gp.dialogueState;
                currentDialogue = "You need more coin to buy that!";
                drawDialogueScreen();
            }
            else if (gp.player.inventory.size() == gp.player.maxInventorySize) {
                subState = 0;
                gp.gameState = gp.dialogueState;
                currentDialogue = "You cannot carry any more!";
            }
            else {
                gp.player.coin -= npc.inventory.get(itemIndex).price;
                gp.player.inventory.add(npc.inventory.get(itemIndex));
            }
        }

    }
    public void trade_Sell() {

        // DRAW PLAYER INVENTORYd
        drawInventory(gp.player, true);
        //DRAW HINT WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize*9+15;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2-10;
        drawSubWindow(x,y,width,height);
        g2.setFont(g2.getFont().deriveFont(30F));
        g2.drawString("[ESC] Back", x+24, y+60 );

        // DRAW PLAYER COIN WINDOW
        x = gp.tileSize*13;
        drawSubWindow(x,y,width,height);
        g2.drawString("Your Coin:" + gp.player.coin, x+24, y+60 );

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlo(playerSlotCol, playerSlotRow);
        if (itemIndex < gp.player.inventory.size()) {
            x = (int)  (gp.tileSize*16.5);
            y = (int)  (gp.tileSize*5.5);
            width = (int)  (gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+ 8,32, 32, null);

            int price = gp.player.inventory.get(itemIndex).price/2;
            if (price <= 0){
                price = 1;
            }
            String text = ""+price;
            x = getXforAlignToRightText(text, gp.tileSize*18-20);
            g2.drawString(text,x, y+ 34);


            // SELL AN ITEM
            if (gp.keyH.enterPressed) {

                if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon
                        || gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
                    commandNum = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You cannot sell an equipped item!";
                } else {
                    gp.player.inventory.remove(itemIndex);
                    gp.player.coin += price;
                }
            }
        }
    }
    public void drawTransition() {

        counter++;
        g2.setColor(new Color(0,0,0,counter*10));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

        if (counter == 25) {
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
        }
    }
    public void drawOptionscreen() {

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        // SUB WINDOW
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        switch (subState){
            case 0: options_top(frameX, frameY); break;
            case 1: options_control(frameX, frameY); break;
            case 2: options_endGameConfirmation(frameX, frameY); break;
        }
        gp.keyH.enterPressed = false;
    }
    public void options_top(int frameX, int frameY) {
        int textX;
        int textY;

        // TITLE
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY+gp.tileSize;
        g2.drawString(text, textX, textY);

        // FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Music", textX,textY);
        if (commandNum == 0){
            g2.drawString(">", textX-25, textY);
        }

        // SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if (commandNum == 1){
            g2.drawString(">", textX-25, textY);
        }
        // CONTROL
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if (commandNum == 2){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed){
                subState = 1;
                commandNum = 0;
            }
        }

        // END GAME
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if (commandNum == 3){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed){

                subState = 2;
                commandNum = 0;
            }
        }

        // BACK
        textY += gp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 4){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        // FULL SCREEN CHECK BOX
        textX = (int) (frameX + gp.tileSize*4.5);
        textY = frameY+ gp.tileSize*2 + 24;
        g2.drawRect(textX, textY, 120, 24); // 120/5 = 24
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX,textY, volumeWidth, 24);

        // SE VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX,textY, volumeWidth, 24);

        gp.config.saveConfig();

    }
    public void options_endGameConfirmation(int frameX, int frameY) {

        int textX = frameX + gp.tileSize;
        int textY = frameY +gp.tileSize*3;

        currentDialogue = "Quit the game and \nreturn to the title screen?";

        for (String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // YES
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
                gp.stopMusic();
                gp.gameState = gp.titleState;
            }
        }

        // NO
         text = "NO";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 3;
            }
        }

    }
    public void options_control(int frameX, int frameY) {
        int textX;
        int textY;

        // TITLE
        String text = "Control";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY +=gp.tileSize;
        g2.drawString("Confirm/Attack", textX, textY); textY +=gp.tileSize;
        g2.drawString("Shoot/Cast", textX, textY); textY +=gp.tileSize;
        g2.drawString("Character Screen", textX, textY); textY +=gp.tileSize;
        g2.drawString("Pause", textX, textY); textY +=gp.tileSize;
        g2.drawString("Options", textX, textY); textY +=gp.tileSize;

        textX = frameX + gp.tileSize*6;
        textY = frameY+gp.tileSize*2;
        g2.drawString("WASD", textX, textY); textY +=gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY +=gp.tileSize;
        g2.drawString("F", textX, textY); textY +=gp.tileSize;
        g2.drawString("C", textX, textY); textY +=gp.tileSize;
        g2.drawString("P", textX, textY); textY +=gp.tileSize;
        g2.drawString("ESC", textX, textY); textY +=gp.tileSize;

        // BACK
        textX = frameX+gp.tileSize;
        textY = frameY+gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 2;
            }
        }
    }
    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public int getXforAlignToRightText(String text, int tailX) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
