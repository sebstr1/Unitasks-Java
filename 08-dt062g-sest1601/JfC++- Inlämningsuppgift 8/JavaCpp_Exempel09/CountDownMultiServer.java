/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM Östersund
 * @version 1.0
 * @file Ex05_07 - CountDownMultiServer.java
 */

import java.net.*;
import java.io.*;

public class CountDownMultiServer
{
	// Den port servern ska lyssna på
	private int port;

	// Konstruktor
	public CountDownMultiServer(int port)
	{
		this.port = port;
		countDown();
	}

	public void countDown()
	{
		ServerSocket ss = null;

		try
		{
			// Skapar ServerSocket
			ss = new ServerSocket(port);
			System.out.println("Lyssnar på port: " + port);
		}
		catch (IOException ioe)
		{
			System.out.println("Kunde inte lyssna på port " + port);
			return;
		}

		while (true)
		{
			try
			{
				// Väntar på att någon ska ansluta
				Socket s = ss.accept();

				// En klient har anslutit så vi skapar en ny klienthanterare
				System.out.println("\nKlient ansluten med adress: " + s.getInetAddress().getHostAddress());
				new CountDownClientHandler(s).start();
			}
			catch (IOException ioe)
			{
				System.out.println("Fel vid upprättande av förbindelse till klient:\n" + ioe);
			}
		}
	}

	public static void main(String[] args)
	{
		new CountDownMultiServer(10003);

    }
}