package objects;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Entity;
import main.GamePanel;

public class Shield extends Entity {
    public Shield(GamePanel gp){
        super(gp);
        super.setSolidArea(new Rectangle(0,0,48,48));
        super.setSolidAreaDefaultX(0);
        super.setSolidAreaDefaultY(0);
        super.setName("Shield");
        setSdown1( setup("/obj/shield",1));
        setDescription(" a shield that can block \ndamage");
        //SET BASIC INFORMATION
        setDefenseValue(1);
        setType(getShieldType());
    }
    
}
