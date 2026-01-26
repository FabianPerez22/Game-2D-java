package entity;

import main.GamePanel;
import main.KeyHandler;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity{

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        initialSpeed = 4;
        speed = initialSpeed;
        direction = "down";

        // PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        strength = 1; // The more trength he has, the more damage he gives.
        dexterity = 1; // The more dexterity he has, the less damage he receives.
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_wood(gp);
        projectile = new OBJ_Fireball(gp);
        //projectile = new OBJ_Rock(gp); example of rock use ammo
        attack = getAttack(); // the total attack value is decided by strength and weapon.
        defense = getDefense(); // the total defense value is decided by dexterity and shield.
    }
    public void setDefaultPositions() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        direction = "down";
    }
    public void restoreLifeAndMana() {
        life = maxLife;
        mana = maxMana;
        invincible = false;
    }
    public void setItems() {

        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
    }
    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }
    public int getSpeed() {
        return speed = initialSpeed;
    }
    public void getPlayerImage() {
        up1 = setup("player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("player/boy_right_2", gp.tileSize, gp.tileSize);
    }
    public void getPlayerAttackImage() {
        if (currentWeapon.type == type_sword) {
            attackUp1 = setup("player/boy_attack_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("player/boy_attack_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("player/boy_attack_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("player/boy_attack_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("player/boy_attack_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("player/boy_attack_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("player/boy_attack_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("player/boy_attack_right_2", gp.tileSize*2, gp.tileSize);
        }
        if (currentWeapon.type == type_axe) {
            attackUp1 = setup("player/boy_axe_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("player/boy_axe_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("player/boy_axe_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("player/boy_axe_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("player/boy_axe_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("player/boy_axe_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("player/boy_axe_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("player/boy_axe_right_2", gp.tileSize*2, gp.tileSize);
        }
    }
    public void update() {
        if (attacking) {
            attacking();
        }

        else if (keyH.upPressed|| keyH.downPressed|| keyH.leftPressed || keyH.rightPressed|| keyH.enterPressed){
            if(keyH.upPressed){
                direction = "up";
            }
            else if (keyH.downPressed){
                direction = "down";
            }
            else if (keyH.leftPressed){
                direction = "left";
            }
            else if (keyH.rightPressed) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objectIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            // CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonsteR(monsterIndex);

            // CHECK INTERACTIVE TILE COLLISION
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn && !keyH.enterPressed) {
                switch (direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            if (keyH.enterPressed && !attackCanceled) {
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            standCounter++;
            if(standCounter == 20){
                spriteNum = 1;
                standCounter = 0;
            }
        }

        if (gp.keyH.shotKeyPressed && !projectile.alive && shotAvailableCounter == 30 && projectile.haveResource(this)) {

            // SET DEFAULT COORDINATEs, DIRECTION AND USER
            projectile.set(worldX, worldY, direction, true, this);

            // SUBTRACT THE COST (MANA, AMMO, ETC.)
            projectile.subtractResource(this);

            // ADD IT TO THE LIST
            gp.projectileList.add(projectile);

            shotAvailableCounter = 0;

            gp.playSE(12);
        }

        // This needs to be outside of key if statement
        if(invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        int iw = 20;
        if(wet) {
            wetCounter++;
            if (wetCounter == iw) {
                generateParticle(this,this);
                speed -= wetDebuf;
            }
            if (wetCounter == iw*2) {
                generateParticle(this,this);
            }
            if (wetCounter == iw*4) {
                generateParticle(this,this);
            }
            if (wetCounter == iw*6) {
                generateParticle(this,this);
            }
            if (wetCounter == iw*18) {
                generateParticle(this,this);
            }if (wetCounter == iw*10) {
                generateParticle(this,this);
            }if (wetCounter == iw*12) {
                generateParticle(this,this);
            }

            if (wetCounter > 240) {
                wet = false;
                speed = getSpeed();
                wetCounter = 0;
            }
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
        if (life > maxLife){
            life = maxLife;
        }
        if (mana > maxMana){
            mana = maxMana;
        }
        if (life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.playSE(14);
        }
    }
    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            // Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the attackArea

            switch (direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // Check monster collision with the update worldX, worldY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void pickUpObject(int i) {
        if(i != 999){

            // PICKUP ONLY ITEMS
            if (gp.obj[i].type == type_pickupOnly) {
                gp.obj[i].use(this);
                gp.obj[i] = null;
            }

            // INVENTORY ITEMS
            else {
                String text;
                if (inventory.size() != maxInventorySize) {
                    inventory.add(gp.obj[i]);
                    gp.playSE(1);
                    text = "Got a " + gp.obj[i].name + "!";
                } else {
                    text = "You cannot carry any more!";
                }
                gp.obj[i] = null;
            }
        }
    }
    public void interactNPC(int i) {
        if (gp.keyH.enterPressed) {
            if(i != 999){
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }
    public void contactMonsteR(int i) {
        if(i != 999) {
            if(!invincible && !gp.monster[i].dying) {
                gp.playSE(6);

                int damage = gp.monster[i].attack - defense;
                if (damage < 0) {
                    damage = 1;
                }

                life -= damage;
                invincible = true;
            }
        }
    }
    public void damageMonster(int i, int attack) {
        if (i != 999) {

            if (!gp.monster[i].invincible) {
                gp.playSE(5);

                int damage = attack - gp.monster[i].defense;
                if (damage < 0) {
                    damage = 1;
                }
                gp.monster[i].life -= damage;
                //gp.ui.addMessage(damage+ " damage!");

                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].life <= 0) {
                    gp.monster[i].life = 0;
                    gp.monster[i].dying = true;
                    //gp.ui.addMessage("Killed the " + gp.monster[i].name + "!");
                    exp += gp.monster[i].exp;
                    checkLevelUp();
                }
            }
        }
    }
    public void damageInteractiveTile(int i) {

        if (i != 999 && gp.iTile[i].destructible &&
                gp.iTile[i].isCorrectItem(this) && !gp.iTile[i].invincible) {
            gp.iTile[i].playSE();
            gp.iTile[i].life--;
            gp.iTile[i].invincible = true;

            // Generate particle
            generateParticle(gp.iTile[i], gp.iTile[i]);

            if (gp.iTile[i].life <= 0) {
                gp.iTile[i] = gp.iTile[i].getDestroyedForm();
            }
        }
    }
    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(9);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level " + level + " now!\n You feel more stronger";
        }
    }
    public void selectItem() {

        int itemIndex = gp.ui.getItemIndexOnSlo();

        if (itemIndex < inventory.size()) {

            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }

            if (selectedItem.type == type_shield ) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == type_consumable) {
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }
    public Color getParticleColor() {
        Color color = new Color(0, 255, 255);
        return color;
    }
    public int getParticleSize() {
        int size = 6;
        return size; // 6 pixels
    }
    public int getParticleSpeed() {
        int speed = 1;
        return speed;
    }
    public int getParticleMaxLife() {
        int maxLife = 20;
        return maxLife;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction){
            case "up":
                if (!attacking){
                    if (spriteNum == 1){ image = up1; }
                    if(spriteNum == 2){ image = up2; }
                }
                if (attacking){
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1){ image = attackUp1; }
                    if(spriteNum == 2){ image = attackUp2; }
                }
                break;
            case "down":
                if (!attacking){
                    if (spriteNum == 1){ image = down1; }
                    if(spriteNum == 2){ image = down2; }
                }
                if (attacking){
                    if (spriteNum == 1){ image = attackDown1; }
                    if(spriteNum == 2){ image = attackDown2; }
                }
                break;
            case "left":
                if (!attacking){
                    if (spriteNum == 1){ image = left1; }
                    if(spriteNum == 2){ image = left2; }
                }
                if (attacking){
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1){ image = attackLeft1; }
                    if(spriteNum == 2){ image = attackLeft2; }
                }
                break;
            case "right":
                if (!attacking){
                    if (spriteNum == 1){ image = right1; }
                    if(spriteNum == 2){ image = right2; }
                }
                if (attacking){
                    if (spriteNum == 1){ image = attackRight1; }
                    if(spriteNum == 2){ image = attackRight2; }
                }
                break;
        }

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY,null);

        // Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // DEBUG invincible
        //g2.setFont(new Font("Arial", Font.PLAIN, 26));
        //g2.setColor(Color.white);
        //g2.drawString("Invincible:" + invincibleCounter, 10, 400);

        // SHOW SOLID AREA PLAYER
        //g2.setColor(Color.red);
        //g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
    }
}
