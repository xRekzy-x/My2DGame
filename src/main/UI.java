package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import objects.Heart;
import objects.StrengPotion;
import main.ImageModification;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;//declare font outside of gameloop
    Font arial_20I;
    Font russo_one_45B;
    ImageModification mod = new ImageModification();

    BufferedImage strengthPotImage;
    private boolean messageOn=false;
    private String message="";
    private int messageCounter;
    private boolean gameFinished;
    private String currentDialogue="";
    private int commandNumber =0;
    private String dialogues[] = new String[20];
    private BufferedImage heart1,heart2,heart3,heart4,heart5;
    private Heart heart;
    StrengPotion strengPotion; 

    public UI(GamePanel gp){
        this.gp=gp;
        strengPotion = new StrengPotion(gp);
        arial_40=new Font("Arial",Font.PLAIN,40);
        arial_20I=new Font("Arial",Font.ITALIC,20);
        russo_one_45B=new Font("Russo one",Font.BOLD,45);

        strengthPotImage= strengPotion.getImage();
        strengthPotImage = mod.scaleImage(strengthPotImage, 40, 40);
        heart = new Heart(gp);
        heart1 = heart.getHeart1();
        heart2 = heart.getHeart2();
        heart3 = heart.getHeart3();
        heart4 = heart.getHeart4();
        heart5 = heart.getHeart5();
        
    }

    //GETTER SETTER
    public void setDialogue(int index,String text){
        this.dialogues[index]=text;
    }
    public String getDialogue(int index){
        return dialogues[index];
    }
    public void setCurrentDialogue(String currentDialogue){
        this.currentDialogue=currentDialogue;
    }
    public String getCurrentDialogue(){
        return currentDialogue;
    }
    public int getXForCenterText(String text){
        int length=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.getScreenWidth()/2-length/2;
        return x;
    }
    public int getLength(String text){
        int length=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return length;
    }
    public int getCommandNumber(){return commandNumber;}
    public void setCommandNumber(int commandNumber){this.commandNumber=commandNumber;}
    

    public void showMessage(String text){
        message=text;
        messageOn=true;
    }
    public void draw(Graphics2D g2){
        this.g2=g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        if(gp.getGameState()==gp.getPlayState()){
            g2.drawImage(strengthPotImage, 20, 70, null );
            g2.drawString(" : "+gp.getStrength(), 74, 100);
            g2.drawString("X: "+gp.getPlayerX()/gp.getTileSize(),74,140);
            g2.drawString("Y: "+gp.getPlayerY()/gp.getTileSize(),170,140);
            drawPlayerLife();
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
        if(gp.getGameState()==gp.getPauseState()){
            drawPlayerLife();
            drawPauseScreen();
        }
        if(gp.getGameState()==gp.getDialogueState()){
            drawPlayerLife();
            drawDialogueScreen(g2);
        }
        if(gp.getGameState()==gp.getTitleState()){
            drawTitleScreen();
        }
    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80));
        String text = "PAUSED";
        int x = getXForCenterText(text);
        int y = gp.getScreenHeight()/2;
        g2.drawString(text, x, y);

    }
    public void drawDialogueScreen(Graphics2D g2){
        int x= gp.getTileSize()*2;
        int y= gp.getTileSize()/2;
        int width=gp.getScreenWidth()-(gp.getTileSize()*4);
        int height = 4*gp.getTileSize();
        drawSubWindow(x, y, width, height);
        x+= gp.getTileSize();
        y+= gp.getTileSize();
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
       // gp.getui().setCurrentDialogue(gp.getui().getDialogue(gp.getExactNPC(gp.getPlayer().getNPCindex()).getDialogueIndex()));
        

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y+=40;
        }
        
    }
    public void drawSubWindow(int x, int y, int width,int height){
        Color c = new Color(0,0,0,210);//4th number is opacity level
        g2.setColor(c); 
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c=new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));//set độ dày của khung định vẽ(5 pixel)
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 35,35);
        g2.setFont(arial_20I);
        g2.drawString("Press Z to skip", width-100, height); 
    }
    public void drawTitleScreen(){
        //BACKGROUND
        //g2.setColor(new Color(70,120,80));
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
        
        //TEXT
        g2.setFont(russo_one_45B);
        String text = "ONLY APOCALYPTIC SURVIVOR";
        int x=getXForCenterText(text);
        int y=gp.getTileSize()*3;
        g2.setColor(Color.gray);//shadow's color
        g2.drawString(text,x+3,y+3);//shadow
        g2.setColor(Color.WHITE);//text's color
        g2.drawString(text, x, y);//text
        
        //IMAGE
        x=gp.getScreenWidth()/2-gp.getTileSize()*3;
        y-=gp.getTileSize()/2;
        g2.drawImage(gp.getPlayer().sdown1,x,y,gp.getTileSize()*6,gp.getTileSize()*6,null);

        //MENU
        text="NEW GAME";
        x=getXForCenterText(text);
        y+=gp.getTileSize()*6;
        g2.drawString(text,x,y);
        if(commandNumber==0){g2.drawString(">", x-gp.getTileSize(), y-5);}
        text="LOAD GAME";
        x=getXForCenterText(text);
        y+=45;
        g2.drawString(text,x,y);
        if(commandNumber==1){g2.drawString(">", x-gp.getTileSize(), y-5);}
        text="QUIT";
        x=getXForCenterText(text);
        y+=45;
        g2.drawString(text,x,y);
        if(commandNumber==2){g2.drawString(">", x-gp.getTileSize(), y-5);}
    }
    public void drawPlayerLife(){
        int x = gp.getTileSize()/2;
        int y = gp.getTileSize()/2;
        int count = 0;
        while(count<gp.getPlayer().getMaxLife()/4){
            g2.drawImage(heart5, x, y,null);
            count++;
            x+= gp.getTileSize()+10;
        }
        x = gp.getTileSize()/2;
        y = gp.getTileSize()/2;
        count = 0;
        while(count<gp.getPlayer().getLife()){
            g2.drawImage(heart4, x, y,null);
            count++;
            if(count<gp.getPlayer().getLife()) g2.drawImage(heart3, x, y,null);
            count++;
            if(count<gp.getPlayer().getLife()) g2.drawImage(heart2, x, y,null);
            count++;
            if(count<gp.getPlayer().getLife()) g2.drawImage(heart1, x, y,null);
            count++;
            x+= gp.getTileSize()+10;
        }
    }
}
