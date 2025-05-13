package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.sql.Time;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.ImageModification;

public class NPC1 extends Entity {
    int dialogueIndex=0;
    private int TimeToChangeDirection;
    public NPC1(GamePanel gp){
        super(gp);
        direction="sdown";
        super.setSpeed(1);
        super.setType(2);
        super.setFrame(2);
        super.setTransitionTime(10);
        addImage();
        setDialogue(); 
    }
    
    public void addImage(){
        // down1 = setup("/skeleton/down1"); 
        // down2 = setup("/skeleton/down2");
        // down3 = setup("/skeleton/down3");
        // down4 = setup("/skeleton/down4");
        // down5 = setup("/skeleton/down5");
        // down6 = setup("/skeleton/down6");
        
        // left1 = setup("/skeleton/left1");
        // left2 = setup("/skeleton/left2");
        // left3 = setup("/skeleton/left3");
        // left4 = setup("/skeleton/left4");
        // left5 = setup("/skeleton/left5");
        // left6 = setup("/skeleton/left6");
        
        // right1 = setup("/skeleton/right1");
        // right2 = setup("/skeleton/right2");
        // right3 = setup("/skeleton/right3");
        // right4 = setup("/skeleton/right4");
        // right5 = setup("/skeleton/right5");
        // right6 = setup("/skeleton/right6");
        
        // up1 = setup("/skeleton/up1");
        // up2 = setup("/skeleton/up2");
        // up3 = setup("/skeleton/up3");
        // up4 = setup("/skeleton/up4");
        // up5 = setup("/skeleton/up5");
        // up6 = setup("/skeleton/up6");
        
        sdown1 = setup("/NPC1/sdown1",2);
        sdown2 = setup("/NPC1/sdown2",2);
        // sdown3 = setup("/skeleton/sdown3");
        // sdown4 = setup("/skeleton/sdown4");
        // sdown5 = setup("/skeleton/sdown5");
        // sdown6 = setup("/skeleton/sdown6");
        
        // sup1 = setup("/skeleton/sup1");
        // sup2 = setup("/skeleton/sup2");
        // sup3 = setup("/skeleton/sup3");
        // sup4 = setup("/skeleton/sup4");
        // sup5 = setup("/skeleton/sup5");
        // sup6 = setup("/skeleton/sup6");
        
        // sright1 = setup("/skeleton/sright1");
        // sright2 = setup("/skeleton/sright2");
        // sright3 = setup("/skeleton/sright3");
        // sright4 = setup("/skeleton/sright4");
        // sright5 = setup("/skeleton/sright5");
        // sright6 = setup("/skeleton/sright6");
        
        // sleft1 = setup("/skeleton/sleft1");
        // sleft2 = setup("/skeleton/sleft2");
        // sleft3 = setup("/skeleton/sleft3");
        // sleft4 = setup("/skeleton/sleft4");
        // sleft5 = setup("/skeleton/sleft5");
        // sleft6 = setup("/skeleton/sleft6");
        
    }
    public void setDialogue(){
        gp.getui().setDialogue(0, "Bye bye nick ga");
        gp.getui().setDialogue(1, "kkkkkkkkkkkkkkkkk");
        gp.getui().setDialogue(2, "De ma noi thi me may rat \nla beo do kkkk");
    }
}
   