package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Event {
    GamePanel gp;
    EventZone eventZone[][];
    
    int previousEventX,previousEventY;
    boolean canTouchEvent = true;
    int healingCount=0;
    public Event(GamePanel gp){
        this.gp=gp;
        eventZone = new EventZone[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
        int col=0;
        int row=0;
        while(col<gp.getMaxWorldCol()&&row<gp.getMaxWorldRow()){
            eventZone[col][row] = new EventZone();
            eventZone[col][row].x = 23;
            eventZone[col][row].y = 23;
            eventZone[col][row].width = 2;
            eventZone[col][row].height = 2;
            eventZone[col][row].eventZoneDefaultX=eventZone[col][row].x;
            eventZone[col][row].eventZoneDefaultY=eventZone[col][row].y;
            col++;
            if(col==gp.getMaxWorldCol()){
                row++;
                col=0;
            }
        }
       


    }
    //GETTER SETTER
    // public Rectangle getEventZone(){return eventZone;}
    // public void setEventZone(Rectangle eventZone){this.eventZone=eventZone;}
    // public int getEventZoneX(){ return eventZone.x;}
    // public void setEventZoneX(int x){this.eventZone.x=x;}
    // public int getEventZoneY(){ return eventZone.y;}
    // public void setEventZoneY(int y){this.eventZone.y=y;}
    // public int getEventZoneDefaultX(){return eventZoneDefaultX;}
    // public int getEventZoneDefaultY(){return eventZoneDefaultY;}

    public void checkEvent(){
    
        //Check if the player is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.getPlayerX()-previousEventX);   
        int yDistance = Math.abs(gp.getPlayerY()-previousEventY);
        int distance = Math.max(xDistance,yDistance);//pick the greater one between 2
        if(distance > gp.getTileSize()) canTouchEvent=true;
        if(hit(12,4,"any")==true){Healing(gp.getDialogueState());}
        if(canTouchEvent){   
            if(hit(1,7,"any")==true){damagePit(gp.getDialogueState());} 
        }
        
    }
    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;
        gp.getPlayer().setSolidAreaX(gp.getPlayerX()+gp.getPlayer().getSolidAreaX());
        gp.getPlayer().setSolidAreaY(gp.getPlayerY()+gp.getPlayer().getSolidAreaY());
        eventZone[col][row].x= col*gp.getTileSize()+eventZone[col][row].x;
        eventZone[col][row].y= row*gp.getTileSize()+eventZone[col][row].y;
        if(gp.getPlayer().getSolidArea().intersects(eventZone[col][row])){
            if(gp.getPlayer().getDirection().contentEquals(reqDirection)||reqDirection.contentEquals("any")){
                hit = true;
                previousEventX = gp.getPlayerX();
                previousEventY = gp.getPlayerY();
            }
        }
        eventZone[col][row].x=eventZone[col][row].eventZoneDefaultX;
        eventZone[col][row].y=eventZone[col][row].eventZoneDefaultY;
        gp.getPlayer().setSolidAreaX(gp.getPlayer().getSolidAreaDefaultX());
        gp.getPlayer().setSolidAreaY(gp.getPlayer().getSolidAreaDefaultY());
        return hit;
    }
    public void damagePit(int gameState){
        gp.setGameState(gameState);
        gp.getui().setCurrentDialogue("RƠI MẸ VÀO HỐ RỒI");
        gp.getPlayer().setLife(gp.getPlayer().getLife()-1);
        canTouchEvent=false;
    }
    public void Healing(int gameState){
        if(gp.getKey().getEnterPressed()){ 
            gp.setGameState(gameState);
            gp.getui().setCurrentDialogue("Drinking....\nHealing...");
            healingCount++;
            if(healingCount==1){
                gp.getPlayer().setLife(gp.getPlayer().getLife()+1);
                healingCount=0;
            } 
        }
    }
    public void debug(Graphics2D g2){
        g2.setColor(Color.blue);
        g2.fillRect(eventZone[0][0].eventZoneDefaultX+1*48-gp.getPlayerX()+gp.getPlayerScreenX(), eventZone[0][0].eventZoneDefaultY+7*48-gp.getPlayerY()+gp.getPlayerScreenY(), eventZone[0][0].width, eventZone[0][0].height);
        g2.fillRect(eventZone[0][0].eventZoneDefaultX+12*48-gp.getPlayerX()+gp.getPlayerScreenX(), eventZone[0][0].eventZoneDefaultY+4*48-gp.getPlayerY()+gp.getPlayerScreenY(), eventZone[0][0].width, eventZone[0][0].height);
    }

}
