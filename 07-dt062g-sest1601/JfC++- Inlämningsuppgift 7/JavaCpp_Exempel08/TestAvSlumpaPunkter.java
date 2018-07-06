/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_12 - TestAvSlumpaPunkter.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestAvSlumpaPunkter extends JFrame implements ActionListener
{
	// Deklarerar de komponenter som ska anv�ndas
	private JPanel panel;
	private JButton knappSluta;
	private SlumpTr�d slumpTr�d;

	// Konstruktorn i vilken vi skapar komponenterna
	public TestAvSlumpaPunkter()
	{
		// S�tter titel p� f�nstret
		setTitle("Test av SlumpaPunkter");

		// Anger vad som ska h�nda n�r vi st�nger
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// S�tter storlek och placering av f�nstret
		setSize(400, 300);
		setLocation(200, 200);
		setResizable(false);

		// Skapar komponenter
		panel = new JPanel();
		knappSluta = new JButton("Sluta slumpa");
		knappSluta.addActionListener(this);

		// Placerar ut komponenterna f�nstret
		add(panel, BorderLayout.CENTER);
		add(knappSluta, BorderLayout.SOUTH);

		// G�r f�nstret synligt
		setVisible(true);

		// Skapar och startar tr�den skickar som argument den panel
		// i vilken vi vill att punkterna ska ritas p�
		slumpTr�d = new SlumpTr�d(panel);
		slumpTr�d.start();
	}

	// H�ndelselyssnare
	public void actionPerformed(ActionEvent ae)
	{
		// Vi g�r ingen kontroll av vilken knapp som genererat
		// h�ndelsen eftersom vi enbart har en knapp
		slumpTr�d.avbryt();
	}

	public static void main(String[] args)
	{
		// Skapar ett objekt av den egna klassen
		TestAvSlumpaPunkter test = new TestAvSlumpaPunkter();
	}
}