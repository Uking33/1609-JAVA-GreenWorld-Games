import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class Space extends World
{
    //actor
    public State state_field;
    List<Enemy> enemys;
    Ship ship;
    Button btn;
    
    
    public boolean isUnlessLife;
    //data
    public Space()
    {
        super(600, 400, 1);
        initData();
        initBackground();
        //Ship
        ship = new Ship(this,100,200);
        addObject(ship, 100, 200);
        //Btn
        btn = new Button(this);
        addObject(btn, 500, 350);
        //Order
        setPaintOrder(State.class,Button.class,
        Ship.class, Laser.class, Enemy.class, 
        Stars.class);
        //init
        Greenfoot.setSpeed(50);        
    }
    private void initData(){
        isUnlessLife=false;
        enemys=new ArrayList<Enemy>();
        //State
        state_field = new State(this,getWidth(),50*2,enemys);
        addObject(state_field, 300, 40);
    }
    private void initBackground(){
        //backgroud
        Color black = new Color(0,0,0);
        GreenfootImage background = getBackground();
        background.setColor(black);
        background.fill();
        createStars(50);
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
    //½Ó¿Ú
    public void setData(String type,int value){
        switch(type){
            case "score":
                state_field.addScore(value);
                break;
            case "life":
                state_field.changeLife(value);
                break;
            case "speed":
                ship.setSpeed(value);
                break;
        }
    }
    public void re(){
        removeObjects(getObjects(Laser.class));
        enemys=new ArrayList<Enemy>();
        removeObjects(getObjects(Enemy.class));
        removeObject(ship);
        Color black = new Color(0,0,0);
        GreenfootImage background = getBackground();
        background.setColor(black);
        background.fill();
        ship = new Ship(this,100,200);
        addObject(ship, 100, 200);
    }
    public void win(){
        setBackground("win.png");
        for(int i=0;i<enemys.size();i++){
            removeObject(enemys.get(i));
            enemys.remove(i);
        }
    }
    public void lost(){
        setBackground("lost.png");
        removeObject(ship);
    }
}
