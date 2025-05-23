package entities;

import java.awt.Rectangle;

import main.GamePanel;

public class Rock extends Projectile{
    public Rock(GamePanel gp) {
        super(gp);
        super.setName("Rock");

        //SET SOLIDAREA
        super.setSolidArea(new Rectangle(32, 32, 32, 24));
        this.setSolidAreaDefaultX(this.getSolidAreaX());
        this.setSolidAreaDefaultY(this.getSolidAreaY());

        //SET BASIC INFORMATION
        super.setDamage(10);
        super.setSpeed(5);  
        super.setMaxLife(100);
        super.setLife(getMaxLife());
        //super.setExp(1);
        super.setCost(0);

        
        //SETTING
        super.setAlive(false);
        super.setType(4);
        super.setFrame(1);
        super.setTransitionTime(2);
        addImage();
    }
    public void addImage(){
        down1 = setup("/projectile/rock", 1,1);
        up1=down1;
        left1=down1;
        right1=down1;
        sdown1=down1;
        sup1=up1;
        sright1=right1;
        sleft1=left1;
        
    }
}
