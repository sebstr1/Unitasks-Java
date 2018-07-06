/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
 * @version 1.0
 * @file Ex05_11 - CountDownTimeClientHandler.java
 */

import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class CountDownTimeClientHandler extends Thread
{
	// Den socket kommunikation sker p�
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
		// Deklarerar instr�m och utstr�m
		ObjectInputStream in = null;
		ObjectOutputStream out = null;

		try
		{
			// Skapar in- och utstr�mmar f�r kommunikation

			// Viktigt att ObjectOutputStream skapas f�rst eftersom
			// ObjectInputStream blockerar tills att motsvarande
			// ObjectOutputStream skickar en header
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

		}
		catch (IOException ioe)
		{
			System.out.println(address + ": Kunde inte skapa str�mmar");
			return; // B�r st�nga str�mmar och sockets f�rst
		}

		// Vi har nu en uppkopplad f�rbindelse med klienten
		// Dags att ta emot tiden som vi ska r�kna ner till
		Date targetDate = null;
		String event = null;
		try
		{
			// Ta emot tiden fr�n klienten
			CountDownTime target = (CountDownTime)in.readObject();
			event = target.getEvent();

			try
			{
				// Skapar det datum vi ska r�kna ner till
				targetDate = DateFormat.getInstance().parse(target.getTime());
			}
			catch (ParseException pe)
			{
				System.out.println(address + ": Felaktigt datum " + target);
				System.out.println(address + ": Avbryter f�rbindelsen");
				out.writeObject(new CountDownTime("00:00:00", "Felaktigt datum"));
				out.flush();
				return; // B�r st�nga str�mmar och sockets f�rst
			}

			// Datum skapades ok. Skickar klartecken till klienten
			out.writeObject(new CountDownTime("00:00:00", "Datum OK"));
			out.flush();
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println(address + ": Hittar inte klassen CountDownTime");
			return; // B�r st�nga str�mmar/sockets f�rst
		}
		catch (IOException ioe)
		{
			System.out.println(address + ": Kunde inte l�sa datum fr�n klienten");
			System.out.println(address + ": Avbryter f�rbindelsen");
			return; // B�r st�nga str�mmar och sockets f�rst
		}

		System.out.println(address + ": R�knar ner till " + targetDate);
		// H�ll p� s� l�nge som slutdatum inte n�tts
		boolean targetDateReached = false;
		while(!targetDateReached)
		{
			// Tar reda p� hur m�nga millisekunder det �r kvar till targetDate
			long dif = targetDate.getTime() - System.currentTimeMillis();

			String time;

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
				time = df.format(hours) + ":" + df.format(minutes) + ":" + df.format(seconds);
			}
			else
			{
				// Slutdatum �r n�dd
				time = "00:00:00";
				targetDateReached = true;
				event = "slutdatum n�dd";
				System.out.println(address + ": Slutdatum n�dd");
			}


			try
			{
				// Skickar tiden till klient
				out.writeObject(new CountDownTime(time, event));
				out.flush();
			}
			catch (IOException ioe)
			{
				System.out.println(address + ": Kunde inte s�nda tid");
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

		// St�nger str�mmar och socket
		try
		{
			in.close();
			out.close();
			socket.close();
			System.out.println(address + ": Nedkopplad");
		}
		catch(IOException ioe)
		{
			System.out.println(address + " Kunde inte st�nga str�mmar och/eller sockets");
		}
	}
}