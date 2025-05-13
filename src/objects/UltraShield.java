package objects;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Entity;
import main.GamePanel;

public class UltraShield extends Entity {
    public UltraShield(GamePanel gp){
        super(gp);
        super.setSolidArea(new Rectangle(0,0,48,48));
        super.setSolidAreaDefaultX(0);
        super.setSolidAreaDefaultY(0);
        super.setName("UltraShield");
        setSdown1( setup("/obj/shield2",1));
        setDescription(" a shield that can block \ndamage");
        //SET BASIC INFORMATION
        setDefenseValue(6);
        setType(getShieldType());
    }
    
}
