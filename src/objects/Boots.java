package objects;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Entity;
import main.GamePanel;

public class Boots extends Entity {
    public Boots(GamePanel gp){
        super(gp);
        super.setSolidArea(new Rectangle(0,0,48,48));
        super.setSolidAreaDefaultX(0);
        super.setSolidAreaDefaultY(0);
      
        super.setName("Boots");
        setWidth(1);
        setHeight(1);
        setSdown1( setup("/obj/boots",1));
    }
    
}
