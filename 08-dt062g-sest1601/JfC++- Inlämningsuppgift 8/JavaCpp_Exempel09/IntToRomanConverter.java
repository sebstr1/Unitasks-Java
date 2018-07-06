/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
 * @version 1.0
 * @file Ex04_08 - IntToRomanConverter.java
 */

import java.net.*;
import java.io.*;

public class IntToRomanConverter
{
	public IntToRomanConverter(int port)
	{
		// Skapar en byte-array f�r att lagra data som tas emot
		byte[] data = new byte[2048];

		// Skapar socket
		DatagramSocket socket;
		try
		{
			socket = new DatagramSocket(port);
		}
		catch(SocketException socketfel)
		{
			System.out.println("Kunde inte skapa socket!\nMest troligt �r port " + port + " redan upptagen");
			return;
		}

		while (true)
		{
			try
			{
				// Skapar DatagramPacket f�r mottagning
				DatagramPacket inpaket = new DatagramPacket(data, data.length);

				// V�ntar p� att ett paket ska komma
				socket.receive(inpaket);

				// Behandla paketet (konvertera till romerskt tal)
				String svar = behandla(inpaket);

				// Konvertera svar till bytearray och skapa ett DatagramPacket f�r s�ndning
				byte[] b = svar.getBytes();
				DatagramPacket utpaket = new DatagramPacket(b, b.length, inpaket.getAddress(), inpaket.getPort());

				// Skicka svaret
				socket.send(utpaket);
			}
			catch (IOException ioFel)
			{
				// Kunde inte ta emot eller skicka paketet
				// Skriver ut ett felmeddelande och forts�tter att v�nta p� paket
				System.out.println(ioFel.toString());
			}
		}
    }

    private String behandla(DatagramPacket paket)
    {
		// Skapar en str�ng av inneh�llet i paketet
		String meddelande = new String(paket.getData(), 0, paket.getLength());

		// Skriver ut ett meddelande p� sk�rmen
		//System.out.println(new java.util.Date(System.currentTimeMillis()));
		System.out.println("Paket mottaget fr�n: " + paket.getAddress().getHostAddress() + ":" + paket.getPort());
		System.out.println("Inneh�ll: " + meddelande);

		String svar;
		// F�rs�ker konvertera till romerskt tal
		try
		{
			int arabic = Integer.parseInt(meddelande);
			RomanNumeral roman = new RomanNumeral(arabic);

			svar = roman.toString();
		}
		catch (Exception fel)
		{
			// Om n�got g�r fel returnerar vi felmeddelande
			// Fel kan ske om str�ngen inte inneh�ller ett giltigt heltal
			// Fel kan ske om heltalet inte ligger i intervallet 1-3999
			svar = fel.toString();
		}

		System.out.println("Svar:     " + svar + "\n");
		return svar;
	}

    public static void main(String[] args)
    {
		// Kontrollerar om det finns ett argument (port)
		if (args.length == 1)
		{
			new IntToRomanConverter(Integer.parseInt(args[0]));
		}
		else
		{
			// Anv�nder annars port 10000
			new IntToRomanConverter(10000);
		}
	}
}