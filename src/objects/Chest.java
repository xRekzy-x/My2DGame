package objects;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Entity;
import main.GamePanel;

public class Chest extends Entity {
    boolean nearChest;
    public Chest(GamePanel gp){
        super(gp);
        super.setSolidArea(new Rectangle(0,0,48,48));
        super.setSolidAreaDefaultX(0);
        super.setSolidAreaDefaultY(0);
        super.setName("Chest");
        setSdown1( setup("/obj/chest",1));
        // try{
        //     super.setImage(ImageIO.read(getClass().getResourceAsStream("/res/obj/chest.png")));
        // }catch(IOException e){ e.printStackTrace(); }
        super.setCollision(true);
    }

    
}
