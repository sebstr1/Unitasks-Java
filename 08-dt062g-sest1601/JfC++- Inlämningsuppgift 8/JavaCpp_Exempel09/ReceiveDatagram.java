/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
 * @version 1.0
 * @file Ex04_07 - ReceiveDatagram.java
 */

import java.net.*;
import java.io.*;

public class ReceiveDatagram
{
	public static void main(String[] args)
	{
		try
		{
			// Skapar en byte-array f�r att lagra paketets data i
			byte[] data = new byte[1024];

			// Skapar DatagramPacket f�r mottagning
			DatagramPacket paket = new DatagramPacket(data, data.length);

			// Skapar socket f�r att ta emot datagram. Tar emot p� port 2345
			DatagramSocket socket = new DatagramSocket(2345);

			// V�ntar p� att ett paket ska komma
			System.out.print("V�ntar p� paket...");
			socket.receive(paket);
			socket.close();

			// Tar reda p� fr�n vilken IP-adress paketet skickats
			InetAddress fr�n = paket.getAddress();

			// Skapar en str�ng av inneh�llet i paketet
			String meddelande = new String(paket.getData(), 0, paket.getLength());

			System.out.println("\n\nMeddelande fr�n: " + fr�n.getHostAddress() + ":" + paket.getPort());
			System.out.println(meddelande);
		}
		catch (SocketException socketFel)
		{
			// Kunde inte skapa DatagramSocket
			socketFel.printStackTrace();
		}
		catch (IOException ioFel)
		{
			// Kunde inte ta emot paketet
			ioFel.printStackTrace();
		}
    }
}