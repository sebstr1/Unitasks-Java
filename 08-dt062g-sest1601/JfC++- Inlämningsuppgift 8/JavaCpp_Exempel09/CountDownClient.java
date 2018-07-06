/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
 * @version 1.0
 * @file Ex05_06 - CountDownClient.java
 */

import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class CountDownClient extends JFrame
{
	private String serverAddress;
	private int serverPort;
	private String targetDate;

	// Deklarerar de komponenter som ska anv�ndas
	private JLabel time;

	// Konstruktorn i vilken vi skapar komponenterna
	public CountDownClient(String address, int port, String target)
	{
		serverAddress = address;
		serverPort = port;
		targetDate = target;

		// S�tter titel p� f�nstret
		setTitle("Time left");

		// Anger vad som ska h�nda n�r vi st�nger
		// B�r egentligen anv�nda en WindowListener s� att
		// vi kan st�nga eventuella �ppna str�mmar och sockets
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// S�tter storlek och placering av f�nstret
		setSize(250, 75);
		setLocation(200, 200);

		// Skapar komponenten
		time = new JLabel("Kontaktar server");
		time.setHorizontalAlignment(JLabel.CENTER);
		time.setFont(new Font("Verdana", Font.BOLD, 24));

		// Placerar ut komponenten i f�nstret
		add(time, BorderLayout.CENTER);

		// G�r f�nstret synligt
		setVisible(true);
	}

	public void startCountDown()
	{
		// Skapar socket och str�mmar
		Socket s = null;
		BufferedReader in = null;
		PrintWriter out = null;

		try
		{
			s = new Socket(serverAddress, serverPort);
			s.setSoTimeout(10000);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true); // G�r flush() efter varje print och println
		}
		catch (IOException ioe)
		{
			System.out.println("Fel vid uppr�ttande av f�rbindelse med server:\n" + ioe);
			time.setText("Ingen kontakt");
			return;
		}

		// Vi har en f�rbindelse med server s� vi skickar det
		// datum vi vill att serverna ska r�kna ner till
		try
		{
			time.setText("Skickar datum");
			out.println(targetDate);
			String response = in.readLine();

			if (!response.equals("Datum OK"))
			{
				// N�got gick fel n�r server skulle skapa datumet
				time.setText(response);
				return; // B�r st�nga str�mmar/sockets f�rst
			}
		}
		catch (IOException ioe)
		{
			System.out.println("Fel vid kommunikationen med server:\n" + ioe);
			time.setText("Ingen kontakt");
			return; // B�r st�nga str�mmar/sockets f�rst
		}

		// Slutdatum �r satt. Dags att b�rja ta emot tiden.
		while (true)
		{
			try
			{
				// Tar emot tid fr�n servern
				String message = in.readLine();
				time.setText(message);

				if (message.equals("00:00:00")) break;
			}
			catch (IOException ioe)
			{
				// Mest troligt har servern st�ngts ner
				System.out.println("Kunde inte ta emot tid:\n" + ioe);
				time.setText("Ingen kontakt");
				break;
			}
		}

		// St�nger str�mmar och socket
		try
		{
			in.close();
			out.close();
			s.close();
		}
		catch (IOException iofel)
		{}

	}
	public static void main(String[] args)
	{
		// Skapar ett objekt av den egna klassen
		CountDownClient cdc = new CountDownClient("localhost", 10003, "2006-12-24 15:00");
		cdc.startCountDown();
	}
}