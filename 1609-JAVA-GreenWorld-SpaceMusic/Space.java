import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Space here.
 * 
 * @Brian Myers 
 * @April 2010
 */
public class Space extends World
{
    private Timer timer_field;
    private Ship ship;
    private int fps=50;
    public Space()
    {    
        // Create a new world with 600x400 cells with a cell size of 10x10 pixels.
        super(1280, 720, 1);
        Color black = new Color(0,0,0);
        GreenfootImage background = getBackground();
        background.setColor(black);
        background.fill();        
        
        timer_field = new Timer(this,fps);
        addObject(timer_field, 1200, 20);
        
        BgmTitle bgmText=new BgmTitle("bgm");
        addObject(bgmText, 640, 680);
        
        ship = new Ship(this,fps,100,200);        
        addObject(ship, 100, 360);
        
        createStars(50);
        
        setPaintOrder( GameOver.class,Timer.class,
        Explosion.class,Ship.class, Laser.class, Stars.class);
        
        Greenfoot.setSpeed(fps);
    }
    public void gameOver(boolean isWin){
        GameOver lost=new GameOver(isWin);
        addObject(lost,getWidth()/2,getHeight()/2);
        timer_field.stop();
        ship.stop();
    }
    private void createStars(int number)
    {        
        for(int i = 0; i < number; i++)
        {
            int x = Greenfoot.getRandomNumber( getWidth() );   
            int y = Greenfoot.getRandomNumber( getHeight() );
            addObject(new Stars(), x, y);
        }
    }
}
