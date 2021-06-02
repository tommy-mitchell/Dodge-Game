import java.awt.Color;

public abstract class GameObject
{
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;

	public GameObject(int x, int y, int width, int height, Color color)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public GraphicsRectangle getRectangle()
	{
		return new GraphicsRectangle(x, y, width, height);
	}

	public boolean intersects(GameObject object)
	{
		return getRectangle().intersects(object.getRectangle());
	}

	public void setColor(Color c)
	{
		color = c;
	}

	public Color getColor()
	{
		return color;
	}

	public void set(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}