import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class LevelImages
{
	private BufferedImage one;
	private BufferedImage two;
	private BufferedImage three;
	private BufferedImage four;
	private BufferedImage five;
	private BufferedImage reset;

	public LevelImages()
	{
		try
		{
			one = ImageIO.read(new File("images\\level_1.png"));
			two = ImageIO.read(new File("images\\level_2.png"));
			three = ImageIO.read(new File("images\\level_3.png"));
			four = ImageIO.read(new File("images\\level_4.png"));
			five = ImageIO.read(new File("images\\level_5.png"));
			reset = ImageIO.read(new File("images\\reset.png"));
		}
		catch(Exception IOException)
		{
			System.out.println("image file error");
		}
	}

	public void draw(Graphics g)
	{
		g.drawImage(one, 35, 702, null);
		g.drawImage(two, 165, 702, null);
		g.drawImage(three, 295, 702, null);
		g.drawImage(four, 425, 702, null);
		g.drawImage(five, 555, 702, null);
		g.drawImage(reset, 685, 702, null);
	}
}