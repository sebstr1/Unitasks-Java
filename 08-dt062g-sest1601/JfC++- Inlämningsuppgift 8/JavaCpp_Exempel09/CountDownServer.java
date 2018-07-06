/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM Östersund
 * @version 1.0
 * @file Ex05_05 - CountDownServer.java
 */

import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class CountDownServer
{
	// Den port servern ska lyssna på
	private int port;

	// Konstruktor
	public CountDownServer(int port)
	{
		this.port = port;
		countDown();
	}

	public void countDown()
	{
		// Deklarerar objekt som används för kommunikationen
		ServerSocket ss = null;
		Socket s = null;
		BufferedReader in = null;
		BufferedWriter out = null;  // 1) se fotnot längs ner

		try
		{
			// Skapar ServerSocket och väntar på att en klient ska ansluta
			ss = new ServerSocket(port);
			System.out.println("Lyssnar på port: " + port);
			s = ss.accept();

			// Skapar in- och utströmmar för kommunikation
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

			System.out.println("Klient ansluten med adress: " + s.getInetAddress().getHostAddress());
		}
		catch (IOException ioe)
		{
			System.out.println("Fel vid upprättande av förbindelse:\n" + ioe);
			return;
		}

		// Vi har nu en uppkopplad förbindelse med klienten
		// Dags att ta emot tiden som vi ska räkna ner till
		Date targetDate = null;
		try
		{
			// Ta emot tiden från klienten
			String target = in.readLine();

			try
			{
				// Skapar det datum vi ska räkna ner till
				targetDate = DateFormat.getInstance().parse(target);
			}
			catch (ParseException pe)
			{
				System.out.println("Felaktigt datum " + target + "\n" + pe);
				out.write("Felaktigt datum");
				out.newLine();
				out.flush();
				return;
			}

			// Datum skapades ok. Skickar klartecken till klienten
			out.write("Datum OK");
			out.newLine();
			out.flush();
		}
		catch (IOException ioe)
		{
			System.out.println("Kunde inte läsa datum från klienten: \n" + ioe);
			return;
		}


		// Håll på så länge som slutdatum inte nåtts
		boolean targetDateReached = false;
		while(!targetDateReached)
		{
			// Tar reda på hur många millisekunder det är kvar till targetDate
			long dif = targetDate.getTime() - System.currentTimeMillis();

			String message;

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
				message = df.format(hours) + ":" + df.format(minutes) + ":" + df.format(seconds);
			}
			else
			{
				// Slutdatum är nådd
				message = "00:00:00";
				targetDateReached = true;
			}


			try
			{
				// Skickar tiden till klient
				System.out.println("Skickar: " + message);
				out.write(message);
				out.newLine();
				out.flush();
			}
			catch (IOException ioe)
			{
				System.out.println("Kunde inte sända tid:\n" + ioe);
				break;
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

		// Stänger strömmar och socket
		try
		{
			in.close();
			out.close();
			s.close();
			ss.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Kunde inte stänga strömmar och/eller sockets:\n" + ioe);
		}
	}

	public static void main(String[] args)
	{
		new CountDownServer(10003);

    }
}

/*
1) Klassen PrintWriter och metoderna print och println kastar inte några undantag. Därför går det t.ex.
inte att upptäcka om klienten stänger ner sin förbindelse. Normalt när någon sida stänger den socket som
används för kommunikation genereras på andra sidan ett SocketException. Nu när det enda servern gör är att
skicka data så kan vi inte fånga upp detta SocketException om vi använder PrintWrtier och print/println.
Därför är det smartare att i detta fall använda BufferedWriter samt metoderna write/nelLine och flush.
*/