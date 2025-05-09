package entities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.management.MBeanAttributeInfo;

import main.GamePanel;
import main.ImageModification;
import main.UI;

public class Entity {
    protected GamePanel gp;
    private UI ui;
    public int x, y;
    private int speed;

    public BufferedImage up1, up2, up3, up4, up5, up6,up7,up8,up9,up10,
            down1, down2, down3, down4, down5, down6,down7,down8,down9,down10,
            right1, right2, right3, right4, right5, right6,right7,right8,right9,right10,
            left1, left2, left3, left4, left5, left6,left7,left8,left9,left10,
            sdown1, sdown2, sdown3, sdown4, sdown5, sdown6,
            sup1, sup2, sup3, sup4, sup5, sup6,
            sright1, sright2, sright3, sright4, sright5, sright6,
            sleft1, sleft2, sleft3, sleft4, sleft5, sleft6,
            adown1,adown2,adown3,adown4,adown5,adown6,adown7,adown8,adown9,adown10,
            aup1,aup2,aup3,aup4,aup5,aup6,aup7,aup8,aup9,aup10,
            aleft1,aleft2,aleft3,aleft4,aleft5,aleft6,aleft7,aleft8,aleft9,aleft10,
            aright1,aright2,aright3,aright4,aright5,aright6,aright7,aright8,aright9,aright10;
    public String direction="sdown";
    
    private Rectangle solidArea = new Rectangle(24, 32, 64, 64);
    private Rectangle triggleInteract = new Rectangle(-50, 0, 192, 192);
    private Rectangle attackingArea = new Rectangle(0,0,24,24);
    public int solidAreaDefaultX, solidAreaDefaultY, triggerInteractDefaultX, triggerInteractDefaultY;
    private int dialogueIndex = 0;

    protected boolean collisionOn = false;
    private BufferedImage image,image2,image3,image4,image5;
    private String name;
    //STATUS
    private boolean collision = false;
    private boolean invincible = false;
    private int invincibleCounter=0;
    private int type;//player =1, npc=2, monster =3;
    private boolean attacking = false;
    private boolean alive = true;
    private boolean dying = false;
    private boolean HPbar = false;
    //CHARACTER STATUS
    private int maxLife,life;
    //COUNTER
    private int dyingCounter=0;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int HPbarCounter=0;


    public Entity(GamePanel gp) {
        this.gp = gp;
        this.solidAreaDefaultX = solidArea.x;
        this.solidAreaDefaultY = solidArea.y;
        this.triggerInteractDefaultX = triggleInteract.x;
        this.triggerInteractDefaultY = triggleInteract.y;
        this.ui = gp.getui();
    }
    

    public BufferedImage setup(String path,int size) {
        BufferedImage image = null;
        ImageModification mod = new ImageModification();
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res" + path + ".png"));
            image = mod.scaleImage(image, gp.getTileSize()*size, gp.getTileSize()*size);
        } catch (Exception e) {
            System.out.println("EXCEPTION while loading: " + path + ".png");
            e.printStackTrace();
        }
        return image;
    }
    //public int getDialogueIndex(){return dialogueIndex;}
    public BufferedImage getSdown1(){return sdown1;}
    public void setSdown1(BufferedImage sdown1){this.sdown1=sdown1;}
    public BufferedImage getImage(){return image;}
    public BufferedImage getImage2(){return image2;}
    public BufferedImage getImage3(){return image3;}
    public BufferedImage getImage4(){return image4;}
    public BufferedImage getImage5(){return image5;}
    public void setImage(BufferedImage image){this.image=image;}
    public void setImage2(BufferedImage image){this.image2=image;}
    public void setImage3(BufferedImage image){this.image3=image;}
    public void setImage4(BufferedImage image){this.image4=image;}
    public void setImage5(BufferedImage image){this.image5=image;}
    public int getAttackX(){return x;}
    public int getAttackY(){return y;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public boolean getCollision(){return collision;}
    public void setCollision(boolean collision){this.collision=collision;}
    public Rectangle getSolidArea() {return solidArea;}
    public void setSolidArea(Rectangle solidArea) {this.solidArea = solidArea;}
    public boolean getCollisionOn() {return collisionOn;}
    public void setCollisionOn(boolean collisionOn) { this.collisionOn = collisionOn;}
    public int getX() {return x;}
    public int getY() { return y;}
    public int getSolidAreaDefaultX() {return solidAreaDefaultX;}
    public int getSolidAreaDefaultY() {return solidAreaDefaultY;}
    public int getSolidAreaX() {return solidArea.x;}
    public int getSolidAreaY() {return solidArea.y;}
    public int getSolidAreaWidth() {return solidArea.width;}
    public int getSolidAreaHeight() {return solidArea.height;}
    public int getTriggerInteractX() {return triggleInteract.x;}
    public int getTriggerInteractY() {return triggleInteract.y;}
    public int getTriggerInteractWidth() {return triggleInteract.width;}
    public int getTriggerInteractHeight() {return triggleInteract.height;}
    public int getTriggerInteractDefaultX() {return triggerInteractDefaultX;}
    public int getTriggerInteractDefaultY() {return triggerInteractDefaultY;}
    public Rectangle getTriggerInteract() {return triggleInteract;}
    public Rectangle getAttackingArea(){return attackingArea;}
    public void setRectangle(Rectangle attackingArea){this.attackingArea=attackingArea;}
    public int getAttackingAreaX(){return attackingArea.x;}
    public int getAttackingAreaY(){return attackingArea.y;}
    public int getAttackingAreaWidth(){return attackingArea.width;}
    public int getAttackingAreaHeight(){return attackingArea.height;}
    public void setAttackingAreaX(int x){this.attackingArea.x=x;}
    public void setAttackingAreaY(int y){this.attackingArea.y=y;}
    public void setAttackingAreaWidth(int width){this.attackingArea.width=width;}
    public void setAttackingAreaHeight(int height){this.attackingArea.height=height;}
    public int getSpeed() {return speed;}
    public UI getui() {return ui;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}

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
    public void setSolidAreaWidth(int width){solidArea.width=width;}
    public void setSolidAreaHeight(int height){solidArea.height=height;}
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
    public int getMaxLife(){return maxLife;}
    public int getLife(){return life;}
    public void setMaxLife(int maxLife){this.maxLife=maxLife;}
    public void setLife(int life){this.life=life;}
    public void setSpeed(int speed){this.speed=speed;}
    public String getDirection(){return direction;}
    public void setDirection(String direction){this.direction=direction;}
    public boolean getInvincible(){return invincible;}
    public void setInvincible(boolean invincible){this.invincible=invincible;}
    public int getInvincibleCounter(){return invincibleCounter;}
    public void setInvincibleCounter(int invincibleCounter){this.invincibleCounter=invincibleCounter;}
    public int getType(){return type;}
    public void setType(int type){ this.type = type;}
    public boolean getAttacking(){return attacking;}
    public void setAttacking(boolean attacking){this.attacking=attacking;}
    public boolean getAlive(){return alive;}
    public boolean getDying(){return dying;}
    public void setAlive(boolean alive){this.alive = alive;}
    public void setDying(boolean dying){this.dying = dying;}

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
        BufferedImage image = null;
        switch(direction){
            case "up":
                if (spriteNum==1){ image=up1; }
                if (spriteNum==2){ image=up2; }
                if (spriteNum==3){ image=up3; }
                if (spriteNum==4){ image=up4; }
                if (spriteNum==5){ image=up5; }
                if (spriteNum==6){ image=up6; }
                if (spriteNum==7){ image=up7; }
                if (spriteNum==8){ image=up8; }
                if (spriteNum==9){ image=up9; }
                if (spriteNum==10){ image=up10; }
                break;
            case "down":
                if (spriteNum==1){ image=down1; }
                if (spriteNum==2){ image=down2; }
                if (spriteNum==3){ image=down3; }
                if (spriteNum==4){ image=down4; }
                if (spriteNum==5){ image=down5; }
                if (spriteNum==6){ image=down6; }
                if (spriteNum==7){ image=down7; }
                if (spriteNum==8){ image=down8; }
                if (spriteNum==9){ image=down9; }
                if (spriteNum==10){ image=down10; }
                break;
            case "right":
                if (spriteNum==1){ image=right1; }
                if (spriteNum==2){ image=right2; }
                if (spriteNum==3){ image=right3; }
                if (spriteNum==4){ image=right4; }
                if (spriteNum==5){ image=right5; }
                if (spriteNum==6){ image=right6; }
                if (spriteNum==7){ image=right7; }
                if (spriteNum==8){ image=right8; }
                if (spriteNum==9){ image=right9; }
                if (spriteNum==10){ image=right10; }
                break;
            case "left":
                if (spriteNum==1){ image=left1; }
                if (spriteNum==2){ image=left2; }
                if (spriteNum==3){ image=left3; }
                if (spriteNum==4){ image=left4; }
                if (spriteNum==5){ image=left5; }
                if (spriteNum==6){ image=left6; }
                if (spriteNum==7){ image=left7; }
                if (spriteNum==8){ image=left8; }
                if (spriteNum==9){ image=left9; }
                if (spriteNum==10){ image=left10; }
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
        if(type==3&&HPbar){
            double ratioBarOverLife = (double) gp.getTileSize()/maxLife;
            double HPvalue = ratioBarOverLife*life;
            g2.setColor(new Color(35,35,35));
            g2.fillRect(x-gp.getPlayerX()+gp.getPlayerScreenX()+getSolidAreaX()-8, y-gp.getPlayerY()+gp.getPlayerScreenY()-1, (int)HPvalue+2, 12);
            g2.setColor(new Color(255,0,30));
            g2.fillRect(x-gp.getPlayerX()+gp.getPlayerScreenX()+getSolidAreaX()-7, y-gp.getPlayerY()+gp.getPlayerScreenY(), (int)HPvalue, 10);
            HPbarCounter++;
            if(HPbarCounter>=600){
                HPbar=false;
                HPbarCounter=0;
            }
        } 
        if (getDying()) {dyingAnimation(g2);} 
        g2.drawImage(image,x - gp.getPlayerX() + gp.getPlayerScreenX(),(y - gp.getPlayerY() + gp.getPlayerScreenY()),null);
        Transparency(g2,1F);
       

    }

    public void update() {
        setAction();
        collisionOn=false;
        gp.getColCheckTile(this);
        gp.getColCheckEntity(this, gp.getNPC());
        gp.getColCheckEntity(this, gp.getMonster());
        gp.getColCheckObject(this, false);
        boolean contactPlayer = gp.getColCheckPlayer(this);
        if(getType()==3&&contactPlayer==true){
            if(gp.getPlayer().getInvincible()==false){
                gp.playSoundEffect(7);
                gp.getPlayer().setLife(gp.getPlayer().getLife()-1);
                gp.getPlayer().setInvincible(true);
            }
        }
  
        if(collisionOn==false){
           switch(direction){
               case "up": y-=getSpeed(); break;
               case "down": y+=getSpeed(); break;
               case "right":x+=getSpeed(); break;
               case "left": x-=getSpeed(); break;
           }
        }
        if(getInvincible()){
            setInvincibleCounter(getInvincibleCounter()+1);
            if(getInvincibleCounter()==20){
                HPbar=true;
                setInvincible(false);
                setInvincibleCounter(0);
            }
        }
    }

    public void setAction() {}

    public void speak() {
        if (ui.getDialogue(dialogueIndex) == null) {
            dialogueIndex = 0;
        }
        gp.getui().setCurrentDialogue(ui.getDialogue(dialogueIndex));
        dialogueIndex++;
    }

    public void getPlayerImage() {
        down1 = setup("/player/down1",2);
        down2 = setup("/player/down2",2);
        down3 = setup("/player/down3",2);
        down4 = setup("/player/down4",2);
        down5 = setup("/player/down5",2);
        down6 = setup("/player/down6",2);

        left1 = setup("/player/left1",2);
        left2 = setup("/player/left2",2);
        left3 = setup("/player/left3",2);
        left4 = setup("/player/left4",2);
        left5 = setup("/player/left5",2);
        left6 = setup("/player/left6",2);

        right1 = setup("/player/right1",2);
        right2 = setup("/player/right2",2);
        right3 = setup("/player/right3",2);
        right4 = setup("/player/right4",2);
        right5 = setup("/player/right5",2);
        right6 = setup("/player/right6",2);

        up1 = setup("/player/up1",2);
        up2 = setup("/player/up2",2);
        up3 = setup("/player/up3",2);
        up4 = setup("/player/up4",2);
        up5 = setup("/player/up5",2);
        up6 = setup("/player/up6",2);

        sdown1 = setup("/player/sdown1",2);
        sdown2 = setup("/player/sdown2",2);
        sdown3 = setup("/player/sdown3",2);
        sdown4 = setup("/player/sdown4",2);

        sup1 = setup("/player/sup1",2);
        sup2 = setup("/player/sup2",2);
        sup3 = setup("/player/sup3",2);
        sup4 = setup("/player/sup4",2);

        sright1 = setup("/player/srigt1",2);
        sright2 = setup("/player/sright2",2);
        sright3 = setup("/player/sright3",2);
        sright4 = setup("/player/sright4",2);

        sleft1 = setup("/player/sleft1",2);
        sleft2 = setup("/player/sleft2",2);
        sleft3 = setup("/player/sleft3",2);
        sleft4 = setup("/player/sleft4",2);
    }
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        System.out.println(dyingCounter);
        int i=5;
        if(dyingCounter<=i) Transparency(g2,0f);
        if(dyingCounter>i&&dyingCounter<=i*2) Transparency(g2,1f);
        if(dyingCounter>i*2&&dyingCounter<=i*3) Transparency(g2,0f);
        if(dyingCounter>i*3&&dyingCounter<=i*4) Transparency(g2,1f);
        if(dyingCounter>i*4&&dyingCounter<=i*5) Transparency(g2,0f);
        if(dyingCounter>i*5&&dyingCounter<=i*6) Transparency(g2,1f);
        if(dyingCounter>i*6&&dyingCounter<=i*7) Transparency(g2,0f);
        if(dyingCounter>i*7&&dyingCounter<=i*8) Transparency(g2,1f);
        if(dyingCounter>i*8){
            dying=false;
            alive=false;
            dyingCounter=0;
        }
    }
    public void Transparency(Graphics2D g2,float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

}