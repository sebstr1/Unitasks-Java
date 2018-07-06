/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_19 - StudsLinje.java
 */

import java.awt.Graphics;
import javax.swing.JPanel;

public class StudsLinje extends JPanel implements Runnable
{
	// Instansvariabler
	private Thread aktivitet;		// Tråden
	private boolean ritaMedLinje;	// Ska vi rita en linje mellan punkterna?

	private final int antalPunkter;	// Hur många punkter ska ritas?
	private int x[];				// Punkternas xkordinat
	private int y[];				// Punkternas ykordinat
	private int xfart[];			// Punkternas fart i x-led
	private int yfart[];			// Punkternas fart i y-led

	// Konstruktor
	public StudsLinje(int antalPunkter, boolean ritaMedLinje)
	{
		this.antalPunkter = antalPunkter;
		this.ritaMedLinje = ritaMedLinje;

		// Skapar arrayer för kordinater och fart
		x = new int[antalPunkter];
		y = new int[antalPunkter];
		xfart = new int[antalPunkter];
		yfart = new int[antalPunkter];
	}

	// Metod för att slumpa fram kordinater och fart för alla punkter
	private void slumpaPunkter()
	{
		for (int i = 0; i < antalPunkter; i++)
		{
			// Slumpa kordinater och fart. Använder getWidth och getHeight
			// för att ta reda på hur stor rityta vi har tillgång till
			x[i] = (int)(Math.random() * getWidth());
			y[i] = (int)(Math.random() * getHeight());
			xfart[i] = (int)(Math.random() * 5) - 2;
			yfart[i] = (int)(Math.random() * 5) - 2;

			// Skulle farten vara noll sätter vi annat värde så
			// att punkten verkligen rör på sig
			if (xfart[i] == 0) xfart[i] = -1;
			if (yfart[i] == 0) yfart[i] = 2;
		}
	}

	// Metod som startar tråden
	public void start()
	{
		// Kontrollerar om tråden redan är startad eller inte
		// Om aktivetet refererar till null vet vi att en tråd
		// inte är skapad och det är säkert att skapa och starta
		// en ny tråd. Görs inte denna kontroll kommer ett exeption
		// att genereras om vi skapar en ny tråd
		if (aktivitet == null)
		{
			// Vi slumpar fram nya punkter varje gång tråden startas om
			// Vi skulle kunna lägga slumpen någpn annan stans om vi vill att
			// punkterna ska starta från samma ställe som när tråden stannas
			slumpaPunkter();
			aktivitet = new Thread(this);
			aktivitet.start();
		}
	}

	// Metod som stoppar tråden
	public void stop()
	{
		// Det är endast säkert att stoppa/avbryta tråden om den körs
		// Därför kontrollerar vi så att aktivitet inte refererar till null
		// eftersom vi då skulle få ett NullPointerException om vi skulle
		// anropa interrupt()
		if (aktivitet != null)
		{
			// Vi ber tråden avbryta och sätter aktivitet att referera till null
			aktivitet.interrupt();
			aktivitet = null;
		}
	}

	// Trådens run-metod
	public void run()
	{
		// Vi kör så länge som inte någon bett tråden att avbryta
		while (!aktivitet.interrupted())
		{
			// Loopar igenom alla punkter
			for (int i = 0; i < antalPunkter; i++)
			{
				// kontrollerar om punkterna studsar mot någon kant
				// i så fall ändrar vi punktens riktningen
				if (x[i] < 0 || x[i] > getWidth())
					xfart[i] = -xfart[i];

				if (y[i] < 0 || y[i] > getHeight())
					yfart[i] = -yfart[i];

				// Ändrar punktens position
				x[i] += xfart[i];
				y[i] += yfart[i];
			}

			// Ber panelen att rita om sig
			repaint();

			// Sover en kort stund innan vi ändrar positionen igen
			try
			{
				aktivitet.sleep(10);
			}
			catch (InterruptedException ie)
			{
				// Om tråden avbryts medan den sover sätter vi aktivitet
				// till att referera till null (så som vi gör i metoden stop())
				// samt att vi avbryter while-loopen med break
				aktivitet = null;
				break;
			}
		}
	}

	// Metod för att rita i panelen
	public void paintComponent(Graphics g)
	{
		// För att rensa bakgrunden
		super.paintComponent(g);

		// Loopa igenom alla punkter och rita ut dessa som en fylld oval
		for (int i = 0; i < antalPunkter; i++)
		{
			g.fillOval(x[i], y[i], 2, 2);

			// Ska en linje ritas mellan punkterna görs detta genom
			// att rita en polygon
			if (ritaMedLinje)
			{
				g.drawPolygon(x, y, antalPunkter);
			}
		}
	}
}