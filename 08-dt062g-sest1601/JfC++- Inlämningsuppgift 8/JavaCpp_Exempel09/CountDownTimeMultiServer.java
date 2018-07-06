/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
 * @version 1.0
 * @file Ex05_10 - CountDownTimeMultiServer.java
 */

import java.net.*;
import java.io.*;

public class CountDownTimeMultiServer
{
	// Den port servern ska lyssna p�
	private int port;

	// Konstruktor
	public CountDownTimeMultiServer(int port)
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
			System.out.println("Lyssnar p� port: " + port);
		}
		catch (IOException ioe)
		{
			System.out.println("Kunde inte lyssna p� port " + port);
			return;
		}

		while (true)
		{
			try
			{
				// V�ntar p� att n�gon ska ansluta
				Socket s = ss.accept();

				// En klient har anslutit s� vi skapar en ny klienthanterare
				System.out.println("\nKlient ansluten med adress: " + s.getInetAddress().getHostAddress());
				new CountDownTimeClientHandler(s).start();
			}
			catch (IOException ioe)
			{
				System.out.println("Fel vid uppr�ttande av f�rbindelse till klient:\n" + ioe);
			}
		}
	}

	public static void main(String[] args)
	{
		new CountDownTimeMultiServer(10003);

    }
}