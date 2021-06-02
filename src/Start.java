public class Start
{
	private int x;
	private int y;
	private int blockedDirection;

	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;

	public Start(int x, int y, int blockedDirection)
	{
		this.x = x;
		this.y = y;
		this.blockedDirection = blockedDirection;
	}

	public int getBlockedDirection(Player player)
	{
		if(atStart(player))
			return blockedDirection;
		else
			return 0;
	}

	public boolean atStart(Player player)
	{
		return player.getX()==x && player.getY()==y;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
}