package tile;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;
    private boolean getCollision = false;
    public BufferedImage getImage(){return image;}
    public void setImage(BufferedImage image){ this.image=image;}
    public boolean getGetCollision() {return getCollision;}
    public void setCollision(boolean collision){ this.getCollision=collision; }
}
