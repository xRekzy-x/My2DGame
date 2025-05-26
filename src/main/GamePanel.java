package main;

import entities.Entity;
import entities.NPC1;
import entities.Player;
import entities.Projectile;
import monster.skeleton;
import monster.Slime;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import objects.Boots;
import objects.Chest;
import objects.Exp;
import objects.Key;
import objects.StrengPotion;
import objects.SuperObj;
import objects.UltraSword;
import tile.Tile;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
   int i;
   final int originalTileSize = 16;
   final int scale = 3;
   final int tileSize = originalTileSize*scale;
   final int maxScreenCol = 16;
   final int maxScreenRow = 12;
   final int screenWidth = maxScreenCol*tileSize;
   final int screenHeight = maxScreenRow*tileSize;
   Graphics2D g2;
   long timer = 0L;
   int drawCount = 999;
   private boolean debug = false;
   KeyHandler key = new KeyHandler(this);
   Thread gameThread;
   TileManager tile = new TileManager(this);
   int FPS = 60;
   UI ui = new UI(this);
   final int maxWorldRow = 20;
   final int maxWorldCol = 25;
   final int worldWidth = maxWorldCol*tileSize;
   final int worldHeight = maxScreenRow*tileSize;
   CollisionChecker colCheck = new CollisionChecker(this);

   //SOUND AND MUSIC
   Sound music = new Sound();
   Sound effect = new Sound();

   //ENTITIES
   Entity[] obj = new Entity[50];
   Entity[] npc = new Entity[10];
   Entity[] monster = new Entity[50];

   //PLAYER
   Player player;
   int playerX;
   int playerY;
   int playerSpeed;

   //GAME STATE
   private int gameState;
   private int titleState=0;
   private int playState=1;
   private int pauseState=2;
   private int dialogueState=3;
   private int characterState=4;
   private int overState = 5;

   //EVENT
   private Event event = new Event(this);

   //DRAW ORDER
   private ArrayList<Projectile> projectileList = new ArrayList<>();
   private ArrayList<Entity> entityList = new ArrayList<>();

   //COUNTER
   private int round=1;
   private int respawnTicket = 3;

   public GamePanel() {
      this.player = new Player(this, this.key);
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
      this.setFocusTraversalKeysEnabled(false);
   }
   //GET COLLISION CHECK
   public void getColCheckTile(Entity entity) {colCheck.checkTile(entity);}
   public int getColCheckObject(Entity entity, boolean isPlayer) {return this.colCheck.checkObj(entity, isPlayer);}
   public boolean getColCheckPlayer(Entity entity) {return colCheck.checkPlayer(entity);}
   public int getColCheckEntity(Entity entity, Entity[] target) {return this.colCheck.checkEntity(entity, target);}
   public int getColCheckInteract(Entity entity, Entity[] target) {return this.colCheck.checkInteract(entity, target);}
   public int getRespawnTicket(){return respawnTicket;}
   public void setRespawnTicket(int ticket){this.respawnTicket=ticket;}
   public void setObjects() {
      this.obj[0] = new Chest(this);
      this.obj[0].setX(5*tileSize);
      this.obj[0].setY(4*tileSize);
      this.obj[1] = new Key(this);
      this.obj[1].setX(480);
      this.obj[1].setY(480);
      this.obj[2] = new Boots(this);
      this.obj[2].setX(576);
      this.obj[2].setY(576);
      this.obj[3] = new Boots(this);
      this.obj[3].setX(624);
      this.obj[3].setY(576);
      this.obj[4] = new Boots(this);
      this.obj[4].setX(672);
      this.obj[4].setY(576);
      this.obj[5] = new StrengPotion(this);
      this.obj[5].setX(768);
      this.obj[5].setY(576);
      this.obj[6] = new Exp(this);
      this.obj[6].setX(tileSize*5);
      this.obj[6].setY(tileSize*3);
      this.obj[7] = new Exp(this);
      this.obj[7].setX(tileSize*6);
      this.obj[7].setY(tileSize*3);
      //7 đã có
   }
   public void setObj(Entity obj,int x, int y){
      int index=0;
      for(int i=0;i<this.obj.length;i++){
         if(this.obj[i]==null){
            index=i;
         }
      }
      this.obj[index] = obj;
      this.obj[index].setX(tileSize*x);
      this.obj[index].setY(tileSize*y);
   }
   public void setNPC() {
      this.npc[1] = new NPC1(this);
      this.npc[1].setX(144);
      this.npc[1].setY(96);
   }
   public int[] generateRandomLocation(){
      int[] location = new int[2];
      int x;
      int y;
      int[][] allLocation = getOverLay2();
      Random random = new Random();
      do{
         x = random.nextInt(maxWorldCol);
         y = random.nextInt(maxWorldRow);
      }while(getExactTile(allLocation[y][x]).getGetCollision());
      location[0]=x*tileSize;
      location[1]=y*tileSize;
      return location;

   }
   public void setMonster(){
      int i = 3;
      monster[0] = new skeleton(this);
      monster[0].setX(480);
      monster[0].setY(480);
   }
   public Graphics2D getG2(){ return g2;}
   public void setGameState(int gameState) {this.gameState = gameState;}
   public int getGameState() {return this.gameState;}
   public int getTitleState() {return this.titleState;}
   public int getPlayState() {return this.playState;}
   public int getPauseState() {return this.pauseState;}
   public int getDialogueState() {return this.dialogueState;}
   public int getCharacterState(){return characterState;}
   public int getTileSize() {return tileSize;}
   public int getOverState() {return overState;}
   public int getScreenWidth() { return screenWidth;}
   public int getScreenHeight() {return screenHeight;}
   public int getScale() {return scale;}
   public int getMaxScreenCol() {return maxScreenCol;}
   public int getMaxScreenRow() {return maxScreenRow;}
   public int getMaxWorldCol() { return maxWorldCol;}
   public int getWorldWidth() {return worldWidth;}
   public int getMaxWorldRow() {return maxWorldRow;}
   public int getWorldHeight() {return worldHeight;}
   public int getPlayerX() {return this.player.getX();}
   public int getPlayerY() {return this.player.getY();}
   public int getPlayerScreenX() {return this.player.getScreenX();}
   public int getPlayerScreenY() {return this.player.getScreenY();}
   public int getStrength() {return this.player.getStrength();}
   public int[][] getMapTile() {return this.tile.getMapTile();}
   public int[][] getMapTile2() {return this.tile.getMapTile2();}
   public int[][] getOverLay() {return this.tile.getOverLay();}
   public int[][] getOverLay2() {return this.tile.getOverLay2();}

   public Tile[] getTile() {
      return this.tile.getTile();
   }

   public Tile getExactTile(int index) {
      return this.tile.getExactTile(index);
   }

   public Entity getObj(int index) {
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

   public void setObj(Entity obj, int index) {
      this.obj[index] = obj;
   }

   public String getObjName(int index) {
      return this.obj[index].getName();
   }

   public UI getui() {
      return this.ui;
   }

   public Entity[] getNPC() { return this.npc; }
   public Entity[] getMonster() { return monster; }
   public Entity getExactNPC(int index) { return this.npc[index]; }
   public Entity getExactMonster(int index) {return monster[index];}
   public void setExactMonster(int i,Entity newMonster){monster[i]=newMonster;}
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
   public Event getEvent(){return event;}
   public void setEvent(Event event){this.event=event;}
   public List<Projectile> getProjectile(){return projectileList;}
   public void addProjectile(Projectile projectile){this.projectileList.add(projectile);}

   public void setupGame() {
      setObjects();
      setNPC();
      setMonster();
      playMusic(0);
      gameState = this.titleState;
   }
   public void generateMonster(){
      boolean isEmpty = true;
      for(int i=0;i<monster.length;i++){
         if(monster[i]!=null) isEmpty=false;
      }
      if(key.getEnterPressed()){
         gameState=playState;
      }
      if(isEmpty){
         int randomLocation[];
         Slime slime = new Slime(this);
         skeleton ske = new skeleton(this);
         int newDmg = slime.getDamage()+10*round;
         int newDef = slime.getDefense()+20*round;
         int newHP= slime.getMaxLife()+50*round;
         int defSke = ske.getDefense()+20*round;
         int dmgSke = ske.getDamage()+10*round;
         int HPske = ske.getMaxLife()+70*round;
         gameState=dialogueState;
         ui.setCurrentDialogue("ROUND "+round+"\nskeleton dmg/def/HP: "+dmgSke+"/"+defSke+"/"+HPske+"\nslime dmg/def/HP: "+newDmg+"/"+newDef+"/"+newHP);
         for(int k =0;k<5;k++){
            randomLocation = generateRandomLocation();
            monster[k]=new Slime(this);
            monster[k].setDamage(newDmg);
            monster[k].setDefense(newDef);
            monster[k].setMaxLife(newHP);
            monster[k].setX(randomLocation[0]);
            monster[k].setY(randomLocation[1]);
         }
         for(int k =5;k<10;k++){
            randomLocation = generateRandomLocation();
            monster[k]=new skeleton(this);
            monster[k].setDamage(dmgSke);
            monster[k].setDefense(defSke);
            monster[k].setMaxLife(HPske);
            monster[k].setX(randomLocation[0]);
            monster[k].setY(randomLocation[1]);
         }
         round++;
      }
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
      generateMonster();
      if (this.gameState == this.playState) {
         this.player.update();
         for(int i = 0; i < npc.length; ++i) if (npc[i] != null) npc[i].update();
         for(int i = 0; i < monster.length; ++i) {if (monster[i] != null){
            if(monster[i].getAlive()&&monster[i].getDying()==false) monster[i].update();
            if(monster[i].getAlive()==false ) monster[i]=null;
            }
         }
         for(int k = 0; k < projectileList.size(); k++) {if (projectileList.get(k) != null){
            if(projectileList.get(k).getAlive()) projectileList.get(k).update();
            if(projectileList.get(k).getAlive()==false ) projectileList.remove(k);
            }
         }
      }
      if (this.gameState == this.dialogueState) {
         this.player.setNPCindex(this.getColCheckInteract(this.player, this.npc));
         this.player.interactNPC(this.player.getNPCindex());
      }
     key.setEnterPressed(false);
   }

   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g2 = (Graphics2D)g;
      long drawStart = 0L;
      drawStart = System.nanoTime();
      if (this.gameState == this.titleState) {
         this.ui.draw(g2);
      } 
      else {
         g2.setColor(Color.black);
         g2.fillRect(0, 0, this.getWidth(), this.getHeight());
         // if (this.key.leftPressed && this.key.downPressed) {
         //    this.tile.draw3(g2, this.tile.getMapTile());
         //    this.tile.draw3(g2, this.tile.getOverLay());
         // } else if (this.key.rightPressed && this.key.upPressed) {
         //    this.tile.draw4(g2, this.tile.getMapTile());
         //    this.tile.draw4(g2, this.tile.getOverLay());
         // } else if (!this.key.rightPressed && !this.key.downPressed) {
         //    this.tile.draw2(g2, this.tile.getMapTile());
         //    this.tile.draw2(g2, this.tile.getOverLay());
         // } else {
         //    this.tile.draw1(g2, this.tile.getMapTile());
         //    this.tile.draw1(g2, this.tile.getOverLay());
         // }
         if (this.key.leftPressed && this.key.downPressed) {
            this.tile.draw3(g2, this.tile.getMapTile2());
            this.tile.draw3(g2, this.tile.getOverLay2());
         } else if (this.key.rightPressed && this.key.upPressed) {
            this.tile.draw4(g2, this.tile.getMapTile2());
            this.tile.draw4(g2, this.tile.getOverLay2());
         } else if (!this.key.rightPressed && !this.key.downPressed) {
            this.tile.draw2(g2, this.tile.getMapTile2());
            this.tile.draw2(g2, this.tile.getOverLay2());
         } else {
            this.tile.draw1(g2, this.tile.getMapTile2());
            this.tile.draw1(g2, this.tile.getOverLay2());
         }

         entityList.add(player);
         //ADD EVERY ENTITIES(obj included) TO AN ARRAYLIST
         int normalY = player.getY();
         player.setY(player.getY()+20);
         for(i=0;i<npc.length;i++){ if(npc[i]!=null) entityList.add(npc[i]);}
         for(i=0;i<obj.length;i++){ if(obj[i]!=null) entityList.add(obj[i]);}
         for(i=0;i<monster.length;i++){ if(monster[i]!=null) entityList.add(monster[i]);}
         for(i=0;i<projectileList.size();i++){ if(projectileList.get(i)!=null) entityList.add(projectileList.get(i));}
         Collections.sort(entityList, new Comparator<Entity>() {
            @Override
            public int compare(Entity e1, Entity e2) {
               return Integer.compare(e1.getY(), e2.getY());
            }
         });//sort by Y(increasing)(index 0 is the has the smallest Y)
         player.setY(normalY);
         for(i=0;i<entityList.size();i++){entityList.get(i).draw(g2);}//DRAW 1 BY 1 FOLLOWING THE ORDER
         entityList.clear();//REMOVE EVERYTHING
         // for(i = 0; i < this.obj.length; ++i) {
         //    if (this.obj[i] != null) {
         //       this.obj[i].draw(g2);
         //    }
         // }

         // for(i = 0; i < this.npc.length; ++i) {
         //    if (this.npc[i] != null) {
         //       this.npc[i].draw(g2);
         //    }
         // }
         if (this.debug) {
            event.debug(g2);
            this.player.debug(g2);
            for(i = 0; i < this.npc.length; ++i) {
               if (this.npc[i] != null) {
                  this.npc[i].debug(g2);
               }
            }
            for(i=0;i<obj.length;i++){
               if (this.obj[i] != null) {
                  this.obj[i].debug(g2);
               }
            }
            for(i=0;i<monster.length;i++){
               if (this.monster[i] != null) {
                  this.monster[i].debug(g2);
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
   public void resetGame(){
      round=1;
      for(int i=0;i<monster.length;i++){
         monster[i]=null;
      }
      player.setDefaultPosition();
      player.resetHealth();
      respawnTicket=3;
   }
   public void Respawn(){
      player.setDefaultPosition();
      player.resetHealth();
      respawnTicket--;
   }


}
   