import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stars here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stars  extends Actor
{
    /**
     * Act - do whatever the Stars wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Stars() 
    { 
      int x = 2;
      int y = 2;
      GreenfootImage star = new GreenfootImage(4, 4);
      star.setColor(Color.WHITE);
      star.fillOval(1,1, 4, 4);
      setImage(star);
    }    
    
    public void act()
    {
        
     setLocation (getX() - 1, getY()); 
      
     if (getX() <= 5)
     {
      setLocation(getWorld().getWidth(), Greenfoot.getRandomNumber(getWorld().getHeight())); 
      
     }
     
     
    }
    
    
}
