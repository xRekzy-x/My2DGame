package main;

import entity.Entity;
import objects.Chest;
import objects.SuperObj;
import tile.Tile;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Entity entity){
       
        int entityTop = entity.y+entity.getSolidAreaY();
        int entityDown = entityTop+entity.getSolidAreaHeight(); 
        int entityLeft = entity.x+entity.getSolidAreaX();
        int entityRight = entityLeft+entity.getSolidAreaWidth();

        int blockTop = entityTop/gp.getTileSize();
        int blockDown = entityDown/gp.getTileSize();
        int blockLeft = entityLeft/gp.getTileSize();
        int blockRight = entityRight/gp.getTileSize();

        int tileNum1, tileNum2;
        int mapTile[][] = gp.getMapTile();
        int overlay[][] = gp.getOverLay();
        Tile tile[] = gp.getTile();
        switch(entity.direction){
            case "up":
                blockTop=(entityTop-entity.speed)/gp.getTileSize();//lấy vị trí block trước mặt
                tileNum1= mapTile[blockTop][blockLeft];
                tileNum2= mapTile[blockTop][blockRight];
                if(tile[tileNum1].getGetCollision()==true||tile[tileNum2].getGetCollision()==true){
                    entity.setCollisionOn(true);
                }
                tileNum1= overlay[blockTop][blockLeft];
                tileNum2= overlay[blockTop][blockRight];
                if(tile[tileNum1].getGetCollision()==true||tile[tileNum2].getGetCollision()==true){
                    entity.setCollisionOn(true);
                }
                break;    
            case "down":
                blockDown=(entityDown+entity.speed)/gp.getTileSize();//lấy vị trí block trước mặt
                tileNum1= mapTile[blockDown][blockLeft];
                tileNum2=mapTile[blockDown][blockRight];
                if(tile[tileNum1].getGetCollision()==true||tile[tileNum2].getGetCollision()==true){
                    entity.setCollisionOn(true);
                }
                tileNum1= overlay[blockDown][blockLeft];
                tileNum2= overlay[blockDown][blockRight];
                if(tile[tileNum1].getGetCollision()==true||tile[tileNum2].getGetCollision()==true){
                    entity.setCollisionOn(true);
                }
                break; 
            case "left":
                blockLeft=(entityLeft-entity.speed)/gp.getTileSize();//lấy vị trí block trước mặt
                tileNum1= mapTile[blockTop][blockLeft];
                tileNum2=mapTile[blockDown][blockLeft];
                if(tile[tileNum1].getGetCollision()==true||tile[tileNum2].getGetCollision()==true){
                    entity.setCollisionOn(true);
                }
                tileNum1= overlay[blockDown][blockLeft];
                tileNum2= overlay[blockDown][blockRight];
                if(tile[tileNum1].getGetCollision()==true||tile[tileNum2].getGetCollision()==true){
                    entity.setCollisionOn(true);
                }
                break; 
            case "right":
                blockRight=(entityRight+entity.speed)/gp.getTileSize();//lấy vị trí block trước mặt
                tileNum1= mapTile[blockTop][blockRight];
                tileNum2=mapTile[blockDown][blockRight];
                if(tile[tileNum1].getGetCollision()==true||tile[tileNum2].getGetCollision()==true){
                    entity.setCollisionOn(true);
                }
                tileNum1= overlay[blockDown][blockLeft];
                tileNum2= overlay[blockDown][blockRight];
                if(tile[tileNum1].getGetCollision()==true||tile[tileNum2].getGetCollision()==true){
                    entity.setCollisionOn(true);
                }
                break; 
        }
    }
    //không dùng intersect để check tile là do nếu dùng phải check từng ô một trên map trong khi cách kia chỉ cần check 2 ô
    public int checkObj(Entity entity, boolean isPlayer){
        int index = 999;
        for(int i=0;i<gp.getObjLength();i++){
            if(gp.getObj(i)!=null){
                //GET ENTITY'S SOLID AREA POSITION
                //MOVE THE POSITION OF THE SOLIDAREA TO THE ACTUALY WORLD'S COORDINATE
                entity.setSolidAreaX(entity.getX()+entity.getSolidAreaX());
                entity.setSolidAreaY(entity.getY()+entity.getSolidAreaY());
                //GET THE OBJECT'S SOLID AREA POSITION
                gp.getObj(i).setSolidAreaX(gp.getObj(i).getX()+gp.getObj(i).getSolidAreaX());
                gp.getObj(i).setSolidAreaY(gp.getObj(i).getY()+gp.getObj(i).getSolidAreaY());
                switch(entity.direction){
                    case "up":
                        entity.setSolidAreaY(entity.getSolidAreaY()-entity.getSpeed());
                        if(entity.getSolidArea().intersects(gp.getObj(i).getSolidArea())){
                            if(gp.getObj(i).getCollision()==true) entity.setCollisionOn(true);
                            if(isPlayer) index=i;
                        }        
                        break;
                    case "down":
                        entity.setSolidAreaY(entity.getSolidAreaY()+entity.getSpeed());
                        if(entity.getSolidArea().intersects(gp.getObj(i).getSolidArea())){
                            if(gp.getObj(i).getCollision()==true) entity.setCollisionOn(true);
                            if(isPlayer) index=i;
                        }
                        break;
                    case "left":
                        entity.setSolidAreaX(entity.getSolidAreaX()-entity.getSpeed());
                        if(entity.getSolidArea().intersects(gp.getObj(i).getSolidArea())){
                            if(gp.getObj(i).getCollision()==true) entity.setCollisionOn(true);
                            if(isPlayer) index=i;
                        }  
                        break;
                    case "right":
                        entity.setSolidAreaX(entity.getSolidAreaX()+entity.getSpeed());
                        if(entity.getSolidArea().intersects(gp.getObj(i).getSolidArea())){
                            if(gp.getObj(i).getCollision()==true) entity.setCollisionOn(true);
                            if(isPlayer) index=i;
                        }  
                        break;
                }
                entity.setSolidAreaX(entity.getSolidAreaDefaultX());//reset lại solidArea 
                entity.setSolidAreaY(entity.getSolidAreaDefaultY());
                gp.getObj(i).setSolidAreaX(gp.getObj(i).getSolidAreaDefaultX());
                gp.getObj(i).setSolidAreaY(gp.getObj(i).getSolidAreaDefaultY());
            }
        }
        return index;
    }
    public int checkEntity(Entity entity,Entity[] target){
        int index = 999;
        for(int i=0;i<target.length;i++){
            if(target[i]!=null){
                //GET ENTITY'S SOLID AREA POSITION
                entity.setSolidAreaX(entity.getX()+entity.getSolidAreaX());
                entity.setSolidAreaY(entity.getY()+entity.getSolidAreaY());
                //GET THE TARGET'S SOLID AREA POSITION
                target[i].setSolidAreaX(target[i].getX()+target[i].getSolidAreaX());
                target[i].setSolidAreaY(target[i].getY()+target[i].getSolidAreaY());
                switch(entity.direction){
                    case "up":
                        entity.setSolidAreaY(entity.getSolidAreaY()-entity.getSpeed());
                        if(entity.getSolidArea().intersects(target[i].getSolidArea())){
                            entity.setCollisionOn(true);
                            index=i;
                        }        
                        break;
                    case "down":
                        entity.setSolidAreaY(entity.getSolidAreaY()+entity.getSpeed());
                        if(entity.getSolidArea().intersects(target[i].getSolidArea())){
                            entity.setCollisionOn(true);
                            index=i;
                        }
                        break;
                    case "left":
                        entity.setSolidAreaX(entity.getSolidAreaX()-entity.getSpeed());
                        if(entity.getSolidArea().intersects(target[i].getSolidArea())){
                            entity.setCollisionOn(true);
                            index=i;
                        }  
                        break;
                    case "right":
                        entity.setSolidAreaX(entity.getSolidAreaX()+entity.getSpeed());
                        if(entity.getSolidArea().intersects(target[i].getSolidArea())){
                            entity.setCollisionOn(true);
                            index=i;
                        }  
                        break;
                }
                entity.setSolidAreaX(entity.getSolidAreaDefaultX());//reset lại solidArea 
                entity.setSolidAreaY(entity.getSolidAreaDefaultY());
                target[i].setSolidAreaX(target[i].getSolidAreaDefaultX());
                target[i].setSolidAreaY(target[i].getSolidAreaDefaultY());
            }
        }
        return index;
    }
    public void checkPlayer(Entity entity){
         //GET ENTITY'S SOLID AREA POSITION
         gp.getPlayer().setSolidAreaX(gp.getPlayer().getX()+gp.getPlayer().getSolidAreaX());
         gp.getPlayer().setSolidAreaY(gp.getPlayer().getY()+gp.getPlayer().getSolidAreaY());
         //GET THE TARGET'S SOLID AREA POSITION
         entity.setSolidAreaX(entity.getX()+entity.getSolidAreaX());
         entity.setSolidAreaY(entity.getY()+entity.getSolidAreaY());
         switch(entity.direction){
             case "up":
                 entity.setSolidAreaY(entity.getSolidAreaY()-entity.getSpeed());
                 if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea())){
                     entity.setCollisionOn(true);
                 }        
                 break;
             case "down":
                 entity.setSolidAreaY(entity.getSolidAreaY()+entity.getSpeed());
                 if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea())){
                     entity.setCollisionOn(true);
                 }
                 break;
             case "left":
                 entity.setSolidAreaX(entity.getSolidAreaX()-entity.getSpeed());
                 if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea())){
                     entity.setCollisionOn(true);
                 }  
                 break;
             case "right":
                 entity.setSolidAreaX(entity.getSolidAreaX()+entity.getSpeed());
                 if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea())){
                     entity.setCollisionOn(true);
                 }  
                 break;
         }
         entity.setSolidAreaX(entity.getSolidAreaDefaultX());//reset lại solidArea 
         entity.setSolidAreaY(entity.getSolidAreaDefaultY());
         gp.getPlayer().setSolidAreaX(gp.getPlayer().getSolidAreaDefaultX());
         gp.getPlayer().setSolidAreaY(gp.getPlayer().getSolidAreaDefaultY());
    }
    
    public int checkInteract(Entity entity,Entity[] target){
        int index = 999;
        for(int i=0;i<target.length;i++){
            if(target[i]!=null){
                //GET ENTITY'S SOLID AREA POSITION
                entity.setSolidAreaX(entity.getX()+entity.getSolidAreaX());
                entity.setSolidAreaY(entity.getY()+entity.getSolidAreaY());
                //GET THE TARGET'S SOLID AREA POSITION
                target[i].setTriggerInteractX(target[i].getX()+target[i].getTriggerInteractX());
                target[i].setTriggerInteractY(target[i].getY()+target[i].getTriggerInteractY());
                switch(entity.direction){
                    case "up":
                        entity.setSolidAreaY(entity.getSolidAreaY()-entity.getSpeed());
                        if(entity.getSolidArea().intersects(target[i].getTriggerI��� �c�   uQm6                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                SQLite format 3   @                                                                       ._                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 