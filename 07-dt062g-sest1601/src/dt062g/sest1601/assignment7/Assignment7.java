/**
 * <h1>Assignment 7</h1>
 * This class is the starting point for the drawing application.
 * It creates our JFrame and makes it visible.
 * 
 *
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-12-07
*/

package dt062g.sest1601.assignment7;

import javax.swing.SwingUtilities;

public class Assignment7 {

	public static void main(String[] args) {
		// Make sure GUI is created on the event dispatching thread
		// This will be explained in the lesson about threads
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});		
	}
}