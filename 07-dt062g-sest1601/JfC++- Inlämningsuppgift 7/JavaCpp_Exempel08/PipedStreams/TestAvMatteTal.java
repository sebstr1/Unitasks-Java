/**
 * @author Robert Jonsson, ITM Östersund
 * @version 2.0
 * @file Ex03_10 - TestAvMatteTal.java
 */

import java.io.*;

public class TestAvMatteTal
{
	public static void main(String[] args)
	{
		try
		{
			// Skapar de strömmar som behövs
			PipedWriter utTråd1 = new PipedWriter();
			PipedReader inTråd2 = new PipedReader(utTråd1);
			PipedWriter utTråd2 = new PipedWriter();
			PipedReader inTråd1 = new PipedReader(utTråd2);

			// Skapar trådar
			Talgenerator generator = new Talgenerator(utTråd1, inTråd1);
			Talberäknare beräknare = new Talberäknare(utTråd2, inTråd2);

			// Startar trådarna
			generator.start();
			beräknare.start();

		}
		catch (IOException fel)
		{
			System.out.println("Kunde inte skapa strömmar...");
			fel.printStackTrace();
		}
	}
}
