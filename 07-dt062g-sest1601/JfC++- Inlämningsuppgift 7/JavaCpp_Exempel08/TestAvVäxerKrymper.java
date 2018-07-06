/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_08 - TestAvV�xerKrymper.java
 */

import java.awt.*;
import javax.swing.*;

public class TestAvV�xerKrymper extends JFrame
{
	// Deklarerar de komponenter som ska anv�ndas
	// Vilket �r v�r egen komponent V�xerKrymperPanel
	private V�xerKrymperPanel panel;

	// Konstruktorn i vilken vi skapar komponenterna
	public TestAvV�xerKrymper()
	{
		// S�tter titel p� f�nstret
		setTitle("Test av V�xerKrymper");

		// Anger vad som ska h�nda n�r vi st�nger
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// S�tter storlek och placering av f�nstret
		setSize(400, 300);
		setLocation(200, 200);

		// Skapar komponenten (panelen)
		panel = new V�xerKrymperPanel();

		// Placerar ut komponenterna i mitten av f�nstret
		add(panel, BorderLayout.CENTER);

		// G�r f�nstret synligt
		setVisible(true);
	}

	public static void main(String[] args)
	{
		// Skapar ett objekt av den egna klassen
		TestAvV�xerKrymper test = new TestAvV�xerKrymper();
	}
}