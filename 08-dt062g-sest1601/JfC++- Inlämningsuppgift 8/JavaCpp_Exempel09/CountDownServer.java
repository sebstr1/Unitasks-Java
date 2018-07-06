/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
 * @version 1.0
 * @file Ex05_05 - CountDownServer.java
 */

import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class CountDownServer
{
	// Den port servern ska lyssna p�
	private int port;

	// Konstruktor
	public CountDownServer(int port)
	{
		this.port = port;
		countDown();
	}

	public void countDown()
	{
		// Deklarerar objekt som anv�nds f�r kommunikationen
		ServerSocket ss = null;
		Socket s = null;
		BufferedReader in = null;
		BufferedWriter out = null;  // 1) se fotnot l�ngs ner

		try
		{
			// Skapar ServerSocket och v�ntar p� att en klient ska ansluta
			ss = new ServerSocket(port);
			System.out.println("Lyssnar p� port: " + port);
			s = ss.accept();

			// Skapar in- och utstr�mmar f�r kommunikation
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

			System.out.println("Klient ansluten med adress: " + s.getInetAddress().getHostAddress());
		}
		catch (IOException ioe)
		{
			System.out.println("Fel vid uppr�ttande av f�rbindelse:\n" + ioe);
			return;
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
			System.out.println("Kunde inte l�sa datum fr�n klienten: \n" + ioe);
			return;
		}


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
				System.out.println("Kunde inte s�nda tid:\n" + ioe);
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

		// St�nger str�mmar och socket
		try
		{
			in.close();
			out.close();
			s.close();
			ss.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Kunde inte st�nga str�mmar och/eller sockets:\n" + ioe);
		}
	}

	public static void main(String[] args)
	{
		new CountDownServer(10003);

    }
}

/*
1) Klassen PrintWriter och metoderna print och println kastar inte n�gra undantag. D�rf�r g�r det t.ex.
inte att uppt�cka om klienten st�nger ner sin f�rbindelse. Normalt n�r n�gon sida st�nger den socket som
anv�nds f�r kommunikation genereras p� andra sidan ett SocketException. Nu n�r det enda servern g�r �r att
skicka data s� kan vi inte f�nga upp detta SocketException om vi anv�nder PrintWrtier och print/println.
D�rf�r �r det smartare att i detta fall anv�nda BufferedWriter samt metoderna write/nelLine och flush.
*/