import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Lives
{
	private int lives;

	private BufferedImage one;
	private BufferedImage two;
	private BufferedImage three;
	private BufferedImage four;
	private BufferedImage five;
	private BufferedImage six;
	private BufferedImage seven;
	private BufferedImage eight;
	private BufferedImage nine;

	private BufferedImage zero;
	private BufferedImage u_ded;
	private BufferedImage x;

	public static final int STARTING_LIVES = 5;

	public Lives()
	{
		lives = STARTING_LIVES;

		try
		{
			one = ImageIO.read(new File("images\\1_life.png"));
			two = ImageIO.read(new File("images\\2_lives.png"));
			three = ImageIO.read(new File("images\\3_lives.png"));
			four = ImageIO.read(new File("images\\4_lives.png"));
			five = ImageIO.read(new File("images\\5_lives.png"));
			six = ImageIO.read(new File("images\\6_lives.png"));
			seven = ImageIO.read(new File("images\\7_lives.png"));
			eight = ImageIO.read(new File("images\\8_lives.png"));
			nine = ImageIO.read(new File("images\\9_lives.png"));

			zero = ImageIO.read(new File("images\\0_lives.png"));
			u_ded = ImageIO.read(new File("images\\u_ded.png"));
			x = ImageIO.read(new File("images\\x.png"));
		}
		catch(Exception IOException)
		{
			System.out.println("image file error");
		}
	}

	public void draw(Graphics g)
	{
		if(lives==0)
			g.drawImage(u_ded, 620, 0, null);
		else if(lives==1)
			g.drawImage(one, 620, 0, null);
		else if(lives==2)
			g.drawImage(two, 620, 0, null);
		else if(lives==3)
			g.drawImage(three, 620, 0, null);
		else if(lives==4)
			g.drawImage(four, 620, 0, null);
		else if(lives==5)
			g.drawImage(five, 620, 0, null);
		else if(lives==6)
			g.drawImage(six, 600, 0, null);
		else if(lives==7)
			g.drawImage(seven, 600, 0, null);
		else if(lives==8)
			g.drawImage(eight, 600, 0, null);
		else if(lives==9)
			g.drawImage(nine, 600, 0, null);
	}

	public boolean outOfLives()
	{
		return (lives==0);
	}

	public void updateLives(boolean dead)
	{
		if(dead)
			lives = STARTING_LIVES;
		else if(lives+1<10)
			lives++;
	}

	public boolean death()
	{
		lives--;
		return outOfLives();
	}
}