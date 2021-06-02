import java.awt.*;
import javax.swing.*;

public class DodgeFrame extends JFrame
{
	public DodgePanel p;

	public DodgeFrame(String frameName)
	{
		super(frameName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		DodgePanel p = new DodgePanel();
		Insets frameInsets = getInsets();
		int frameWidth = p.getWidth()
				+ (frameInsets.left + frameInsets.right);
		int frameHeight = p.getHeight()
				+ (frameInsets.top + frameInsets.bottom);
		setPreferredSize(new Dimension(frameWidth, frameHeight));
		setLayout(null);
		add(p);
		pack();
		setVisible(true);

		this.p = p;
	}
}