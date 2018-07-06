/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_16 - TelePrinter.java
 */

public class TelePrinter
{
	public /*synchronized*/ void print(String s)
	{
		// Loopa igenom alla tecken i str�ngen
		for (int i = 0; i < s.length(); i++)
		{
			// Skriv ut tecknet p� sk�rmen och sov en
			// stund innan n�sta utskrift sker
			System.out.print(s.charAt(i));
			try { Thread.sleep(100); }
			catch (InterruptedException ie) {}
		}

		// Skriv en radbrytning
		System.out.println();
	}
}