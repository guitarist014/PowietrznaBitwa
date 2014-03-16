import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;


public class SpriteCache 
{
	public HashMap<String, BufferedImage> sprites;
	
	public SpriteCache() 
	{
		sprites = new HashMap<String, BufferedImage>();
	}
	
	private BufferedImage loadImage(String path)
	{
		URL url = null;
		try
		{
			url = getClass().getClassLoader().getResource(path);
			return ImageIO.read(url);
		}
		catch (Exception e)
		{
			System.out.println("Przy otwieraniu " + path + "jako " + url);
			System.out.println("Wystapil blad: " + e.getClass().getName() +
								" " + e.getMessage());
		System.exit(0);
			return null;
		}
	}
	
	public BufferedImage getSprite(String path)
	{
		BufferedImage img = (BufferedImage)sprites.get(path);
		if(img == null)
		{
			img = loadImage(path);
			sprites.put(path, img);
		}
		
		return img;
		
	}
	

}
