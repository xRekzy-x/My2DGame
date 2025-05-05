package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.sql.Time;
import java.util.Random;

import main.GamePanel;

public class NPC1 extends Entity {
    int dialogueIndex=0;
    private int TimeToChangeDirection;
    public NPC1(GamePanel gp){
        super(gp);
        direction="sdown";
        speed=1;
        getImage();
        setDialogue(); 
    }
    
    public void getImage(){
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
        
        sdown1 = setup("/NPC1/sdown1");
        sdown2 = setup("/NPC1/sdown2");
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
    public void update(){ 
        setAction();
        collisionOn = false;
        //gp.getColCheckTile(this);
        //gp.getColCheckObject(this, false);
        gp.getColCheckPlayer(this);
        //int check = gp.getColCheckEntity(this, new Entity[]{gp.getPlayer()});
        if(collisionOn==false){
            switch(direction){
                case "up": y-=speed; break;
                case "down": y+=speed; break;
                case "right":x+=speed; break;
                case "left": x-=speed; break;
            } 
        spriteCounter++;
        if(spriteCounter>10){
            if(spriteNum==1) spriteNum=2;
            else if (spriteNum==2) spriteNum=1;
            spriteCounter=0;
            }
        }
    }
    public void setDialogue(){
        gp.getui().setDialogue(0, "Bye bye nick ga");
        gp.getui().setDialogue(1, "kkkkkkkkkkkkkkkkk");
        gp.getui().setDialogue(2, "Để mà noi thi me may rat \nla beo do kkkk");
    }
    // public void setAction(){
    //     Random random = new Random();
    //     TimeToChangeDirection++;
    //     if(TimeToChangeDirection==120){
    //         int i = random.nextInt(5)+1;
    //         switch(i){
    //             case 1: direction="up"; break;
    //             case 2: direction="down"; break;
    //             case 3{"version":1,"cache_data":[{"file_hash":"df08cb0dd8ef5fda","server_context":"1;c5faad59-a2e3-31f2-b86e-aaf958e12824;phsh:005;7e-05","result":0,"response_category":"Allowed","expiration_time":1746476475105189},{"file_hash":"ae561ec6ce1f7211","server_context":"1;f94c025f-7523-6972-b613-ce2c246c55ce;unkn:100;0.01","result":1,"response_category":"Allowed","expiration_time":1746475863061316},{"file_hash":"cf5201d920142855","server_context":"1;c5faad59-a2e3-31f2-b86e-aaf958e12824;phsh:005;