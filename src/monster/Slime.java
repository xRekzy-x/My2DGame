package monster;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import entities.Entity;
import main.GamePanel;

public class Slime extends Entity {
    int TimeToChangeDirection;
   

    public Slime(GamePanel gp) {
        super(gp);
        super.setName("Slime");

        //SET SOLIDAREA
        super.setSolidArea(new Rectangle(32, 32, 32, 24));
        this.setSolidAreaDefaultX(this.getSolidAreaX());
        this.setSolidAreaDefaultY(this.getSolidAreaY());

        //SET BASIC INFORMATION
        super.setDamage(3);
        super.setSpeed(1);
        super.setMaxLife(50);
        super.setLife(getMaxLife());
        
        //SETTING
        super.setType(3);
        super.setFrame(9);
        super.setTransitionTime(5);
        addImage();
    }

    public void addImage() {
        down1 = setup("/monster/slime1", 2);
        down2 = setup("/monster/slime2", 2);
        down3 = setup("/monster/slime3", 2);
        down4 = setup("/monster/slime4", 2);
        down5 = setup("/monster/slime5", 2);
        down6 = setup("/monster/slime6", 2);
        down7 = setup("/monster/slime7", 2);
        down8 = setup("/monster/slime8", 2);
        down9 = setup("/monster/slime9", 2);
        down10 = setup("/monster/slime10", 2);

        up1 = setup("/monster/slime1", 2);
        up2 = setup("/monster/slime2", 2);
        up3 = setup("/monster/slime3", 2);
        up4 = setup("/monster/slime4", 2);
        up5 = setup("/monster/slime5", 2);
        up6 = setup("/monster/slime6", 2);
        up7 = setup("/monster/slime7", 2);
        up8 = setup("/monster/slime8", 2);
        up9 = setup("/monster/slime9", 2);
        up10 = setup("/monster/slime10", 2);

        left1 = setup("/monster/slime1", 2);
        left2 = setup("/monster/slime2", 2);
        left3 = setup("/monster/slime3", 2);
        left4 = setup("/monster/slime4", 2);
        left5 = setup("/monster/slime5", 2);
        left6 = setup("/monster/slime6", 2);
        left7 = setup("/monster/slime7", 2);
        left8 = setup("/monster/slime8", 2);
        left9 = setup("/monster/slime9", 2);
        left10 = setup("/monster/slime10", 2);

        right1 = setup("/monster/slime1", 2);
        right2 = setup("/monster/slime2", 2);
        right3 = setup("/monster/slime3", 2);
        right4 = setup("/monster/slime4", 2);
        right5 = setup("/monster/slime5", 2);
        right6 = setup("/monster/slime6", 2);
        right7 = setup("/monster/slime7", 2);
        right8 = setup("/monster/slime8", 2);
        right9 = setup("/monster/slime9", 2);
        left10 = setup("/monster/slime10", 2);

    }

    public void setAction() {
        Random random = new Random();
        TimeToChangeDirection++;
        if (this.TimeToChangeDirection == 120) {
            int i = random.nextInt(4) + 1;
            switch (i) {
                case 1: direction = "up"; break;
                case 2: direction = "down"; break;
                case 3: direction = "left";break;
                case 4: direction = "right"; break;
                // case 5:
                // if (this.direction == "down") {this.direction = "sdown";}
                // if (this.direction == "up") {this.direction = "sup";}
                // if (this.direction == "left") {this.direction = "sleft";}
                // if (this.direction == "right") {this.direction = "sright";}
                // break;
            }
            TimeToChangeDirection = 0;
        }
    }
    public void damageReaction(){
        TimeToChangeDirection=0;
        if(gp.getPlayer().getDirection()=="sdown") direction = "down";
        if(gp.getPlayer().getDirection()=="sup") direction = "up";
        if(gp.getPlayer().getDirection()=="sleft") direction = "left";
        if(gp.getPlayer().getDirection()=="sright") direction = "right";
        System.out.println(direction);
    }
}
