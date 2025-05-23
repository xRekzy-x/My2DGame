package objects;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Entity;
import main.GamePanel;
import main.ImageModification;

public class Mana extends Entity {
    ImageModification mod = new ImageModification();
    public Mana(GamePanel gp){
        super(gp);
        super.setName("Mana");
        super.setImage(setup("/mana/mana_full",1));
        super.setImage2(setup("/mana/mana_blank",1));
    }
    public BufferedImage getManaFull(){return super.getImage();}
    public BufferedImage getManaBlank(){return super.getImage2();}
    
}
