package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
   public boolean upPressed;
   public boolean downPressed;
   public boolean leftPressed;
   public boolean rightPressed;
   private boolean zPressed = false;
   private boolean enterPressed=false;
   private boolean shotKeyPressed = false;
   GamePanel gp;

   public KeyHandler(GamePanel gp) {
      this.gp = gp;
   }

   public boolean getZPressed() {
      return this.zPressed;
   }

   public void setZPressed(boolean zPressed) {
      this.zPressed = zPressed;
   }

   public boolean getEnterPressed() {
      return this.enterPressed;
   }

   public void setEnterPressed(boolean enterPressed) {
      this.enterPressed = enterPressed;
   }
   public boolean getShotKeyPressed(){return shotKeyPressed;}
   public void setShotKeyPressed(boolean shotKeyPressed){this.shotKeyPressed=shotKeyPressed;}
   public void keyTyped(KeyEvent e) {
   }

   public void keyPressed(KeyEvent e) {
      int code = e.getKeyCode();
      if (this.gp.getGameState() == this.gp.getTitleState()) {titleState(code);}
      else if (this.gp.getGameState() == this.gp.getPlayState()) {playState(code);} 
      else if (this.gp.getGameState() == this.gp.getPauseState()) {pauseState(code);} 
      else if (this.gp.getGameState() == this.gp.getDialogueState()) {dialogueState(code);}
      else if (this.gp.getGameState() == this.gp.getCharacterState()){ characterState(code);}
      else if (this.gp.getGameState() == this.gp.getOverState()){ overState(code); }

   }
   public void titleState(int code){
      // int maxCommand=3;
      // if (code == KeyEvent.VK_W) {
      //    if (this.gp.getui().getCommandNumber() == 0) 
      //       this.gp.getui().setCommandNumber(maxCommand-1);
      //    else
      //       this.gp.getui().setCommandNumber(this.gp.getui().getCommandNumber() - 1);
      //    }
      // if (code == KeyEvent.VK_S) {
      //    if (this.gp.getui().getCommandNumber() == maxCommand-1)
      //       this.gp.getui().setCommandNumber(0);
      //    else
      //       this.gp.getui().setCommandNumber(this.gp.getui().getCommandNumber() + 1);
      //    }
      changeCommand(code, 3);
      if (code == KeyEvent.VK_ENTER) {
         if (this.gp.getui().getCommandNumber() == 0) {
            this.gp.setGameState(this.gp.getPlayState());
         }
         if (this.gp.getui().getCommandNumber() == 2) {
            System.exit(0);
         }
      }
   }
   public void playState(int code){
       if (code == KeyEvent.VK_W) {
            this.upPressed = true;
         }
         if (code == KeyEvent.VK_S) {
            this.downPressed = true;
         }

         if (code == KeyEvent.VK_A) {
            this.leftPressed = true;
         }

         if (code == KeyEvent.VK_D) {
            this.rightPressed = true;
         }

         if (code == KeyEvent.VK_P) {
            this.gp.setGameState(this.gp.getPauseState());
         }

         if (code == KeyEvent.VK_Z) {
            this.zPressed = true;
         }

         if (code == KeyEvent.VK_F1) {
            if (this.gp.getDebug()) {
               this.gp.setDebug(false);
            } else {
               this.gp.setDebug(true);
            }
         }
         if (code == KeyEvent.VK_ENTER) {
            enterPressed=true;
         }
         if (code == KeyEvent.VK_R){ gp.getPlayer().setAttacking(true);}
         if( code == KeyEvent.VK_F){ shotKeyPressed=true;}
         if (code == KeyEvent.VK_TAB){
            this.gp.setGameState(this.gp.getCharacterState());
         }
   }
   public void pauseState(int code){
      if (code == 80) {
            this.gp.setGameState(this.gp.getPlayState());
      }
   }
   public void dialogueState(int code){
       if (code == KeyEvent.VK_P) {
            this.gp.setGameState(this.gp.getPlayState());
         }

         if (code == KeyEvent.VK_ENTER) {
            this.enterPressed = true;
         }
   }
   public void characterState(int code){
      if (code == KeyEvent.VK_TAB) {gp.setGameState(gp.getPlayState());}
      if (code == KeyEvent.VK_ENTER) {gp.getPlayer().selectItem();}
      if (code == KeyEvent.VK_A) {
         if(gp.getui().getSlotCol()>0) gp.getui().setSlotCol(gp.getui().getSlotCol()-1);
         gp.playSoundEffect(9);
      }
      if (code == KeyEvent.VK_D) {
         if(gp.getui().getSlotCol()<gp.getui().getMaxSlotCol()-1) gp.getui().setSlotCol(gp.getui().getSlotCol()+1);
         gp.playSoundEffect(9);
      }
       if (code == KeyEvent.VK_W) {
         if(gp.getui().getSlotRow()>0) gp.getui().setSlotRow(gp.getui().getSlotRow()-1);
         gp.playSoundEffect(9);
      }
       if (code == KeyEvent.VK_S) {
         if(gp.getui().getSlotRow()<gp.getui().getMaxSlotRow()-1) gp.getui().setSlotRow(gp.getui().getSlotRow()+1);
         gp.playSoundEffect(9);
      }
   }
   public void overState(int code){
      changeCommand(code, 3);
      if (code == KeyEvent.VK_ENTER) {
         if (this.gp.getui().getCommandNumber() == 0) {
            gp.resetGame();
            this.gp.setGameState(gp.getPlayState());
         }
         if (this.gp.getui().getCommandNumber() == 1) {
            if(gp.getRespawnTicket()>0){
               gp.Respawn();
               this.gp.setGameState(gp.getPlayState());
            }
         }
         if (this.gp.getui().getCommandNumber() == 2) {
            System.exit(0);
         }
      }
   }
   public void keyReleased(KeyEvent e) {
      int code = e.getKeyCode();
      if (code == 87) {
         this.upPressed = false;
      }

      if (code == 83) {
         this.downPressed = false;
      }

      if (code == 65) {
         this.leftPressed = false;
      }

      if (code == 68) {
         this.rightPressed = false;
      }
   }
   private void changeCommand(int code, int maxCommand){
      if (code == KeyEvent.VK_W) {
         if (this.gp.getui().getCommandNumber() == 0) 
            this.gp.getui().setCommandNumber(maxCommand-1);
         else
            this.gp.getui().setCommandNumber(this.gp.getui().getCommandNumber() - 1);
         }
      if (code == KeyEvent.VK_S) {
         if (this.gp.getui().getCommandNumber() == maxCommand-1)
            this.gp.getui().setCommandNumber(0);
         else
            this.gp.getui().setCommandNumber(this.gp.getui().getCommandNumber() + 1);
         }
   }
}