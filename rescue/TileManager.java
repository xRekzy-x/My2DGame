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

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        getTileImage();

        mapTile = new int[gp.getMaxWorldRow()][gp.getMaxWorldCol()];// row-col
        overlay = new int[gp.getMaxWorldRow()][gp.getMaxWorldCol()];

        loadmap("/res/map/map1.txt", mapTile);
        loadmap("/res/map/map2.txt", overlay);

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
                screenX = (int)(worldX - .883
!MESSAGE Validated 1. Took 52 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:19.222
!MESSAGE Reconciled 1. Took 9 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:19.539
!MESSAGE Reconciled 1. Took 6 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:19.984
!MESSAGE begin problem for /Player.java

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:19.985
!MESSAGE 21 problems reported for /Player.java

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:19.987
!MESSAGE Validated 1. Took 45 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:20.607
!MESSAGE Reconciled 1. Took 15 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:20.761
!MESSAGE Reconciled 1. Took 0 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:20.872
!MESSAGE Reconciled 1. Took 5 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:21.020
!MESSAGE Reconciled 1. Took 2 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:21.232
!MESSAGE Reconciled 1. Took 5 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:21.272
!MESSAGE Completion request completed

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:21.379
!MESSAGE Reconciled 1. Took 4 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:21.418
!MESSAGE Reconciled 1. Took 4 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:21.500
!MESSAGE Reconciled 1. Took 4 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:21.692
!MESSAGE Reconciled 1. Took 3 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:21.823
!MESSAGE Reconciled 1. Took 5 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:21.918
!MESSAGE Completion request completed

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:21.961
!MESSAGE Reconciled 1. Took 9 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:22.145
!MESSAGE Reconciled 1. Took 16 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:22.164
!MESSAGE Completion request completed

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:22.263
!MESSAGE Reconciled 1. Took 5 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:22.403
!MESSAGE Reconciled 1. Took 12 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:22.592
!MESSAGE Reconciled 1. Took 8 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:22.764
!MESSAGE Reconciled 1. Took 14 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:23.243
!MESSAGE begin problem for /Player.java

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:23.247
!MESSAGE 22 problems reported for /Player.java

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:23.248
!MESSAGE Validated 1. Took 64 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:23.991
!MESSAGE Reconciled 1. Took 0 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:24.597
!MESSAGE onDidCompletionItemSelect:solidArea

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:24.618
!MESSAGE Reconciled 1. Took 0 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:25.059
!MESSAGE begin problem for /Player.java

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:25.060
!MESSAGE 22 problems reported for /Player.java

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:25.061
!MESSAGE Validated 1. Took 34 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:25.168
!MESSAGE Reconciled 1. Took 5 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:25.252
!MESSAGE Reconciled 1. Took 7 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:25.269
!MESSAGE Completion request completed

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:25.425
!MESSAGE Reconciled 1. Took 4 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:25.864
!MESSAGE begin problem for /Player.java

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:25.864
!MESSAGE 21 problems reported for /Player.java

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:25.867
!MESSAGE Validated 1. Took 37 ms

!ENTRY org.eclipse.jdt.ls.core 1 0 2025-05-05 00:05:26.580
!MESSAGE Reconciled 1. Took 0 ms
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

}
