package entities;

import java.awt.Color;
//import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import main.ImageModification;

import javax.imageio.ImageIO;

//import main.GamePanel;
import main.KeyHandler;
import objects.Key;
import objects.Shield;
import objects.Sword;
import objects.UltraShield;
import objects.UltraSword;
import main.GamePanel;

public class Player extends Entity {
    KeyHandler key;
    final int screenX;
    final int screenY;
    private boolean hasKey = false;
    private boolean usedKey = false;
    int randomX, randomY, checkIndex;
    int[][] mapTile;
    private int NPCindex = 0, monsterIndex = 0;
    private int countDialogue = 0;
    private Entity monster;
    boolean chestOpenSoundEffect = true;
    private int attackSpriteCounter = 0;
    // INVENTORY
    private List<Entity> inventory = new ArrayList<>();
    private final int maxInventorySize = 20;
    //COUNTER
    int chestCount = 0;
    int chestCountTemp = 0;
    int manaCooldownCount = 0;

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
        setItems();

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

    public int getNPCindex() {
        return NPCindex;
    }

    public void setNPCindex(int NPCindex) {
        this.NPCindex = NPCindex;
    }

    public List<Entity> getInventory() {
        return inventory;
    }

    public void addInventory(Entity item) {
        inventory.add(item);
    }

    public void removeInventory(Entity item) {
        inventory.remove(item);
    }

    public int getMaxInventorySize() {
        return maxInventorySize;
    }

    public void setDefaultPosition() {
        x = 300;
        y = 200;
        direction = "sdown";
    }

    public void setDefaultValues() {
        setDefaultPosition();
        // PLAYER STATUS
        setMaxLife(12);
        setLife(getMaxLife());
        setMaxMana(4);
        setMana(getMaxMana());
        setSpeed(4);
        setLevel(1);
        setExp(0);
        setNextLevelExp(5);
        setCurrentWeapon(new Sword(gp));
        setCurrentShield(new Shield(gp));
        setStrength(1);
        setDef(1);
        setDamage(getStrength() * getCurrentWeapon().getAttackValue());
        setDefense(getDef() * getCurrentShield().getDefenseValue());
        setCoin(0);
        setProjectile(new Fireball(gp));
    }

    public void resetHealth() {
        setLife(getMaxLife());
    }

    public void setItems() {
        inventory.clear();
        inventory.add(getCurrentWeapon());
        inventory.add(getCurrentShield());
    }

    // public void defenseCalculation(){return
    // defense*getCurrentShield().getDefenseValue();}
    public BufferedImage setup(String path, int width, int height) {
        BufferedImage image = null;
        ImageModification mod = new ImageModification();
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res" + path + ".png"));
            image = mod.scaleImage(image, width, height);
        } catch (Exception e) {
            System.out.println("ERROR WHEN TRYING TO LOAD" + path);
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

        aup1 = setup("/player/aup1", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aup2 = setup("/player/aup2", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aup3 = setup("/player/aup3", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aup4 = setup("/player/aup4", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aup5 = setup("/player/aup5", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aup6 = setup("/player/aup6", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aup7 = setup("/player/aup7", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aup8 = setup("/player/aup8", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aup9 = setup("/player/aup9", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aup10 = setup("/player/aup10", gp.getTileSize() * 2, gp.getTileSize() * 3);

        aleft1 = setup("/player/aleft1", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aleft2 = setup("/player/aleft2", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aleft3 = setup("/player/aleft3", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aleft4 = setup("/player/aleft4", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aleft5 = setup("/player/aleft5", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aleft6 = setup("/player/aleft6", gp.getTileSize() * 2, gp.getTileSize() * 3);

        aright1 = setup("/player/aright1", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aright2 = setup("/player/aright2", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aright3 = setup("/player/aright3", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aright4 = setup("/player/aright4", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aright5 = setup("/player/aright5", gp.getTileSize() * 2, gp.getTileSize() * 3);
        aright6 = setup("/player/aright6", gp.getTileSize() * 2, gp.getTileSize() * 3);

    }

    public void update() {
        // checkIfDied();
        ensureLimit();
        checkLevelUp();
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
                } else {// khi va chạm
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
            } else {// khi đứng im
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

            // OUTSIDE (KHI CẢ ĐI CẢ ĐỨNG IM)
            manaCooldown();
            if(getShotCountdown()<30) setShotCountdown(getShotCountdown()+1);
            if (key.getShotKeyPressed() && getProjectile().getAlive() == false && getShotCountdown()==30 && getProjectile().enoughMana(this)) {// alive= false để ensure ko bắn ra cục khác
                                                                            // khi đang bắn 1 cục
                String proDirect = getDirection();
                int proX = getX();
                int proY = getY();
                if (proDirect=="up"||proDirect=="sup") proDirect = "up";
                if (proDirect=="down"||proDirect=="sdown") proY+= gp.getTileSize();
                if (proDirect=="right"||proDirect=="sright") proY+= gp.getTileSize()/2;
                if (proDirect=="left"||proDirect=="sleft")  proY+= gp.getTileSize()/2;

                getProjectile().set(proX, proY, proDirect, true, this);//SHOOTING
                setMana(getMana()-getProjectile().getCost()); //substract mana
                // gp.getProjectile().add(projectile);
                gp.addProjectile(getProjectile());
                setShotCountdown(0);
            }
            // CHECK ENTITY'S COLLISION
            monsterIndex = gp.getColCheckEntity(this, gp.getMonster());
            // CHECK EVENT
            gp.getEvent().checkEvent();
            // CHECK INTERACTION OF NPC
            NPCindex = gp.getColCheckInteract(this, gp.getNPC());
            interactNPC(NPCindex);
            if (getInvincible()) {
                setInvincibleCounter(getInvincibleCounter() + 1);
                if (getInvincibleCounter() == 60) {
                    setInvincible(false);
                    setInvincibleCounter(0);
                }
            }
            interactMonster(monsterIndex);
        }
    }

    public void ensureLimit() {
        if (getLife() > getMaxLife()) setLife(getMaxLife());
        if (getLife() <= 0) gp.setGameState(gp.getOverState());
        if (getMana() > getMaxMana()) setMana(getMaxMana());
        if (getMana() < 0) setMana(0);
    }

    public void debug(Graphics2D g2) {
        g2.setColor(new Color(255, 0, 0, 100));
        g2.fillRect(
                (gp.getPlayerScreenX() + getSolidAreaX()),
                (gp.getPlayerScreenY() + getSolidAreaY()),
                getSolidAreaWidth(), getSolidAreaHeight());
        g2.setColor(new Color(100, 100, 100, 100));
        switch (direction) {
            case "up":
            case "sup":
                g2.fillRect(
                        (gp.getPlayerScreenX() + getSolidAreaX() - 24),
                        (gp.getPlayerScreenY() - getAttackingAreaHeight() + getSolidAreaY()),
                        getAttackingAreaWidth(), getAttackingAreaHeight());
                break;
            case "down":
            case "sdown":
                g2.fillRect(
                        (gp.getPlayerScreenX() + getSolidAreaX() - 24),
                        (gp.getPlayerScreenY() + getAttackingAreaHeight() + getSolidAreaY()),
                        getAttackingAreaWidth(), getAttackingAreaHeight());
                break;
            case "left":
            case "sleft":
                g2.fillRect(
                        (gp.getPlayerScreenX() + getSolidAreaX() - 24 - getAttackingAreaWidth()),
                        (gp.getPlayerScreenY() + getSolidAreaY()),
                        getAttackingAreaWidth(), getAttackingAreaHeight());
                break;
            case "right":
            case "sright":
                g2.fillRect(
                        (gp.getPlayerScreenX() + getSolidAreaX() - 24 + getAttackingAreaWidth()),
                        (gp.getPlayerScreenY() + getSolidAreaY()),
                        getAttackingAreaWidth(), getAttackingAreaHeight());
                break;
        }

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
                    tempX -= 24;
                    if (spriteNum == 1) {
                        image = aup1;
                    }
                    if (spriteNum == 2) {
                        image = aup2;
                    }
                    if (spriteNum == 3) {
                        image = aup3;
                    }
                    if (spriteNum == 4) {
                        image = aup4;
                    }
                    if (spriteNum == 5) {
                        image = aup5;
                    }
                    if (spriteNum == 6) {
                        image = aup6;
                    }
                    if (spriteNum == 7) {
                        image = aup7;
                    }
                    if (spriteNum == 8) {
                        image = aup8;
                    }
                    if (spriteNum == 9) {
                        image = aup9;
                    }
                    if (spriteNum == 10) {
                        image = aup10;
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
                    tempX -= 24;
                    if (spriteNum == 1) {
                        image = aright1;
                    }
                    if (spriteNum == 2) {
                        image = aright2;
                    }
                    if (spriteNum == 3) {
                        image = aright3;
                    }
                    if (spriteNum == 4) {
                        image = aright4;
                    }
                    if (spriteNum == 5) {
                        image = aright5;
                    }
                    if (spriteNum == 6) {
                        image = aright6;
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
                    tempX -= 24;
                    if (spriteNum == 1) {
                        image = aleft1;
                    }
                    if (spriteNum == 2) {
                        image = aleft2;
                    }
                    if (spriteNum == 3) {
                        image = aleft3;
                    }
                    if (spriteNum == 4) {
                        image = aleft4;
                    }
                    if (spriteNum == 5) {
                        image = aleft5;
                    }
                    if (spriteNum == 6) {
                        image = aleft6;
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
                if (!getAttacking()) {
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
                }
                if (getAttacking()) {
                    tempX -= 24;
                    if (spriteNum == 1) {
                        image = aup1;
                    }
                    if (spriteNum == 2) {
                        image = aup2;
                    }
                    if (spriteNum == 3) {
                        image = aup3;
                    }
                    if (spriteNum == 4) {
                        image = aup4;
                    }
                    if (spriteNum == 5) {
                        image = aup5;
                    }
                    if (spriteNum == 6) {
                        image = aup6;
                    }
                    if (spriteNum == 7) {
                        image = aup7;
                    }
                    if (spriteNum == 8) {
                        image = aup8;
                    }
                    if (spriteNum == 9) {
                        image = aup9;
                    }
                    if (spriteNum == 10) {
                        image = aup10;
                    }
                }
                break;

            case "sleft":
                if (!getAttacking()) {
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
                }
                if (getAttacking()) {
                    tempX -= 24;
                    if (spriteNum == 1) {
                        image = aleft1;
                    }
                    if (spriteNum == 2) {
                        image = aleft2;
                    }
                    if (spriteNum == 3) {
                        image = aleft3;
                    }
                    if (spriteNum == 4) {
                        image = aleft4;
                    }
                    if (spriteNum == 5) {
                        image = aleft5;
                    }
                    if (spriteNum == 6) {
                        image = aleft6;
                    }
                }
                break;
            case "sright":
                if (!getAttacking()) {
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
                }
                if (getAttacking()) {
                    tempX -= 24;
                    if (spriteNum == 1) {
                        image = aright1;
                    }
                    if (spriteNum == 2) {
                        image = aright2;
                    }
                    if (spriteNum == 3) {
                        image = aright3;
                    }
                    if (spriteNum == 4) {
                        image = aright4;
                    }
                    if (spriteNum == 5) {
                        image = aright5;
                    }
                    if (spriteNum == 6) {
                        image = aright6;
                    }
                }
                break;
        }
        g2.drawImage(image, tempX, tempY, null);
    }

    public int getAttackX() {
        int attX = x - 12;
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
            // case "down":
            // attY += getAttackingAreaHeight();
            // break;
            // case "sdown":
            // attY += getAttackingAreaHeight();
            // break;
        }
        return attY;
    }

    public void attack() {
        int solidAreaWidth = getSolidAreaWidth(), solidAreaHeight = getSolidAreaHeight();
        setSolidAreaWidth(getAttackingAreaWidth());
        setSolidAreaHeight(getAttackingAreaHeight());
        damageMonster(gp.getColCheckEntity(this, gp.getMonster()),getDamage());
        setSolidAreaWidth(solidAreaWidth);
        setSolidAreaHeight(solidAreaHeight);
        attackSpriteCounter++;
        switch (direction) {
            case "up":
            case "sup":
            case "down":
            case "sdown":
                if (attackSpriteCounter <= 5) {
                    spriteNum = 1;
                } else if (attackSpriteCounter % 5 == 1) {
                    spriteNum++;
                }
                if (spriteCounter == 11)
                    gp.playSoundEffect(8);
                if (spriteCounter == 31)
                    gp.playSoundEffect(8);
                if (attackSpriteCounter > 50) {
                    spriteNum = 1;
                    attackSpriteCounter = 0;
                    setAttacking(false);
                }
                break;
            case "left":
            case "sleft":
            case "right":
            case "sright":
                if (attackSpriteCounter <= 8) {
                    spriteNum = 1;
                } else if (attackSpriteCounter % 8 == 1) {
                    spriteNum++;
                }
                if (spriteCounter == 11)
                    gp.playSoundEffect(8);
                if (spriteCounter == 31)
                    gp.playSoundEffect(8);
                if (attackSpriteCounter > 48) {
                    spriteNum = 1;
                    attackSpriteCounter = 0;
                    setAttacking(false);
                }
                break;
        }
    }

    public void pickupObject(int ObjIndex) {
        if (inventory.size() <= maxInventorySize) {
            inventory.add(gp.getObj(ObjIndex));
            gp.getui().addMessage("you've got a " + gp.getObj(ObjIndex).getName());
            gp.setObj(null, ObjIndex);
        } else {
            gp.getui().addMessage("Inventory full!");
        }

    }

    public void objectBehavior(int ObjIndex) {
        if (ObjIndex != 999) {// 999 is the index of monster, NPC ...
            switch (gp.getObjName(ObjIndex)) {
                case "Key":
                    hasKey = true;
                    pickupObject(ObjIndex);
                    break;

                case "Chest":
                    for (int i = 0; i < inventory.size(); i++) {
                        if (inventory.get(i).getName().matches("Key") &&
                            gp.getObj(ObjIndex).getName().equals("Chest")) {
                            gp.getObj(ObjIndex).setSdown1(setup("/obj/PLEASE", 1));
                            gp.getObj(ObjIndex).setName("UsedChest");
                            gp.setObj(new UltraSword(gp), 5, 5);
                            gp.setObj(new UltraShield(gp), 6, 5);

                            if (chestOpenSoundEffect) {
                                gp.playSoundEffect(3);
                                chestOpenSoundEffect = false;
                            }
                            inventory.remove(i);
                            usedKey = true;
                            break;
                        } else {
                            gp.getui().showMessage("You need a key");
                        }
                    }
                    break;
                case "UsedChest":
                    gp.getui().showMessage("chest is used");
                    break;
                case "Boots":
                    gp.setObj(null, ObjIndex);
                    setSpeed(getSpeed() + 3);
                    gp.playSoundEffect(2);
                    gp.getui().addMessage("Speed up!");
                    break;
                case "StrengthPotion":
                    pickupObject(ObjIndex);
                    break;
                case "UltraSword":
                    this.setCurrentWeapon(gp.getObj(ObjIndex));
                    pickupObject(ObjIndex);
                    break;
                case "UltraShield":
                    this.setCurrentShield(gp.getObj(ObjIndex));
                    pickupObject(ObjIndex);
                    break;
                case "Exp":
                    int [] newLoc = gp.generateRandomLocation();
                    setExp(getExp()+1);
                    gp.getObj(ObjIndex).setX(newLoc[0]);
                    gp.getObj(ObjIndex).setY(newLoc[1]);
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
            //gp.getKey().setEnterPressed(false);
        }
    }

    public void interactMonster(int monsterIndex) {
        if (monsterIndex != 999) {
            if (getInvincible() == false && !gp.getExactMonster(monsterIndex).getDying()) {
                int damageDeal = gp.getExactMonster(monsterIndex).getDamage() - gp.getPlayer().getDefense();
                if (damageDeal < 0)
                    damageDeal = 0;
                gp.playSoundEffect(7);
                setLife(getLife() - damageDeal);
                setInvincible(true);
            }
        }
    }// when player collide monster, player loses hp

    public void damageMonster(int i, int damage) {
        if (i != 999) {
            if (gp.getExactMonster(i).getInvincible() == false) {
                int damageDeal = damage - gp.getExactMonster(i).getDefense();
                if (damageDeal < 0)
                    damageDeal = 0;
                gp.playSoundEffect(6);
                gp.getExactMonster(i).setLife(gp.getExactMonster(i).getLife() - damageDeal);// reduce the health
                gp.getui().addMessage("-" + damageDeal);
                gp.getExactMonster(i).setInvincible(true);
                gp.getExactMonster(i).damageReaction();
                if (gp.getExactMonster(i).getLife() <= 0) {
                    gp.getExactMonster(i).setDying(true);
                    gp.getui().addMessage("You killed a " + gp.getExactMonster(i).getName());
                    setExp(getExp() + gp.getExactMonster(i).getExp());
                    gp.getui().addMessage("+" + gp.getExactMonster(i).getExp() + " EXP");
                }
            }
        }
    }// when player attact monster, monster lose hp

    public void selectItem() {
        if (inventory.get(gp.getui().getItemIndexInInventory()).getType() == getSwordType()) {
            setCurrentWeapon(inventory.get(gp.getui().getItemIndexInInventory()));
            gp.getKey().setEnterPressed(false);
        }
        if (inventory.get(gp.getui().getItemIndexInInventory()).getType() == getShieldType()) {
            setCurrentShield(inventory.get(gp.getui().getItemIndexInInventory()));
            gp.getKey().setEnterPressed(false);
        }
        if (inventory.get(gp.getui().getItemIndexInInventory()).getType() == getConsumableType()) {
            setDamage(getDamage() + 20);
            inventory.remove(gp.getui().getItemIndexInInventory());
            gp.getKey().setEnterPressed(false);
        }
    }

    public void checkLevelUp() {
        if (getExp() >= getNextLevelExp()) {
            setLevel(getLevel() + 1);
            setExp(0);
            setNextLevelExp(getNextLevelExp() * 2);
            setMaxLife(getMaxLife() + 4);
            setDamage(getDamage() + 50);
            setDefense(getDefense()+10);
            getProjectile().setDamage(getProjectile().getDamage()+100);
            setLife(getMaxLife());// heal to full health
            gp.playSoundEffect(10);
        }
    }
    public void manaCooldown(){
        if(manaCooldownCount<180) manaCooldownCount++;
        if(manaCooldownCount==180 && getMana()<getMaxMana()){
            setMana(getMana()+1);
            manaCooldownCount=0;
        }
    }
}
