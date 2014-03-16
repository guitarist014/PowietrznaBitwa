
public class Plane extends Actor
{
	protected int vx;
	protected int vy;
	public Plane(Stage stage)
	{
		super(stage);
		setSpriteName("samolocik.png");
	}
	
	public void act()
	{
		x+=vx;
		y+=vy;
		if(x<0 || x> Stage.WIDTH)
		{
			vx = -vx;
		}
		if(y<0 || y> Stage.HEIGHT)
		{
			vy = -vy;
		}
	}
	
	public int getVx() { return vx; }
	public void setVx(int i) { vx = i; }
	public int getVy() { return vy; }
	public void setVy(int i) { vy = i; }
}
