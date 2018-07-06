/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_02 - TestAvUppräknare.java
 */

import java.awt.*;
import javax.swing.*;

public class TestAvUppräknare extends JFrame
{
	// Deklarerar de komponenter som ska användas
	// Vilket är vår omdefinierade JLabel
	private Uppräknare räknare1;
	private Uppräknare räknare2;
	private Uppräknare räknare3;

	// Konstruktorn i vilken vi skapar komponenterna
	public TestAvUppräknare()
	{
		// Sätter titel på fönstret
		setTitle("Test av uppräknare");

		// Anger vad som ska hända när vi stänger
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Sätter storlek och placering av fönstret
		setSize(400, 75);
		setLocation(200, 200);

		// Skapar layout
		setLayout(new GridLayout(1, 3));

		// Skapar komponenterna (uppräknarna)
		räknare1 = new Uppräknare();
		räknare2 = new Uppräknare(1000, 10);
		räknare3 = new Uppräknare(0, 100);

		// Placerar ut komponenterna i fönstret
		add(räknare1);
		add(räknare2);
		add(räknare3);

		// Gör fönstret synligt
		setVisible(true);
	}

	public static void main(String[] args)
	{
		// Skapar ett objekt av den egna klassen
		TestAvUppräknare test = new TestAvUppräknare();
	}
}