import java.awt.Color;

public class Player extends GameObject
{
	private int x;
	private int y;
	private boolean upPressed;
	private boolean downPressed;
	private boolean rightPressed;
	private boolean leftPressed;

	private DodgeGame game;

	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	public static final int SPEED = 5; // original: 5

	public Player(int x, int y, DodgeGame game)
	{
		super(x, y, WIDTH, HEIGHT, Color.blue);
		this.x = x;
		this.y = y;

		this.game = game;

		upPressed = false;
		downPressed = false;
		rightPressed = false;
		leftPressed = false;
	}

	public void update(Start start)
	{
		if(upPressed && start.getBlockedDirection(this)!=start.UP)
			y -= SPEED;
		if(downPressed && start.getBlockedDirection(this)!=start.DOWN)
			y += SPEED;
		if(leftPressed && start.getBlockedDirection(this)!=start.LEFT)
			x -= SPEED;
		if(rightPressed && start.getBlockedDirection(this)!=start.RIGHT)
			x += SPEED;

		super.set(x, y);
	}

	public void wallReset()
	{
		if(upPressed)
			y += SPEED;
		if(downPressed)
			y -= SPEED;
		if(leftPressed)
			x += SPEED;
		if(rightPressed)
			x -= SPEED;

		super.set(x, y);
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
		super.set(x, y);
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
		super.set(x, y);
	}

	public void setUpPressed(boolean upPressed)
	{
		this.upPressed = upPressed;
	}

	public void setDownPressed(boolean downPressed)
	{
		this.downPressed = downPressed;
	}

	public void setRightPressed(boolean rightPressed)
	{
		this.rightPressed = rightPressed;
	}

	public void setLeftPressed(boolean leftPressed)
	{
		this.leftPressed = leftPressed;
	}

	public void died()
	{
		super.setColor(Color.white);
	}
}