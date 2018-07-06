/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_08 - TestAvVäxerKrymper.java
 */

import java.awt.*;
import javax.swing.*;

public class TestAvVäxerKrymper extends JFrame
{
	// Deklarerar de komponenter som ska användas
	// Vilket är vår egen komponent VäxerKrymperPanel
	private VäxerKrymperPanel panel;

	// Konstruktorn i vilken vi skapar komponenterna
	public TestAvVäxerKrymper()
	{
		// Sätter titel på fönstret
		setTitle("Test av VäxerKrymper");

		// Anger vad som ska hända när vi stänger
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Sätter storlek och placering av fönstret
		setSize(400, 300);
		setLocation(200, 200);

		// Skapar komponenten (panelen)
		panel = new VäxerKrymperPanel();

		// Placerar ut komponenterna i mitten av fönstret
		add(panel, BorderLayout.CENTER);

		// Gör fönstret synligt
		setVisible(true);
	}

	public static void main(String[] args)
	{
		// Skapar ett objekt av den egna klassen
		TestAvVäxerKrymper test = new TestAvVäxerKrymper();
	}
}