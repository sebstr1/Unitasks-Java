/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
 * @version 1.0
 * @file Ex05_12 - CountDownTimeClient.java
 */

import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class CountDownTimeClient extends JFrame
{
	private String serverAddress;
	private int serverPort;

	// Deklarerar de komponenter som ska anv�ndas
	private JLabel time;

	// Konstruktorn i vilken vi skapar komponenterna
	public CountDownTimeClient(String address, int port)
	{
		serverAddress = address;
		serverPort = port;

		// S�tter titel p� f�nstret
		setTitle("Tid kvar till");

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

	public void startCountDown(String target, String event)
	{
		// Skapar socket och str�mmar
		Socket s = null;
		ObjectInputStream in = null;
		ObjectOutputStream out = null;

		try
		{
			s = new Socket(serverAddress, serverPort);
			s.setSoTimeout(10000);

			// Viktigt att ObjectOutputStream skapas f�rst eftersom
			// ObjectInputStream blockerar tills att motsvarande
			// ObjectOutputStream skickar en header
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());

		}
		catch (IOException ioe)
		{
			System.out.println("Fel vid uppr�ttande av f�rbindelse med server:\n" + ioe);
			time.setText("Ingen kontakt");
			return;
		}

		// Vi har en f�rbindelse med server s� vi skickar det
		// datum vi vill att servern ska r�kna ner till
		try
		{
			time.setText("Skickar datum");
			out.writeObject(new CountDownTime(target, event));
			out.flush();

			CountDownTime response = (CountDownTime)in.readObject();

			if (!response.getEvent().equals("Datum OK"))
			{
				// N�got gick fel n�r server skulle skapa datumet
				time.setText(response.getEvent());
				return; // B�r st�nga str�mmar/sockets f�rst
			}
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Hittar inte klassen CountDownTime:\n" + cnfe);
			time.setText("Avbryter");
			return; // B�r st�nga str�mmar/sockets f�rst
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
				CountDownTime cdt = (CountDownTime)in.readObject();
				time.setText(cdt.getTime());
				setTitle("Tid kvar till " + cdt.getEvent());

				if (cdt.getTime().equals("00:00:00")) break;
			}
			catch (ClassNotFoundException cnfe)
			{
				System.out.println("Hittar inte klassen CountDownTime:\n" + cnfe);
				time.setText("Avbryter");
				return; // B�r st�nga str�mmar/sockets f�rst
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
		CountDownTimeClient cdc = new CountDownTimeClient("localhost", 10003);
		cdc.startCountDown("2006-12-24 15:00", "Kalle Anka p� julafton");
	}
}