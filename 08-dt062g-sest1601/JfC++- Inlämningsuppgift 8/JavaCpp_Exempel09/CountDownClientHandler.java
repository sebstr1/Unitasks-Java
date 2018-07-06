/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
 * @version 1.0
 * @file Ex05_08 - CountDownClientHandler.java
 */

import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class CountDownClientHandler extends Thread
{
	// Den socket kommunikation sker p�
	private Socket socket;

	// Klientens adress
	private String address;

	// Konstruktor
	public CountDownClientHandler(Socket s)
	{
		socket = s;
		address = socket.getInetAddress().getHostAddress();
	}


	public void run()
	{
		System.out.println("Hello from ClientHandler?");
		// Deklarerar instr�m och utstr�m
		BufferedReader in = null;
		BufferedWriter out = null;  // 1) se fotnot l�ngs ner

		try
		{
			// Skapar in- och utstr�mmar f�r kommunikation
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		}
		catch (IOException ioe)
		{
			System.out.println(address + ": Kunde inte skapa str�mmar");
			return; // B�r st�nga str�mmar och sockets f�rst
		}

		// Vi har nu en uppkopplad f�rbindelse med klienten
		// Dags att ta emot tiden som vi ska r�kna ner till
		Date targetDate = null;
		try
		{
			// Ta emot tiden fr�n klienten
			String target = in.readLine();

			try
			{
				// Skapar det datum vi ska r�kna ner till
				targetDate = DateFormat.getInstance().parse(target);
			}
			catch (ParseException pe)
			{
				System.out.println(address + ": Felaktigt datum " + target);
				System.out.println(address + ": Avbryter f�rbindelsen");
				out.write("Felaktigt datum");
				out.newLine();
				out.flush();
				return; // B�r st�nga str�mmar och sockets f�rst
			}

			// Datum skapades ok. Skickar klartecken till klienten
			out.write("Datum OK");
			out.newLine();
			out.flush();
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
				System.out.println(address + ": Slutdatum n�tt");
			}


			try
			{
				// Skickar tiden till klient
				out.write(message);
				out.newLine();
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