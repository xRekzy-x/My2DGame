package main;

import javax.swing.JFrame;
//x,y: tọa độ x và y của entity và object trong thế giới
//screenX, screenY: tọa độ của entity và object trong màn hình TOTALLY DIFFERENT FROM X,Y
public class GamePlay {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// this lets the window properly close when user clicks
                                                              // the button X 
        window.setResizable(false);
        window.setTitle("_xOas");

        GamePanel gamepanel =GamePanel.getInstance();
        window.add(gamepanel);
        window.pack();//cause this window to be sized to fit the preferred size and layouts of its subcomponents(gamepanel)

        window.setLocationRelativeTo(null);// the window will be displayed at the center of screen
        window.setVisible(true);//so we can see this window. 


        gamepanel.setupGame();//prepare the setup for the game 
        gamepanel.startGameThread();//run game 
    }
}
