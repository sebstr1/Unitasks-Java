/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_20 - TestAvStudsLinjer.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestAvStudsLinje extends JFrame implements ActionListener
{
	// Deklarerar de komponenter som ska användas
	// Vilket är vår egen komponent VäxerKrymperPanel
	private StudsLinje linje;
	private JButton knappStarta;
	private JButton knappStoppa;

	// Konstruktorn i vilken vi skapar komponenterna
	public TestAvStudsLinje()
	{
		// Sätter titel på fönstret
		setTitle("Test av StudsLinje");

		// Anger vad som ska hända när vi stänger
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Sätter storlek och placering av fönstret
		setSize(400, 300);
		setLocation(200, 200);

		// Skapar komponenterna
		// Prova gärna att ändra värden för StudsLinje nedan
		linje = new StudsLinje(15, true);
		knappStarta = new JButton("Starta");
		knappStarta.addActionListener(this);
		knappStoppa = new JButton("Stoppa");
		knappStoppa.addActionListener(this);

		// Skapar en panel i vilken de två knapparna läggs
		JPanel knappPanel = new JPanel(new GridLayout(1,2));
		knappPanel.add(knappStarta);
		knappPanel.add(knappStoppa);

		// Placerar ut komponenterna i fönstret
		add(linje, BorderLayout.CENTER);
		add(knappPanel, BorderLayout.SOUTH);

		// Gör fönstret synligt
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		// Vilken knapp har användaren klickat på?
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