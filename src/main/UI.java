package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import objects.Heart;
import objects.Mana;
import objects.StrengPotion;
import main.ImageModification;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;//declare font outside of gameloop
    Font arial_20;
    Font arial_20I;
    Font arial_10I;
    Font russo_one_45B;
    ImageModification mod = new ImageModification();

    BufferedImage strengthPotImage;
    private boolean singleMessage=false;
    private String message="";
    private int messageCounter;
    private String currentDialogue="";
    private int commandNumber =0;
    private String dialogues[] = new String[20];
    private BufferedImage heart1,heart2,heart3,heart4,heart5,mana_full,mana_blank;
    private Heart heart;
    private Mana mana;
    StrengPotion strengPotion;
    ArrayList<String> combat= new ArrayList<>(); 
    ArrayList<Integer> combatCounter = new ArrayList<>();
    //SLOT
    int slotCol=0;
    int slotRow=0;
    int maxSlotCol=5;
    int maxSlotRow=4;

    public UI(GamePanel gp){
        this.gp=gp;
        strengPotion = new StrengPotion(gp);
        arial_40=new Font("Arial",Font.PLAIN,40);
        arial_20=new Font("Arial",Font.PLAIN,20);
        arial_20I=new Font("Arial",Font.ITALIC,20);
        arial_10I=new Font("Arial",Font.ITALIC,10);
        russo_one_45B=new Font("Russo one",Font.BOLD,45);

        strengthPotImage= strengPotion.getImage();
        strengthPotImage = mod.scaleImage(strengthPotImage, 40, 40);
        heart = new Heart(gp);
        heart1 = heart.getHeart1();
        heart2 = heart.getHeart2();
        heart3 = heart.getHeart3();
        heart4 = heart.getHeart4();
        heart5 = heart.getHeart5();

        mana = new Mana(gp);
        mana_blank= mana.getManaBlank();
        mana_full=mana.getManaFull();  
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
     public int getXForRightText(String text,int endX){
        int length=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = endX-length;
        return x;
    }
    public int getLength(String text){
        int length=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return length;
    }
    public int getCommandNumber(){return commandNumber;}
    public void setCommandNumber(int commandNumber){this.commandNumber=commandNumber;}
    public int getSlotCol(){return slotCol;}
    public void setSlotCol(int slotCol){this.slotCol=slotCol;}
    public int getSlotRow(){return slotRow;}
    public void setSlotRow(int slotRow){this.slotRow=slotRow;}
    public int getMaxSlotCol(){return maxSlotCol;}
    public void setMaxSlotCol(int maxSlotCol){this.maxSlotCol=maxSlotCol;}
    public int getMaxSlotRow(){return maxSlotRow;}
    public void setMaxSlotRow(int maxSlotRow){this.maxSlotRow=maxSlotRow;}
    

    public void showMessage(String text){
        message=text;
        singleMessage=true;
    }
    public void addMessage(String text){
        combat.add(text);
        combatCounter.add(0);
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
            drawMessage();
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
        if(gp.getGameState()==gp.getCharacterState()){
            drawCharacterScreen();
            drawInventory();
        }
        if(gp.getGameState()==gp.getOverState()){
            drawOverScreen();
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
        drawText(currentDialogue, x, y, 40);
        // for(String line : currentDialogue.split("\n")){
        //     g2.drawString(line,x,y);
        //     y+=40;
        // } 
    }
    public void drawSubWindow(int x, int y, int width,int height){
        Color c = new Color(0,0,0,210);//4th number is opacity level
        g2.setColor(c); 
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c=new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));//set độ dày của khung định vẽ(5 pixel)
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 35,35);
        g2.setFont(arial_10I);
        g2.drawString("Press Z to skip", x+width-100, y+height-20); 
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
        x=gp.getTileSize()/2;
        y=(int)(gp.getTileSize()*1.5);
        count=0;
        while(count<gp.getPlayer().getMaxMana()){
            g2.drawImage(mana_blank, x, y,null);
            count++;
            x+= 35;
        }
        x=gp.getTileSize()/2;
        count=0;
        while(count<gp.getPlayer().getMana()){
            g2.drawImage(mana_full, x, y,null);
            count++;
            x+= 35;
        }
    }
    public void drawCharacterScreen(){
        int x = gp.getTileSize()*2;
        int y = gp.getTileSize();
        int width = gp.getTileSize()*5;
        int height = gp.getTileSize()*10;
        drawSubWindow(x, y, width, height);
        //DISPLAY TEXT:
        int textX=x+20;
        int textY=y+gp.getTileSize();
        int initialTextY=textY;
        int lineHeight=32;
        g2.setColor(Color.white);
        g2.setFont(arial_20I);
        g2.drawString("Level: ",textX,textY);
        textY+=lineHeight;
        g2.drawString("Exp ",textX,textY);
        textY+=lineHeight;
        g2.drawString("Next level: ",textX,textY);
        textY+=lineHeight;
        g2.drawString("Strength: ",textX,textY);
        textY+=lineHeight;
        g2.drawString("Defense: ",textX,textY);
        textY+=lineHeight;
        g2.drawString("Health: ",textX,textY);
        textY+=lineHeight;
        g2.drawString("Mana: ",textX,textY);
        textY+=lineHeight;
        g2.drawString("Coin: ",textX,textY);
        textY+=lineHeight+20;
        g2.drawString("Weapon: ",textX,textY);
        textY+=lineHeight+20;
        g2.drawString("Shield: ",textX,textY);
        //VALUE:
        int tailX = x+ width - 30;
        textY=initialTextY;//reset textY
        String value;
            value = String.valueOf(gp.getPlayer().getLevel());
        textX=getXForRightText(value, tailX);
        g2.drawString(value, textX, textY);
            value = String.valueOf(gp.getPlayer().getExp());
        textX=getXForRightText(value, tailX);
        textY+=lineHeight;
        g2.drawString(value, textX, textY);
          value = String.valueOf(gp.getPlayer().getNextLevelExp());
        textX=getXForRightText(value, tailX);
        textY+=lineHeight;
        g2.drawString(value, textX, textY);
          value = String.valueOf(gp.getPlayer().getDamage());
        textX=getXForRightText(value, tailX);
        textY+=lineHeight;
        g2.drawString(value, textX, textY);
          value = String.valueOf(gp.getPlayer().getDefense());
        textX=getXForRightText(value, tailX);
        textY+=lineHeight;
        g2.drawString(value, textX, textY);
          value = String.valueOf(gp.getPlayer().getLife()+"/"+gp.getPlayer().getMaxLife());
        textX=getXForRightText(value, tailX);
        textY+=lineHeight;
        g2.drawString(value, textX, textY);
            value = String.valueOf(gp.getPlayer().getMana()+"/"+gp.getPlayer().getMaxMana());
        textX=getXForRightText(value, tailX);
        textY+=lineHeight;
        g2.drawString(value, textX, textY);
          value = String.valueOf(gp.getPlayer().getCoin());
        textX=getXForRightText(value, tailX);
        textY+=lineHeight;
        g2.drawString(value, textX, textY);

        textY+=lineHeight;
        g2.drawImage(gp.getPlayer().getCurrentWeapon().getSdown1(), tailX-gp.getTileSize(), textY-15,null);
        textY+=gp.getTileSize();
        g2.drawImage(gp.getPlayer().getCurrentShield().getSdown1(), tailX-gp.getTileSize(), textY-15,null);
    }
    public void drawMessage(){
        int x=48;
        int y=gp.getScreenHeight()/2;
        g2.setFont(arial_40);
        if(!singleMessage){
            for(int i=0;i<combat.size();i++){
                if(combat.get(i)!=null){
                    g2.setColor(Color.white);
                    g2.setFont(arial_20I);
                    g2.drawString(combat.get(i), x, y);
                    
                    combatCounter.set(i,combatCounter.get(i)+1);
                    y+=50;
                    if(combatCounter.get(i)>180){
                        combatCounter.remove(i);
                        combat.remove(i);
                    }
                }
            }
        }
        else{
            g2.setColor(Color.white);
            g2.setFont(arial_20I);
            messageCounter++;
            g2.drawString(message, x, y);
            if(messageCounter==90){
                singleMessage=false;
                messageCounter=0;
            }
        }
    }
    public void drawInventory(){
        //frame
        int frameX=gp.getTileSize()*9;
        int frameY=gp.getTileSize();
        int frameWidth=gp.getTileSize()*6;
        int frameHeight=gp.getTileSize()*5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        //SLOT
        int slotSize = gp.getTileSize()+3;
        int initialSlotX = frameX+20;
        int initialSlotY = frameY+20;
        int slotX = initialSlotX;
        int slotY= initialSlotY;
        
        for(int i=0; i < gp.getPlayer().getInventory().size();i++){
            //EQUIPPED ITEM
            if(gp.getPlayer().getCurrentWeapon()==gp.getPlayer().getInventory().get(i)||
                gp.getPlayer().getCurrentShield()==gp.getPlayer().getInventory().get(i)){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX, slotY, slotSize, slotSize, 10, 10);
            }
            // g2.setColor(new Color(0,0,0));
            // g2.fillRoundRect(slotX, slotY, slotSize, slotSize, 10, 10);
            g2.drawImage(gp.getPlayer().getInventory().get(i).getSdown1(), slotX, slotY,null);
            if((i+1)%maxSlotCol==0){
                slotX=initialSlotX;
                slotY+=slotSize;
            }
            else slotX+=slotSize;
        }

        //SELECTING ITEM
        int selectionX = initialSlotX+(slotSize*slotCol);
        int selectionY = initialSlotY+(slotSize*slotRow);
        int selectionWidth = slotSize;
        int selectionHeight = slotSize;
        //DRAW SELECTION
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));//set độ dày của khung 
        g2.drawRoundRect(selectionX, selectionY, selectionWidth, selectionHeight, 10, 10);
        //SELECTING ITEM
        // if(gp.getKey().getEnterPressed()){
        //     if(gp.getPlayer().getInventory().get(getItemIndexInInventory()).getType()==4){
        //         gp.getPlayer().setCurrentWeapon(gp.getPlayer().getInventory().get(getItemIndexInInventory()));
        //         gp.getKey().setEnterPressed(false);
        //     }
        // }

        //DESCRIPTION
        int descriptionFrameX= frameX;
        int descriptionFrameY= frameY + frameHeight+10;
        int descriptionFrameWidth = frameWidth;
        int descriptionFrameHeight = gp.getTileSize()*3;

        int descriptionX = descriptionFrameX+20;
        int descriptionY = descriptionFrameY+gp.getTileSize();
        int itemIndex = getItemIndexInInventory();
        if(itemIndex < gp.getPlayer().getInventory().size()){
            drawSubWindow(descriptionFrameX, descriptionFrameY, descriptionFrameWidth, descriptionFrameHeight);
            g2.setFont(arial_20);
            drawText(gp.getPlayer().getInventory().get(itemIndex).getDescription(), descriptionX, descriptionY, 20);
        }
    }
    public int getItemIndexInInventory(){
        return slotCol+(slotRow*maxSlotCol);
    }
    public void drawText(String text, int x, int y, int distanceBetweenLine){
        for(String line : text.split("\n")){
            g2.drawString(line,x,y);
            y+=distanceBetweenLine;
        }
        
    }
    public void drawOverScreen(){
        g2.setColor(new Color(255,0,0,150));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
        String text = "GAME OVER";
        int x = getXForCenterText(text);
        int y = gp.getTileSize()*4;
        //DRAW SHADOW OF TEXT
        g2.setFont(russo_one_45B);
        g2.setColor(Color.black);
        g2.drawString(text, x, y);
        //DRAW TEXT
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);
        //retry
        g2.setFont(arial_40);
        text = "Retry";
        x=getXForCenterText(text);
        y+=150;
        g2.drawString(text, x, y);
        if(commandNumber==0) g2.drawString(">", x-40, y);
        //Quit
        text = "Respawn("+gp.getRespawnTicket()+" left)";
        x=getXForCenterText(text);
        y+=50;
        g2.drawString(text, x, y);
        if(commandNumber==1) g2.drawString(">", x-40, y);
        //Quit
        text = "Quit";
        x=getXForCenterText(text);
        y+=50;
        g2.drawString(text, x, y);
        if(commandNumber==2) g2.drawString(">", x-40, y);
    }
}
