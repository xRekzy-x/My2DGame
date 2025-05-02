package entity;

import main.GamePanel;

public class npc extends Entity {
    public npc(GamePanel gp){
        super(gp);
        direction="down";
        speed=1; 
    }
}
