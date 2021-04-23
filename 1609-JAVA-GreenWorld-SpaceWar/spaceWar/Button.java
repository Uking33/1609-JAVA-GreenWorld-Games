import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;

public class Button extends Actor
{
    boolean state;
    GreenfootImage img_field;
    Space parent;
    Font font1=new Font("Helvetica",Font.BOLD,25);
    public Button(Space parent){
        this.parent=parent;
        img_field = new GreenfootImage(80,50);
        img_field.setColor(Color.WHITE);
        img_field.fillRect(1,1, 80, 50);
        state=false;
        img_field.setFont(font1);
        img_field.setColor(Color.BLACK);
        if(!state)
            img_field.drawString("无敌", 12, 30);
        else
            img_field.drawString("取消", 12, 30);
        setImage(img_field);
    }
    public void act() 
    {        
        checkMouse();
    }
    void checkMouse(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse!=null&&Greenfoot.mouseClicked(this)){
            state=!state;
            img_field.clear();
            img_field.setColor(Color.WHITE);
            img_field.fillRect(1,1, 80, 50);
            img_field.setColor(Color.BLACK);
            if(!state)
                img_field.drawString("无敌", 12, 30);
            else
                img_field.drawString("取消", 12, 30);                
            parent.isUnlessLife=state;        
            parent.state_field.reInit();
        }
    }
}
