import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

public class DodgePanel extends JPanel implements KeyListener, Runnable, MouseListener, MouseMotionListener
{
	private BufferedImage buffer;
	private int updatesPerSecond;
	private long updateCount;
	private DodgeGame game;
	private boolean wonLevel = false;
	private boolean died = false;
	private int levelNum = 1;
	private boolean lockedSelect = true;
	private boolean lockedHover = false;
	private ArrayList<LevelSelector> selectors;
	private LevelImages images;

	private BufferedImage locked;
	private BufferedImage unlocked;
	private BufferedImage levelSelect;
	private BufferedImage win;
	private BufferedImage lose;
	private BufferedImage n;

	public DodgePanel()
	{
		super();
		setSize(800, 800);

		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

		buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		try
		{
			locked = ImageIO.read(new File("images\\locked.png"));
			unlocked = ImageIO.read(new File("images\\unlocked.png"));
			levelSelect = ImageIO.read(new File("images\\level_select.png"));
			win = ImageIO.read(new File("images\\win.png"));
			lose = ImageIO.read(new File("images\\lose.png"));
			n = ImageIO.read(new File("images\\n.png"));
		}
		catch(Exception IOException)
		{
			System.out.println("file error");
		}

		game = new DodgeGame(levelNum);
		selectors = game.getSelectors();
		images = new LevelImages();

		updatesPerSecond = 25;

		new Thread(this).start();
	}

	public void paint(Graphics g)
	{
		g.setColor(Color.white);
			g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.black);
			g.fillRect(0, 650, getWidth(), 150);

		g.setColor(new Color(205, 203, 39));
			g.fillRect(0, 650, getWidth(), 30);

		g.setColor(new Color(138, 125, 40));
			g.fillRect(0, 680, getWidth(), 4);

		g.drawImage(levelSelect, 0, 650, null);

		if(lockedSelect)
			g.drawImage(locked, getWidth() - 30, 650, null);
		else
			g.drawImage(unlocked, getWidth()-30, 650, null);

		if(lockedHover)
		{
			g.setColor(new Color(246, 247, 204)); // light
			g.drawRect(getWidth() - 2, 652, 0, 26);
			g.drawRect(getWidth() - 29, 678, 27, 0);

			g.setColor(new Color(138, 125, 40)); // dark
			g.drawRect(getWidth() - 29, 651, 27, 0);
			g.drawRect(getWidth() - 29, 651, 0, 27);
		}
		else
		{
			g.setColor(new Color(138, 125, 40)); // dark
			g.drawRect(getWidth() - 2, 652, 0, 26);
			g.drawRect(getWidth() - 29, 678, 27, 0);

			g.setColor(new Color(246, 247, 204)); // light
			g.drawRect(getWidth() - 29, 651, 27, 0);
			g.drawRect(getWidth() - 29, 651, 0, 26);
		}

		images.draw(g);
		game.getPlayer().getRectangle().paintRectangle(g, game.getPlayer());
		for(LaserTube t: game.getTubes())
			t.draw(g);
		game.drawObjects(g);
		game.getLives().draw(g);

		if(wonLevel || died)
		{
			g.setColor(new Color(128, 128, 128, 127));
				if(game.getLevel()!=2)
					g.fillRect(200, 125, 400, 400);
				else
					g.fillRect(200, 125, 400, 420);

			if(wonLevel)
				g.drawImage(win, 0, 0, null);

			if(died)
				g.drawImage(lose, 0, 0, null);

			g.drawImage(n, 0, 0, null);
		}
	}

	public void run()
	{
		int waitUpdate = (1000/updatesPerSecond);
		long startTime = System.nanoTime();

		while(true)
		{
			if(game.wonLevel())
			{
				wonLevel = true;
				repaint();
			}

			if(game.getLives().outOfLives())
			{
				died = true;
				repaint();
			}

			boolean shouldRepaint = false;
			long currentTime = System.nanoTime();
			long updatesNeeded = ((currentTime - startTime) / 1000000) / waitUpdate;
			for(long x = updateCount; x<updatesNeeded; x++)
			{
				game.update();
				shouldRepaint = true;
				updateCount++;
			}

			if(shouldRepaint)
				repaint();

			try
			{
				Thread.sleep(5);
			}
			catch(Exception e)
			{
				System.out.println("Error sleeping: " + e.getMessage());
			}
		}
	}

	public void keyPressed(KeyEvent e)
	{
		if(Character.toUpperCase(e.getKeyChar())=='W')
			game.getPlayer().setUpPressed(true);
		if(Character.toUpperCase(e.getKeyChar())=='S')
			game.getPlayer().setDownPressed(true);
		if(Character.toUpperCase(e.getKeyChar())=='D')
			game.getPlayer().setRightPressed(true);
		if(Character.toUpperCase(e.getKeyChar())=='A')
			game.getPlayer().setLeftPressed(true);
	}

	public void keyReleased(KeyEvent e)
	{
		if(Character.toUpperCase(e.getKeyChar())=='W')
			game.getPlayer().setUpPressed(false);
		if(Character.toUpperCase(e.getKeyChar())=='S')
			game.getPlayer().setDownPressed(false);
		if(Character.toUpperCase(e.getKeyChar())=='D')
			game.getPlayer().setRightPressed(false);
		if(Character.toUpperCase(e.getKeyChar())=='A')
			game.getPlayer().setLeftPressed(false);
	}

	public void keyTyped(KeyEvent e)
	{
		if(Character.toUpperCase(e.getKeyChar())=='N')
		{
			if(levelNum+1<6)
			{
				if(wonLevel)
				{
					game.setLevel(++levelNum);
					wonLevel = false;
					game.getLives().updateLives(false);
				}

				if(died)
				{
					died = false;
					game.setLevel(1);
					game.getLives().updateLives(true);
				}
			}
			else if(wonLevel || died)
			{
				 wonLevel = died = false;
				 game.setLevel(1);
				game.getLives().updateLives(true);
			}
		}
	}

	public void mousePressed(MouseEvent e)
	{
		if(lockedHover)
			lockedSelect = !lockedSelect;

		for(LevelSelector s: selectors)
		{
			if(lockedSelect)
				s.setLocked(true);
			else if(s.mouseAbove(e))
			{
				game.setLevel(s.getLevelNum());
				levelNum = s.getLevelNum();
				if(e.getX()>670)
					game.getLives().updateLives(true);
				died = false;
				wonLevel = false;

				selectors = game.getSelectors();
				for(LevelSelector sel: selectors)
					sel.setLocked(false);

				s.setHighlighted(true);
			}
		}
	}

	public void mouseMoved(MouseEvent e)
	{
		lockedHover = mouseOver(e);

		for(LevelSelector s: selectors)
		{
			if(!lockedSelect && e.getY()>650)
				s.setHighlighted(s.mouseAbove(e));
		}
	}

	public boolean mouseOver(MouseEvent e)
	{
		return (e.getX()>getWidth()-29 && e.getX()<getWidth()-1) && (e.getY()>651 && e.getY()<678);
	}

	public void mouseReleased(MouseEvent e)
	{
	}

	public void mouseClicked(MouseEvent e)
	{

	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{
	}

	public void mouseDragged(MouseEvent e)
	{
	}

	public void addNotify()
	{
		super.addNotify();
		requestFocus();
	}
}