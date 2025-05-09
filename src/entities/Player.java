package entities;

import java.awt.Color;
//import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import main.ImageModification;

import javax.imageio.ImageIO;

//import main.GamePanel;
import main.KeyHandler;
import main.GamePanel;

public class Player extends Entity {
    KeyHandler key;
    final int screenX;
    final int screenY;
    private boolean hasKey = false;
    int randomX, randomY, checkIndex;
    int[][] mapTile;
    private int strenth = 0;
    private int NPCindex = 0, monsterIndex = 0;
    private int countDialogue = 0;
    private Entity monster;
    boolean chestOpenSoundEffect = true;

    Random random = new Random();

    public Player(GamePanel gp, KeyHandler key) {
        super(gp);
        this.key = key;// cho phép player hỏi trạng thái từ bàn phím chứ ko trực tiếp nghe
        setDefaultValues();
        getPlayerImage();
        addAttackImage();
        screenX = gp.getScreenWidth() / 2 - (gp.getTileSize());
        screenY = gp.getScreenHeight() / 2 - (gp.getTileSize());

        setSolidArea(new Rectangle(20 * 2, 62, 9 * 2, 7 * 2));
        this.setSolidAreaDefaultX(getSolidAreaX());
        this.setSolidAreaDefaultY(getSolidAreaY());// save the original value of x and y of solidArea cause it is going
                                                   // to change
        super.setType(1);

    }

    public boolean hasKey() {
        return hasKey;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public int getRandomX() {
        return randomX;
    }

    public void setRandomX(int randomX) {
        this.randomX = randomX;
    }

    public int getRandomY() {
        return randomY;
    }

    public void setRandomY(int randomY) {
        this.randomY = randomY;
    }

    public int getCheckIndex() {
        return checkIndex;
    }

    public void setCheckIndex(int checkIndex) {
        this.checkIndex = checkIndex;
    }

    public int[][] getMapTile() {
        return mapTile;
    }

    public void setMapTile(int[][] mapTile) {
        this.mapTile = mapTile;
    }

    public void setStrength(int strength) {
        this.strenth = strength;
    }

    public int getCountDialogue() {
        return countDialogue;
    }

    public void setCountDialogue(int countDialogue) {
        this.countDialogue = countDialogue;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getStrength() {
        return strenth;
    }

    public void setDefaultValues() {
        x = 300;
        y = 200;
        super.setSpeed(4);
        direction = "down";
        // PLAYER STATUS
        setMaxLife(12);
        setLife(getMaxLife());
    }

    public int getNPCindex() {
        return NPCindex;
    }

    public void setNPCindex(int NPCindex) {
        this.NPCindex = NPCindex;
    }

    public BufferedImage setup(String path, int width, int height) {
        BufferedImage image = null;
        ImageModification mod = new ImageModification();
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res" + path + ".png"));
            image = mod.scaleImage(image, width, height);
        } catch (Exception e) {
            System.out.println("ERROR WHEN TRYING TO LOAD"+path);
            e.printStackTrace();
        }
        return image;
    }

    public void getPlayerImage() {
        down1 = setup("/player/down1", gp.getTileSize() * 2, gp.getTileSize() * 2);
        down2 = setup("/player/down2", gp.getTileSize() * 2, gp.getTileSize() * 2);
        down3 = setup("/player/down3", gp.getTileSize() * 2, gp.getTileSize() * 2);
        down4 = setup("/player/down4", gp.getTileSize() * 2, gp.getTileSize() * 2);
        down5 = setup("/player/down5", gp.getTileSize() * 2, gp.getTileSize() * 2);
        down6 = setup("/player/down6", gp.getTileSize() * 2, gp.getTileSize() * 2);

        left1 = setup("/player/left1", gp.getTileSize() * 2, gp.getTileSize() * 2);
        left2 = setup("/player/left2", gp.getTileSize() * 2, gp.getTileSize() * 2);
        left3 = setup("/player/left3", gp.getTileSize() * 2, gp.getTileSize() * 2);
        left4 = setup("/player/left4", gp.getTileSize() * 2, gp.getTileSize() * 2);
        left5 = setup("/player/left5", gp.getTileSize() * 2, gp.getTileSize() * 2);
        left6 = setup("/player/left6", gp.getTileSize() * 2, gp.getTileSize() * 2);

        right1 = setup("/player/right1", gp.getTileSize() * 2, gp.getTileSize() * 2);
        right2 = setup("/player/right2", gp.getTileSize() * 2, gp.getTileSize() * 2);
        right3 = setup("/player/right3", gp.getTileSize() * 2, gp.getTileSize() * 2);
        right4 = setup("/player/right4", gp.getTileSize() * 2, gp.getTileSize() * 2);
        right5 = setup("/player/right5", gp.getTileSize() * 2, gp.getTileSize() * 2);
        right6 = setup("/player/right6", gp.getTileSize() * 2, gp.getTileSize() * 2);

        up1 = setup("/player/up1", gp.getTileSize() * 2, gp.getTileSize() * 2);
        up2 = setup("/player/up2", gp.getTileSize() * 2, gp.getTileSize() * 2);
        up3 = setup("/player/up3", gp.getTileSize() * 2, gp.getTileSize() * 2);
        up4 = setup("/player/up4", gp.getTileSize() * 2, gp.getTileSize() * 2);
        up5 = setup("/player/up5", gp.getTileSize() * 2, gp.getTileSize() * 2);
        up6 = setup("/player/up6", gp.getTileSize() * 2, gp.getTileSize() * 2);

        sdown1 = setup("/player/sdown1", gp.getTileSize() * 2, gp.getTileSize() * 2);
        sdown2 = setup("/player/sdown2", gp.getTileSize() * 2, gp.getTileSize() * 2);
        sdown3 = setup("/player/sdown3", gp.getTileSize() * 2, gp.getTileSize() * 2);
        sdown4 = setup("/player/sdown4", gp.getTileSize() * 2, gp.getTileSize() * 2);

        sup1 = setup("/player/sup1", gp.getTileSize() * 2, gp.getTileSize() * 2);
        sup2 = setup("/player/sup2", gp.getTileSize() * 2, gp.getTileSize() * 2);
        sup3 = setup("/player/sup3", gp.getTileSize() * 2, gp.getTileSize() * 2);
        sup4 = setup("/player/sup4", gp.getTileSize() * 2, gp.getTileSize() * 2);

        sright1 = setup("/player/sright1", gp.getTileSize() * 2, gp.getTileSize() * 2);
        sright2 = setup("/player/sright2", gp.getTileSize() * 2, gp.getTileSize() * 2);
        sright3 = setup("/player/sright3", gp.getTileSize() * 2, gp.getTileSize() * 2);
        sright4 = setup("/player/sright4", gp.getTileSize() * 2, gp.getTileSize() * 2);

        sleft1 = setup("/player/sleft1", gp.getTileSize() * 2, gp.getTileSize() * 2);
        sleft2 = setup("/player/sleft2", gp.getTileSize() * 2, gp.getTileSize() * 2);
        sleft3 = setup("/player/sleft3", gp.getTileSize() * 2, gp.getTileSize() * 2);
        sleft4 = setup("/player/sleft4", gp.getTileSize() * 2, gp.getTileSize() * 2);
    }

    public void addAttackImage() {
        adown1 = setup("/player/adown1", gp.getTileSize() * 2, gp.getTileSize() * 3);
        adown2 = setup("/player/adown2", gp.getTileSize() * 2, gp.getTileSize() * 3);
        adown3 = setup("/player/adown3", gp.getTileSize() * 2, gp.getTileSize() * 3);
        adown4 = setup("/player/adown4", gp.getTileSize() * 2, gp.getTileSize() * 3);
        adown5 = setup("/player/adown5", gp.getTileSize() * 2, gp.getTileSize() * 3);
        adown6 = setup("/player/adown6", gp.getTileSize() * 2, gp.getTileSize() * 3);
        adown7 = setup("/player/adown7", gp.getTileSize() * 2, gp.getTileSize() * 3);
        adown8 = setup("/player/adown8", gp.getTileSize() * 2, gp.getTileSize() * 3);
        adown9 = setup("/player/adown9", gp.getTileSize() * 2, gp.getTileSize() * 3);
        adown10 = setup("/player/adown10", gp.getTileSize() * 2, gp.getTileSize() * 3);
    }

    public void update() {
        // ATTACKING
        if (getAttacking()) {
            attack();
            // if(key.upPressed==true) y-=getSpeed();
            // if(key.downPressed==true) y+=getSpeed();
            // if(key.leftPressed==true) x-=getSpeed();
            // if(key.rightPressed==true) x+=getSpeed();
        } else {
            if (key.upPressed == true || key.downPressed == true || key.leftPressed == true
                    || key.rightPressed == true) {
                if (key.upPressed == true) {
                    direction = "up";
                }
                if (key.downPressed == true) {
                    direction = "down";
                }
                if (key.leftPressed == true) {
                    direction = "left";
                }
                if (key.rightPressed == true) {
                    direction = "right";
                }
                spriteCounter++;
                // CHECK TILE'S COLLISION
                collisionOn = false;
                gp.getColCheckTile(this);
                // CHECK OBJECT'S COLLISION
                int objIndex = gp.getColCheckObject(this, true);
                objectBehavior(objIndex);
                // CHECK ENTITY'S COLLISION
                monsterIndex = gp.getColCheckEntity(this, gp.getMonster());
                NPCindex = gp.getColCheckEntity(this, gp.getNPC());
                if (collisionOn == false) {
                    // switch(direction){
                    // case "up": y-=speed; break;
                    // case "down": y+=speed; break;
                    // case "right":x+=speed; break;
                    // case "left": x-=speed; break;
                    // } nếu không muốn người chơi đi chéo
                    if (key.upPressed == true)
                        y -= getSpeed();
                    if (key.downPressed == true)
                        y += getSpeed();
                    if (key.leftPressed == true)
                        x -= getSpeed();
                    if (key.rightPressed == true)
                        x += getSpeed();
                } else {
                    if (spriteCounter == 3 && monsterIndex == 999)
                        gp.playSoundEffect(5);
                }
                if (spriteCounter > 5) {
                    if (spriteNum == 1)
                        spriteNum = 2;
                    else if (spriteNum == 2)
                        spriteNum = 3;
                    else if (spriteNum == 3)
                        spriteNum = 4;
                    else if (spriteNum == 4)
                        spriteNum = 5;
                    else if (spriteNum == 5)
                        spriteNum = 6;
                    else if (spriteNum == 6)
                        spriteNum = 1;
                    spriteCounter = 0;
                }
            } else {
                switch (direction) {
                    case "up":
                        direction = "sup";
                        spriteNum = 1;
                        break;
                    case "down":
                        direction = "sdown";
                        spriteNum = 1;
                        break;
                    case "right":
                        direction = "sright";
                        spriteNum = 1;
                        break;
                    case "left":
                        direction = "sleft";
                        spriteNum = 1;
                        break;
                }

                spriteCounter++;
                if (spriteCounter > 10) {
                    if (spriteNum == 1)
                        spriteNum = 2;
                    else if (spriteNum == 2)
                        spriteNum = 3;
                    else if (spriteNum == 3)
                        spriteNum = 4;
                    else if (spriteNum == 4)
                        spriteNum = 1;
                    spriteCounter = 0;
                }
            }

            // CHECK EVENT
            gp.getEvent().checkEvent();

            // CHECK INTERACTION OF NPC
            NPCindex = gp.getColCheckInteract(this, gp.getNPC());
            interactNPC(NPCindex);
            if (getInvincible()) {
                setInvincibleCounter(getInvincibleCounter() + 1);
                if (getInvincibleCounter() == 30) {
                    setInvincible(false);
                    setInvincibleCounter(0);
                }
            }
            interactMonster(monsterIndex);
            // CHECK EVENT
            gp.getKey().setEnterPressed(false);

        }
    }

    public void debug(Graphics2D g2) {
        g2.setColor(new Color(255, 0, 0, 100));
        g2.fillRect(
                (gp.getPlayerScreenX() + getSolidAreaX()),
                (gp.getPlayerScreenY() + getSolidAreaY()),
                getSolidAreaWidth(), getSolidAreaHeight());
    }
    public void draw(Graphics2D g2) {
        int tempX = screenX;
        int tempY = screenY;
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (getAttacking() == false) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    if (spriteNum == 3) {
                        image = up3;
                    }
                    if (spriteNum == 4) {
                        image = up4;
                    }
                    if (spriteNum == 5) {
                        image = up5;
                    }
                    if (spriteNum == 6) {
                        image = up6;
                    }
                }
                if (getAttacking()) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    if (spriteNum == 3) {
                        image = up3;
                    }
                    if (spriteNum == 4) {
                        image = up4;
                    }
                    if (spriteNum == 5) {
                        image = up5;
                    }
                    if (spriteNum == 6) {
                        image = up6;
                    }
                }
                break;
            case "down":
                if (getAttacking() == false) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    if (spriteNum == 3) {
                        image = down3;
                    }
                    if (spriteNum == 4) {
                        image = down4;
                    }
                    if (spriteNum == 5) {
                        image = down5;
                    }
                    if (spriteNum == 6) {
                        image = down6;
                    }
                }
                if (getAttacking()) {
                    tempX -= 24;
                    if (spriteNum == 1) {
                        image = adown1;
                    }
                    if (spriteNum == 2) {
                        image = adown2;
                    }
                    if (spriteNum == 3) {
                        image = adown3;
                    }
                    if (spriteNum == 4) {
                        image = adown4;
                    }
                    if (spriteNum == 5) {
                        image = adown5;
                    }
                    if (spriteNum == 6) {
                        image = adown6;
                    }
                    if (spriteNum == 7) {
                        image = adown7;
                    }
                    if (spriteNum == 8) {
                        image = adown8;
                    }
                    if (spriteNum == 9) {
                        image = adown9;
                    }
                    if (spriteNum == 10) {
                        image = adown10;
                    }
                }
                break;
            case "right":
                if (getAttacking() == false) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    if (spriteNum == 3) {
                        image = right3;
                    }
                    if (spriteNum == 4) {
                        image = right4;
                    }
                    if (spriteNum == 5) {
                        image = right5;
                    }
                    if (spriteNum == 6) {
                        image = right6;
                    }
                }
                if (getAttacking()) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    if (spriteNum == 3) {
                        image = right3;
                    }
                    if (spriteNum == 4) {
                        image = right4;
                    }
                    if (spriteNum == 5) {
                        image = right5;
                    }
                    if (spriteNum == 6) {
                        image = right6;
                    }
                }
                break;
            case "left":
                if (getAttacking() == false) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    if (spriteNum == 3) {
                        image = left3;
                    }
                    if (spriteNum == 4) {
                        image = left4;
                    }
                    if (spriteNum == 5) {
                        image = left5;
                    }
                    if (spriteNum == 6) {
                        image = left6;
                    }
                }
                if (getAttacking()) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    if (spriteNum == 3) {
                        image = left3;
                    }
                    if (spriteNum == 4) {
                        image = left4;
                    }
                    if (spriteNum == 5) {
                        image = left5;
                    }
                    if (spriteNum == 6) {
                        image = left6;
                    }
                }
                break;
            case "sdown":
                if (getAttacking() == false) {
                    if (spriteNum == 1) {
                        image = sdown1;
                    }
                    if (spriteNum == 2) {
                        image = sdown2;
                    }
                    if (spriteNum == 3) {
                        image = sdown3;
                    }
                    if (spriteNum == 4) {
                        image = sdown4;
                    }
                }
                if (getAttacking()) {
                    tempX -= 24;
                    if (spriteNum == 1) {
                        image = adown1;
                    }
                    if (spriteNum == 2) {
                        image = adown2;
                    }
                    if (spriteNum == 3) {
                        image = adown3;
                    }
                    if (spriteNum == 4) {
                        image = adown4;
                    }
                    if (spriteNum == 5) {
                        image = adown5;
                    }
                    if (spriteNum == 6) {
                        image = adown6;
                    }
                    if (spriteNum == 7) {
                        image = adown7;
                    }
                    if (spriteNum == 8) {
                        image = adown8;
                    }
                    if (spriteNum == 9) {
                        image = adown9;
                    }
                    if (spriteNum == 10) {
                        image = adown10;
                    }
                }
                break;
            case "sup":
                if (spriteNum == 1) {
                    image = sup1;
                }
                if (spriteNum == 2) {
                    image = sup2;
                }
                if (spriteNum == 3) {
                    image = sup3;
                }
                if (spriteNum == 4) {
                    image = sup4;
                }
                break;
            case "sleft":
                if (spriteNum == 1) {
                    image = sleft1;
                }
                if (spriteNum == 2) {
                    image = sleft2;
                }
                if (spriteNum == 3) {
                    image = sleft3;
                }
                if (spriteNum == 4) {
                    image = sleft4;
                }
                break;
            case "sright":
                if (spriteNum == 1) {
                    image = sright1;
                }
                if (spriteNum == 2) {
                    image = sright2;
                }
                if (spriteNum == 3) {
                    image = sright3;
                }
                if (spriteNum == 4) {
                    image = sright4;
                }
                break;
        }
        g2.drawImage(image, tempX, tempY, null);
    }

    public int getAttackX() {
        int attX = x;
        switch (direction) {
            case "left":
                attX -= getAttackingAreaWidth();
                break;
            case "sleft":
                attX -= getAttackingAreaWidth();
                break;
            case "right":
                attX += getAttackingAreaWidth();
                break;
            case "sright":
                attX += getAttackingAreaWidth();
                break;
        }
        return attX;
    }

    public int getAttackY() {
        int attY = y;
        switch (direction) {
            case "up":
                attY -= getAttackingAreaHeight();
                break;
            case "sup":
                attY -= getAttackingAreaHeight();
                break;
            case "down":
                attY += getAttackingAreaHeight();
                break;
            case "sdown":
                attY += getAttackingAreaHeight();
                break;
        }
        return attY;
    }

    public void attack() {
        System.out.println(spriteCounter);
        int solidAreaWidth = getSolidAreaWidth(), solidAreaHeight = getSolidAreaHeight();
        setSolidAreaWidth(getAttackingAreaWidth());
        setSolidAreaHeight(getAttackingAreaHeight());
        damageMonster(gp.getColCheckEntity(this, gp.getMonster()));
        setSolidAreaWidth(solidAreaWidth);
        setSolidAreaHeight(solidAreaHeight);
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 10)
            spriteNum = 2;
        if (spriteCounter > 10 && spriteCounter <= 15) {
            spriteNum = 3;
            if (spriteCounter == 11)
                gp.playSoundEffect(8);
        }
        if (spriteCounter > 15 && spriteCounter <= 20)
            spriteNum = 4;
        if (spriteCounter > 20 && spriteCounter <= 25) {
            spriteNum = 5;
        }
        if (spriteCounter > 25 && spriteCounter <= 30)
            spriteNum = 6;
        if (spriteCounter > 30 && spriteCounter <= 35) {
            spriteNum = 7;
            if (spriteCounter == 31)
                gp.playSoundEffect(8);
        }
        if (spriteCounter > 35 && spriteCounter <= 40)
            spriteNum = 8;
        if (spriteCounter > 40 && spriteCounter <= 45)
            spriteNum = 9;
        if (spriteCounter > 45 && spriteCounter <= 50)
            spriteNum = 10;
        if (spriteCounter > 50) {
            spriteNum = 1;
            spriteCounter = 0;
            setAttacking(false);
        }
    }

    public void objectBehavior(int ObjIndex) {
        if (ObjIndex != 999) {// 999 is the index of monster, NPC ...
            switch (gp.getObjName(ObjIndex)) {
                case "Key":
                    gp.setObj(null, ObjIndex);
                    hasKey = true;
                    gp.getui().showMessage("+1");
                    break;

                case "Chest":
                    if (hasKey) {
                        System.out.println("HASSSSSKEUY");
                        gp.getObj(ObjIndex).setSdown1(setup("/obj/PLEASE", 1));
                        if (chestOpenSoundEffect) {
                            gp.playSoundEffect(3);
                            chestOpenSoundEffect = false;
                        }
                    } else
                        gp.getui().showMessage("You need a key");
                    break;
                case "Boots":
                    gp.setObj(null, ObjIndex);
                    setSpeed(getSpeed() + 3);
                    gp.playSoundEffect(2);
                    gp.getui().showMessage("Speed up!");
                    break;
                case "StrengthPotion":
                    strenth += 10;
                    randomX = random.nextInt(gp.getMaxWorldCol());
                    randomY = random.nextInt(gp.getMaxWorldRow());
                    mapTile = gp.getMapTile();

                    checkIndex = mapTile[randomY][randomX];
                    System.out.println(checkIndex);
                    System.out.println(randomX);
                    System.out.println(randomY);
                    System.out.println(gp.getExactTile(checkIndex).getGetCollision());
                    while (gp.getExactTile(checkIndex).getGetCollision()) {
                        if (randomX < gp.getMaxWorldCol() - 1)
                            randomX++;
                        else if (randomY < gp.getMaxWorldRow())
                            randomY++;
                        else
                            randomY--;
                        checkIndex = mapTile[randomY][randomX];
                    }

                    gp.getObj(ObjIndex).setX(randomX * gp.getTileSize());
                    gp.getObj(ObjIndex).setY(randomY * gp.getTileSize());
                    break;

            }
        } else {
            try {
                gp.getObj(0).setImage(ImageIO.read(getClass().getResourceAsStream("/res/obj/chest.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void interactNPC(int NPCindex) {
        if (NPCindex != 999) {
            switch (NPCindex) {
                case 1:
                    if (gp.getKey().getZPressed()) {
                        gp.setGameState(gp.getDialogueState());
                        gp.getExactNPC(NPCindex).speak();
                        gp.getKey().setZPressed(false);
                        countDialogue++;
                    }
                    if (gp.getKey().getEnterPressed()) {
                        if (gp.getui().getDialogue(countDialogue) == null) {
                            gp.setGameState(gp.getPlayState());
                            gp.getKey().setEnterPressed(false);
                            countDialogue = 0;
                        } else {
                            if (gp.getGameState() == gp.getPlayState()) {
                                gp.setGameState(gp.getDialogueState());
                            }
                            gp.getExactNPC(NPCindex).speak();
                            gp.getKey().setEnterPressed(false);
                            countDialogue++;
                        }
                    }
                    break;
            }
        } else {
            gp.getKey().setEnterPressed(false);
        }
    }

    public void interactMonster(int monsterIndex) {
        if (monsterIndex != 999) {
            // switch (monsterIndex) {
            // case 1:
            // break;

            // default:
            // break;
            // }
            if (getInvincible() == false) {
                gp.playSoundEffect(7);
                setLife(getLife() - 1);
                setInvincible(true);
            }
        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (gp.getExactMonster(i).getInvincible() == false) {
                gp.playSoundEffect(6);
                gp.getExactMonster(i).setLife(gp.getExactMonster(i).getLife() - 1);
                gp.getExactMonster(i).setInvincible(true);
                if (gp.getExactMonster(i).getLife() <= 0) {
                    System.out.println("hit");
                    // gp.setExactMonster(i, null);
                    gp.getExactMonster(i).setDying(true);
                }
            }
        } else {
            System.out.println("miss");
        }
    }
}
