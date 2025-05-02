package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class StrengPotion extends SuperObj {
    public StrengPotion(){
        super.setName("StrengthPotion");
        try{
            super.setImage(ImageIO.read(getClass().getResourceAsStream("/res/obj/potion.png")));
        }catch(IOException e){ e.printStackTrace(); }
    }
    
}
