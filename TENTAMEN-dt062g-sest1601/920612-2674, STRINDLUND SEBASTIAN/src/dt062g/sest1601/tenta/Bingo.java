/*
* @author  Sebastian Strindlund (sest1601)
* @version 1.0
* @since   01-19-2018
*/

package dt062g.sest1601.tenta;
import javax.swing.SwingUtilities;

public class Bingo {


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BingoGUI().setVisible(true);
			}
		});
	}
}