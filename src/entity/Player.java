package entity;

import main.GamePanel;
import main.KeyHandler;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCanceled = false;
    public boolean lightUpdated = false;

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
    }

    public void setDefaultValues() {

        //init map
        //worldX = gp.tileSize * 88;
        //worldY = gp.tileSize * 76;

        //shop
        //worldX = gp.tileSize * 54;
        //worldY = gp.tileSize * 15;

        // Far from the cave
       worldX = gp.tileSize * 10;
       worldY = gp.tileSize * 36;

        // merchant map


        //worldX = gp.tileSize * 10;
        //worldY = gp.tileSize * 40;

        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down";

        // PLAYER STATUS
        level = 9;
        maxLife = 6;
        life = maxLife;
        maxMana = 10;
        mana = maxMana;
        maxStamina = 100;
        stamina = maxStamina;
        ammo = 10;
        strength = 1; // The more trength he has, the more damage he gives.
        dexterity = 1; // The more dexterity he has, the less damage he receives.
        exp = 0;
        nextLevelExp = 5;
        coin = 10000;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_wood(gp);
        currentLight = null;
        projectile = new OBJ_Fireball(gp);
        attack = getAttack(); // the total attack value is decided by strength and weapon.
        defense = getDefense(); // the total defense value is decided by dexterity and shield.

        getImage();
        getAttackImage();
        getGuardImage();
        setItems();
        setDialogue();

    }
    public void setDefaultPositions() {
        gp.currentMap = 0;
        worldX = gp.tileSize *  88;
        worldY = gp.tileSize *  76;
        direction = "down";
    }
    public void restoreStatus() {
        life = maxLife;
        mana = maxMana;
        stamina = maxStamina;
        speed = defaultSpeed;
        transparent = false;
        invincible = false;
        attacking = false;
        guarding = false;
        knockBack = false;
        lightUpdated = true;
    }
    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Pickaxe(gp));
        inventory.add(new OBJ_Lanter(gp));
        inventory.add(new OBJ_Lanter(gp));
    }
    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        attack = 0;
        if (currentWeapon.type == type_sword) {
            attack = strength * currentWeapon.attackValue;
        } else if (currentWeapon.type == type_axe) {
            attack = strength * currentWeapon.attackValue/-1;
        }
        return attack;
    }
    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }
    public int getCurrentWeaponSlot(){
        int currentWeaponSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == currentWeapon) {
                currentWeaponSlot = i;
            }
        }
        return currentWeaponSlot;
    }
    public int getCurrentShieldlot(){
        int currentShieldSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == currentShield) {
                currentShieldSlot = i;
            }
        }
        return currentShieldSlot;
    }
    public int getSpeed() {
        return speed = defaultSpeed;
    }
    public void getStamina() {
        stamina = maxStamina;
    }
    public void getImage() {
        up1 = setup("player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("player/boy_right_2", gp.tileSize, gp.tileSize);
    }
    public void getAttackImage() {
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
        if (currentWeapon.type == type_picaxe) {
            attackUp1 = setup("player/boy_pick_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("player/boy_pick_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("player/boy_pick_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("player/boy_pick_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("player/boy_pick_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("player/boy_pick_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("player/boy_pick_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("player/boy_pick_right_2", gp.tileSize*2, gp.tileSize);
        }
    }
    public void getGuardImage() {
        guardUp = setup("player/boy_guard_up", gp.tileSize, gp.tileSize);
        guardDown = setup("player/boy_guard_down", gp.tileSize, gp.tileSize);
        guardLeft = setup("player/boy_guard_left", gp.tileSize, gp.tileSize);
        guardRight = setup("player/boy_guard_right", gp.tileSize, gp.tileSize);
    }
    public void getSleepingImage(BufferedImage image) {

        up1 = image;
        up2 = image;
        down1 = image;
        down2 = image;
        left1 = image;
        left2 = image;
        right1 =image;
        right2 =image;
    }
    public void update() {

        if (currentLight != null && !lightUpdated) {
            lightCounter++;
            System.out.println("Ligh counter " + lightCounter);
            if (lightCounter > 180 ) {
                lightUpdated = true;
                currentLight.lightRadius =
                        Math.max(5, currentLight.lightRadius - lightConsumed);
                lightCounter = 0;
                if (currentLight.lightRadius <= 50) {
                    currentLight = null;
                }
            }
        }

        if (knockBack) {
            collisionOn = false;
            gp.cChecker.checkTile(this);
            gp.cChecker.checkObject(this, true);
            gp.cChecker.checkEntity(this, gp.npc);
            gp.cChecker.checkEntity(this, gp.monster);
            gp.cChecker.checkEntity(this, gp.iTile);

            if (collisionOn) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else if (!collisionOn) {
                switch (knockBackDirection) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
            knockBackCounter++;
            if (knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }

        else if (attacking) {
                attacking();
        }

        else if (keyH.spacePressed) {
            guarding = true;
            guardCounter++;
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
            if (!keyH.godModeOn){
                gp.cChecker.checkTile(this);
            }

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
            gp.cChecker.checkEntity(this, gp.iTile);

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
                if (stamina >= 10){
                    gp.playSE(7);
                    attacking = true;
                    spriteCounter = 0;
                    staminaCounter = 0;

                    // DECREASED DURABILITY
                    currentWeapon.durabilidy -= 0.2;

                    if (currentWeapon.type == type_axe) {
                        stamina -= 5;
                    } else if (currentWeapon.type == type_picaxe) {
                        stamina -=2;
                    } else if (currentWeapon.type == type_sword) {
                        stamina -= 10;
                    }
                }
            }

            attackCanceled = false;
            gp.keyH.enterPressed = false;
            guarding = false;
            guardCounter = 0;

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
            guarding = false;
            guardCounter = 0;
        }

        if (gp.keyH.shotKeyPressed && !projectile.alive && shotAvailableCounter == 30 && projectile.haveResource(this)) {

            // SET DEFAULT COORDINATEs, DIRECTION AND USER
            projectile.set(worldX, worldY, direction, true, this);

            // SUBTRACT THE COST (MANA, AMMO, ETC.)
            projectile.subtractResource(this);

            // CHECK VACANCY
            for (int i = 0; i < gp.projectileList[1].length; i++) {
                if (gp.projectileList[gp.currentMap][i] == null) {
                    gp.projectileList[gp.currentMap][i] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;

            gp.playSE(12);
        }

        // This needs to be outside of key if statement
        if(invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                transparent = false;
                invincibleCounter = 0;
            }
        }
        getDebuff();

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
        if (life > maxLife){
            life = maxLife;
        }
        if (mana > maxMana){
            mana = maxMana;
        }
        if (!keyH.godModeOn) {
            if (life <= 0) {
                gp.gameState = gp.gameOverState;
                gp.ui.commandNum = -1;
                gp.stopMusic();
                gp.playSE(14);
            }
        }
        if (stamina > 0 & !wet) {
            if (keyH.shiftPressed && stamina >= 15) {
                runCounter++;
                speed = defaultSpeed+1;
                staminaCounter = 0;
                if (runCounter > 60) {
                    stamina -= 10;
                    runCounter = 0;
                }
            } else {
                speed = getSpeed();
            }
        }

        if (stamina != maxStamina && !keyH.shiftPressed && !attacking) {
            staminaCounter++;
            if (staminaCounter > 120) {
                stamina += 10;
                staminaCounter = 80;
            }
        }

    }
    public void damageProjectile(int i) {
        if (i != 999) {
            Entity projectile = gp.projectileList[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
        }
    }
    public void pickUpObject(int i) {
        if(i != 999){

            // PICKUP ONLY ITEMS
            if (gp.obj[gp.currentMap][i].type == type_pickupOnly) {
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }
            // OBSTACLE
            else if (gp.obj[gp.currentMap][i].type == type_obstacle) {
                if (keyH.enterPressed) {
                    attackCanceled = true;
                    gp.obj[gp.currentMap][i].interact();
                }
            }

            // INVENTORY ITEMS
            else {
                String text;
                if (canObtainItem(gp.obj[gp.currentMap][i])) {
                    gp.playSE(1);
                    text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
                } else {
                    text = "You cannot carry any more!";
                }
                gp.obj[gp.currentMap][i] = null;
            }
        }
    }
    public void interactNPC(int i) {
        if(i != 999){
            if (gp.keyH.enterPressed) {
                attackCanceled = true;
                gp.npc[gp.currentMap][i].speak();
            }
            gp.npc[gp.currentMap][i].move(direction);
        }
    }
    public void contactMonsteR(int i) {
        if(i != 999) {
            if(!invincible && !gp.monster[gp.currentMap][i].dying) {
                gp.playSE(6);

                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if (damage < 1) {
                    damage = 1;
                }
                this.wet = gp.monster[gp.currentMap][i].applyWet;
                this.burned = gp.monster[gp.currentMap][i].applyBurned;

                life -= damage;
                transparent = true;
                invincible = true;
            }
        }
    }
    public void damageMonster(int i,Entity attacker, int attack, int knockBackPower) {
        if (i != 999) {
            if (!gp.monster[gp.currentMap][i].invincible) {
                gp.playSE(5);

                if (knockBackPower > 0) {
                    setKnockBack(gp.monster[gp.currentMap][i],attacker,  knockBackPower);
                }

                if (gp.monster[gp.currentMap][i].offBalance) {
                    attack *= 2;
                }

                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }
                // APPLY DEBUFF FROM THE CURRENT WEAPON
                gp.monster[gp.currentMap][i].wet = gp.player.currentWeapon.wet;
                gp.monster[gp.currentMap][i].burned = gp.player.currentWeapon.burned;

                gp.monster[gp.currentMap][i].life -= damage;

                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].life = 0;
                    gp.monster[gp.currentMap][i].dying = true;
                    //gp.ui.addMessage("Killed the " + gp.monster[i].name + "!");
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }
    public void damageInteractiveTile(int i) {

        if (i != 999 && gp.iTile[gp.currentMap][i].destructible &&
                gp.iTile[gp.currentMap][i].isCorrectItem(this) && !gp.iTile[gp.currentMap][i].invincible) {
            gp.iTile[gp.currentMap][i].playSE();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;

            // Generate particle
            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);

            if (gp.iTile[gp.currentMap][i].life <= 0) {
                gp.iTile[gp.currentMap][i].checkDrop();
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
            }
        }
    }
    public void setDialogue() {
        dialogues[0][0] = "You are level " + level + " now! You feel more stronger";
        if (level == 10) {
            dialogues[0][1] = "And you feel your fire ball can apply burned.";
        } else {
            dialogues[0][1] = null;
        }
    }
    public void checkLevelUp() {
        while (exp >= nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 2;
            strength++;
            dexterity++;
            maxStamina += 2;
            attack = getAttack();
            defense = getDefense();
            getStamina();
            setDialogue();
            gp.playSE(9);
            gp.gameState = gp.dialogueState;


            startDialogue(this,0);
        }
    }
    public void selectItem() {

        int itemIndex = gp.ui.getItemIndexOnSlo(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

        if (itemIndex < inventory.size()) {

            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == type_sword || selectedItem.type == type_axe || selectedItem.type == type_picaxe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getAttackImage();
            }

            if (selectedItem.type == type_shield ) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == type_light) {
                if (currentLight == selectedItem){
                    currentLight = null;
                }
                else {
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }
            if (selectedItem.type == type_consumable || selectedItem.type == type_light) {
                if(selectedItem.use(this)) {
                    if (selectedItem.amount > 1) {
                        selectedItem.amount--;
                    }
                    else {
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
    }
    public int searchItemInInventory(String itemName) {

        int itemIndex = 999;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name.equals(itemName)) {
                itemIndex = i;
                break;
            }
        }
        return  itemIndex;
    }
    public boolean canObtainItem(Entity item) {

        boolean canObtain = false;
        Entity newItem = gp.eGenerator.getObject(item.name);

        // CHECK IF STACKABLE
        if (newItem.stackable) {
            int index =  searchItemInInventory(newItem.name);

            if (index != 999) {
                inventory.get(index).amount++;
                canObtain = true;
            }
            else {
                // New Item so need to check vacany
                if(inventory.size() != maxInventorySize){
                    inventory.add(newItem);
                    canObtain = true;
                }
            }
        }
        else {
            // NOT STACKABLE so check vacancy
            if(inventory.size() != maxInventorySize){
                inventory.add(newItem);
                canObtain = true;
            }
        }
        return canObtain;
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
                if (guarding){
                    image = guardUp;
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
                if (guarding){
                    image = guardDown;
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
                if (guarding){
                    image = guardLeft;
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
                if (guarding){
                    image = guardRight;
                }
                break;
        }

        if (transparent) {
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
