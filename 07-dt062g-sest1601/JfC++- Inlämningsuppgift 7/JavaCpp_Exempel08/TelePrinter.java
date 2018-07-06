/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_16 - TelePrinter.java
 */

public class TelePrinter
{
	public /*synchronized*/ void print(String s)
	{
		// Loopa igenom alla tecken i strängen
		for (int i = 0; i < s.length(); i++)
		{
			// Skriv ut tecknet på skärmen och sov en
			// stund innan nästa utskrift sker
			System.out.print(s.charAt(i));
			try { Thread.sleep(100); }
			catch (InterruptedException ie) {}
		}

		// Skriv en radbrytning
		System.out.println();
	}
}