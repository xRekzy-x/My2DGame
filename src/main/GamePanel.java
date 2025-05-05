package main;

import entities.Entity;
import entities.NPC1;
import entities.Player;
import entities.skeleton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import objects.Boots;
import objects.Chest;
import objects.Key;
import objects.StrengPotion;
import objects.SuperObj;
import tile.Tile;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
   final int originalTileSize = 16;
   final int scale = 3;
   final int tileSize = 48;
   final int maxScreenCol = 16;
   final int maxScreenRow = 12;
   final int screenWidth = 768;
   final int screenHeight = 576;
   long timer = 0L;
   int drawCount = 999;
   private boolean debug = false;
   KeyHandler key = new KeyHandler(this);
   Thread gameThread;
   TileManager tile = new TileManager(this);
   int FPS = 60;
   UI ui = new UI(this);
   final int maxWorldCol = 50;
   final int maxWorldRow = 50;
   final int worldWidth = 2400;
   final int worldHeight = 2400;
   CollisionChecker colCheck = new CollisionChecker(this);
   Sound music = new Sound();
   Sound effect = new Sound();
   SuperObj[] obj = new SuperObj[50];
   Player player;
   Entity[] npc;
   int playerX;
   int playerY;
   int playerSpeed;
   private int gameState;
   private int titleState=0;
   private int playState=1;
   private int pauseState=2;
   private int dialogueState=3;

   public GamePanel() {
      this.player = new Player(this, this.key);
      this.npc = new Entity[10];
      this.playerX = 100;
      this.playerY = 100;
      this.playerSpeed = 4;
      this.titleState = 0;
      this.playState = 1;
      this.pauseState = 2;
      this.dialogueState = 3;
      this.setPreferredSize(new DimensionUIResource(768, 576));
      this.setBackground(Color.black);
      this.setDoubleBuffered(true);
      this.addKeyListener(this.key);
      this.setFocusable(true);
   }

   public void getColCheckTile(Entity entity) {
      this.colCheck.checkTile(entity);
   }

   public int getColCheckObject(Entity entity, boolean isPlayer) {
      return this.colCheck.checkObj(entity, isPlayer);
   }

   public void getColCheckPlayer(Entity entity) {
      this.colCheck.checkPlayer(entity);
   }

   public int getColCheckEntity(Entity entity, Entity[] target) {
      return this.colCheck.checkEntity(entity, target);
   }

   public int getColCheckInteract(Entity entity, Entity[] target) {
      return this.colCheck.checkInteract(entity, target);
   }

   public void setObjects() {
      this.obj[0] = new Chest();
      this.obj[0].setX(192);
      this.obj[0].setY(192);
      this.obj[1] = new Key();
      this.obj[1].setX(480);
      this.obj[1].setY(480);
      this.obj[2] = new Boots();
      this.obj[2].setX(576);
      this.obj[2].setY(576);
      this.obj[3] = new Boots();
      this.obj[3].setX(624);
      this.obj[3].setY(576);
      this.obj[4] = new Boots();
      this.obj[4].setX(672);
      this.obj[4].setY(576);
      this.obj[5] = new StrengPotion();
      this.obj[5].setX(768);
      this.obj[5].setY(576);
   }

   public void setNPC() {
      this.npc[0] = new skeleton(this);
      this.npc[0].setX(480);
      this.npc[0].setY(480);
      this.npc[1] = new NPC1(this);
      this.npc[1].setX(144);
      this.npc[1].setY(96);
   }

   public void setGameState(int gameState) {
      this.gameState = gameState;
   }

   public int getGameState() {
      return this.gameState;
   }

   public int getTitleState() {
      return this.titleState;
   }

   public int getPlayState() {
      return this.playState;
   }

   public int getPauseState() {
      return this.pauseState;
   }

   public int getDialogueState() {
      return this.dialogueState;
   }

   public int getTileSize() {
      return 48;
   }

   public int getScreenWidth() {
      return 768;
   }

   public int getScreenHeight() {
      return 576;
   }

   public int getScale() {
      return 3;
   }

   public int getMaxScreenCol() {
      return 16;
   }

   public int getMaxScreenRow() {
      return 12;
   }

   public int getMaxWorldCol() {
      return 50;
   }

   public int getWorldWidth() {
      return 2400;
   }

   public int getMaxWorldRow() {
      return 50;
   }

   public int getWorldHeight() {
      return 2400;
   }

   public int getPlayerX() {
      return this.player.x;
   }

   public int getPlayerY() {
      return this.player.y;
   }

   public int getPlayerScreenX() {
      return this.player.getScreenX();
   }

   public int getPlayerScreenY() {
      return this.player.getScreenY();
   }

   public int getStrength() {
      return this.player.getStrength();
   }

   public int[][] getMapTile() {
      return this.tile.getMapTile();
   }

   public int[][] getOverLay() {
      return this.tile.getOverLay();
   }

   public Tile[] getTile() {
      return this.tile.getTile();
   }

   public Tile getExactTile(int index) {
      return this.tile.getExactTile(index);
   }

   public SuperObj getObj(int index) {
      return this.obj[index];
   }

   public int getObjLength() {
      return this.obj.length;
   }

   public int getEntityLength() {
      return this.npc.length;
   }

   public Entity getEntity(int i) {
      return this.npc[i];
   }

   public void setObj(SuperObj obj, int index) {
      this.obj[index] = obj;
   }

   public String getObjName(int index) {
      return this.obj[index].getName();
   }

   public UI getui() {
      return this.ui;
   }

   public Entity[] getNPC() {
      return this.npc;
   }

   public Entity getExactNPC(int index) {
      return this.npc[index];
   }

   public Player getPlayer() {
      return this.player;
   }

   public KeyHandler getKey() {
      return this.key;
   }

   public void setKey(KeyHandler key) {
      this.key = key;
   }

   public boolean getDebug() {
      return this.debug;
   }

   public void setDebug(boolean debug) {
      this.debug = debug;
   }

   public void setupGame() {
      this.setObjects();
      this.setNPC();
      this.playMusic(0);
      this.gameState = this.titleState;
   }

   public void startGameThread() {
      this.gameThread = new Thread(this);
      this.gameThread.start();
   }

   public void run() {
      double drawInterval = (double)(1000000000 / this.FPS);
      double nextDrawTime = (double)System.nanoTime() + drawInterval;
      long lastTime = System.nanoTime();
      long timer = 0L;
      int drawCount = 0;
      int temp = 0;

      while(this.gameThread != null) {
         long currentTime = System.nanoTime();
         this.update();
         this.repaint();

         try {
            double remainingTime = nextDrawTime - (double)System.nanoTime();
            if (remainingTime < 0.0D) {
               remainingTime = 0.0D;
            }

            Thread.sleep((long)remainingTime / 1000000L);
            ++drawCount;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            nextDrawTime += drawInterval;
         } catch (InterruptedException var15) {
            var15.printStackTrace();
         }

         this.timer = timer;
         if (timer >= 1000000000L) {
            temp = drawCount;
            this.drawCount = drawCount;
            drawCount = 0;
            timer = 0L;
         } else {
            this.drawCount = temp;
         }
      }

   }

   public void update() {
      if (this.gameState == this.playState) {
         this.player.update();

         for(int i = 0; i < this.npc.length; ++i) {
            if (this.npc[i] != null) {
               this.npc[i].update();
            }
         }
      }

      if (this.gameState == this.dialogueState) {
         this.player.setNPCindex(this.getColCheckInteract(this.player, this.npc));
         this.player.interactNPC(this.player.getNPCindex());
      }

   }

   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      long drawStart = 0L;
      drawStart = System.nanoTime();
      if (this.gameState == this.titleState) {
         this.ui.draw(g2);
      } 
      else {
         g2.setColor(Color.black);
         g2.fillRect(0, 0, this.getWidth(), this.getHeight());
         if (this.key.leftPressed && this.key.downPressed) {
            this.tile.draw3(g2, this.tile.getMapTile());
            this.tile.draw3(g2, this.tile.getOverLay());
         } else if (this.key.rightPressed && this.key.upPressed) {
            this.tile.draw4(g2, this.tile.getMapTile());
            this.tile.draw4(g2, this.tile.getOverLay());
         } else if (!this.key.rightPressed && !this.key.downPressed) {
            this.tile.draw2(g2, this.tile.getMapTile());
            this.tile.draw2(g2, this.tile.getOverLay());
         } else {
            this.tile.draw1(g2, this.tile.getMapTile());
            this.tile.draw1(g2, this.tile.getOverLay());
         }

         int i;
         for(i = 0; i < this.obj.length; ++i) {
            if (this.obj[i] != null) {
               this.obj[i].draw(g2, this);
            }
         }

         for(i = 0; i < this.npc.length; ++i) {
            if (this.npc[i] != null) {
               this.npc[i].draw(g2);
            }
         }

         this.player.draw(g2);
         if (this.debug) {
            this.player.debug(g2);

            for(i = 0; i < this.npc.length; ++i) {
               if (this.npc[i] != null) {
                  this.npc[i].debug(g2);
               }
            }
         }

         long drawEnd = System.nanoTime();
         g2.setColor(Color.WHITE);
         g2.drawString("Time-to-draw:" + (drawEnd - drawStart), 10, 50);
         g2.drawString("FPS:" + this.drawCount, 10, 40);
         this.drawCount = 0;
         this.timer = 0L;
         this.ui.draw(g2);
      }

      g2.dispose();
   }

   public void playMusic(int i) {
      this.music.setFile(i);
      this.music.play();
      this.music.loop();
   }

   public void stopMusic() {
      this.music.stop();
   }

   public void playSoundEffect(int i) {
      this.effect.setFile(i);
      this.effect.play();
   }
}
   