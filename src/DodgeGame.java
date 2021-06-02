import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DodgeGame
{
	private Level level;
	private Player player;
	private Lives lives;

	public DodgeGame(int levelNum)
	{
		setLevel(levelNum);
		lives = new Lives();
	}

	public void update()
	{
		level.update();

		if(!wonLevel() && !lives.outOfLives())
		{
			player.update(level.getStart()); // after hit - do and undos separate - maybe have game stored in player, use to move and then check if it hit and then undo

			for(LaserTube t: getTubes())
				if(t.intersects(player))
					player.wallReset();

			GameObject collision = level.hitObject(player);
				if(collision instanceof Obstacle)
				{
					resetPlayer();
					if(lives.death())
						player.died();
				}
				else if(collision instanceof Wall)
					player.wallReset();
		}
	}

	public Lives getLives()
	{
		return lives;
	}

	public boolean wonLevel()
	{
		 return player.getX()==level.getEnd().getX() && player.getY()==level.getEnd().getY();
	}

	public void setLevel(int levelNum)
	{
		level = new Level(levelNum);
		resetPlayer();
	}

	public int getLevel()
	{
		return level.getFileNum();
	}

	public void resetPlayer()
	{
		player = new Player(level.getStart().getX(), level.getStart().getY(), this);
	}

	public Player getPlayer()
	{
		return player;
	}

	public ArrayList<LevelSelector> getSelectors()
	{
		return level.getNumbers();
	}

	public void drawObjects(Graphics g)
	{
		for(GameObject o: level.getObjects())
			o.getRectangle().paintRectangle(g, o);
	}

	public ArrayList<LaserTube> getTubes()
	{
		return level.getTubes();
	}
}