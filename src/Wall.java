import java.awt.Color;

public class Wall extends GameObject
{
	private int x;
	private int y;

	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;

	public Wall(int x, int y)
	{
		super(x, y, WIDTH, HEIGHT, Color.black);
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public String toString()
	{
		return "W";
	}
}