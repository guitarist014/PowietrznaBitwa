import java.awt.image.ImageObserver;


public interface Stage extends ImageObserver 
{
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	public static final int INTERVAL = 40;
	public SpriteCache getSpriteCache();
}
