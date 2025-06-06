package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import objects.StrengPotion;
import main.ImageModification;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;//declare font outside of gameloop
    Font arial_20I;
    Font russo_one_45B;
    ImageModification mod = new ImageModification();
    StrengPotion strengPotion = new StrengPotion();
    BufferedImage strengthPotImage;
    private boolean messageOn=false;
    private String message="";
    private int messageCounter;
    private boolean gameFinished;
    private String currentDialogue="";
    private int commandNumber =0;
    private String dialogues[] = new String[20];
    public UI(GamePanel gp){
        this.gp=gp;
        arial_40=new Font("Arial",Font.PLAIN,40);
        arial_20I=new Font("Arial",Font.ITALIC,20);
        russo_one_45B=new Font("Russo one",Font.BOLD,45);

        strengthPotImage= strengPotion.getImage();
        strengthPotImage = mod.scaleImage(strengthPotImage, 40, 40);
        
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
            drawPauseScreen();
        }
        if(gp.getGameState()==gp.getDialogueState()){
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
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y+=40;
        }
   � � ! !	Le�W�a�4     �'�@>�    ��$@*�@H� �$�.�@� �  0�@�	 �$@1�@ �6 �  2�@�	 �$@4�@m�6 �  5�@�	 �$�8�@W�6 �:� � �:�5 ;�@g	 �� �	 �$@=�@s�Q��%C$�?�@a� � �@�@�	 �$ D�@�6 �b�G�@��0 �0 I�@�� � I	�J�@�	 �� �	 �(@N�@�#zA/��	� NQ�`l  �(�c�@�y6 �$ h�@����	Qu!< � � uk@v�@Z)��	5P~�@��wk��	@ �	 ���6 �� A	 � ��@=�5 � �� ��@}f���I^�@��@�h0��	0 ��@I} � �	e �A� � �@ �	 ��!� �5��j �	P��@� �� L	 �$@��@��6 �$ ��@M� ���@�	 �����@��4 � ���@N� � ���@�	 � ��@��@��5 ����@ɃE���E�$���@jp�	^����@q0 �0 �� �!o	 � �� �!�	 ��;rP �� i	 � @��@�	 �$���@�eQ � ��@Rf � �	���@�^ �P �	 �$���@k_6 � ���@�	 �$@��@(T6 � ���@�	 �$ ��@|U6 �@ �	 �b���@AGK �0 � �@�	 �  �@�	 �$��@&>Q ����!s	�@�V������	�5�
�@�55 �5 �@����I��O�@���� O@�@I� �� m	 �$��@��6 � �@B� � 	k�@t	 �$ -�@{�6 � 2�@ � �q� 2	P .	 �^��3�@�	0 �e$�5�@��f �K$ 8�@�� �� �	 �>!VZ!��ʩC�>�PB�@R6���T(���@�P9��	$0��@l^ �6V �$���@� �6 �$ ��@֥ �(p��@bi;���	��� ��n� �$���@΢ �^� �0��0 �0���@Q� �( ��@�<� ��). �6��@��=6��	� ��@k� ���50��@�� �!"�@AD>P��	P$0�@E �� �	 �$