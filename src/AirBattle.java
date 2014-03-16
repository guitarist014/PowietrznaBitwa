import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class AirBattle extends Canvas implements Stage 
{
	private static final long serialVersionUID = 8487351804233233568L;
	
	public long usedTime;
	public BufferStrategy strategy;
	private SpriteCache spriteCache;
	private ArrayList<Actor> actors;
	int numberOfObject = 120;

	public AirBattle() 
	{
		spriteCache = new SpriteCache();
		JFrame window = new JFrame("Air Battle");
		window.setBounds(0, 0, Stage.WIDTH, Stage.HEIGHT);
		window.setVisible(true);
		window.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		window.setResizable(false);
		
		JPanel panel = (JPanel)window.getContentPane();
		setBounds(0,0,Stage.WIDTH, Stage.HEIGHT);
		panel.setPreferredSize(new Dimension(Stage.WIDTH, Stage.HEIGHT));
		panel.setLayout(null);
		panel.add(this);
		
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		requestFocus();
	}
	
	public void initWorld()
	{
		actors = new ArrayList<Actor>();
		for(int i=0; i<numberOfObject; i++)
		{
			Plane p = new Plane(this);
			p.setX((int)(Math.random() * Stage.WIDTH));
			p.setY(i*20);
			p.setVy((int)(Math.random()*3) +1);
			p.setVx((int)(Math.random()*3) +1);
			actors.add(p);
			
			PlanePasazer pp = new PlanePasazer(this);
			pp.setX((int)(Math.random() * Stage.WIDTH));
			pp.setY(i*20);
			pp.setVy((int)(Math.random()*3) +1);
			pp.setVx((int)(Math.random()*3) +1);
			actors.add(pp);
		}
	}
	
	public void paintWorld() 
	{
		Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
		g.setColor(Color.white);	
		g.fillRect(0, 0, getWidth(),getHeight());
		for(int i=0; i<actors.size(); i++)
		{
			Actor p = (Actor)actors.get(i);
			p.paint(g);
		}
		
		g.setColor(Color.black);
		if(usedTime>0)
		{
			g.drawString(String.valueOf(1000/usedTime)+" fps", 5, Stage.HEIGHT-50);
		}
		else
		{
			g.drawString("------- fps", 5, Stage.HEIGHT-50);
		}
		strategy.show();
		
	}
	
	public void updateWorld()
	{
		for (int i = 0; i < actors.size(); i++) 
		{
			Actor p = (Actor)actors.get(i);
			p.act();
		}
	}
	
	public void game()
	{
		initWorld();
		while(isVisible())
		{
			long startTime = System.currentTimeMillis();
			updateWorld();
			paintWorld();
			usedTime = System.currentTimeMillis() - startTime;
			try
			{
				Thread.sleep(Stage.INTERVAL);
			}
			catch(InterruptedException e)
			{
			}
		}
	}
	
	public SpriteCache getSpriteCache()
	{
		return spriteCache;
	}

	public static void main(String[] args) 
	{
		AirBattle battle = new AirBattle();
		battle.game();
	}

}
