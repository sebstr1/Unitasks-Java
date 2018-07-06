/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_12 - TestAvSlumpaPunkter.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestAvSlumpaPunkter extends JFrame implements ActionListener
{
	// Deklarerar de komponenter som ska användas
	private JPanel panel;
	private JButton knappSluta;
	private SlumpTråd slumpTråd;

	// Konstruktorn i vilken vi skapar komponenterna
	public TestAvSlumpaPunkter()
	{
		// Sätter titel på fönstret
		setTitle("Test av SlumpaPunkter");

		// Anger vad som ska hända när vi stänger
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Sätter storlek och placering av fönstret
		setSize(400, 300);
		setLocation(200, 200);
		setResizable(false);

		// Skapar komponenter
		panel = new JPanel();
		knappSluta = new JButton("Sluta slumpa");
		knappSluta.addActionListener(this);

		// Placerar ut komponenterna fönstret
		add(panel, BorderLayout.CENTER);
		add(knappSluta, BorderLayout.SOUTH);

		// Gör fönstret synligt
		setVisible(true);

		// Skapar och startar tråden skickar som argument den panel
		// i vilken vi vill att punkterna ska ritas på
		slumpTråd = new SlumpTråd(panel);
		slumpTråd.start();
	}

	// Händelselyssnare
	public void actionPerformed(ActionEvent ae)
	{
		// Vi gör ingen kontroll av vilken knapp som genererat
		// händelsen eftersom vi enbart har en knapp
		slumpTråd.avbryt();
	}

	public static void main(String[] args)
	{
		// Skapar ett objekt av den egna klassen
		TestAvSlumpaPunkter test = new TestAvSlumpaPunkter();
	}
}