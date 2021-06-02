import java.awt.Color;
import java.awt.event.MouseEvent;

public class LevelSelector extends GameObject
{
	private int x;
	private int y;
	private int levelNum;

	public static final Color transparent = new Color(0, 0, 0, 1);
	public static final Color highlight = new Color(127, 127, 127, 127);
	public static final Color dark = new Color(63, 63, 63, 127);

	public static final int LENGTH = 80;

	public LevelSelector(int x, int y, int levelNum)
	{
		super(x, y, LENGTH, LENGTH, dark);
		this.x = x;
		this.y = y;
		this.levelNum = levelNum;
	}

	public boolean mouseAbove(MouseEvent e)
	{
		return((e.getX()>=x && e.getX()<=x+LENGTH) && (e.getY()>=y && e.getY()<=y+LENGTH));
	}

	public void setHighlighted(boolean highlighted)
	{
		if(highlighted)
			setColor(highlight);
		else
			setColor(transparent);
	}

	public void setLocked(boolean locked)
	{
		if(locked)
			setColor(dark);
		else
			setColor(transparent);
	}

	public int getLevelNum()
	{
		return levelNum;
	}
}