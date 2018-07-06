/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM Östersund
 * @version 1.0
 * @file Ex05_11 - CountDownTimeClientHandler.java
 */

import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class CountDownTimeClientHandler extends Thread
{
	// Den socket kommunikation sker på
	private Socket socket;

	// Klientens adress
	private String address;

	// Konstruktor
	public CountDownTimeClientHandler(Socket s)
	{
		socket = s;
		address = socket.getInetAddress().getHostAddress();
	}


	public void run()
	{
		// Deklarerar inström och utström
		ObjectInputStream in = null;
		ObjectOutputStream out = null;

		try
		{
			// Skapar in- och utströmmar för kommunikation

			// Viktigt att ObjectOutputStream skapas först eftersom
			// ObjectInputStream blockerar tills att motsvarande
			// ObjectOutputStream skickar en header
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

		}
		catch (IOException ioe)
		{
			System.out.println(address + ": Kunde inte skapa strömmar");
			return; // Bör stänga strömmar och sockets först
		}

		// Vi har nu en uppkopplad förbindelse med klienten
		// Dags att ta emot tiden som vi ska räkna ner till
		Date targetDate = null;
		String event = null;
		try
		{
			// Ta emot tiden från klienten
			CountDownTime target = (CountDownTime)in.readObject();
			event = target.getEvent();

			try
			{
				// Skapar det datum vi ska räkna ner till
				targetDate = DateFormat.getInstance().parse(target.getTime());
			}
			catch (ParseException pe)
			{
				System.out.println(address + ": Felaktigt datum " + target);
				System.out.println(address + ": Avbryter förbindelsen");
				out.writeObject(new CountDownTime("00:00:00", "Felaktigt datum"));
				out.flush();
				return; // Bör stänga strömmar och sockets först
			}

			// Datum skapades ok. Skickar klartecken till klienten
			out.writeObject(new CountDownTime("00:00:00", "Datum OK"));
			out.flush();
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println(address + ": Hittar inte klassen CountDownTime");
			return; // Bör stänga strömmar/sockets först
		}
		catch (IOException ioe)
		{
			System.out.println(address + ": Kunde inte läsa datum från klienten");
			System.out.println(address + ": Avbryter förbindelsen");
			return; // Bör stänga strömmar och sockets först
		}

		System.out.println(address + ": Räknar ner till " + targetDate);
		// Håll på så länge som slutdatum inte nåtts
		boolean targetDateReached = false;
		while(!targetDateReached)
		{
			// Tar reda på hur många millisekunder det är kvar till targetDate
			long dif = targetDate.getTime() - System.currentTimeMillis();

			String time;

			// Om skillnaden är positiv har vi ännu inte nått slutdatumet
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
				time = df.format(hours) + ":" + df.format(minutes) + ":" + df.format(seconds);
			}
			else
			{
				// Slutdatum är nådd
				time = "00:00:00";
				targetDateReached = true;
				event = "slutdatum nådd";
				System.out.println(address + ": Slutdatum nådd");
			}


			try
			{
				// Skickar tiden till klient
				out.writeObject(new CountDownTime(time, event));
				out.flush();
			}
			catch (IOException ioe)
			{
				System.out.println(address + ": Kunde inte sända tid");
				break;
			}

			// Sover en stund
			try
			{
				sleep(1000);
			}
			catch (InterruptedException ie)
			{
				break;
			}
		}

		// Stänger strömmar och socket
		try
		{
			in.close();
			out.close();
			socket.close();
			System.out.println(address + ": Nedkopplad");
		}
		catch(IOException ioe)
		{
			System.out.println(address + " Kunde inte stänga strömmar och/eller sockets");
		}
	}
}