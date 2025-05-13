package objects;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Entity;
import main.GamePanel;

public class StrengPotion extends Entity {
    public StrengPotion(GamePanel gp){
        super(gp);
        super.setSolidArea(new Rectangle(0,0,48,48));
        super.setSolidAreaDefaultX(0);
        super.setSolidAreaDefaultY(0);
        super.setName("StrengthPotion");
        setSdown1( setup("/obj/potion",1));
        setType(getConsumableType());
        // try{
        //     super.setImage(ImageIO.read(getClass().getResourceAsStream("/res/obj/potion.png")));
        // }catch(IOException e){ e.printStackTrace(); }
    }
    
}
