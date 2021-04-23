import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Write a description of class Laser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Laser extends Sprite
{

  public Laser(int type)
  {
      super(0,0);  
      GreenfootImage laser = new GreenfootImage(21, 21);
      laser.setColor(Color.GREEN);
      if(type==1)
        laser.fillRect(1,1, 21, 10);
      else if(type==2)
        laser.fillRect(1,1, 10, 21);
      else if(type==3)
        laser.fillRect(1,1, 10, 10);
      else if(type==4)
        laser.fillRect(1,10, 10, 21);
      setImage(laser);
  }
  
    /**
     * Act - do whatever the Laser wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){       
        move(-20,0);
        handleShotCollision();
    }  
    
    public void handleShotCollision(){
        if(this!=null && getX()<=0){
            getWorld().removeObject(this);
        }
    }
}
