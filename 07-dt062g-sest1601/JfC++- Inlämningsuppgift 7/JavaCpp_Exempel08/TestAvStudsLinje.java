/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_20 - TestAvStudsLinjer.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestAvStudsLinje extends JFrame implements ActionListener
{
	// Deklarerar de komponenter som ska anv�ndas
	// Vilket �r v�r egen komponent V�xerKrymperPanel
	private StudsLinje linje;
	private JButton knappStarta;
	private JButton knappStoppa;

	// Konstruktorn i vilken vi skapar komponenterna
	public TestAvStudsLinje()
	{
		// S�tter titel p� f�nstret
		setTitle("Test av StudsLinje");

		// Anger vad som ska h�nda n�r vi st�nger
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// S�tter storlek och placering av f�nstret
		setSize(400, 300);
		setLocation(200, 200);

		// Skapar komponenterna
		// Prova g�rna att �ndra v�rden f�r StudsLinje nedan
		linje = new StudsLinje(15, true);
		knappStarta = new JButton("Starta");
		knappStarta.addActionListener(this);
		knappStoppa = new JButton("Stoppa");
		knappStoppa.addActionListener(this);

		// Skapar en panel i vilken de tv� knapparna l�ggs
		JPanel knappPanel = new JPanel(new GridLayout(1,2));
		knappPanel.add(knappStarta);
		knappPanel.add(knappStoppa);

		// Placerar ut komponenterna i f�nstret
		add(linje, BorderLayout.CENTER);
		add(knappPanel, BorderLayout.SOUTH);

		// G�r f�nstret synligt
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		// Vilken knapp har anv�ndaren klickat p�?
		if (ae.getSource() == knappStarta)
			linje.start();
		else if (ae.getSource() == knappStoppa)
			linje.stop();
	}

	public static void main(String[] args)
	{
		// Skapar ett objekt av den egna klassen
		TestAvStudsLinje test = new TestAvStudsLinje();
	}
}