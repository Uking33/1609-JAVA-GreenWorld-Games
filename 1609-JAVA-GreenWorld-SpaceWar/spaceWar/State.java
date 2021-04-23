import greenfoot.*;
import java.awt.Color;
import java.applet.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class State extends Actor
{
    Font font1=new Font("Helvetica",Font.BOLD,25);
    private int life;
    private int score;
    private int width;
    private int nextTime;
    private int nextTimer;
    private int level;
    private boolean isRun;
    private Space parent;
    private int[] enemyNum;
    private List<Enemy> enemys;
    public State(Space parent,int width,int nextTime,List<Enemy> enemys)
    {
        score=0;
        life=3;
        level=0;
        this.parent=parent;
        this.width=width;
        this.nextTime=nextTime;
        this.nextTimer=nextTime;
        this.enemys=enemys;
        
        //level
        enemyNum=new int[5];
        nextLevel();
        newEnemy();
        
        GreenfootImage img_field = new GreenfootImage(width,50);
        img_field.setFont(font1);
        img_field.setColor (Color.white);
        setImage(img_field);
        setText();
        Start();
    }
    public void act() 
    {
        if(isRun){
            //刷敌人
            if(nextTimer>0)
                nextTimer--;
            if(nextTimer==0){
                newEnemy();
                nextTimer=nextTime;
            }
        }
        else
            checkKeys();
    }
    public void checkKeys()
    {
        if (Greenfoot.isKeyDown("R") ) {
            reInit();
        }
    }
    public void Stop(){
        isRun=false;
    }    
    public void Start(){
        isRun=true;
    }
    public void reTime(){
        nextTimer=nextTime;
    }
    public void reInit(){
        parent.re();
        this.enemys=parent.enemys;
        score=0;
        life=3;
        level=0;
        nextTimer=nextTime;
        //level
        nextLevel();
        newEnemy();
        setText();        
        Start();
    }
    public void setText()
    {
       GreenfootImage img_field = getImage();
       img_field.clear();
       img_field.drawString("Score: "+score, 25, 45);
       String str="Level"+level;
       switch(level){
           case 1:
            str+="(火星)";
            break;
           case 2:
            str+="(木星)";
            break;
           case 3:
            str+="(土星)";
            break;
           case 4:
            str+="(天王星)";
            break;
           case 5:
            str+="(海王星)";
            break;
           default:
            break;
       }       
       img_field.drawString(str, width/2-70, 45);
       img_field.drawString("Life: "+life, width-100, 45);
    }
    public void addScore(int value){
        score+=value;
        setText();
    }
    public void changeLife(int value){
        life+=value;
        setText();
        if (life <= 0 && !parent.isUnlessLife){
            parent.lost();
            Stop();
        }
    }
    
    public void newEnemy(){
        int[] types=new int[0];
        for(int i=1;i<enemyNum.length;i++){
            if(enemyNum[i]>0){
                types=Arrays.copyOf(types, types.length+1);
                types[types.length-1]=i;
            }
        }
        int typeIndex;
        if(types.length==0){
            //等待最后一个飞机被灭
        }
        else{//>=1
            if(types.length>=2){
                typeIndex=(int)Greenfoot.getRandomNumber(types.length-1);
            }
            else
                typeIndex=0;
            addEnemy(types[typeIndex]);
            reTime();
        }
    }
    public boolean addEnemy(int type){
        if(type==4){
            if(enemyNum[4]>0){
                Enemy enemy=new Enemy(parent,4);
                enemys.add(enemy);
                enemyNum[4]--;
                parent.addObject(enemy, parent.getWidth() + 1 , Greenfoot.getRandomNumber(
                parent.getHeight()-enemy.getImage().getHeight())+enemy.getImage().getHeight()/2);
                return true;
            }
            else
                return false;
        }
        else if(type==3){
            if(enemyNum[3]>0){
                Enemy enemy=new Enemy(parent,3);
                enemys.add(enemy);
                enemyNum[3]--;
                parent.addObject(enemy, parent.getWidth() + 1 , Greenfoot.getRandomNumber(
                parent.getHeight()-enemy.getImage().getHeight())+enemy.getImage().getHeight()/2);
                return true;
            }
            else
                return false;
        }         
        else if(type==2){
            if(enemyNum[2]>0){
                Enemy enemy=new Enemy(parent,2);
                enemys.add(enemy);
                enemyNum[2]--;
                parent.addObject(enemy, parent.getWidth() + 1 , Greenfoot.getRandomNumber(
                parent.getHeight()-enemy.getImage().getHeight())+enemy.getImage().getHeight()/2);
                return true;
            }
            else
                return false;
        }
        else if(type==1){
            if(enemyNum[1]>0){
                Enemy enemy=new Enemy(parent,1);
                enemys.add(enemy);
                enemyNum[1]--;
                parent.addObject(enemy, parent.getWidth() + 1 , Greenfoot.getRandomNumber(
                parent.getHeight()-enemy.getImage().getHeight())+enemy.getImage().getHeight()/2);
                return true;
            }
            else
                return false;
        }
        else{
            if(enemyNum[0]>0){
                Enemy enemy=new Enemy(parent,10+level);
                enemys.add(enemy);
                enemyNum[0]--;
                parent.addObject(enemy, parent.getWidth() + 1 , Greenfoot.getRandomNumber(
                parent.getHeight()-enemy.getImage().getHeight())+enemy.getImage().getHeight()/2);
                return true;
            }
            else
                return false;
        }
    }
    public void nextLevel(){
        level++;
        switch(level){
            case 1:
                enemyNum[0]=1;
                enemyNum[1]=10;
                enemyNum[2]=5;
                enemyNum[3]=0;
                enemyNum[4]=0;
                break;
            case 2:
                enemyNum[0]=1;
                enemyNum[1]=10;
                enemyNum[2]=8;
                enemyNum[3]=1;
                enemyNum[4]=1;
                break;
            case 3:
                enemyNum[0]=1;
                enemyNum[1]=20;
                enemyNum[2]=8;
                enemyNum[3]=3;
                enemyNum[4]=3;
                break;
            case 4:
                enemyNum[0]=1;
                enemyNum[1]=10;
                enemyNum[2]=16;
                enemyNum[3]=7;
                enemyNum[4]=7;
                break;
            case 5:
                enemyNum[0]=1;
                enemyNum[1]=0;
                enemyNum[2]=10;
                enemyNum[3]=15;
                enemyNum[4]=15;
                break;
        }
    }
    public void  win(){
        if(enemys.isEmpty() && 
                enemyNum[1]==0 &&
                enemyNum[2]==0 &&
                enemyNum[3]==0 &&
                enemyNum[4]==0){
                    if(enemyNum[0]!=0){//boss
                        addEnemy(level+10);
                        reTime();
                    }
                    else if(level<5){
                        nextLevel();
                        life++;
                        reTime();
                        setText();                        
                    }
                    else{
                        parent.win();
                        Stop();
                    }
                }
    }
}