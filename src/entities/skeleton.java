package entities;

import java.awt.Rectangle;
import java.util.Random;
import main.GamePanel;

public class skeleton extends Entity {
   private int TimeToChangeDirection;
   Rectangle newSolid = new Rectangle(33, 12, 33, 57);
   //private int spriteCounter=0;

   public skeleton(GamePanel gp) {
      super(gp);
      this.direction = "down";
      this.speed = 1;
      this.getImage();
      this.setSolidArea(this.newSolid);
      this.setSolidAreaDefaultX(this.getSolidAreaX());
      this.setSolidAreaDefaultY(this.getSolidAreaY());
   }

   public void getImage() {
      this.down1 = this.setup("/skeleton/down1");
      this.down2 = this.setup("/skeleton/down2");
      this.down3 = this.setup("/skeleton/down3");
      this.down4 = this.setup("/skeleton/down4");
      this.down5 = this.setup("/skeleton/down5");
      this.down6 = this.setup("/skeleton/down6");
      this.left1 = this.setup("/skeleton/left1");
      this.left2 = this.setup("/skeleton/left2");
      this.left3 = this.setup("/skeleton/left3");
      this.left4 = this.setup("/skeleton/left4");
      this.left5 = this.setup("/skeleton/left5");
      this.left6 = this.setup("/skeleton/left6");
      this.right1 = this.setup("/skeleton/right1");
      this.right2 = this.setup("/skeleton/right2");
      this.right3 = this.setup("/skeleton/right3");
      this.right4 = this.setup("/skeleton/right4");
      this.right5 = this.setup("/skeleton/right5");
      this.right6 = this.setup("/skeleton/right6");
      this.up1 = this.setup("/skeleton/up1");
      this.up2 = this.setup("/skeleton/up2");
      this.up3 = this.setup("/skeleton/up3");
      this.up4 = this.setup("/skeleton/up4");
      this.up5 = this.setup("/skeleton/up5");
      this.up6 = this.setup("/skeleton/up6");
      this.sdown1 = this.setup("/skeleton/sdown1");
      this.sdown2 = this.setup("/skeleton/sdown2");
      this.sdown3 = this.setup("/skeleton/sdown3");
      this.sdown4 = this.setup("/skeleton/sdown4");
      this.sdown5 = this.setup("/skeleton/sdown5");
      this.sdown6 = this.setup("/skeleton/sdown6");
      this.sup1 = this.setup("/skeleton/sup1");
      this.sup2 = this.setup("/skeleton/sup2");
      this.sup3 = this.setup("/skeleton/sup3");
      this.sup4 = this.setup("/skeleton/sup4");
      this.sup5 = this.setup("/skeleton/sup5");
      this.sup6 = this.setup("/skeleton/sup6");
      this.sright1 = this.setup("/skeleton/sright1");
      this.sright2 = this.setup("/skeleton/sright2");
      this.sright3 = this.setup("/skeleton/sright3");
      this.sright4 = this.setup("/skeleton/sright4");
      this.sright5 = this.setup("/skeleton/sright5");
      this.sright6 = this.setup("/skeleton/sright6");
      this.sleft1 = this.setup("/skeleton/sleft1");
      this.sleft2 = this.setup("/skeleton/sleft2");
      this.sleft3 = this.setup("/skeleton/sleft3");
      this.sleft4 = this.setup("/skeleton/sleft4");
      this.sleft5 = this.setup("/skeleton/sleft5");
      this.sleft6 = this.setup("/skeleton/sleft6");
   }

   public void setAction() {
      Random random = new Random();
      this.TimeToChangeDirection++;
      if (this.TimeToChangeDirection == 120) {
         int i = random.nextInt(5) + 1;
         switch(i) {
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
         case 5:
            if (this.direction == "down") {
               this.direction = "sdown";
            }

            if (this.direction == "up") {
               this.direction = "sup";
            }

            if (this.direction == "left") {
               this.direction = "sleft";
            }

            if (this.direction == "right") {
               this.direction = "sright";
            }
         }

         this.TimeToChangeDirection = 0;
      }
   }
   public void update(){
      setAction();
      collisionOn=false;
      gp.getColCheckObject(this, false);
      gp.getColCheckPlayer(this);

      if(collisionOn==false){
         switch(direction){
             case "up": y-=speed; break;
             case "down": y+=speed; break;
             case "right":x+=speed; break;
             case "left": x-=speed; break;
         }
      }
      spriteCounter++;
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
}