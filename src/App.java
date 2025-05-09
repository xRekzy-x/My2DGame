import main.ImageModification;
public class App {
    public static void main(String[] args) throws Exception {
        ImageModification image = new ImageModification();
        image.crop(128,64, "slimeDie4");
        //image.flip("fenceVertical");
       //image.rotate("fenceRight");
        //image.ImageOverlay("null", "1", "huh");
    }
}
