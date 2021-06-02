import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class LaserTube
{
	private int x;
	private int y;
	private BufferedImage tube;
	private String direction;
	private int width;
	private int height;

	public LaserTube(int x, int y, int face)
	{
		this.x = x;
		this.y = y;
		setAttributes(face);

		try
		{
			tube = ImageIO.read(new File("images\\laser_" +direction+ ".png"));
		}
		catch(Exception IOException)
		{
			System.out.println("image file error");
		}
	}

	public void draw(Graphics g)
	{
		g.drawImage(tube, x, y, null);
	}

	public void setAttributes(int face)
	{
		if(face==1 || face==2)
		{
			if(face==1)
				direction = "up";
			else // 2
				direction = "down";

			width = 30;
			height = 20;
		}
		else if(face==3 || face==4)
		{
			if(face==3)
				direction = "left";
			else // 4
				direction = "right";

			width = 20;
			height = 30;
		}
	}

	public boolean intersects(Player player)
	{
		if(direction.equals("up") || direction.equals("down"))
			return new Rectangle(x+5, y, width-10, height).intersects(player.getRectangle()); // find way to get player under prongs of tube
		else
			return new Rectangle(x, y+5, width, height-10).intersects(player.getRectangle());
	}
}