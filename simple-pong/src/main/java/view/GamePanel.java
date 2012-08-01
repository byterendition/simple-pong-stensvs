package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	Point mouseLocation = new Point();
	BufferedImage offScreenImage;
	
	public GamePanel(){
		setPreferredSize(new Dimension(400, 400));
		offScreenImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		addMouseMotionListener(new GamePanelListener());
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics gOffScreen = offScreenImage.getGraphics();
		
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, getWidth(), getHeight());
		gOffScreen.setColor(Color.WHITE);
		gOffScreen.fillRect(mouseLocation.x-20, 385, 40, 5);
		
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	private class GamePanelListener extends MouseInputAdapter{
		
		public void mouseMoved(MouseEvent e){
			if(contains(e.getPoint())){
				mouseLocation = e.getPoint();
				repaint();
			}
		}
	}
}
