package main;

import entities.Entity;
import tile.Tile;

public class CollisionChecker {
   GamePanel gp;

   public CollisionChecker(GamePanel gp) {
      this.gp = gp;
   }

   public void checkTile(Entity entity) {
      int entityTop = entity.y + entity.getSolidAreaY();
      int entityDown = entityTop + entity.getSolidAreaHeight();
      int entityLeft = entity.x + entity.getSolidAreaX();
      int entityRight = entityLeft + entity.getSolidAreaWidth();
      int blockTop = entityTop / this.gp.getTileSize();
      int blockDown = entityDown / this.gp.getTileSize();
      int blockLeft = entityLeft / this.gp.getTileSize();
      int blockRight = entityRight / this.gp.getTileSize();
      int[][] mapTile = this.gp.getMapTile2();
      int[][] overlay = this.gp.getOverLay2();
      Tile[] tile = this.gp.getTile();
      int tileNum1;
      int tileNum2;
      switch(entity.getDirection()) {
      case "up":
            blockTop = (entityTop - entity.getSpeed()) / this.gp.getTileSize();
            tileNum1 = mapTile[blockTop][blockLeft];
            tileNum2 = mapTile[blockTop][blockRight];
            if (tile[tileNum1].getGetCollision() || tile[tileNum2].getGetCollision()) {
               entity.setCollisionOn(true);
            }

            tileNum1 = overlay[blockTop][blockLeft];
            tileNum2 = overlay[blockTop][blockRight];
            if (tile[tileNum1].getGetCollision() || tile[tileNum2].getGetCollision()) {
               entity.setCollisionOn(true);
            }

         break;
      case "down":
            blockDown = (entityDown + entity.getSpeed()) / this.gp.getTileSize();
            tileNum1 = mapTile[blockDown][blockLeft];
            tileNum2 = mapTile[blockDown][blockRight];
            if (tile[tileNum1].getGetCollision() || tile[tileNum2].getGetCollision()) {
               entity.setCollisionOn(true);
            }

            tileNum1 = overlay[blockDown][blockLeft];
            tileNum2 = overlay[blockDown][blockRight];
            if (tile[tileNum1].getGetCollision() || tile[tileNum2].getGetCollision()) {
               entity.setCollisionOn(true);
            }
         break;
      case "left":
            blockLeft = (entityLeft - entity.getSpeed()) / this.gp.getTileSize();
            tileNum1 = mapTile[blockTop][blockLeft];
            tileNum2 = mapTile[blockDown][blockLeft];
            if (tile[tileNum1].getGetCollision() || tile[tileNum2].getGetCollision()) {
               entity.setCollisionOn(true);
            }

            tileNum1 = overlay[blockTop][blockLeft];
            tileNum2 = overlay[blockDown][blockLeft];
            if (tile[tileNum1].getGetCollision() || tile[tileNum2].getGetCollision()) {
               entity.setCollisionOn(true);
            }
         break;
      case "right":
            blockRight = (entityRight + entity.getSpeed()) / this.gp.getTileSize();
            tileNum1 = mapTile[blockTop][blockRight];
            tileNum2 = mapTile[blockDown][blockRight];
            if (tile[tileNum1].getGetCollision() || tile[tileNum2].getGetCollision()) {
               entity.setCollisionOn(true);
            }

            tileNum1 = overlay[blockTop][blockRight];
            tileNum2 = overlay[blockDown][blockRight];
            if (tile[tileNum1].getGetCollision() || tile[tileNum2].getGetCollision()) {
               entity.setCollisionOn(true);
            }
            break;
         }
         
   }

   public int checkObj(Entity entity, boolean isPlayer) {
      int index = 999;
      for(int i = 0; i < this.gp.getObjLength(); ++i) {
         if (this.gp.getObj(i) != null) {
            entity.setSolidAreaX(entity.getX() + entity.getSolidAreaX());
            entity.setSolidAreaY(entity.getY() + entity.getSolidAreaY());
            this.gp.getObj(i).setSolidAreaX(this.gp.getObj(i).getX() + this.gp.getObj(i).getSolidAreaX());
            this.gp.getObj(i).setSolidAreaY(this.gp.getObj(i).getY() + this.gp.getObj(i).getSolidAreaY());
            switch(entity.getDirection()) {
            case "up": entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed()); break;
            case "down": entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed()); break;
            case "left": entity.setSolidAreaX(entity.getSolidAreaX() - entity.getSpeed());break;
            case "right": entity.setSolidAreaX(entity.getSolidAreaX() + entity.getSpeed());break;
            }
            if (entity.getSolidArea().intersects(this.gp.getObj(i).getSolidArea())) {
               if (this.gp.getObj(i).getCollision()) {
                  entity.setCollisionOn(true);
               }
               if (isPlayer) {index = i;}
            }
            entity.setSolidAreaX(entity.getSolidAreaDefaultX());
            entity.setSolidAreaY(entity.getSolidAreaDefaultY());
            this.gp.getObj(i).setSolidAreaX(this.gp.getObj(i).getSolidAreaDefaultX());
            this.gp.getObj(i).setSolidAreaY(this.gp.getObj(i).getSolidAreaDefaultY());
         }
      }

      return index;
   }

   public int checkEntity(Entity entity, Entity[] target) {
      int index = 999;

      for(int i = 0; i < target.length; i++) {
         if (target[i] != null) {
            if(gp.getPlayer().getAttacking()){
               entity.setSolidAreaX(entity.getAttackX() + entity.getSolidAreaX());
               entity.setSolidAreaY(entity.getAttackY() + entity.getSolidAreaY());
            }
            else if(gp.getPlayer().getAttacking()==false){
               entity.setSolidAreaX(entity.getX() + entity.getSolidAreaX());
               entity.setSolidAreaY(entity.getY() + entity.getSolidAreaY());
            }
            target[i].setSolidAreaX(target[i].getX() + target[i].getSolidAreaX());
            target[i].setSolidAreaY(target[i].getY() + target[i].getSolidAreaY());
            switch(entity.getDirection()) {
            case "up": entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed()); break;
            case "down": entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed()); break;
            case "left":entity.setSolidAreaX(entity.getSolidAreaX() - entity.getSpeed()); break;
            case "right": entity.setSolidAreaX(entity.getSolidAreaX() + entity.getSpeed());break;
            }
            if (entity.getSolidArea().intersects(target[i].getSolidArea())) {
               if(entity!= target[i]){
                  entity.setCollisionOn(true);
                  index = i;
               }
            }
            entity.setSolidAreaX(entity.getSolidAreaDefaultX());
            entity.setSolidAreaY(entity.getSolidAreaDefaultY());
            target[i].setSolidAreaX(target[i].getSolidAreaDefaultX());
            target[i].setSolidAreaY(target[i].getSolidAreaDefaultY());
         }
      }
      return index;
   }
   public boolean checkPlayer(Entity entity) {
      boolean contactPlayer=false;
      this.gp.getPlayer().setSolidAreaX(this.gp.getPlayer().getX() + this.gp.getPlayer().getSolidAreaX());
      this.gp.getPlayer().setSolidAreaY(this.gp.getPlayer().getY() + this.gp.getPlayer().getSolidAreaY());
      entity.setSolidAreaX(entity.getX() + entity.getSolidAreaX());
      entity.setSolidAreaY(entity.getY() + entity.getSolidAreaY());
      switch(entity.getDirection()) {
      case "up": entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed());break;
      case "down": entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed());break;
      case "left": entity.setSolidAreaX(entity.getSolidAreaX() - entity.getSpeed());break;
      case "right": entity.setSolidAreaX(entity.getSolidAreaX() + entity.getSpeed());
      }
      if (entity.getSolidArea().intersects(this.gp.getPlayer().getSolidArea())) {
         entity.setCollisionOn(true);
         contactPlayer=true;
      }
      entity.setSolidAreaX(entity.getSolidAreaDefaultX());
      entity.setSolidAreaY(entity.getSolidAreaDefaultY());
      this.gp.getPlayer().setSolidAreaX(this.gp.getPlayer().getSolidAreaDefaultX());
      this.gp.getPlayer().setSolidAreaY(this.gp.getPlayer().getSolidAreaDefaultY());
      return contactPlayer;
   }

   public int checkInteract(Entity entity, Entity[] target) {
      int index = 999;

      for(int i = 0; i < target.length; ++i) {
         if (target[i] != null) {
               entity.setSolidAreaX(entity.getX() + entity.getSolidAreaX());
               entity.setSolidAreaY(entity.getY() + entity.getSolidAreaY());
               target[i].setTriggerInteractX(target[i].getX() + target[i].getTriggerInteractX());
               target[i].setTriggerInteractY(target[i].getY() + target[i].getTriggerInteractY());
               switch(entity.getDirection()) {
               case "up":entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed());break;
               case "down": entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed());break;
               case "left": entity.setSolidAreaX(entity.getSolidAreaX() - entity.getSpeed());break;
               case "right": entity.setSolidAreaX(entity.getSolidAreaX() + entity.getSpeed()); break;
               }
               if (entity.getSolidArea().intersects(target[i].getTriggerInteract())) index = i;

            entity.setSolidAreaX(entity.getSolidAreaDefaultX());
            entity.setSolidAreaY(entity.getSolidAreaDefaultY());
            target[i].setTriggerInteractX(target[i].getTriggerInteractDefaultX());
            target[i].setTriggerInteractY(target[i].getTriggerInteractDefaultY());
         }
      }
      return index;
   }
}