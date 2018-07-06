/**
* <h1>Assignment 8</h1>
* This class is the starting point for the drawing application.
* It creates our JFrame and makes it visible. A Client is createds createds created
* that the JFrame uses to communicate with the server.
* 
*
* @author  Sebastian Strindlund (sest1601)
* @version 1.0
* @since   01-05-2018
*/


package dt062g.sest1601.assignment8;
import javax.swing.SwingUtilities;
import dt062g.sest1601.assignment8.client.Client;


public class Assignment8 {

	public static void main(String[] args) {
		// Default address and port to server.
		String address = Client.DEFAULT_ADDRESS;
		int port = Client.DEFAULT_PORT;
		Client client = null;
				
		// Check arguments if different address and port should be used.		
		// YOUR CODE HERE
		if (args.length == 0) {
			client = new Client(address, port);
		}
		else {
			try {
				address = args[0];
				port = Integer.parseInt(args[1]);
				client = new Client(address, port);
			} catch (Exception e) {
				client = new Client(Client.DEFAULT_ADDRESS, Client.DEFAULT_PORT);
			}
		}

		final Client c = client; 
		
		
		// Make sure GUI is created on the event dispatching thread
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame(c).setVisible(true);
			}
		});
	}
}