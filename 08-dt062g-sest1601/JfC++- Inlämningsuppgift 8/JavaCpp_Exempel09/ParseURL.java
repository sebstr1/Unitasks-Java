/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM Östersund
 * @version 1.0
 * @file Ex04_01 - ParseURL.java
 */

import java.net.*;

public class ParseURL
{

	public static void main(String[] args)
	{
		try
		{
			URL java = new URL("http://www.java.com/sv/index.jsp");

			System.out.println(java.toExternalForm());
			System.out.println("Protocol.... " + java.getProtocol());
			System.out.println("Host........ " + java.getHost());
			System.out.println("File........ " + java.getFile());
			System.out.println("Port........ " + java.getPort());
			System.out.println("Default port " + java.getDefaultPort());
		}
		catch (MalformedURLException fel)
		{
			// Fel vid skapande av URL
			fel.printStackTrace();
		}
    }
}