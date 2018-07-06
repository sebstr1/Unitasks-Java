/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_05 - Boll.java
 */

import java.awt.*;

public class Boll extends RitObjekt
{
	// Konstruktor
	public Boll(int x, int y)
	{
		// Anropar superklassens konstruktor och anger
		// st�rsta och minsta storlek objektet kan ha
		super(x, y, 20, 1);
	}

	// Denna metod kommer att anropas av den som vill rita ut
	// objektet p� sk�rmen. Som parameter skickas det Graphics-
	// objekt som ska anv�ndas f�r att rita.
	public void rita(Graphics g)
	{
		// Ritar en fylld oval
		g.fillOval(x, y , storlek, storlek);
	}
}