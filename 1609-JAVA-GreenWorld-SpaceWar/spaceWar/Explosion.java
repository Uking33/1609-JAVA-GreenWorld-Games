import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * An explosion. It starts by expanding and then collapsing. 
 * The explosion will explode other obejcts that the explosion intersects.
 * 
 * @author Poul Henriksen
 * @version 1.0.1
 */
public class Explosion extends Actor
{
    private final static int IMAGE_COUNT= 12;   //爆炸冲击波效果图片总张数
    private static GreenfootImage[] images;     //数组images是用来储存爆炸冲击波效果图片的
    private int imageNo = 0;         //变量imageNo记录已显示的爆炸效果图片的张数
    
    private int increment=1;        //变量increment表示图片数组中元素下标的变化值。increment=1，表示元素下标从小到大；incremnet=-1，表示元素下标从大到小变化
    
    public Explosion() 
    {
        initializeImages();                                 //按比例尺寸生成一些列爆炸效果图片
        setImage(images[0]);                                //显示第一张爆炸效果图片
        Greenfoot.playSound("explosion.wav");   //播放爆炸音效
    }    
    
    public static void initializeImages()      //按比例尺寸生成一些列爆炸效果图片
    {
        if(images == null) {
            GreenfootImage baseImage = new GreenfootImage("explosion.png");
            images = new GreenfootImage[IMAGE_COUNT];
            for (int i = 0; i < IMAGE_COUNT; i++)
            {
                int size = (i+1) * ( baseImage.getWidth()/3 / IMAGE_COUNT );
                images[i] = new GreenfootImage(baseImage);
                images[i].scale(size, size);
            }
        }
    }
    
    public void act()
    { 
        setImage(images[imageNo]);
        Greenfoot.delay(1);
        imageNo += increment;
        if(imageNo >= IMAGE_COUNT) {    //如果从头至尾显示完图片数组中的每一张图片,然后从尾至头逆序显示完数组中的每张图片
            increment = -1;         //图片数组元素的下标每次的变化值。下标每次变化-1，表示从数组的最后一个元素变化到第一个元素
            imageNo += increment;
        }
        
        if(imageNo < 0) {
            getWorld().removeObject(this);
        }
    }
}