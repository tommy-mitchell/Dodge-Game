public class BasicObstacle extends Obstacle
{
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;

	private int x;
	private int y;
	private int[] movements;

	private int index = 0;
	private int altX;
	private int altY;

	public BasicObstacle(int x, int y, int width, int height, int[] movements)
	{
		super(x, y, width, height);
		this.x = x;
		this.y = y;
			altX = x;
			altY = y;
		this.movements = movements;
	}

	public void update()
	{
		direction();

		if(right && x<movements[index])
			x += SPEED;
		else if(left && x>movements[index])
			x -= SPEED;

		index++;
		direction();

		if(up && y>movements[index])
			y -= SPEED;
		else if(down && y<movements[index])
			y += SPEED;

		super.set(x, y);
		index--;
	}

	public void direction()
	{
		if(index%2==0)
		{
			int xLim = movements[index];
				if(x<xLim)
					right = true;
				else if(x>xLim)
					left = true;
				else
				{
					movements[index] = altX;
					altX = xLim;
				}
		}

		if(index%2!=0)
		{
			int yLim = movements[index];
				if(y>yLim)
					up = true;
				else if(y<yLim)
					down = true;
				else
				{
					movements[index] = altY;
					altY = yLim;
				}
		}
	}
}