package objects;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Entity;
import main.GamePanel;

public class Exp extends Entity {
    public Exp(GamePanel gp){
        super(gp);
        super.setSolidArea(new Rectangle(0,0,48,48));
        super.setSolidAreaDefaultX(0);
        super.setSolidAreaDefaultY(0);
      
        super.setName("Exp");
        setWidth(1);
        setHeight(1);
        setSdown1( setup("/mana/mana_full",1));
    }
    
}
