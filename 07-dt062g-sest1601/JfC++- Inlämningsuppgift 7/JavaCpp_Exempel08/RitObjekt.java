/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_04 - RitObjekt.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class RitObjekt implements VäxerKrymper, ActionListener
{
	// Instansvariabler
	protected int x;
	protected int y;
	protected int storlek;            // Objektets nuvarande storlek
	protected int tillstånd;		  // Objketets nuvarande tillstånd
	protected final int MAX_STORLEK;  // Objektets maximala storlek
	protected final int MIN_STORLEK;  // Objektets minsta storlek

	public RitObjekt(int x, int y, int max, int min)
	{
		this.x = x;
		this.y = y;
		MAX_STORLEK = max;
		MIN_STORLEK = min;

		// Vi sätter inledningsvis objektets storlek till minsta
		// och tillståndet till växer.
		storlek = MIN_STORLEK;
		tillstånd = VäxerKrymper.VÄXER;

		// Skapar och startar en Timer. Gör en beräkning så att det tar en sekund
		// för objektet att växa från sin minsta storlek till den största. Detta sätt
		// är inte optimalt för objekt med stora storlekar då tiden mellan varje
		// händelse som genereras blir för kort. Helst bör vi se till att tiden blir
		// ca 100 ms och i stället öka antalet pixlar vi ökar/minskar storleken med.
		Timer t = new Timer(1000 / (MAX_STORLEK - MIN_STORLEK), this);
		t.start();
	}

	// Metoden väx anropas när objektet ska växa
	public void väx()
	{
		// Vi ska bara växa om vår storlek är mindre än max
		if (storlek < MAX_STORLEK)
		{
			storlek++;
		}
		else
		{
			// Vi har vaxt klart och ändrar tillstånd till krymper
			tillstånd = VäxerKrymper.KRYMPER;
		}
	}

	// Metoden krymp anropas när objektet ska krympa
	public void krymp()
	{
		// Vi ska bara krympa om vår storlek är större än min
		if (storlek > MIN_STORLEK)
		{
			storlek--;
		}
		else
		{
			// Vi har krympt klart och ändrar tillstånd till växer
			tillstånd = VäxerKrymper.VÄXER;
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		// Hit kommer vi varje gång timern genererar en händelse
		// Kontrollerar vilket tillstånd vi befinner oss i och
		// anropar antingen väx eller krymp beroende på tillstånd
		if (tillstånd == VäxerKrymper.VÄXER)
		{
			väx();
		}
		else if (tillstånd == VäxerKrymper.KRYMPER)
		{
			krymp();
		}

	}
}

/*
Observera att klassen måste deklareras som abstrakt eftersom endast metoderna
väx och krymp i gränssnittet VäxerKrymper implementeras. Alla objekt som kan
krympa och växa gör det genom att storleken minskar eller ökar med ett. Därför
kan dessa två metoder implementeras direkt i denna basklass.

Metoden rita däremot kan inte implementeras eftersom sättet att rita ut olika
objekt skiljer sig åt. Att implementera metoden rita överlåter vi till subklasserna.
*/