/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_17 - PrintThread.java
 */

public class PrintThread extends Thread
{
	// Den text som ska skrivas ut
	private String text;

	// Den TelePrinter som ska användas vid utskrift
	TelePrinter printer;

	// Konstruktorn
	public PrintThread(TelePrinter printer, String text)
	{
		this.printer = printer;
		this.text = text;
	}

	public void run()
	{
		// Anropar teleprinterns print metod för att sköta utskriften
		printer.print(text);
	}
}