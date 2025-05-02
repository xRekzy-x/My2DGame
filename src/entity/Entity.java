package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity {
    GamePanel gp;
    public int x,y;
    
    public int speed;
    
    public BufferedImage up1,up2,up3,up4,up5,up6,
                        down1,down2,down3,down4,down5,down6,
                        right1,right2,right3,right4,right5,right6,
                        left1,left2,left3,left4,left5,left6,
                        sdown1,sdown2,sdown3,sdown4,
                        sup1,sup2,sup3,sup4,
                        sright1,sright2,sright3,sright4,
                        sleft1,sleft2,sleft3,sleft4;
    public String direction;
    public int spriteCounter=0;
    public int spriteNum=1;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX,solidAreaDefaultY;
    protected boolean collisionOn = false;
    public Entity(GamePanel gp){
        this.gp=gp;

    }
    public Rectangle getSolidArea(){
        return solidArea;
    }
    public void setSolidArea(Rectangle solidArea){
        this.solidArea=solidArea;
    }
    public boolean getCollisionOn(){
        return collisionOn;
    }
    public void setCollisionOn(boolean collisionOn){
        this.collisionOn=collisionOn;
    }
    public int getX(){return x;}
    public int getY(){return y;}
    public int getSolidAreaDefaultX(){ return solidAreaDefaultX; }
    public int getSolidAreaDefaultY(){ return solidAreaDefaultY; }
    public int getSolidAreaX(){return solidArea.x;}
    public int getSolidAreaY(){return solidArea.y;}
    public int getSpeed(){return speed;}

    public void setSolidAreaDefaultX(int solidAreaDefaultX){ this.solidAreaDefaultX=solidAreaDefaultX;}
    public void setSolidAreaDefaultY(int solidAreaDefaultY){ this.solidAreaDefaultY=solidAreaDefaultY;}
    public void setSolidAreaX(int solidAreaX) {this.solidArea.x=solidAreaX; }
    public void setSolidAreaY(int solidAreaY) {this.solidArea.y=solidAreaY; }
}
