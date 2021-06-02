import java.awt.*;

public class GraphicsRectangle extends Rectangle
{
	public GraphicsRectangle(int x, int y, int w, int h)
	{
		super(x, y, w, h);
	}

	public void paintRectangle(Graphics g, GameObject object)
	{
		g.setColor(object.getColor());
		g.fillRect((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
	}
}