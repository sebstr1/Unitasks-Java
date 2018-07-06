/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_22 - Talgenerator.java
 */

public class Talgenerator extends Thread
{
	// Instansvariabler
	private Mattetal tal;

	// Konstruktor
	public Talgenerator(Mattetal tal)
	{
		this.tal = tal;
	}

	// Klassens run-metod
	public void run()
	{
		// Kör så länge som inte någon bett trådan att avbryta
		while (!isInterrupted())
		{
			// Börja med att sova en stund (max 10 sekunder). Detta görs enbart
			// för att simulera att någon intensiv/lång beräkning görs för att få fram
			// ett nytt tal.
			try
			{
				this.sleep((int)(Math.random() * 10000));
			}
			catch (InterruptedException ie)
			{
				// Blir trådan avbryten så avbryter vi while-loopen
				// med break.
				break;
			}

			// Vi slumpar fram nya värden för talet (0-10)
			int op1 = (int)(Math.random() * 11);
			int op2 = (int)(Math.random() * 11);

			// Slumpar vilken operator som ska användas
			String op = null;

			switch ((int)(Math.random() * 5))
			{
				case 0: op = "+"; break;
				case 1: op = "-"; break;
				case 2: op = "*"; break;
				case 3: op = "/"; break;
				default: op = "+"; break;
			}

			// Anropar metoden nyttTal för att sätta det nya talet
			// Observera att om det befintliga talet i Mattetal inte
			// hunnit hämtas så kommer denna tråd att få vänta (wait)
			tal.nyttTal(op1, op, op2);
		}
	}
}