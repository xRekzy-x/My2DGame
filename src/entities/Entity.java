package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.management.MBeanAttributeInfo;

import main.GamePanel;
import main.ImageModification;
import main.UI;

public class Entity {
    GamePanel gp;
    private UI ui;
    public int x, y;

    public int speed;

    public BufferedImage up1, up2, up3, up4, up5, up6,
            down1, down2, down3, down4, down5, down6,
            right1, right2, right3, right4, right5, right6,
            left1, left2, left3, left4, left5, left6,
            sdown1, sdown2, sdown3, sdown4, sdown5, sdown6,
            sup1, sup2, sup3, sup4, sup5, sup6,
            sright1, sright2, sright3, sright4, sright5, sright6,
            sleft1, sleft2, sleft3, sleft4, sleft5, sleft6;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    private Rectangle solidArea = new Rectangle(24, 32, 64, 64);
    public int solidAreaDefaultX, solidAreaDefaultY, triggerInteractDefaultX, triggerInteractDefaultY;
    private int dialogueIndex = 0;
    private Rectangle triggleInteract = new Rectangle(-50, 0, 192, 192);
    protected boolean collisionOn = false;

    public Entity(GamePanel gp) {
        this.gp = gp;
        this.solidAreaDefaultX = solidArea.x;
        this.solidAreaDefaultY = solidArea.y;
        this.triggerInteractDefaultX = triggleInteract.x;
        this.triggerInteractDefaultY = triggleInteract.y;
        this.ui = gp.getui();
    }
    

    public BufferedImage setup(String path) {
        BufferedImage image = null;
        ImageModification mod = new ImageModification();
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res" + path + ".png"));
            image = mod.scaleImage(image, gp.getTileSize()*2, gp.getTileSize()*2);
        } catch (Exception e) {
            System.out.println("EXCEPTION while loading: " + path + ".png");
            e.printStackTrace();
        }
        return image;
    }
    //public int getDialogueIndex(){return dialogueIndex;}
    public Rectangle getSolidArea() { 
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public boolean getCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public int getSolidAreaX() {
        return solidArea.x;
    }

    public int getSolidAreaY() {
        return solidArea.y;
    }

    public int getSolidAreaWidth() {
        return solidArea.width;
    }

    public int getSolidAreaHeight() {
        return solidArea.height;
    }

    public int getTriggerInteractX() {
        return triggleInteract.x;
    }

    public int getTriggerInteractY() {
        return triggleInteract.y;
    }

    public int getTriggerInteractWidth() {
        return triggleInteract.width;
    }

    public int getTriggerInteractHeight() {
        return triggleInteract.height;
    }

    public int getTriggerInteractDefaultX() {
        return triggerInteractDefaultX;
    }

    public int getTriggerInteractDefaultY() {
        return triggerInteractDefaultY;
    }

    public Rectangle getTriggerInteract() {
        return triggleInteract;
    }

    public int getSpeed() {
        return speed;
    }

    public UI getui() {
        return ui;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

    public void setSolidAreaX(int solidAreaX) {
        this.solidArea.x = solidAreaX;
    }

    public void setSolidAreaY(int solidAreaY) {
        this.solidArea.y = solidAreaY;
    }

    public void setTriggerInteractX(int triggerInteractX) {
        this.triggleInteract.x = triggerInteractX;
    }

    public void setTriggerInteractY(int triggerInteractY) {
        this.triggleInteract.y = triggerInteractY;
    }

    public void setTriggerInteractWidth(int triggerInteractWidth) {
        this.triggleInteract.x = triggerInteractWidth;
    }

    public void setTriggerInteractHeight(int triggerInteractHeight) {
        this.triggleInteract.x = triggerInteractHeight;
    }

    public void setui(UI ui) {
        this.ui = ui;
    }

    public int getDialogueIndex() {
        return dialogueIndex;
    }
    public String getDirection(){return direction;}
    public void setDirection(String direction){this.direction=direction;}
    public void debug(Graphics2D g2){
        g2.setColor(new Color(255,255,255,100));
        g2.fillRect(x-gp.getPlayerX()+gp.getPlayerScreenX()+triggleInteract.x, y-gp.getPlayerY()+gp.getPlayerScreenY()+triggleInteract.y, triggleInteract.width, triggleInteract.height);
        g2.setColor(new Color(255,0,0,100)); // semi-transparent red
        g2.fillRect(
            (x-gp.getPlayerX()+gp.getPlayerScreenX()+getSolidAreaX()),
            (y-gp.getPlayerY()+gp.getPlayerScreenY()+getSolidAreaY()),
            getSolidAreaWidth(),
            getSolidAreaHeight()
        ); 
    }
    public void draw(Graphics2D g2){
  
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, 48, 48);
        // g2.setColor(Color.WHITE); // semi-transparent red
        // g2.fillRect(
        //     (gp.getPlayerScreenX()+getSolidAreaX()),
        //     (gp.getPlayerScreenY()+getSolidAreaY()),
        //     getSolidAreaWidth(),
        //     getSolidAreaHeight()
        // ); 
        BufferedImage image = null;
        switch(direction){
            case "up":
                if (spriteNum==1){ image=up1; }
                if (spriteNum==2){ image=up2; }
                if (spriteNum==3){ image=up3; }
                if (spriteNum==4){ image=up4; }
                if (spriteNum==5){ image=up5; }
                if (spriteNum==6){ image=up6; }
                break;
            case "down":
                if (spriteNum==1){ image=down1; }
                if (spriteNum==2){ image=down2; }
                if (spriteNum==3){ image=down3; }
                if (spriteNum==4){ image=down4; }
                if (spriteNum==5){ image=down5; }
                if (spriteNum==6){ image=down6; }
                break;
            case "right":
                if (spriteNum==1){ image=right1; }
                if (spriteNum==2){ image=right2; }
                if (spriteNum==3){ image=right3; }
                if (spriteNum==4){ image=right4; }
                if (spriteNum==5){ image=right5; }
                if (spriteNum==6){ image=right6; }
                break;
            case "left":
                if (spriteNum==1){ image=left1; }
                if (spriteNum==2){ image=left2; }
                if (spriteNum==3){ image=left3; }
                if (spriteNum==4){ image=left4; }
                if (spriteNum==5){ image=left5; }
                if (spriteNum==6){ image=left6; }
                break;
            case "sdown":
                if (spriteNum==1){ image=sdown1; }
                if (spriteNum==2){ image=sdown2; }
                if (spriteNum==3){ image=sdown3; }
                if (spriteNum==4){ image=sdown4; }
                if (spriteNum==5){ image=sdown5; }
                if (spriteNum==6){ image=sdown6; }
                break;
            case "sup":
                if (spriteNum==1){ image=sup1; }
                if (spriteNum==2){ image=sup2; }
                if (spriteNum==3){ image=sup3; }
                if (spriteNum==4){ image=sup4; }
                if (spriteNum==5){ image=sup5; }
                if (spriteNum==6){ image=sup6; }
                break;
            case "sleft":
                if (spriteNum==1){ image=sleft1; }
                if (spriteNum==2){ image=sleft2; }
                if (spriteNum==3){ image=sleft3; }
                if (spriteNum==4){ image=sleft4; }
                if (spriteNum==5){ image=sleft5; }
                if (spriteNum==6){ image=sleft6; }
                break;
            case "sright":
                if (spriteNum==1){ image=sright1; }
                if (spriteNum==2){ image=sright2; }
                if (spriteNum==3){ image=sright3; }
                if (spriteNum==4){ image=sright4; }
                if (spriteNum==5){ image=sright5; }
                if (spriteNum==6){ image=sright6; }
                break;
        }
        g2.drawImage(image,x - gp.getPlayerX() + gp.getPlayerScreenX(),(y - gp.getPlayerY() + gp.getPlayerScreenY()),null);
        //g2.drawImage(image, screenX, screenY, null);
    }

    public void update() {

    }

    public void setAction() {
    }

    public void speak() {
        if (ui.getDialogue(dialogueIndex) == null) {
            dialogueIndex = 0;
        }
        gp.getui().setCurrentDialogue(ui.getDialogue(dialogueIndex));
        dialogueIndex++;
    }

    public void getPlayerImage() {
        down1 = setup("/player/down1");
        down2 = setup("/player/down2");
        down3 = setup("/player/down3");
        down4 = setup("/player/down4");
        down5 = setup("/player/down5");
        down6 = setup("/player/down6");

        left1 = setup("/player/left1");
        left2 = setup("/player/left2");
        left3 = setup("/player/left3");
        left4 = setup("/player/left4");
        left5 = setup("/player/left5");
        left6 = setup("/player/left6");

        right1 = setup("/player/right1");
        right2 = setup("/player/right2");
        right3 = setup("/player/right3");
        right4 = setup("/player/right4");
        right5 = setup("/player/right5");
        right6 = setup("/player/right6");

        up1 = setup("/player/up1");
        up2 = setup("/player/up2");
        up3 = setup("/player/up3");
        up4 = setup("/player/up4");
        up5 = setup("/player/up5");
        up6 = setup("/player/up6");

        sdown1 = setup("/player/sdown1");
        sdown2 = setup("/player/sdown2");
        sdown3 = setup("/player/sdown3");
        sdown4 = setup("/player/sdown4");

        sup1 = setup("/player/sup1");
        sup2 = setup("/player/sup2");
        sup3 = setup("/player/sup3");
        sup4 = setup("/player/sup4");

        sright1 = setup("/player/sright1");
        sright2 = setup("/player/sright2");
        sright3 = setup("/player/sright3");
        sright4 = setup("/player/sright4");

        sleft1 = setup("/player/sleft1");
        sleft2 = setup("/player/sleft2");
        sleft3 = setup("/player/sleft3");
        sleft4 = setup("/player/sleft4");
    }

}