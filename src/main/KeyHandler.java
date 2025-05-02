package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); //returns number of the key that was pressed
        if(code == KeyEvent.VK_W)/*nếu ng dùng ấn nút W*/{
            upPressed=true;
        }
        if(code == KeyEvent.VK_S)/*nếu ng dùng ấn nút S*/{
            downPressed=true;
        }
        if(code == KeyEvent.VK_A)/*nếu ng dùng ấn nút A*/{
            leftPressed=true;
        }
        if(code == KeyEvent.VK_D)/*nếu ng dùng ấn nút D*/{
            rightPressed=true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode(); //returns number of the key that was pressed
        if(code == KeyEvent.VK_W)/*nếu ng dùng ấn nút W*/{
            upPressed=false;
        }
        if(code == KeyEvent.VK_S)/*nếu ng dùng ấn nút S*/{
            downPressed=false;
        }
        if(code == KeyEvent.VK_A)/*nếu ng dùng ấn nút A*/{
            leftPressed=false;
        }
        if(code == KeyEvent.VK_D)/*nếu ng dùng ấn nút D*/{
            rightPressed=false;
        }
    }
    
}
