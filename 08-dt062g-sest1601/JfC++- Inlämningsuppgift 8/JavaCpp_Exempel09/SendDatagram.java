/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM Östersund
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
			// Gör i ordning meddelandet som ska skickas
			String meddelande = "JavaIII";
			byte[] data = meddelande.getBytes();

			// Skapar InetAddress till den dator meddelande ska skickas
			// Använder getLocalHost eftersom vi skickar till oss själva
			InetAddress till = InetAddress.getLocalHost();

			// Skapar datagrammet och anger att den mottagande datorn "lyssnar" på porten 2345
			DatagramPacket paket = new DatagramPacket(data, data.length, till, 2345);

			// Skapar en socket för att skicka datagrammet. Stänger när vi är klara
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