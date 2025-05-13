package objects;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Entity;
import main.GamePanel;

public class Sword extends Entity {
    public Sword(GamePanel gp){
        super(gp);
        super.setSolidArea(new Rectangle(0,0,48,48));
        super.setSolidAreaDefaultX(0);
        super.setSolidAreaDefaultY(0);
        super.setName("Sword");
        setSdown1( setup("/obj/sword2",getWidth(),getHeight()));
        setDescription("an old sword");
        //SET BASIC INFORMATION
        setAttackValue(2);
        setType(getSwordType());
        
    }
    
}
