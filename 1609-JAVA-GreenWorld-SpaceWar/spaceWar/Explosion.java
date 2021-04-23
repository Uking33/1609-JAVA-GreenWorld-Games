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
    private final static int IMAGE_COUNT= 12;   //��ը�����Ч��ͼƬ������
    private static GreenfootImage[] images;     //����images���������汬ը�����Ч��ͼƬ��
    private int imageNo = 0;         //����imageNo��¼����ʾ�ı�ըЧ��ͼƬ������
    
    private int increment=1;        //����increment��ʾͼƬ������Ԫ���±�ı仯ֵ��increment=1����ʾԪ���±��С����incremnet=-1����ʾԪ���±�Ӵ�С�仯
    
    public Explosion() 
    {
        initializeImages();                                 //�������ߴ�����һЩ�б�ըЧ��ͼƬ
        setImage(images[0]);                                //��ʾ��һ�ű�ըЧ��ͼƬ
        Greenfoot.playSound("explosion.wav");   //���ű�ը��Ч
    }    
    
    public static void initializeImages()      //�������ߴ�����һЩ�б�ըЧ��ͼƬ
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
        if(imageNo >= IMAGE_COUNT) {    //�����ͷ��β��ʾ��ͼƬ�����е�ÿһ��ͼƬ,Ȼ���β��ͷ������ʾ�������е�ÿ��ͼƬ
            increment = -1;         //ͼƬ����Ԫ�ص��±�ÿ�εı仯ֵ���±�ÿ�α仯-1����ʾ����������һ��Ԫ�ر仯����һ��Ԫ��
            imageNo += increment;
        }
        
        if(imageNo < 0) {
            getWorld().removeObject(this);
        }
    }
}