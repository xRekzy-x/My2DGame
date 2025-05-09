package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
   public boolean upPressed;
   public boolean downPressed;
   public boolean leftPressed;
   public boolean rightPressed;
   public boolean zPressed = false;
   public boolean enterPressed=false;
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

   public void keyTyped(KeyEvent e) {
   }

   public void keyPressed(KeyEvent e) {
      int code = e.getKeyCode();
      if (this.gp.getGameState() == this.gp.getTitleState()) {
         if (code == KeyEvent.VK_W) {
            if (this.gp.getui().getCommandNumber() == 0) {
               this.gp.getui().setCommandNumber(3);
            }

            this.gp.getui().setCommandNumber(this.gp.getui().getCommandNumber() - 1);
         }

         if (code == KeyEvent.VK_S) {
            if (this.gp.getui().getCommandNumber() == 2) {
               this.gp.getui().setCommandNumber(-1);
            }

            this.gp.getui().setCommandNumber(this.gp.getui().getCommandNumber() + 1);
         }

         if (code == KeyEvent.VK_ENTER) {
            if (this.gp.getui().getCommandNumber() == 0) {
               this.gp.setGameState(this.gp.getPlayState());
            }

            if (this.gp.getui().getCommandNumber() == 2) {
               System.exit(0);
            }
         }
      }

      else if (this.gp.getGameState() == this.gp.getPlayState()) {
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
         if (code == KeyEvent.VK_R){
            gp.getPlayer().setAttacking(true);
         }
      } 
      else if (this.gp.getGameState() == this.gp.getPauseState()) {
         if (code == 80) {
            this.gp.setGameState(this.gp.getPlayState());
         }
      } else if (this.gp.getGameState() == this.gp.getDialogueState()) {
         if (code == 90) {
            this.gp.setGameState(this.gp.getPlayState());
         }

         if (code == KeyEvent.VK_ENTER) {
            this.enterPressed = true;
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
}