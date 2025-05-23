package entities;

import main.GamePanel;

public class Projectile extends Entity{
    Entity user;
    public Projectile(GamePanel gp) {
        super(gp);
    }
    public void set(int x, int y, String direction,boolean alive, Entity user ){
        setX(x);
        setY(y);
        setDirection(direction);
        setAlive(alive);
        this.user=user;
        setLife(getMaxLife());//reset the life to the max everytime user shoots it
    }
    public void update(){
        if(user==gp.getPlayer()){
            int monsterIndex=gp.getColCheckEntity(this, gp.getMonster());
            if(monsterIndex!=999){
                gp.getPlayer().damageMonster(monsterIndex,this.getDamage());
                setAlive(false);
                gp.getKey().setShotKeyPressed(false);
            }
        }
        else if(user!=gp.getPlayer()){
            boolean isPlayer = gp.getColCheckPlayer(this);
            if(isPlayer&&gp.getPlayer().getInvincible()==false){
                damagePlayer(getDamage());
                setAlive(false);
                gp.getPlayer().setInvincible(true);
            }
        }
        switch(getDirection()){
            case "up": case "sup": setY(getY()-getSpeed()); break;
            case "down": case "sdown": setY(getY()+getSpeed()); break;
            case "left": case "sleft": setX(getX()-getSpeed()); break;
            case "right": case "sright": setX(getX()+getSpeed()); break;
        }
        setLife(getLife()-1);
        if(getLife()<=0){
            setAlive(false);
            gp.getKey().setShotKeyPressed(false);
        }
    }
    public boolean enoughMana(Entity user){
        boolean enoughMana =false;
        if(this.getCost()<=user.getMana()) enoughMana=true;
        return enoughMana;
    }
}
