import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.applet.*;
import java.awt.*;
public class BgmTitle extends Actor
{
    Font font1=new Font("свт╡",Font.BOLD,25);
    public BgmTitle(String text)
    {
        GreenfootImage img_field = new GreenfootImage(130,50);
        img_field.setFont(font1);
        img_field.setColor(Color.white);
        setImage(img_field);
        setText(text);
    }
    public void setText(String text)
    {
        GreenfootImage img_field = getImage();
        img_field.clear();
        img_field.drawString(text, 5, 45);        
    }
}
