import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Level
{
	private ArrayList<Obstacle> obstacles = new ArrayList<>();
	private ArrayList<LevelSelector> numbers = new ArrayList<>();
	private ArrayList<LaserTube> tubes = new ArrayList<>();
	private Wall[][] walls;
	private Start start;
	private Point end;
	private Scanner scanner;

	private int fileNum;

	public Level(int fileNum)
	{
		this.fileNum = fileNum;

		int num = 1;
		for(int x = 35; x<670; x+=130)
			numbers.add(new LevelSelector(x, 702, num++));
		numbers.add(new LevelSelector(685, 702, 1));

		try
		{
			scanner = new Scanner(new File("levels\\" +fileNum+ ".txt"));
		}
		catch(Exception IOException)
		{
			System.out.println("no file");
		}

		walls = new Wall[Integer.parseInt(scanner.nextLine())][Integer.parseInt(scanner.nextLine())];

		while(scanner.hasNextLine())
		{
			String s = scanner.nextLine();

			if(Character.toUpperCase(s.charAt(0))!='W')
			{
				String[] split = s.substring(2).split(", ");

				int[] numbers = stringToInt(split);

				if(s.charAt(0)=='S') // start
					start = new Start(numbers[0], numbers[1], numbers[2]);
				else if(s.charAt(0)=='E') // end
					end = new Point(numbers[0], numbers[1]);
				else if(s.charAt(0)=='B') // basic obstacle
					obstacles.add(new BasicObstacle(numbers[0], numbers[1], numbers[2], numbers[3], getMovements(numbers)));
				else if(s.charAt(0)=='P') // pattern obstacle
					obstacles.add(new PatternObstacle(numbers[0], numbers[1], numbers[2], numbers[3], getMovements(numbers)));
				else if(s.charAt(0)=='L') // laser obstacle
					obstacles.add(new LaserObstacle(numbers[0], numbers[1], numbers[2], numbers[3], numbers[4]));
				else if(s.charAt(0)=='T') // laser tube
					tubes.add(new LaserTube(numbers[0], numbers[1], numbers[2]));
			}
			else
			{
				for(int row = 0; row<walls.length; row++)
				{
					for(int col = 0; col<walls[0].length; col++)
					{
						if(s.charAt(col)=='W') // wall
							walls[row][col] = new Wall(col*20+200, row*20+125);
					}

					if(scanner.hasNextLine())
						s = scanner.nextLine();
				}
			}
		}
	}

	public int[] stringToInt(String[] strings)
	{
		int[] ints = new int[strings.length];

		for(int index = 0; index<strings.length; index++)
			ints[index] = Integer.parseInt(strings[index]);

		return ints;
	}

	public int[] getMovements(int[] numbers)
	{
		int[] movements = new int[numbers.length-4];

		for(int index = 4; index<numbers.length; index++)
			movements[index-4] = numbers[index];

		return movements;
	}

	public void update()
	{
		for(Obstacle o: obstacles)
			o.update();
	}

	public Start getStart()
	{
		return start;
	}

	public Point getEnd()
	{
		return end;
	}

	public ArrayList<LevelSelector> getNumbers()
	{
		return numbers;
	}

	public ArrayList<GameObject> getObjects()
	{
		ArrayList<GameObject> objects = new ArrayList<>();

		for(Wall[] w1: walls)
			for(Wall w2: w1)
				if(w2!=null)
					objects.add(w2);

		for(Obstacle o: obstacles)
			objects.add(o);

		for(LevelSelector s: numbers)
			objects.add(s);

		return objects;
	}

	public GameObject hitObject(Player player)
	{
		for(Wall[] w1: walls)
			for(Wall w2 : w1)
				if(w2!=null && w2.intersects(player))
					return w2;

		for(Obstacle o: obstacles)
		{
			if(o.intersects(player))
			{
				if(o instanceof LaserObstacle)
					return laserCheck((LaserObstacle)o);

				return o;
			}
		}

		return null;
	}

	public LaserObstacle laserCheck(LaserObstacle laser)
	{
		if(laser.isVisible())
			return laser;
		else
			return null;
	}

	public ArrayList<LaserTube> getTubes()
	{
		return tubes;
	}

	public int getFileNum()
	{
		return fileNum;
	}
}