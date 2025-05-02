package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import objects.StrengPotion;
import main.ImageModification;

public class UI {
    GamePanel gp;
    Font arial_40;//declare font outside of gameloop
    ImageModification mod = new ImageModification();
    StrengPotion strengPotion = new StrengPotion();
    BufferedImage strengthPotImage;
    private boolean messageOn=false;
    private String message="";
    private int messageCounter;
    private boolean gameFinished;
    public UI(GamePanel gp){
        this.gp=gp;
        arial_40=new Font("Arial",Font.PLAIN,40);

        strengthPotImage= strengPotion.getImage();
        strengthPotImage = mod.scaleImage(strengthPotImage, 40, 40);
        
    }
    public void showMessage(String text){
        message=text;
        messageOn=true;
    }
    public void draw(Graphics g2){
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(strengthPotImage, 20, 70, null );
        g2.drawString(" : "+gp.getStrength(), 74, 100);
        g2.drawString("X: "+gp.getPlayerX()/gp.getTileSize(),74,140);
        g2.drawString("Y: "+gp.getPlayerY()/gp.getTileSize(),170,140);
        if(messageOn==true){
            g2.setFont(g2.getFont().deriveFont(30));
            g2.drawString(message, gp.getScreenWidth()/2+48 ,gp.getScreenHeight()/2);
            messageCounter++;
            if(messageCounter>90) {
                messageOn=false;
                messageCounter=0;
            }
        }
    }
}
