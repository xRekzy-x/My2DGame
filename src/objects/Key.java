package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Key extends SuperObj {
    public Key(){
        super.setName("Key");
        try{
            super.setImage(ImageIO.read(getClass().getResourceAsStream("/res/obj/key.png")));
        }catch(IOException e){ e.printStackTrace(); }
    }
    
}
