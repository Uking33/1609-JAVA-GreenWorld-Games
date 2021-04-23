import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ship class. This is the player character. It shoots, maneuvers, and - watch out - explodes.
 * 
 * @author Brian Myers 
 * @version 1.0
 */
public class Ship extends Sprite {   
    private int gunTimer;
    private int protectTimer;
    private Space parent;
    
    private int speed;
    public Ship(Space parent,int x,int y){
        super(x,y);
        this.gunTimer=0;
        this.protectTimer = 0;
        this.parent=parent;
        this.speed=4;
        setImage("rocket_mid.png");
    }
    public void setSpeed(int value){
        if(speed*value<=8 && speed*value>=2){
            speed*=value;
        }
    }
    public void act() 
    {
       checkKeys();
       handleShotCollision();       
    } 
   
    public void checkKeys()
    {
        if (Greenfoot.isKeyDown("up") && getY()-getImage().getHeight()/2>0){
            move(0,-speed);
        }
        else if (Greenfoot.isKeyDown("down") && getY()+getImage().getHeight()/2<getWorld().getHeight()){
            move(0,speed);
        }        
        if (Greenfoot.isKeyDown("left") && getX()-getImage().getWidth()/2>0){
            move(-speed,0);
        }
        else if (Greenfoot.isKeyDown("right") && getX()+getImage().getWidth()/2<getWorld().getWidth()){
            move(speed,0);
        }
        if (Greenfoot.isKeyDown("space") && (protectTimer <= 0) && (gunTimer <= 0)) {
            getWorld().addObject(new Laser(1,20), getX(), getY());
            Greenfoot.playSound("shot1.wav");
            gunTimer = 10;
        }
    }
    public void resurrect(){
        int x,y;
        int dx=0;
        x=50+Greenfoot.getRandomNumber(100-getImage().getWidth())+getImage().getWidth()/2;
        y=Greenfoot.getRandomNumber(400-getImage().getHeight())+getImage().getHeight()/2;
        setLocation(x,y);
        protectTimer=150;
    }
    public void handleShotCollision(){
       if(gunTimer>0)
        gunTimer --;
       if(protectTimer>0){
           protectTimer --;
           if(protectTimer%30<20)
            setImage("rocket_mid.png");
           else
            setImage("rocket_hide.png");
       } 
       if(protectTimer<=0 && 
       (getOneIntersectingObject(Enemy.class)!=null || 
       (getOneIntersectingObject(Laser.class)!=null&&getOneIntersectingObject(Laser.class).getType()>=2))){
            Actor laser = getOneIntersectingObject(Laser.class);
            getWorld().removeObject(laser);
            struckEffect();
            resurrect();
            parent.setData("life",-1);
       }      
    }
}
