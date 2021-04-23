import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

public class Laser extends Sprite
{
    private int type;
    private int speed;
    final float PI=3.141592653f;
    public Laser(int type,int speed)
    {
        super(0,0);
        this.type=type;
        this.speed=speed;
        GreenfootImage laser = new GreenfootImage(20, 5);
        switch(type){
          case 1:
              laser.setColor(Color.RED);
              laser.fillOval(1,1, 6, 5);
              laser.setColor(Color.YELLOW);
              laser.fillRect(7,1, 20, 5);
              break;
          case 2:
              laser.setColor(Color.WHITE);
              laser.fillOval(1,1, 6, 5);
              laser.setColor(Color.BLUE);
              laser.fillRect(7,1, 20, 5);
              break;
          case 3:
              laser.setColor(Color.WHITE);
              laser.fillOval(1,1, 6, 5);
              laser.setColor(Color.BLUE);
              laser.fillRect(7,1, 20, 5);
              break;
        }
        setImage(laser);        
    }
    public int getType(){
        return type;
    }
    public void act(){
        int angle;
        switch(type){
            case 1:
                move(speed,0);
                break;
            case 2:
                angle=getRotation();
                if(angle<90)
                    move(-(int)(speed*Math.abs(Math.cos(angle*PI/180))),-(int)(speed*Math.abs(Math.sin(angle*PI/180))));               
                else
                    move(-(int)(speed*Math.abs(Math.cos(angle*PI/180))),(int)(speed*Math.abs(Math.sin(angle*PI/180))));
                break;
            case 3:
                angle=getRotation();
                if(angle<180)
                    move(-(int)(speed*Math.abs(Math.cos(angle*PI/180))),(int)(speed*Math.abs(Math.sin(angle*PI/180))));               
                else
                    move(-(int)(speed*Math.abs(Math.cos(angle*PI/180))),-(int)(speed*Math.abs(Math.sin(angle*PI/180))));
                break;
        }
        handleShotCollision();
    }  
    
    public void handleShotCollision(){
        if(this!=null && (getX()>=getWorld().getWidth()-1 || (getY()>=getWorld().getHeight()-1) || (getY()<=0) || (getX()<=0))){
            getWorld().removeObject(this);
        }
    }
}
