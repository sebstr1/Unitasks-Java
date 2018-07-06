/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_02 - TestAvUppr�knare.java
 */

import java.awt.*;
import javax.swing.*;

public class TestAvUppr�knare extends JFrame
{
	// Deklarerar de komponenter som ska anv�ndas
	// Vilket �r v�r omdefinierade JLabel
	private Uppr�knare r�knare1;
	private Uppr�knare r�knare2;
	private Uppr�knare r�knare3;

	// Konstruktorn i vilken vi skapar komponenterna
	public TestAvUppr�knare()
	{
		// S�tter titel p� f�nstret
		setTitle("Test av uppr�knare");

		// Anger vad som ska h�nda n�r vi st�nger
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// S�tter storlek och placering av f�nstret
		setSize(400, 75);
		setLocation(200, 200);

		// Skapar layout
		setLayout(new GridLayout(1, 3));

		// Skapar komponenterna (uppr�knarna)
		r�knare1 = new Uppr�knare();
		r�knare2 = new Uppr�knare(1000, 10);
		r�knare3 = new Uppr�knare(0, 100);

		// Placerar ut komponenterna i f�nstret
		add(r�knare1);
		add(r�knare2);
		add(r�knare3);

		// G�r f�nstret synligt
		setVisible(true);
	}

	public static void main(String[] args)
	{
		// Skapar ett objekt av den egna klassen
		TestAvUppr�knare test = new TestAvUppr�knare();
	}
}