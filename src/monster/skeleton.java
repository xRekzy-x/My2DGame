package monster;

import java.awt.Rectangle;
import java.util.Random;

import entities.Entity;
import main.GamePanel;

public class skeleton extends Entity {
   private int TimeToChangeDirection;
   Rectangle newSolid = new Rectangle(33, 25, 33, 44);

   public skeleton(GamePanel gp) {
      super(gp);
      this.direction = "down";
      //SET SOLID AREA
      this.setSolidArea(this.newSolid);
      this.setSolidAreaDefaultX(this.getSolidAreaX());
      this.setSolidAreaDefaultY(this.getSolidAreaY());

      //SET BASIC INFORMATION
      super.setSpeed(1);
      super.setMaxLife(1000);
      super.setLife(getMaxLife());
      super.setDamage(10);

      //SETTING
      super.setType(3);
      super.setFrame(6);
      super.setTransitionTime(10);
      this.addImage();
   }

   public void addImage() {
      this.down1 = this.setup("/skeleton/down1",2);
      this.down2 = this.setup("/skeleton/down2",2);
      this.down3 = this.setup("/skeleton/down3",2);
      this.down4 = this.setup("/skeleton/down4",2);
      this.down5 = this.setup("/skeleton/down5",2);
      this.down6 = this.setup("/skeleton/down6",2);
      this.left1 = this.setup("/skeleton/left1",2);
      this.left2 = this.setup("/skeleton/left2",2);
      this.left3 = this.setup("/skeleton/left3",2);
      this.left4 = this.setup("/skeleton/left4",2);
      this.left5 = this.setup("/skeleton/left5",2);
      this.left6 = this.setup("/skeleton/left6",2);
      this.right1 = this.setup("/skeleton/right1",2);
      this.right2 = this.setup("/skeleton/right2",2);
      this.right3 = this.setup("/skeleton/right3",2);
      this.right4 = this.setup("/skeleton/right4",2);
      this.right5 = this.setup("/skeleton/right5",2);
      this.right6 = this.setup("/skeleton/right6",2);
      this.up1 = this.setup("/skeleton/up1",2);
      this.up2 = this.setup("/skeleton/up2",2);
      this.up3 = this.setup("/skeleton/up3",2);
      this.up4 = this.setup("/skeleton/up4",2);
      this.up5 = this.setup("/skeleton/up5",2);
      this.up6 = this.setup("/skeleton/up6",2);
      this.sdown1 = this.setup("/skeleton/sdown1",2);
      this.sdown2 = this.setup("/skeleton/sdown2",2);
      this.sdown3 = this.setup("/skeleton/sdown3",2);
      this.sdown4 = this.setup("/skeleton/sdown4",2);
      this.sdown5 = this.setup("/skeleton/sdown5",2);
      this.sdown6 = this.setup("/skeleton/sdown6",2);
      this.sup1 = this.setup("/skeleton/sup1",2);
      this.sup2 = this.setup("/skeleton/sup2",2);
      this.sup3 = this.setup("/skeleton/sup3",2);
      this.sup4 = this.setup("/skeleton/sup4",2);
      this.sup5 = this.setup("/skeleton/sup5",2);
      this.sup6 = this.setup("/skeleton/sup6",2);
      this.sright1 = this.setup("/skeleton/sright1",2);
      this.sright2 = this.setup("/skeleton/sright2",2);
      this.sright3 = this.setup("/skeleton/sright3",2);
      this.sright4 = this.setup("/skeleton/sright4",2);
      this.sright5 = this.setup("/skeleton/sright5",2);
      this.sright6 = this.setup("/skeleton/sright6",2);
      this.sleft1 = this.setup("/skeleton/sleft1",2);
      this.sleft2 = this.setup("/skeleton/sleft2",2);
      this.sleft3 = this.setup("/skeleton/sleft3",2);
      this.sleft4 = this.setup("/skeleton/sleft4",2);
      this.sleft5 = this.setup("/skeleton/sleft5",2);
      this.sleft6 = this.setup("/skeleton/sleft6",2);
   }
   public void setAction() {
      Random random = new Random();
      TimeToChangeDirection++;
      if (this.TimeToChangeDirection == 120) {
      int i = random.nextInt(5) + 1;
        switch(i) {
            case 1:this.direction = "up";break;
            case 2: this.direction = "down"; break;
            case 3:this.direction = "left"; break;
            case 4: this.direction = "right"; break;
            case 5:
                if (this.direction == "down") {this.direction = "sdown";}
                if (this.direction == "up") {this.direction = "sup";}
                if (this.direction == "left") {this.direction = "sleft";}
                if (this.direction == "right") {this.direction = "sright";}
                break;
        }
        TimeToChangeDirection = 0;
      }
   }
      public void damageReaction(){
      //   TimeToChangeDirection=0;
      //   if(gp.getPlayer().getDirection()=="sdown") direction = "up";
      //   if(gp.getPlayer().getDirection()=="sup") direction = "down";
      //   if(gp.getPlayer().getDirection()=="sleft") direction = "right";
      //   if(gp.getPlayer().getDirection()=="sright") direction = "left";
      //   System.out.println(direction);
    }
}