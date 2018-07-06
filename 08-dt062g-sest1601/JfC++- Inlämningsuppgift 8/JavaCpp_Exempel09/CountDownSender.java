/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
 * @version 1.0
 * @file Ex05_01 - CountDownSender.java
 */

import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class CountDownSender
{
	private String multicastAddress = "225.200.200.200";
	private int multicastPort = 10002;
	private Date targetDate;

	public CountDownSender(String target)
	{
		try
		{
			// Skapar det datum vi ska r�kna ner till
			targetDate = DateFormat.getInstance().parse(target);
		}
		catch (ParseException pe)
		{
			System.out.println("Kunde inte skapa datum:\n" + pe.getMessage());
			System.exit(0);
		}

		countDown();
	}

	public void countDown()
	{
		// Skapar socket och multicast-address
		MulticastSocket socket = null;
		InetAddress multicastGroup = null;

		try
		{
			multicastGroup = InetAddress.getByName(multicastAddress);
			socket = new MulticastSocket();
			socket.setTimeToLive(1);  // Skickas bara lokalt inom n�tverket
		}
		catch (IOException iofel)
		{
			System.out.println("Fel vid skapande av MulticastSocket:\n" + iofel.getMessage());
			System.exit(0);
		}

		// H�ll p� s� l�nge som slutdatum inte n�tts
		boolean targetDateReached = false;
		while(!targetDateReached)
		{
			// Nuvarande tid
			Date now = new Date(System.currentTimeMillis());

			// Tar reda p� hur m�nga millisekunder det �r kvar till targetDate
			long dif = targetDate.getTime() - now.getTime();

			String message;

			// Om skillnaden �r positiv har vi �nnu inte n�tt slutdatumet
			if (dif > 0)
			{
				// Delar upp tiden i timmar, minuter och sekunder
				long hours = dif / 3600000;
				dif %= 3600000;
				long minutes = dif / 60000;
				dif %= 60000;
				long seconds = dif / 1000;

				// Formaterar tiden
				DecimalFormat df = new java.text.DecimalFormat("00");
				message = df.format(hours) + ":" + df.format(minutes) + ":" + df.format(seconds);
			}
			else
			{
				// Slutdatum �r n�dd
				message = "00:00:00";
				targetDateReached = true;
			}

			// Skickar paketet
			try
			{
				System.out.println("Skickar: " + message);
				// Skapar datagram
				DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, multicastGroup, multicastPort);
				socket.send(packet);
			}
			catch (IOException iofel)
			{
				System.out.println("Kunde inte skicka:\n" + iofel.getMessage());
			}

			// Sover en stund
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException ie)
			{
				break;
			}
		}

		// St�nger socket
		socket.close();
	}

	public static void main(String[] args)
	{
		new CountDownSender("2006-12-24 15:00");

    }
}