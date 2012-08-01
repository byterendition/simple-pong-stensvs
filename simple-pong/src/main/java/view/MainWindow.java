package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.Game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private static final Logger	log	= LoggerFactory.getLogger(MainWindow.class);
	
	public MainWindow(Game game) {
		createAndShowGUI();
		// TODO Auto-generated constructor stub
	}
	
	private void createAndShowGUI() {
		setTitle("Simple Pong - by RickvS and Sir_ClickALot");
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new MainWindowListener());
		
		setResizable(false);
		
		JPanel contentPane = new JPanel();
		GamePanel gamePanel = new GamePanel();
		
		contentPane.add(gamePanel);
		add(contentPane);
		
		pack();
	}
	
	/**
	 * Listener class which decides what happens if the window is closed
	 * 
	 * @author Sir_ClickALot
	 */
	private class MainWindowListener extends WindowAdapter {
		public MainWindowListener() {
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			log.info("Exit");
			System.exit(0);
		}
	}
}
