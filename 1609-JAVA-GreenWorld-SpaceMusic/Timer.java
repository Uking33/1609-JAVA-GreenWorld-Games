import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.applet.*;
import java.awt.*;

public class Timer extends Actor
{
    int winTimer;
    int fps;
    boolean isRun;
    Space space;
    Font font1=new Font("Ó×Ô²",Font.BOLD,25);
    public Timer(Space space,int fps)
    {
        this.space=space;
        this.fps=fps;
        isRun=true;
        winTimer=fps*60*2;
        GreenfootImage img_field = new GreenfootImage(130,50);
        img_field.setFont(font1);
        img_field.setColor(Color.white);
        setImage(img_field);
        setText();
    }
    public void setText()
    {
        GreenfootImage img_field = getImage();
        img_field.clear();
        String str="Ê±¼ä "+winTimer/fps/60+":";
        if(winTimer/fps%60>=10)
            str+=winTimer/fps%60;
        else if(winTimer/fps%60==0)
            str+="00";
        else
            str+="0"+winTimer/fps%60;
        img_field.drawString(str, 5, 45);        
    }
    public void act(){
        if(isRun){
            winTimer--;
            setText();
            if(winTimer==0)
                space.gameOver(true);
        }
    }
    public void stop(){
        isRun=false;
    }
}
