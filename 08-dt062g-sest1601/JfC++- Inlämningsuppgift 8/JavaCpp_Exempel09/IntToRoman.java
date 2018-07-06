/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM Östersund
 * @version 1.0
 * @file Ex04_09 - IntToRoman.java
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class IntToRoman
{
	public IntToRoman(String mottagare, int port)
	{
		// Skapar Scanner för inmatning från tangentbort
		Scanner tgb = new Scanner(System.in);


		// Skapar en byte-array för att lagra data som tas emot
		byte[] data = new byte[2048];

		// Skapar socket samt IP-address till mottagaren
		InetAddress till;
		DatagramSocket socket;
		try
		{
			till = InetAddress.getByName(mottagare);
			socket = new DatagramSocket();
			socket.setSoTimeout(10000);
		}
		catch(UnknownHostException adressfel)
		{
			System.out.println("Kunde inte skapa IP-address!");
			return;
		}
		catch(SocketException socketfel)
		{
			System.out.println("Kunde inte skapa socket!");
			return;
		}

		while (true)
		{
			try
			{
				// Fråga efter ett tal
				System.out.print("\n>");
				String tal = tgb.nextLine();

				// Har användaren enbart tryckt på enter så avbryter vi
				if (tal.length() == 0)
					break;

				// Skapa bytearray som ska skickas
				byte[] b = tal.getBytes();

				// Skapar datagram som ska skickas
				DatagramPacket utpaket = new DatagramPacket(b, b.length, till, port);

				// Skickar meddelandet
				socket.send(utpaket);

				// Skapar datagram som ska tas emot
				DatagramPacket inpaket = new DatagramPacket(data, data.length);

				// Tar emot svaret
				socket.receive(inpaket);

				// Skapar en sträng av det mottagna data
				String svar = new String(inpaket.getData(), 0, inpaket.getLength());

				System.out.println(svar);
			}
			catch(SocketTimeoutException socketfel)
			{
				System.out.println("10 sekunder har passerat utan svar");
			}
			catch (IOException iofel)
			{
				// Kunde inte skicka/ta emot paketet
				System.out.println(iofel.toString());
			}
		}

    }

    public static void main(String[] args)
	{
		// Kontrollerar om det finns två argument (ip och port)
		if (args.length == 2)
		{
			new IntToRoman(args[0], Integer.parseInt(args[1]));
		}
		else
		{
			// Använder annars localhost och port 10000
			new IntToRoman("localhost", 10000);
		}
	}
}