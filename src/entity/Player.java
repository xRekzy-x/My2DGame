package entity;
import java.awt.Color;
//import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

//import main.GamePanel;
import main.KeyHandler;
import objects.Chest;
import main.GamePanel;
import main.ImageModification;

public class Player extends Entity{
    KeyHandler key;
    final int screenX;
    final int screenY;
    private boolean hasKey=false;
    int randomX, randomY,checkIndex;
    int[][] mapTile;
    private int strenth=0;
   

    Random random = new Random();
   
    public Player(GamePanel gp,KeyHandler key){
        super(gp);
        this.key=key;//cho phép player hỏi trạng thái từ bàn phím chứ ko trực tiếp nghe
        setDefaultValues();
        getPlayerImage();
        screenX = gp.getScreenWidth()/2-(gp.getTileSize());
        screenY = gp.getScreenHeight()/2-(gp.getTileSize());

        solidArea = new Rectangle(20*2,55*2,7*2,5*2);
        this.setSolidAreaDefaultX(solidArea.x);
        this.setSolidAreaDefaultY(solidArea.y);// save the original value of x and y of solidArea cause it is going to change 

     
    }
   
    public int getScreenX(){
        return screenX;
    }
    public int getScreenY(){
        return screenY;
    }
    public int getStrength(){
        return strenth;
    }
    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;
        direction="down"; 
    }
    public void getPlayerImage(){
      
        down1 = setup("down1");
        down2 = setup("down2");
        down3 = setup("down3");
        down4 = setup("down4");
        down5 = setup("down5");
        down6 = setup("down6");
        
        left1 = setup("left1");
        left2 = setup("left2");
        left3 = setup("left3");
        left4 = setup("left4");
        left5 = setup("left5");
        left6 = setup("left6");
        
        right1 = setup("right1");
        right2 = setup("right2");
        right3 = setup("right3");
        right4 = setup("right4");
        right5 = setup("right5");
        right6 = setup("right6");
        
        up1 = setup("up1");
        up2 = setup("up2");
        up3 = setup("up3");
        up4 = setup("up4");
        up5 = setup("up5");
        up6 = setup("up6");
        
        sdown1 = setup("sdown1");
        sdown2 = setup("sdown2");
        sdown3 = setup("sdown3");
        sdown4 = setup("sdown4");
        
        sup1 = setup("sup1");
        sup2 = setup("sup2");
        sup3 = setup("sup3");
        sup4 = setup("sup4");
        
        sright1 = setup("sright1");
        sright2 = setup("sright2");
        sright3 = setup("sright3");
        sright4 = setup("sright4");
        
        sleft1 = setup("sleft1");
        sleft2 = setup("sleft2");
        sleft3 = setup("sleft3");
        sleft4 = setup("sleft4");   
 
    }
    public BufferedImage setup(String imageName){
        ImageModification mod = new ImageModification();
        BufferedImage image = null;
        try{
            
            image = ImageIO.read(getClass().getResourceAsStream("/res/player/"+imageName+".png"));
            image = mod.scaleImage(image, gp.getTileSize()*2, gp.getTileSize()*2);
        }
        catch(Exception e){ e.printStackTrace(); }
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2d.dispose();
        return image;

    }
    public void update(){
        if(key.upPressed==true||key.downPressed==true||key.leftPressed==true||key.rightPressed==true){
            if(key.upPressed==true){  direction="up";} 
            if(key.downPressed==true){  direction="down";}
            if(key.leftPressed==true){  direction="left";}
            if(key.rightPressed==true){  direction="right";}
            spriteCounter++;

            //CHECK TILE'S COLLISION
            collisionOn=false;
            gp.getColCheckTile(this);
            //CHECK OBJECT'S COLLISION
            int objIndex = gp.getColCheckObject(this, true);
            ObjectBehavior(objIndex);
            if(collisionOn==false){
                // switch(direction){
                //     case "up": y-=speed; break;
                //     case "down": y+=speed; break;
                //     case "right":x+=speed; break;
                //     case "left": x-=speed; break;
                // } nếu không muốn người chơi đi chéo
                if(key.upPressed==true) y-=speed;
                if(key.downPressed==true) y+=speed;
                if(key.leftPressed==true) x-=speed;
                if(key.rightPressed==true) x+=speed;
            }
            else gp.playSoundEffect(5);
            if(spriteCounter>5){
                if(spriteNum==1) spriteNum=2;
                else if (spriteNum==2) spriteNum=3;
                else if (spriteNum==3) spriteNum=4;
                else if (spriteNum==4) spriteNum=5;
                else if (spriteNum==5) spriteNum=6;
                else if (spriteNum==6) spriteNum=1;
                spriteCounter=0;
            }
        }
        else{
            switch(direction){
                case "up": direction="sup"; spriteNum=1; break;
                case "down": direction="sdown"; spriteNum=1; break;
                case "right": direction="sright"; spriteNum=1; break;
                case "left": direction="sleft"; spriteNum=1; break;
            }

            spriteCounter++;
            if(spriteCounter>10){
                if(spriteNum==1) spriteNum=2;
                else if (spriteNum==2) spriteNum=3;
                else if (spriteNum==3) spriteNum=4;
                else if (spriteNum==4) spriteNum=1;
                spriteCounter=0;
            }
        }
        
    }
    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, 48, 48);
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
                break;
            case "sup":
                if (spriteNum==1){ image=sup1; }
                if (spriteNum==2){ image=sup2; }
                if (spriteNum==3){ image=sup3; }
                if (spriteNum==4){ image=sup4; }
                break;
            case "sleft":
                if (spriteNum==1){ image=sleft1; }
                if (spriteNum==2){ image=sleft2; }
                if (spriteNum==3){ image=sleft3; }
                if (spriteNum==4){ image=sleft4; }
                break;
            case "sright":
                if (spriteNum==1){ image=sright1; }
                if (spriteNum==2){ image=sright2; }
                if (spriteNum==3){ image=sright3; }
                if (spriteNum==4){ image=sright4; }
                break;
        }
        g2.drawImage(image,screenX,screenY,null);
    }
    public void ObjectBehavior(int ObjIndex){
        if(ObjIndex!=999){//999 is the index of monster, NPC ...
            switch(gp.getObjName(ObjIndex)){
                case "Key": gp.setObj(null,ObjIndex); hasKey=true;
                    gp.getui().showMessage("+1");
                    break;

                case "Chest":
                if(hasKey){
                    try{
                        gp.getObj(ObjIndex).setImage(ImageIO.read(getClass().getResourceAsStream("/res/obj/PLEASE.png")));
                    }catch(IOException e){ e.printStackTrace(); }
                    gp.playSoundEffect(3);
                }
                else gp.getui().showMessage("You need a key");
                break;
                case "Boots":
                    gp.setObj(null,ObjIndex);
                    speed+=3;
                    gp.playSoundEffect(2);
                    gp.getui().showMessage("Speed up!");
                    break;
                case "StrengthPotion":
                    //gp.setObj(null,ObjIndex);
                    strenth+=10;
                    randomX=random.nextInt(gp.getMaxWorldCol());
                    randomY=random.nextInt(gp.getMaxWorldRow());
                    mapTile=gp.getMapTile();
                    
                    checkIndex= mapTile[randomY][randomX];
                    System.out.println(checkIndex);
                    System.out.println(randomX);
                    System.out.println(randomY);
                    System.out.println(gp.getExactTile(checkIndex).getGetCollision());
                    while(gp.getExactTile(checkIndex).getGetCollision()){
                        if(randomX<gp.getMaxWorldCol()-1) randomX++;
                        else if(randomY<gp.getMaxWorldRow()) randomY++;
                        else randomY--;
                        checkIndex= mapTile[randomY][randomX];
                    }
                    
                    gp.getObj(ObjIndex).setX(randomX*gp.getTileSize());
                    gp.getObj(ObjIndex).setY(randomY*gp.getTileSize());
                    break;

            }
        }
        else{
                try{
                    gp.getObj(0).setImage(ImageIO.read(getClass().getResourceAsStream("/res/obj/chest.png")));
                }catch(IOException e){ e.printStackTrace(); }
        }
    }
}
