package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Boots extends SuperObj {
    public Boots(){
        super.setName("Boots");
        try{
            super.setImage(ImageIO.read(getClass().getResourceAsStream("/res/obj/boots.png")));
        }catch(IOException e){ e.printStackTrace(); }
    }
    
}
