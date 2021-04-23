import greenfoot.*;  
import java.lang.Math;
public class Enemy extends Sprite
{
    final static int STOP=0;
    final static int MOVE=1;
    final static int ATTACK1=2;
    final static int ATTACK2=3;
    final static int ATTACK3=4;
    final static float PI=3.141592653f;
    public int count = 0;
    private Space parent;
    private int type;
    private int speed;
    private int life;
    private int gunSpeed;
    private int gunTimer;
    private int score;
    private int angle;
    private int state;
    
    private int shotTimer;
    public Enemy(Space parent,int type)
    {
        super(0,0);
        this.parent=parent;
        this.type=type;
        switch(type){
            case 1:
                speed=3;
                life=1;
                gunSpeed=0;
                score=10;
                setImage("cruiser.gif");
                break;
            case 2:
                speed=3;
                life=3;
                gunSpeed=0;
                score=30;
                setImage("cruiser_red.gif");
                break;
            case 3:
                speed=6;
                life=1;
                gunSpeed=0;
                score=30;
                setImage("cruiser_green.gif");
                break;
            case 4:
                speed=3;
                life=1;
                gunSpeed=10;
                score=30;
                gunTimer=25;
                shotTimer=gunTimer;
                setImage("cruiser_blue.gif");
                break;
            case 11:
                speed=3;
                life=10+1*5;
                gunSpeed=5;
                score=100;
                gunTimer=100;
                shotTimer=gunTimer;
                setImage("saucer_grey.png");
                break;
            case 12:
                speed=3;
                life=10+2*5;
                gunSpeed=5;
                score=200;
                gunTimer=100;
                shotTimer=gunTimer;
                setImage("saucer_blue.png");
                break;
            case 13:
                speed=3;
                life=10+3*5;
                gunSpeed=5;
                score=300;
                gunTimer=100;
                shotTimer=gunTimer;
                setImage("saucer_red.png");
                break;
            case 14:
                speed=3;
                life=10+4*5;
                gunSpeed=5;
                score=400;
                gunTimer=100;
                shotTimer=gunTimer;
                setImage("saucer_green.png");
                break;
            case 15:
                speed=3;
                life=10+5*5;
                gunSpeed=5;
                score=500;
                gunTimer=100;
                shotTimer=gunTimer;
                setImage("saucer_colorful.png");
                break;
        }
    } 
  
    public void act() 
    {
        switch(type){
            case 1:
            case 2:
                move(-speed,0);
                break;
            case 3:{
                float x1=getX();
                float y1=getY();
                float x2=parent.ship.getX();
                float y2=parent.ship.getY();
                if(x1>x2){
                    angle=(int)(Math.atan((y1-y2)/(x1-x2))*180/PI);
                    setRotation(angle);
                    if(y1<y2)
                        move(-(int)(speed*Math.abs(Math.cos(angle*PI/180))),(int)(speed*Math.abs(Math.sin(angle*PI/180))));               
                    else
                        move(-(int)(speed*Math.abs(Math.cos(angle*PI/180))),-(int)(speed*Math.abs(Math.sin(angle*PI/180))));                  
                }
                else{
                    setRotation(0);
                    move(-speed,0);
                }
                break;
            }
            case 4:{
                float x1=getX();
                float y1=getY();
                float x2=parent.ship.getX();
                float y2=parent.ship.getY();
                if(shotTimer>0)
                    shotTimer--;
                if(shotTimer<=0){
                    if(x1>x2){//子弹
                        angle=(int)(Math.atan((y1-y2)/(x1-x2))*180/PI);
                        setRotation(angle);
                        Laser laser=new Laser(2,gunSpeed);
                        laser.setRotation(angle);
                        getWorld().addObject(laser, getX(), getY());
                        if(angle<0)
                            move(-(int)(speed*Math.abs(Math.cos(angle*PI/180))),(int)(speed*Math.abs(Math.sin(angle*PI/180))));               
                        else
                            move(-(int)(speed*Math.abs(Math.cos(angle*PI/180))),-(int)(speed*Math.abs(Math.sin(angle*PI/180)))); 
                        shotTimer = gunTimer;
                    }
                    else{//直线
                        shotTimer = 0;
                        if(angle>0)
                            angle-=1;
                        if(angle<0)
                            angle+=1;
                        setRotation(angle);
                        if(angle<0)
                            move(-(int)(speed*Math.abs(Math.cos(angle*PI/180))),(int)(speed*Math.abs(Math.sin(angle*PI/180))));               
                        else
                            move(-(int)(speed*Math.abs(Math.cos(angle*PI/180))),-(int)(speed*Math.abs(Math.sin(angle*PI/180)))); 
                    }
                }
                else{//移动
                    if(angle>0)
                        angle-=1;
                    if(angle<0)
                        angle+=1;
                    setRotation(angle);
                    if(angle<0)
                        move(-(int)(speed*Math.abs(Math.cos(angle*PI/180))),(int)(speed*Math.abs(Math.sin(angle*PI/180))));               
                    else
                        move(-(int)(speed*Math.abs(Math.cos(angle*PI/180))),-(int)(speed*Math.abs(Math.sin(angle*PI/180)))); 
                }
                break;
            }
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                if(getX()+getImage().getWidth()>getWorld().getWidth()){//出场
                    move(-speed,0);
                    return;
                }
                else{
                    if(shotTimer>0)
                        shotTimer--;
                    if(shotTimer>gunTimer/2){
                        //移动
                        if(getY()+getImage().getHeight()/2>=parent.getHeight())
                            speed=-speed;
                        if(getY()-getImage().getHeight()/2<0)
                            speed=-speed;
                        move(0,speed);
                    }
                    else if(shotTimer==gunTimer/2){
                        int level=type-10;
                        int num=level*2+5;
                        switch(level){
                            case 1:
                                state=ATTACK1;
                                break;
                            case 2:
                                state=ATTACK2;
                                break;
                            case 3:
                                state=Greenfoot.getRandomNumber(2)+2;
                                break;
                            case 4:
                                state=Greenfoot.getRandomNumber(2)+2;
                                if(state==ATTACK2)
                                    state=ATTACK3;
                                break;
                            case 5:
                                state=Greenfoot.getRandomNumber(3)+2;
                                break; 
                        }
                        //攻击
                        switch(state){
                            case ATTACK1:{
                                for(int i=0;i<num;i++){
                                    angle=120/num*i+135;
                                    Laser laser=new Laser(3,gunSpeed);
                                    laser.setRotation(angle);
                                    getWorld().addObject(laser, getX(), getY());
                                }
                                break;
                            }
                            case ATTACK2:
                                break;
                            case ATTACK3:{                                
                                Enemy enemy=new Enemy(parent,3);
                                parent.enemys.add(enemy);
                                int size=40;
                                enemy.getImage().scale(size,size);
                                parent.addObject(enemy, getX()+getImage().getWidth()/2,getY()+getImage().getHeight()+enemy.getImage().getHeight()/2);
                                break;
                            }
                        }
                    }
                    else if(shotTimer<gunTimer/2 && shotTimer>0){                        
                        switch(state){
                            case ATTACK1:
                                break;
                            case ATTACK2:{
                                    float x1=getX();
                                    float y1=getY();
                                    float x2=parent.ship.getX();
                                    float y2=parent.ship.getY();
                                    angle=(int)(Math.atan((y1-y2)/(x1-x2))*180/PI);
                                    Laser laser=new Laser(2,gunSpeed);
                                    laser.setRotation(angle);
                                    getWorld().addObject(laser, getX(), getY());
                                break;
                            }
                            case ATTACK3:
                                break;
                        }
                    }
                    else if(shotTimer<=0){
                        shotTimer=gunTimer;
                        state=MOVE;
                    }
                }
                break;
        }
        handleShotCollision();
    }      
    
    public void resurrect(){
        parent.enemys.remove(this);
        getWorld().removeObject(this);
    }
    public void destory(){    
        struckEffect();
        resurrect();
        parent.setData("score",score);
    }
    public void handleShotCollision(){
        if (getX() <= 0){
            parent.setData("score",-5);
            resurrect();
            parent.state_field.win();
        }
        else if(getOneIntersectingObject(Laser.class) != null && getOneIntersectingObject(Laser.class).getType()==1){
            Actor laser = getOneIntersectingObject(Laser.class);
            getWorld().removeObject(laser);
            if(--life <= 0)
                destory();
            parent.state_field.win();
        }
    }
}
