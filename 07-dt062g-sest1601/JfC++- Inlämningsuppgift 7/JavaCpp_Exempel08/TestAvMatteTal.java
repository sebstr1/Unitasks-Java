/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_24 - TestAvMatteTal.java
 */

public class TestAvMatteTal
{
	public static void main(String[] args)
	{
		Mattetal tal = new Mattetal();
		Talgenerator generator = new Talgenerator(tal);
		Talberäknare beräknare = new Talberäknare(tal);
		generator.start();
		beräknare.start();
	}
}
