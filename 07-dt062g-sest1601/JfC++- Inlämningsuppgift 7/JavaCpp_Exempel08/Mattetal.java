/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_21 - Mattetal.java
 */

public class Mattetal
{
	// Instansvariabler
	private double operand1;
	private double operand2;
	private String operator = null;

	// Metod f�r att s�tta nytt mattetal att ber�kna
	public synchronized void nyttTal(double op1, String op, double op2)
	{
		// S� l�nge som operator refererar till null s� vet vi att det inte
		// finns n�got mattetal satt och vi kan d� utan risk s�tta ett nytt.
		// Om det finns ett mattetal redan satt s� kommer operator att referera
		// till n�got annat �n null. Vi kan d� inte s�tta nytt utan ber tr�den
		// att v�nta med wait. Detta g�rs i en while-loop s� n�r tr�den vaknar
		// till igen kontrolleras det f�rst om det �r ok att s�tta nytt tal eller
		// om tr�den m�ste v�nta ytterligare ett tag
		while (operator != null)
		{
			try
			{
				// Det finns redan ett mattetal. V�nta tills att det befintliga talet har
				// h�mtats innan vi s�tter det nya.
				wait();

			}
			catch (InterruptedException ie) {}
		}

		// Det �r ok att s�tta nytt tal
		this.operand1 = op1;
		this.operator = op;
		this.operand2 = op2;

		// V�cker eventuella tr�dar som v�ntar p� att h�mta talet
		notify();

	}

	// Metod som returnerar aktuellt mattetal som en str�ng
	public synchronized String h�mtaTal()
	{
		// S� l�nge som operator refererar till null finns inget tal att h�mta
		// Tr�den som vill h�mta tal m�ste d� v�nta genom att anrop till wait.
		while (operator == null)
		{
			try
			{
				System.out.print("V�ntar p� nytt tal");
				wait();
				System.out.println();
			}
			catch (InterruptedException ie) {}
		}

		// Formaterar den str�ng som returneras och som inneh�ller talet
		// Vi s�tter �ven operator att referera till null (motsvarar att inget tal l�ngre �r satt)
		String uttryck = Double.toString(operand1) + " " +  operator + " " + Double.toString(operand2);
		operator = null;

		// V�cker eventuella tr�dar som v�ntar p� att f� s�tta nytt tal
		notify();

		// Returnerar talet
		return uttryck;
	}
}