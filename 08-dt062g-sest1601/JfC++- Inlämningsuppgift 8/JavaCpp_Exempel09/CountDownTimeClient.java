/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM Östersund
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

	// Deklarerar de komponenter som ska användas
	private JLabel time;

	// Konstruktorn i vilken vi skapar komponenterna
	public CountDownTimeClient(String address, int port)
	{
		serverAddress = address;
		serverPort = port;

		// Sätter titel på fönstret
		setTitle("Tid kvar till");

		// Anger vad som ska hända när vi stänger
		// Bör egentligen använda en WindowListener så att
		// vi kan stänga eventuella öppna strömmar och sockets
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Sätter storlek och placering av fönstret
		setSize(250, 75);
		setLocation(200, 200);

		// Skapar komponenten
		time = new JLabel("Kontaktar server");
		time.setHorizontalAlignment(JLabel.CENTER);
		time.setFont(new Font("Verdana", Font.BOLD, 24));

		// Placerar ut komponenten i fönstret
		add(time, BorderLayout.CENTER);

		// Gör fönstret synligt
		setVisible(true);
	}

	public void startCountDown(String target, String event)
	{
		// Skapar socket och strömmar
		Socket s = null;
		ObjectInputStream in = null;
		ObjectOutputStream out = null;

		try
		{
			s = new Socket(serverAddress, serverPort);
			s.setSoTimeout(10000);

			// Viktigt att ObjectOutputStream skapas först eftersom
			// ObjectInputStream blockerar tills att motsvarande
			// ObjectOutputStream skickar en header
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());

		}
		catch (IOException ioe)
		{
			System.out.println("Fel vid upprättande av förbindelse med server:\n" + ioe);
			time.setText("Ingen kontakt");
			return;
		}

		// Vi har en förbindelse med server så vi skickar det
		// datum vi vill att servern ska räkna ner till
		try
		{
			time.setText("Skickar datum");
			out.writeObject(new CountDownTime(target, event));
			out.flush();

			CountDownTime response = (CountDownTime)in.readObject();

			if (!response.getEvent().equals("Datum OK"))
			{
				// Något gick fel när server skulle skapa datumet
				time.setText(response.getEvent());
				return; // Bör stänga strömmar/sockets först
			}
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Hittar inte klassen CountDownTime:\n" + cnfe);
			time.setText("Avbryter");
			return; // Bör stänga strömmar/sockets först
		}
		catch (IOException ioe)
		{
			System.out.println("Fel vid kommunikationen med server:\n" + ioe);
			time.setText("Ingen kontakt");
			return; // Bör stänga strömmar/sockets först
		}

		// Slutdatum är satt. Dags att börja ta emot tiden.
		while (true)
		{
			try
			{
				// Tar emot tid från servern
				CountDownTime cdt = (CountDownTime)in.readObject();
				time.setText(cdt.getTime());
				setTitle("Tid kvar till " + cdt.getEvent());

				if (cdt.getTime().equals("00:00:00")) break;
			}
			catch (ClassNotFoundException cnfe)
			{
				System.out.println("Hittar inte klassen CountDownTime:\n" + cnfe);
				time.setText("Avbryter");
				return; // Bör stänga strömmar/sockets först
			}
			catch (IOException ioe)
			{
				// Mest troligt har servern stängts ner
				System.out.println("Kunde inte ta emot tid:\n" + ioe);
				time.setText("Ingen kontakt");
				break;
			}
		}

		// Stänger strömmar och socket
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
		cdc.startCountDown("2006-12-24 15:00", "Kalle Anka på julafton");
	}
}