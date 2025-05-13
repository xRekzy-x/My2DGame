import main.ImageModification;
public class App {
    public static void main(String[] args) throws Exception {
        ImageModification image = new ImageModification();
        image.crop( 100,42, "sword4");
        //image.flip("fenceVertical");
        //image.rotate("fenceRight");
        //image.ImageOverlay("null", "sword2", "VCLVCL");
        
    }
}
