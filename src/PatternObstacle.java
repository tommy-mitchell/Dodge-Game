public class PatternObstacle extends Obstacle
{
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;

	private int x;
	private int y;
	private int[] movements;

	private int index = 0;

	public PatternObstacle(int x, int y, int width, int height, int[] movements)
	{
		super(x, y, width, height);
		this.x = x;
		this.y = y;
		this.movements = movements;
	}

	public void update()
	{
		if(index%2==0) // x
			movementX();
		else // y
			movementY();

		if(index==movements.length)
			index = 0;

		super.set(x, y);

	}

	public boolean movementX()
	{
		int xLim = movements[index];
			if(x<xLim)
				right = true;
			else if(x>xLim)
				left = true;
			else
			{
				index++;
				return true;
			}

		if(right&&x<movements[index])
			x += SPEED;
		else if(left&&x>movements[index])
			x -= SPEED;

		return false;
	}

	public boolean movementY()
	{
		int yLim = movements[index];
			if(y>yLim)
				up = true;
			else if(y<yLim)
				down = true;
			else
			{
				index++;
				return true;
			}

		if(up&&y>movements[index])
			y -= SPEED;
		else if(down&&y<movements[index])
			y += SPEED;

		return false;
	}
}
