/*
* @author  Sebastian Strindlund (sest1601)
* @version 1.0
* @since   01-19-2018
*/

package dt062g.sest1601.tenta;


import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;



public class BingoCaller extends JPanel {
	
	private SwingWorker<Boolean,Void> caller;
	private ArrayList<Integer> numbers;


	// Default constructor, creates new ArrayList, loads upp 75 numbers and shuffles it.
	public BingoCaller() {}

	public void addBingoBall(BingoBall ball) {
		
		ImageIcon ballImage;
		ballImage = new ImageIcon(this.getClass().getClassLoader().getResource(ball.getImgPath()));
		JLabel ballLabel = new JLabel(ball.getNumber(), ballImage, SwingConstants.CENTER);
		add(ballLabel);
		ballLabel.setHorizontalTextPosition(JLabel.CENTER);
	

	}

	public void startCaller() {

		numbers = new ArrayList<Integer>(); 

		for (int i = 1; i <= 75; i++) { // Add 1-75
			numbers.add(i);
		}
		Collections.shuffle(numbers); // Shuffle the order of numbers

		removeAll();  // Remove all balls if any
		repaint();    // Repaint panel
		
		caller = new SwingWorker<Boolean, Void>() {

			@Override
			protected Boolean doInBackground() {
				for (int nr : numbers) {					// For each number (75 numbers in random order)
					try {
						addBingoBall(new BingoBall(nr));	// add bingoball to JPanel
						Thread.sleep(1500);					// Sleep for 3 seconds
					} catch (InterruptedException e) {
						System.err.println("User interrupeted the caller"); // If interrupted return
						return true;
					}
					
				}
				return true;
			}
			@Override
			protected void done() {}
		};

		caller.execute(); // Execute worker

	}

	public void stopCaller() {
		synchronized (caller) { // Stop swingworker
			caller.notify();
			caller.cancel(true);
		}
	}
}