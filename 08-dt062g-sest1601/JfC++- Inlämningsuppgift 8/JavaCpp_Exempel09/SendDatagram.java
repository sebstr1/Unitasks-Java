/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
 * @version 1.0
 * @file Ex04_06 - SendDatagram.java
 */

import java.net.*;
import java.io.*;

public class SendDatagram
{
	public static void main(String[] args)
	{
		try
		{
			// G�r i ordning meddelandet som ska skickas
			String meddelande = "JavaIII";
			byte[] data = meddelande.getBytes();

			// Skapar InetAddress till den dator meddelande ska skickas
			// Anv�nder getLocalHost eftersom vi skickar till oss sj�lva
			InetAddress till = InetAddress.getLocalHost();

			// Skapar datagrammet och anger att den mottagande datorn "lyssnar" p� porten 2345
			DatagramPacket paket = new DatagramPacket(data, data.length, till, 2345);

			// Skapar en socket f�r att skicka datagrammet. St�nger n�r vi �r klara
			DatagramSocket socket = new DatagramSocket();

			System.out.print("Skickar meddelandet... ");
			socket.send(paket);
			System.out.println("klart");

			socket.close();
		}
		catch (UnknownHostException addressFel)
		{
			// Kunde inte skapa InetAddress
			addressFel.printStackTrace();
		}
		catch (SocketException socketFel)
		{
			// Kunde inte skapa DatagramSocket
			socketFel.printStackTrace();
		}
		catch (IOException ioFel)
		{
			// Kunde inte skicka paketet
			ioFel.printStackTrace();
		}

    }
}