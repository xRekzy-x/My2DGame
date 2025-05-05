import main.ImageModification;
public class App {
    public static void main(String[] args) throws Exception {
        ImageModification image = new ImageModification();
        //image.crop(32,0, "fenceTop1");
        //image.flip("fenceVertical");
        image.rotate("fenceRight");
        //image.ImageOverlay("001", "ohshjt", "PLEASE");
    }
}
