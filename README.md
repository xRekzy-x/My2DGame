## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OverlayImages {
    public static void main(String[] args) throws IOException {
        // Load base image
        BufferedImage baseImage = ImageIO.read(new File("base.png"));

        // Load overlay image
        BufferedImage overlayImage = ImageIO.read(new File("overlay.png"));

        // Create a new BufferedImage with same dimensions as baseImage
        BufferedImage combined = new BufferedImage(
            baseImage.getWidth(),
            baseImage.getHeight(),
            BufferedImage.TYPE_INT_ARGB
        );

        // Draw images on top of each other
        Graphics2D g = combined.createGraphics();
        g.drawImage(baseImage, 0, 0, null);
        g.drawImage(overlayImage, 50, 50, null); // Adjust position as needed
        g.dispose();

        // Save the final image
        ImageIO.write(combined, "PNG", new File("output.png"));

        System.out.println("Overlay complete, saved as output.png");
    }
}
