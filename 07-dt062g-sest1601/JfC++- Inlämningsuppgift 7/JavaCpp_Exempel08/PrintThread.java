/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_17 - PrintThread.java
 */

public class PrintThread extends Thread
{
	// Den text som ska skrivas ut
	private String text;

	// Den TelePrinter som ska anv�ndas vid utskrift
	TelePrinter printer;

	// Konstruktorn
	public PrintThread(TelePrinter printer, String text)
	{
		this.printer = printer;
		this.text = text;
	}

	public void run()
	{
		// Anropar teleprinterns print metod f�r att sk�ta utskriften
		printer.print(text);
	}
}