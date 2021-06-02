import java.awt.Color;

public abstract class Obstacle extends GameObject
{
	private int x;
	private int y;

	public static final int SPEED = 2;  // original: 2

	public Obstacle(int x, int y, int width, int height)
	{
		super(x, y, width, height, Color.red);

		this.x = x;
		this.y = y;
	}

	public abstract void update();
}