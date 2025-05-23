package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObj {
    private BufferedImage image,image2,image3,image4,image5;
    private String name;
    private boolean collision = false;
    private int x,y;
    private Rectangle solidArea= new Rectangle(0,0,48,48);
    private int defaultSolidAreaX=0;
    private int defaultSolidAreaY=0;
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
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public boolean getCollision(){return collision;}
    public void setCollision(boolean collision){this.collision=collision;}
    public Rectangle getSolidArea(){return solidArea;}
    public int getSolidAreaX(){ return solidArea.x;}
    public int getSolidAreaY(){ return solidArea.y;}
    public void setSolidAreaX(int solidAreaX){this.solidArea.x=solidAreaX;}
    public void setSolidAreaY(int solidAreaY){this.solidArea.y=solidAreaY;}
    public int getX(){ return x;}
    public int getY(){ return y;}
    public void setX(int x){ this.x=x; }
    public void setY(int y){ this.y=y; }
    public int getSolidAreaDefaultX(){return defaultSolidAreaX;}
    public int getSolidAreaDefaultY(){return defaultSolidAreaY;}
    public void draw(Graphics2D g2,GamePanel gp){
        int screenX=(x-gp.getPlayerX()+gp.getPlayerScreenX());
        int screenY=(y-gp.getPlayerY()+gp.getPlayerScreenY());
        
        /*giải thích 2 dòng trên: player đang ở vị trí random thì mình cho 
        player về góc trái trên cùng của màn hình rồi sau đó +screenX screenY
         để lôi player ra giữa màn hình */
        if(x+2*gp.getTileSize()>gp.getPlayerX()-gp.getPlayerScreenX()&&
           x-2*gp.getTileSize()<gp.getPlayerX()+gp.getPlayerScreenX()&&
           y-3*gp.getTileSize()<gp.getPlayerY()+gp.getPlayerScreenY()&&
           y+2*gp.getTileSize()>gp.getPlayerY()-gp.getPlayerScreenY()){
            g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }
}
