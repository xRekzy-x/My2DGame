package objects;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Entity;
import main.GamePanel;
import main.ImageModification;

public class Heart extends Entity {
    ImageModification mod = new ImageModification();
    public Heart(GamePanel gp){
        super(gp);
        super.setName("Heart");
        super.setImage(setup("/heart/heart1",1));
        super.setImage2(setup("/heart/heart2",1));
        super.setImage3(setup("/heart/heart3",1));
        super.setImage4(setup("/heart/heart4",1));
        super.setImage5(setup("/heart/heart5",1));
        // try{
        //     super.setImage(mod.scaleImage(ImageIO.read(getClass().getResourceAsStream("/res/heart/heart1.png")),gp.getTileSize(),gp.getTileSize()));
        //     super.setImage2(mod.scaleImage(ImageIO.read(getClass().getResourceAsStream("/res/heart/heart2.png")),gp.getTileSize(),gp.getTileSize()));
        //     super.setImage3(mod.scaleImage(ImageIO.read(getClass().getResourceAsStream("/res/heart/heart3.png")),gp.getTileSize(),gp.getTileSize()));
        //     super.setImage4(mod.scaleImage(ImageIO.read(getClass().getResourceAsStream("/res/heart/heart4.png")),gp.getTileSize(),gp.getTileSize()));
        //     super.setImage5(mod.scaleImage(ImageIO.read(getClass().getResourceAsStream("/res/heart/heart5.png")),gp.getTileSize(),gp.getTileSize()));
        // }catch(IOException e){ e.printStackTrace(); }
    }
    public BufferedImage getHeart1(){return super.getImage();}
    public BufferedImage getHeart2(){return super.getImage2();}
    public BufferedImage getHeart3(){return super.getImage3();}
    public BufferedImage getHeart4(){return super.getImage4();}
    public BufferedImage getHeart5(){return super.getImage5();}
    
}
