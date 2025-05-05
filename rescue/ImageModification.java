package main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.*;

public class ImageModification {
    public ImageModification(){
    }
    public void crop(int x, int y, String fileName) {
        try {
            // Load the sprite sheet
            // BufferedImage spriteSheet = ImageIO.read(new File("./res/player/Walk.png"));
            // BufferedImage spriteSheet =
            // ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/Walk.png"));
            BufferedImage spriteSheet = ImageIO.read(new File(
                    "C:/Users/rosto/practice/tutorial/loopex/javalab/My2DGame/My2DGame/src/res/NPC1/NPC.png"));

            // Define the crop area (x, y, width, height)
            // Adjust based on the sprite position

            int width = 24; // Width of a single sprite
            int height =24; // Height of a single sprite

            // Crop the image
            BufferedImage croppedImage = spriteSheet.getSubimage(x, y, width, height);

            // Save the cropped image

            File outputFile = new File("src/res/NPC1/" + fileName + ".png");
            outputFile.getParentFile().mkdirs(); // Create directories if missing
            ImageIO.write(croppedImage, "png", outputFile);

            //ImageIO.write(croppedImage, "png", new File("res/cropped.png"));

            System.out.println("Cropped image saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void flip(String fileName){
        try {
            // Load the image
            BufferedImage image = ImageIO.read(new File("src/res/skeleton/" + fileName + ".png"));

            // Flip the image 180 degrees
            BufferedImage flippedImage = flipImage180(image);

            // Save the flipped image
            ImageIO.write(flippedImage, "png", new File("src/res/skeleton/" + fileName + ".png"));

            System.out.println("Image flipped successfully!");
    } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void rotate(String fileName){
        try {
            // Load the image
            BufferedImage image = ImageIO.read(new File("My2DGame/src/res/tile/" + fileName + ".png"));

            // rotate the image 90 degrees
            BufferedImage rotatedImage = rotateImage90Degrees(image);

            // Save the flipped image
            ImageIO.write(rotatedImage, "png", new File("My2DGame/src/res/tile/" + fileName + ".png"));

            System.out.println("Image rotated successfully!");
    } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static BufferedImage flipImage180(BufferedImage image) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1); // Flip both horizontally and vertically
        tx.translate(-image.getWidth(), 0);

        BufferedImage flippedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = flippedImage.createGraphics();
        g.drawImage(image, tx, null);
        g.dispose();

        return flippedImage;
    }
    public BufferedImage rotateImage90Degrees(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
    
        // Create a new blank image with swapped dimensions (width and height)
        BufferedImage rotatedImage = new BufferedImage(height, width, image.getType());
        Graphics2D g = rotatedImage.createGraphics();
    
        // Create an AffineTransform to rotate the image by 90 degrees
        AffineTransform tx = new AffineTransform();
        tx.translate(height / 2.0, width / 2.0); // Move the origin to the center of the new image
        tx.rotate(Math.PI / 2); // Rotate 90 degrees clockwise (π/2 radians)
        tx.translate(-width / 2.0, -height / 2.0); // Move the origin back to the top-left of the original image
    
        // Draw the original image onto the new rotated image
        g.setTransform(tx);
        g.drawImage(image, 0, 0, null);
    
        g.dispose();
        return rotatedImage;
    }
    
    public void ImageOverlay(String under, String top, String result) throws IOException {
        // Load the background and overlay images
        BufferedImage background = ImageIO.read(new File("src/res/tile/" + under + ".png"));
        BufferedImage overlay = ImageIO.read(new File("src/res/tile/" + top + ".png"));
    
        // Scale the overlay image
        Image tempOverlay = overlay.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        BufferedImage scaledOverlay = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dOverlay = scaledOverlay.createGraphics();
        g2dOverlay.drawImage(tempOverlay, 0, 0, null);
        g2dOverlay.dispose();
    
        // Scale the background image
        Image tempBackground = background.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        BufferedImage scaledBackground = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dBackground = scaledBackground.createGraphics();
        g2dBackground.drawImage(tempBackground, 0, 0, null);
        g2dBackground.dispose();
    
        // Merge the scaled overlay onto the scaled background
        Graphics2D g = scaledBackground.createGraphics();
        g.drawImage(scaledOverlay, 0, 0, null);
        g.dispose();
    
        // Save the final combined image
        ImageIO.write(scaledBackground, "png", new File("src/res/tile/" + result + ".png"));
        System.out.println("Ghép ảnh thành công!");
    }
    public BufferedImage scaleImage(BufferedImage original,int height,int width){
            BufferedImage scaledImage= new BufferedImage(width, height,original.TYPE_INT_ARGB);//create a blank canvas
            Graphics2D store = scaledImage.createGraphics();//store the image that we are going to draw
            store.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            store.drawImage(original,0,0,width,height,null);//draw it
            store.dispose();

            return scaledImage;
    }   
}



