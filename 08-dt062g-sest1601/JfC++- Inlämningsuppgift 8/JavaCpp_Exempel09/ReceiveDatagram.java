/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM Östersund
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
			// Skapar en byte-array för att lagra paketets data i
			byte[] data = new byte[1024];

			// Skapar DatagramPacket för mottagning
			DatagramPacket paket = new DatagramPacket(data, data.length);

			// Skapar socket för att ta emot datagram. Tar emot på port 2345
			DatagramSocket socket = new DatagramSocket(2345);

			// Väntar på att ett paket ska komma
			System.out.print("Väntar på paket...");
			socket.receive(paket);
			socket.close();

			// Tar reda på från vilken IP-adress paketet skickats
			InetAddress från = paket.getAddress();

			// Skapar en sträng av innehållet i paketet
			String meddelande = new String(paket.getData(), 0, paket.getLength());

			System.out.println("\n\nMeddelande från: " + från.getHostAddress() + ":" + paket.getPort());
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