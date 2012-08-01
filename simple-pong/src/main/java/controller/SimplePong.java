package controller;

import javax.swing.SwingUtilities;

import model.Game;
import view.MainWindow;

public class SimplePong implements Runnable {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new SimplePong());
	}
	
	@Override
	public void run() {
		MainWindow mainWindow = new MainWindow(new Game());
		
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
	}
}
