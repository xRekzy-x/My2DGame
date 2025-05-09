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
        super.setSpeed(1);
        super.setMaxLife(3);
        super.setLife(getMaxLife());
        super.setSolidArea(new Rectangle(32, 32, 32, 24));
        this.setSolidAreaDefaultX(this.getSolidAreaX());
        this.setSolidAreaDefaultY(this.getSolidAreaY());
        super.setType(3);
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
                case 1:
                    this.direction = "up";
                    break;
                case 2:
                    this.direction = "down";
                    break;
                case 3:
                    this.direction = "left";
                    break;
                case 4:
                    this.direction = "right";
                    break;
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

    public void update() {
       super.update();
        spriteCounter++;
        if (spriteCounter > 5) {
            if (spriteNum == 1)
                spriteNum = 2;
            else if (spriteNum == 2)
                spriteNum = 3;
            else if (spriteNum == 3)
                spriteNum = 4;
            else if (spriteNum == 4)
                spriteNum = 5;
            else if (spriteNum == 5)
                spriteNum = 6;
            else if (spriteNum == 6)
                spriteNum = 7;
            else if (spriteNum == 7)
                spriteNum = 8;
            else if (spriteNum == 8)
                spriteNum = 9;
            else if (spriteNum == 9)
                spriteNum = 1;
            spriteCounter = 0;
        }
    }
    // @Override
    // public void draw(Graphics2D g2) {
    //     BufferedImage image = null;
    //     switch (direction) {
    //         case "up":
    //             if (spriteNum == 1) {
    //                 image = up1;
    //             } else if (spriteNum == 2) {
    //                 image = up2;
    //             } else if (spriteNum == 3) {
    //                 image = up3;
    //             } else if (spriteNum == 4) {
    //                 image = up4;
    //             } else if (spriteNum == 5) {
    //                 image = up5;
    //             } else if (spriteNum == 6) {
    //                 image = up6;
    //             } else if (spriteNum == 7) {
    //                 image = up7;
    //             } else if (spriteNum == 8) {
    //                 image = up8;
    //             } else if (spriteNum == 9) {
    //                 image = up9;
    //             } else if (spriteNum == 10) {
    //                 image = up10;
    //             }
    //             break;
    //         case "down":
    //             if (spriteNum == 1) {
    //                 image = down1;
    //             } else if (spriteNum == 2) {
    //                 image = down2;
    //             } else if (spriteNum == 3) {
    //                 image = down3;
    //             } else if (spriteNum == 4) {
    //                 image = down4;
    //             } else if (spriteNum == 5) {
    //                 image = down5;
    //             } else if (spriteNum == 6) {
    //                 image = down6;
    //             } else if (spriteNum == 7) {
    //                 image = down7;
    //             } else if (spriteNum == 8) {
    //                 image = down8;
    //             } else if (spriteNum == 9) {
    //                 image = down9;
    //             } else if (spriteNum == 10) {
    //                 image = down10;
    //             }
    //             break;
    //         case "right":
    //             if (spriteNum == 1) {
    //                 image = right1;
    //             } else if (spriteNum == 2) {
    //                 image = right2;
    //             } else if (spriteNum == 3) {
    //                 image = right3;
    //             } else if (spriteNum == 4) {
    //                 image = right4;
    //             } else if (spriteNum == 5) {
    //                 image = right5;
    //             } else if (spriteNum == 6) {
    //                 image = right6;
    //             } else if (spriteNum == 7) {
    //                 image = right7;
    //             } else if (spriteNum == 8) {
    //                 image = right8;
    //             } else if (spriteNum == 9) {
    //                 image = right9;
    //             } else if (spriteNum == 10) {
    //                 image = right10;
    //             }
    //             break;
    //         case "left":
    //             if (spriteNum == 1) {
    //                 image = left1;
    //             } else if (spriteNum == 2) {
    //                 image = left2;
    //             } else if (spriteNum == 3) {
    //                 image = left3;
    //             } else if (spriteNum == 4) {
    //                 image = left4;
    //             } else if (spriteNum == 5) {
    //                 image = left5;
    //             } else if (spriteNum == 6) {
    //                 image = left6;
    //             } else if (spriteNum == 7) {
    //                 image = left7;
    //             } else if (spriteNum == 8) {
    //                 image = left8;
    //             } else if (spriteNum == 9) {
    //                 image = left9;
    //             } else if (spriteNum == 10) {
    //                 image = left10;
    //             }
    //             break;
    //     }
    //     if (getDying()) {
    //         dyingAnimation(g2);
    //     } 
    //     Transparency(g2, 0F);
    //     g2.drawImage(image, x - gp.getPlayerX() + gp.getPlayerScreenX(), (y - gp.getPlayerY() + gp.getPlayerScreenY()),
    //             null);
    //     // g2.drawImage(image, screenX, screenY, null);
    // }
}
