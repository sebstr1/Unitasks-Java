/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_06 - Linje.java
 */

import java.awt.*;

public class Linje extends RitObjekt
{
	// Konstruktor
	public Linje(int x, int y)
	{
		// Anropar superklassens konstruktor och anger
		// st�rsta och minsta storlek objektet kan ha
		super(x, y, 30, 10);
	}

	// Denna metod kommer att anropas av den som vill rita ut
	// objektet p� sk�rmen. Som parameter skickas det Graphics-
	// objekt som ska anv�ndas f�r att rita.
	public void rita(Graphics g)
	{
		g.drawLine(x, y - storlek / 2 , x, y + storlek / 2);
	}
}