package entities;

import main.GamePanel;

public class NPC1 extends Entity {
    int dialogueIndex=0;
    public NPC1(GamePanel gp){
        super(gp);
        setDirection("sdown");
        super.setSpeed(1);
        super.setType(2);
        super.setFrame(2);
        super.setTransitionTime(10);
        addImage();
        setDialogue(); 
    }
    
    public void addImage(){

        sdown1 = setup("/NPC1/sdown1",2);
        sdown2 = setup("/NPC1/sdown2",2);
        
    }
    public void setDialogue(){
        gp.getui().setDialogue(0, "Hi sir! The world is being\ntaken over by monsters!");
        gp.getui().setDialogue(1, "You have to stop it!");
        gp.getui().setDialogue(2, "Items inside this chest is really \nvaluable but it is locked");
        gp.getui().setDialogue(3, "So you have to find a key around \nhere to unlock it and have \nyourself a a better gear\n Good Luck!");
    }
}
   