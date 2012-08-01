package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private boolean	gameStarted		= false;
	private boolean	lost			= false;
	
	private Point	boardLocation	= new Point();
	final int		boardWidth		= 40;
	
	private Point	ballLocation	= new Point();
	private Point	ballSpeedVector	= new Point();
	final int		ballSpeed		= 4;
	final int		ballRadius		= 5;
	
	BufferedImage	offScreenImage;
	
	Timer			timer;
	
	public GamePanel() {
		setPreferredSize(new Dimension(400, 400));
		offScreenImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		addMouseListener(new GamePanelListener());
		addMouseMotionListener(new GamePanelListener());
		
		boardLocation = new Point(200, 385);
		ballLocation = new Point(200, 50);
		timer = new Timer(20, new TimerListener());
		timer.start();
		
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics gOffScreen = offScreenImage.getGraphics();
		
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, getWidth(), getHeight());
		gOffScreen.setColor(Color.WHITE);
		gOffScreen.fillRect(boardLocation.x - boardWidth / 2, boardLocation.y, boardWidth, 5);
		
		if (lost) {
			gOffScreen.setColor(Color.RED);
		}
		gOffScreen.fillOval(ballLocation.x, ballLocation.y, ballRadius * 2, ballRadius * 2);
		
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	private class GamePanelListener extends MouseInputAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (!gameStarted) {
				gameStarted = true;
				lost = false;
				ballLocation = new Point(200, 50);
				Random r = new Random();
				int direction = r.nextInt(2) * 2 - 1;
				ballSpeedVector = new Point(direction * ballSpeed, ballSpeed);
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if (contains(e.getPoint())) {
				boardLocation.x = e.getPoint().x;
				repaint();
			}
		}
	}
	
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (gameStarted) {
				ballLocation.x += ballSpeedVector.x;
				ballLocation.y += ballSpeedVector.y;
				
				if (ballLocation.x < ballRadius || ballLocation.x > getWidth() - ballRadius) {
					ballSpeedVector.x = -ballSpeedVector.x;
				}
				
				if (ballLocation.y < ballRadius) {
					ballSpeedVector.y = -ballSpeedVector.y;
				}
				
				if (ballLocation.y > boardLocation.y - ballRadius && ballLocation.y <= boardLocation.y + ballSpeedVector.y && ballLocation.x > boardLocation.x - boardWidth / 2 && ballLocation.x < boardLocation.x + boardWidth / 2) {
					ballSpeedVector.y = -ballSpeedVector.y;
					
					int overshoot = (boardLocation.y - ballRadius) - ballLocation.y;
					ballLocation.y += 2 * overshoot;
				}
				
				if (ballLocation.y + ballSpeedVector.y > 400 - ballRadius) {
					gameStarted = false;
					lost = true;
				}
				
				repaint();
			}
		}
	}
}
