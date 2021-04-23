import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameOver extends Actor
{
    
    public GameOver(boolean isWin)
    {
        if(isWin)
            setImage("win.png");
        else
            setImage("gameover.png");
    }
}
