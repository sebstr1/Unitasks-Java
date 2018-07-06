/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
 * @version 1.0
 * @file Ex05_02 - CountDownReceiver.java
 */

import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class CountDownReceiver extends JFrame
{
	private String multicastAddress = "225.200.200.200";
	private int multicastPort = 10002;

	// Deklarerar de komponenter som ska anv�ndas
	private JLabel time;

	// Konstruktorn i vilken vi skapar komponenterna
	public CountDownReceiver()
	{
		// S�tter titel p� f�nstret
		setTitle("Time left");

		// Anger vad som ska h�nda n�r vi st�nger
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
		MulticastSocket socket = null;
		InetAddress multicastGroup = null;

		try
		{
			multicastGroup = InetAddress.getByName(multicastAddress);
			socket = new MulticastSocket(multicastPort);
			socket.setSoTimeout(10000);
			socket.joinGroup(multicastGroup);
		}
		catch (IOException iofel)
		{
			System.out.println("Fel vid skapande av MulticastSocket:\n" + iofel.getMessage());
			System.exit(0);
		}

		byte[] data = new byte[1024];

		while (true)
		{
			//Skapar datagram f�r mottagning
			DatagramPacket packet = new DatagramPacket(data, data.length);

			// Tar emot meddelande
			try
			{
				socket.receive(packet);

				// Skapa str�ng
				String message = new String(data, 0, packet.getLength());
				time.setText(message);

				if (message.equals("00:00:00")) break;
			}
			catch (IOException iofel)
			{
				time.setText("Ingen kontakt");
				System.out.println("Kunde inte ta emot paket:\n" + iofel.getMessage());
				break;
			}
		}

		try
		{
			socket.leaveGroup(multicastGroup);
			socket.close();
		}
		catch (IOException iofel)
		{}

	}
	public static void main(String[] args)
	{
		// Skapar ett objekt av den egna klassen
		CountDownReceiver cdr = new CountDownReceiver();
		cdr.startCountDown();
	}
}