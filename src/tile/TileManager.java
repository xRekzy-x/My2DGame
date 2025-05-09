package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.ImageModification;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    private int mapTile[][];
    private int overlay[][];

    private int mapTile2[][];
    private int overlay2[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[100];
        getTileImage();

        mapTile = new int[gp.getMaxWorldRow()][gp.getMaxWorldCol()];// row-col
        overlay = new int[gp.getMaxWorldRow()][gp.getMaxWorldCol()];

        mapTile2 = new int[20][25];// row-col
        overlay2 = new int[20][25];

        loadmap("/res/map/map1.txt", mapTile);
        loadmap("/res/map/map2.txt", overlay);
        
        loadmap("/res/map/map5.txt", mapTile2);
        loadmap("/res/map/map6.txt", overlay2);
    }

    public void setUp(int index, String imageName, boolean collision) {
        ImageModification mod = new ImageModification();
        try {
            tile[index] = new Tile();
            tile[index].setImage(ImageIO.read(getClass().getResource("/res/tile/" + imageName + ".png")));
            tile[index].setImage(mod.scaleImage(tile[index].getImage(), gp.getTileSize(), gp.getTileSize()));
            tile[index].setCollision(collision);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTileImage() {
        setUp(0, "000", false);
        setUp(1, "yes", false);
        setUp(2, "third", false);
        setUp(3, "001", false);
        setUp(4, "farmland", false);
        setUp(5, "smalltree", true);
        setUp(6, "bigtree1", true);
        setUp(7, "bigtree2", true);
        setUp(8, "bigtree3", true);
        setUp(9, "bigtree4", true);
        setUp(10, "bigtree5", true);
        setUp(11, "bigtree6", true);
        setUp(12, "bigtree7", true);
        setUp(13, "bigtree8", true);
        setUp(14, "bigtree9", true);
        setUp(15, "water1", true);
        setUp(16, "water2", true);
        setUp(17, "water3", true);
        setUp(18, "water_rock", true);
        setUp(19, "water_leaf", true);
        setUp(20, "bridgeNgangTop1", false);
        setUp(21, "bridgeNgangTop2", false);
        setUp(22, "bridgeNgangTop3", false);
        setUp(23, "bridgeNgangMid1", false);
        setUp(24, "bridgeNgangMid2", false);
        setUp(25, "bridgeNgangMid3", false);
        setUp(26, "bridgeNgangDown1", false);
        setUp(27, "bridgeNgangDown2", false);
        setUp(28, "bridgeNgangDown3", false);
        setUp(29, "fenceTop", true);
        setUp(30, "fenceBottom", true);
        setUp(31, "fenceLeft", true);
        setUp(32, "fenceRight", true);
        setUp(33, "water_hole1", true);
        setUp(34, "water_hole2", true);
        setUp(35, "water_hole3", true);
        setUp(36, "water_hole4", true);
        setUp(37, "water_hole5", true);
        setUp(38, "water_hole6", true);
        setUp(39, "water_hole7", true);
        setUp(40, "water_hole8", true);
        setUp(41, "water_hole9", true);
        setUp(42, "tile_0_3", true);
        setUp(43, "cayhoatrang", true);
        setUp(44, "cay_blue_top", true);
        setUp(45, "cay_blue_bottom", true);
        setUp(46, "cay_green_top", true);
        setUp(47, "cay_green_bottom", true);
        setUp(48, "cay_orange_top", true);
        setUp(49, "cay_orange_bottom", true);
        setUp(50, "cay_xanhdam_top", true);
        setUp(51, "cay_xanhdam_bottom", true);
        setUp(52, "goc_cay_bi_chat", true);
        setUp(53, "flower_red", true);
        setUp(54, "bluetreeNW_top", true);
        setUp(55, "bluetreeNE_top", true);
        setUp(56, "bluetreeSW_bottom", true);
        setUp(57, "bluetreeSE_bottom", true);
        setUp(58, "greentreeNW_top", true);
        setUp(59, "greentreeNE_top", true);
        setUp(60, "greentreeSW_bottom", true);
        setUp(61, "greentreeSE_bottom", true);
    }

    public void loadmap(String filepath, int mapTile[][]) {
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
                String line = br.readLine();// String line sẽ chứa 1 dòng của map
                while (col < gp.getMaxWorldCol()) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTile[row][col] = num;
                    col++;
                }
                if (col == gp.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw1(Graphics2D g2, int mapTile[][]) {
        int screenX;
        int screenY;
        int worldX;
        int worldY;
        int tileSize=gp.getTileSize();
        for (int i = 0; i < gp.getMaxWorldRow(); i++) {
            for (int j = 0; j < gp.getMaxWorldCol(); j++) {
                worldX = j * tileSize;
                worldY = i * tileSize;
                screenX = (int)(worldX - gp.getPlayerX() + gp.getPlayerScreenX());
                screenY = (int) (worldY - gp.getPlayerY() + gp.getPlayerScreenY());
                /*
                 * giải thích 2 dòng trên: player đang ở vị trí random thì mình cho
                 * player về góc trái trên cùng của màn hình rồi sau đó +screenX screenY
                 * để lôi player ra giữa màn hình
                 */
                int num = mapTile[i][j];
                if (worldX + 2 * tileSize > gp.getPlayerX() - gp.getPlayerScreenX() &&
                        worldX - 2 * tileSize < gp.getPlayerX() + gp.getPlayerScreenX() &&
                        worldY - 3 * tileSize < gp.getPlayerY() + gp.getPlayerScreenY() &&
                        worldY + 2 * tileSize > gp.getPlayerY() - gp.getPlayerScreenY()) {
                    g2.drawImage(tile[num].getImage(), screenX, screenY, null);
                }

            }
        }
    }

    public void draw2(Graphics2D g2, int mapTile[][]) {
        int screenX;
        int screenY;
        int worldX;
        int worldY;
        int tileSize=gp.getTileSize();
        for (int i = gp.getMaxWorldRow() - 1; i >= 0; i--) {
            for (int j = gp.getMaxWorldCol() - 1; j >= 0; j--) {
                worldX = j * tileSize;
                worldY = i * tileSize;
                screenX = (int) (worldX - gp.getPlayerX() + gp.getPlayerScreenX());
                screenY = (int) (worldY - gp.getPlayerY() + gp.getPlayerScreenY());
                /*
                 * giải thích 2 dòng trên: player đang ở vị trí random thì mình cho
                 * player về góc trái trên cùng của màn hình rồi sau đó +screenX screenY
                 * để lôi player ra giữa màn hình
                 */
                int num = mapTile[i][j];
                if (worldX + 2 * tileSize > gp.getPlayerX() - gp.getPlayerScreenX() &&
                        worldX - 2 * tileSize < gp.getPlayerX() + gp.getPlayerScreenX() &&
                        worldY - 3 * tileSize < gp.getPlayerY() + gp.getPlayerScreenY() &&
                        worldY + 2 * tileSize > gp.getPlayerY() - gp.getPlayerScreenY()) {
                    g2.drawImage(tile[num].getImage(), screenX, screenY, null);
                }
            }
        }
    }

    public void draw3(Graphics2D g2, int mapTile[][]) {
        int screenX;
        int screenY;
        int worldX;
        int worldY;
        int tileSize=gp.getTileSize();
        for (int i = 0; i < gp.getMaxWorldRow(); i++) {
            for (int j = gp.getMaxWorldCol() - 1; j >= 0; j--) {
                worldX = j * tileSize;
                worldY = i * tileSize;
                screenX =(int) (worldX - gp.getPlayerX() + gp.getPlayerScreenX());
                screenY =(int) (worldY - gp.getPlayerY() + gp.getPlayerScreenY());
                /*
                 * giải thích 2 dòng trên: player đang ở vị trí random thì mình cho
                 * player về góc trái trên cùng của màn hình rồi sau đó +screenX screenY
                 * để lôi player ra giữa màn hình
                 */
                int num = mapTile[i][j];
                if (worldX + 2 * tileSize > gp.getPlayerX() - gp.getPlayerScreenX() &&
                        worldX - 2 * tileSize < gp.getPlayerX() + gp.getPlayerScreenX() &&
                        worldY - 3 * tileSize < gp.getPlayerY() + gp.getPlayerScreenY() &&
                        worldY + 2 * tileSize > gp.getPlayerY() - gp.getPlayerScreenY()) {
                    g2.drawImage(tile[num].getImage(), screenX, screenY, null);
                }
            }
        }
    }

    public void draw4(Graphics2D g2, int mapTile[][]) {
        int screenX;
        int screenY;
        int worldX;
        int worldY;
        int tileSize=gp.getTileSize();
        for (int i = gp.getMaxWorldRow() - 1; i >= 0; i--) {
            for (int j = 0; j < gp.getMaxWorldCol(); j++) {
                worldX = j * tileSize;
                worldY = i * tileSize;
                screenX = (worldX - gp.getPlayerX() + gp.getPlayerScreenX());
                screenY = (worldY - gp.getPlayerY() + gp.getPlayerScreenY());
                /*
                 * giải thích 2 dòng trên: player đang ở vị trí random thì mình cho
                 * player về góc trái trên cùng của màn hình rồi sau đó +screenX screenY
                 * để lôi player ra giữa màn hình
                 */
                int num = mapTile[i][j];
                if (worldX + 2 * tileSize > gp.getPlayerX() - gp.getPlayerScreenX() &&
                        worldX - 2 * tileSize < gp.getPlayerX() + gp.getPlayerScreenX() &&
                        worldY - 3 * tileSize < gp.getPlayerY() + gp.getPlayerScreenY() &&
                        worldY + 2 * tileSize > gp.getPlayerY() - gp.getPlayerScreenY()) {
                    g2.drawImage(tile[num].getImage(), screenX, screenY, null);
                }
            }
        }
    }

    public int[][] getMapTile() {
        return mapTile;
    }

    public int[][] getOverLay() {
        return overlay;
    }

    public Tile[] getTile() {
        return tile;
    }

    public Tile getExactTile(int index) {
        return tile[index];
    }
    public int[][] getMapTile2(){ return mapTile2; }
    public int[][] getOverLay2(){ return overlay2; }

}
