/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM Östersund
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

	// Deklarerar de komponenter som ska användas
	private JLabel time;

	// Konstruktorn i vilken vi skapar komponenterna
	public CountDownClient(String address, int port, String target)
	{
		serverAddress = address;
		serverPort = port;
		targetDate = target;

		// Sätter titel på fönstret
		setTitle("Time left");

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

	public void startCountDown()
	{
		// Skapar socket och strömmar
		Socket s = null;
		BufferedReader in = null;
		PrintWriter out = null;

		try
		{
			s = new Socket(serverAddress, serverPort);
			s.setSoTimeout(10000);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true); // Gör flush() efter varje print och println
		}
		catch (IOException ioe)
		{
			System.out.println("Fel vid upprättande av förbindelse med server:\n" + ioe);
			time.setText("Ingen kontakt");
			return;
		}

		// Vi har en förbindelse med server så vi skickar det
		// datum vi vill att serverna ska räkna ner till
		try
		{
			time.setText("Skickar datum");
			out.println(targetDate);
			String response = in.readLine();

			if (!response.equals("Datum OK"))
			{
				// Något gick fel när server skulle skapa datumet
				time.setText(response);
				return; // Bör stänga strömmar/sockets först
			}
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
				String message = in.readLine();
				time.setText(message);

				if (message.equals("00:00:00")) break;
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
		CountDownClient cdc = new CountDownClient("localhost", 10003, "2006-12-24 15:00");
		cdc.startCountDown();
	}
}