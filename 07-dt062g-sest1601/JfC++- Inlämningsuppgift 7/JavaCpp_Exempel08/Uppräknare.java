/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_01 - Uppr�knare.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Uppr�knare extends JLabel implements ActionListener
{
	private int v�rde; // Vilket v�rde uppr�knaren har just nu
	private int fart;  // Antal v�rden per sekund som ska r�knas upp

	public Uppr�knare()
	{
		this(0, 1);
	}

	public Uppr�knare(int v�rde, int fart)
	{
		this.v�rde = v�rde;
		this.fart = fart;

		setHorizontalAlignment(JLabel.CENTER);
		setFont(new Font("Courier", Font.BOLD, 24));
		setText(Integer.toString(v�rde));

		// Skapa en timer och starta den
		Timer timer = new Timer(1000 / fart, this);
		timer.start();
	}

	public void actionPerformed(ActionEvent ae)
	{
		// Hit kommer vi varje g�ng v�r timer genererar en h�ndelse

		// Vi �kar v�rdet med ett och uppdaterar sen texten
		v�rde++;
		setText(Integer.toString(v�rde));
	}
}