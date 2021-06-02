import java.awt.Color;

public class LaserObstacle extends Obstacle
{
	private boolean visible;
	private int wait;
	private int altWait;

	public LaserObstacle(int x, int y, int width, int height, int wait)
	{
		super(x, y, width, height);
		visible = true;
		this.wait = wait;
		altWait = wait;
	}

	public void update()
	{
		if(wait==0)
		{
			visible = !visible;

			if(visible)
				super.setColor(Color.red);
			else
				super.setColor(new Color(0, 0, 0, 1));

			wait = altWait;
		}
		else
			wait--;
	}

	public boolean isVisible()
	{
		return visible;
	}
}