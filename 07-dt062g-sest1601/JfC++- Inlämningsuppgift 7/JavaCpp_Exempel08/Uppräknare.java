/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_01 - Uppräknare.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Uppräknare extends JLabel implements ActionListener
{
	private int värde; // Vilket värde uppräknaren har just nu
	private int fart;  // Antal värden per sekund som ska räknas upp

	public Uppräknare()
	{
		this(0, 1);
	}

	public Uppräknare(int värde, int fart)
	{
		this.värde = värde;
		this.fart = fart;

		setHorizontalAlignment(JLabel.CENTER);
		setFont(new Font("Courier", Font.BOLD, 24));
		setText(Integer.toString(värde));

		// Skapa en timer och starta den
		Timer timer = new Timer(1000 / fart, this);
		timer.start();
	}

	public void actionPerformed(ActionEvent ae)
	{
		// Hit kommer vi varje gång vår timer genererar en händelse

		// Vi ökar värdet med ett och uppdaterar sen texten
		värde++;
		setText(Integer.toString(värde));
	}
}