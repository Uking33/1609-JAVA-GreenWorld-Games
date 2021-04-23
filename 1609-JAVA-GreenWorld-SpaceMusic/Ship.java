import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ship class. This is the player character. It shoots, maneuvers, and - watch out - explodes.
 * 
 * @author Brian Myers 
 * @version 1.0
 */
public class Ship extends Sprite {
    private int startTimer;
   private boolean isRun;
    private int fps;
    private int laserTimer;
    private int laserX;
    private int laserY;
    private int laserWidth;
    private int laserChangeNum;
    private int laserType;
    private Space space;
    
    public Ship(Space space,int fps,int x,int y){
        super(x,y);
        this.fps=fps;
        this.space=space;
        isRun=false;
        startTimer=50;
        laserX=1280;
        laserY=360;
        laserTimer=fps-1;
        laserType=1;
        laserWidth=110;
        laserChangeNum=0; 
    }
    public void stop(){
        isRun=false;
        startTimer=-1;
    }
    public void act() 
    {
        if(isRun){
            createLaser();
            handleShotCollision();
        }
        else{
            if(startTimer>=0){
                createLaser();
                startTimer--;
                if(startTimer==0)
                    isRun=true;
            }
        }
        if(startTimer<=0)
            checkKeys();
    }
    public void createLaser(){
        laserTimer--;
        if(laserTimer==0){
            laserType=Greenfoot.getRandomNumber(2)+1; 
            laserTimer=fps;
            switch(laserType){
                case 1:
                    laserChangeNum=0; 
                    getWorld().addObject(new Laser(1),laserX,laserY-laserWidth);
                    getWorld().addObject(new Laser(1),laserX,laserY+laserWidth);
                    break;
                case 2:
                    //Num
                    laserChangeNum=Greenfoot.getRandomNumber(5)+5; 
                    //Way                    
                    int t=Greenfoot.getRandomNumber(2); 
                    if(laserY+laserChangeNum*20+laserWidth/2>=getWorld().getHeight()-85)
                        laserChangeNum=-laserChangeNum;
                    else if(laserY-laserChangeNum*20-laserWidth/2<=100)
                        laserChangeNum=laserChangeNum;
                    else{
                        if(t==1)
                            laserChangeNum=laserChangeNum;
                        else
                            laserChangeNum=-laserChangeNum;
                    }
                    if(laserChangeNum>0){
                        getWorld().addObject(new Laser(1),laserX,laserY-laserWidth);
                        for(int i=0;i<Math.abs(laserChangeNum);i++)
                            getWorld().addObject(new Laser(2),laserX,laserY+laserWidth+i*20);                     
                        getWorld().addObject(new Laser(1),laserX,laserY+laserWidth+laserChangeNum*20);
                    }
                    else{
                        getWorld().addObject(new Laser(1),laserX,laserY+laserWidth);
                        
                        getWorld().addObject(new Laser(3),laserX,laserY-laserWidth);     
                        for(int i=1;i<Math.abs(laserChangeNum);i++)
                            getWorld().addObject(new Laser(2),laserX,laserY-laserWidth-i*20);
                        getWorld().addObject(new Laser(4),laserX,laserY-laserWidth+laserChangeNum*20);
                        getWorld().addObject(new Laser(1),laserX,laserY-laserWidth+laserChangeNum*20);
                    }
                    break;                
            }
        }
        else{            
            switch(laserType){
                case 1:
                    getWorld().addObject(new Laser(1),laserX,laserY-laserWidth);
                    getWorld().addObject(new Laser(1),laserX,laserY+laserWidth);
                    break;
                case 2:
                    if(laserTimer>50-2*Math.abs(laserChangeNum)){
                        if(laserChangeNum>0){
                            getWorld().addObject(new Laser(1),laserX,laserY-laserWidth);
                            getWorld().addObject(new Laser(1),laserX,laserY+laserWidth+laserChangeNum*20);
                        }
                        else{
                            getWorld().addObject(new Laser(1),laserX,laserY+laserWidth);
                            getWorld().addObject(new Laser(1),laserX,laserY-laserWidth+laserChangeNum*20);
                        }
                    }
                    else if(laserTimer==50-2*Math.abs(laserChangeNum)){
                        if(laserChangeNum>0){
                            for(int i=0;i<Math.abs(laserChangeNum);i++)
                                getWorld().addObject(new Laser(2),laserX,laserY-laserWidth+i*20);
                            getWorld().addObject(new Laser(1),laserX,laserY-laserWidth+laserChangeNum*20);
                            getWorld().addObject(new Laser(1),laserX,laserY+laserWidth+laserChangeNum*20);
                        }
                        else{                            
                            getWorld().addObject(new Laser(1),laserX,laserY-laserWidth+laserChangeNum*20);
                            
                            getWorld().addObject(new Laser(3),laserX,laserY+laserWidth);
                            for(int i=1;i<Math.abs(laserChangeNum);i++)
                                getWorld().addObject(new Laser(2),laserX,laserY+laserWidth-i*20);
                            getWorld().addObject(new Laser(4),laserX,laserY+laserWidth+laserChangeNum*20);                            
                            getWorld().addObject(new Laser(1),laserX,laserY+laserWidth+laserChangeNum*20);                            
                        }
                        laserY+=laserChangeNum*20;
                    }
                    else{
                        getWorld().addObject(new Laser(1),laserX,laserY-laserWidth);
                        getWorld().addObject(new Laser(1),laserX,laserY+laserWidth);
                    }
                    break;
            }
        }
    }
   
    public void checkKeys()
    {
        if (Greenfoot.isKeyDown("up")){
            move(0,-10);
        }
        
        if (Greenfoot.isKeyDown("down")){
            move(0,10);
        }
    }
    public void handleShotCollision(){
       if(getOneIntersectingObject(Laser.class)!=null){           
           struckEffect();
           space.gameOver(false);
           getWorld().removeObject(this);
       }
    }
}
