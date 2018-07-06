/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_19 - StudsLinje.java
 */

import java.awt.Graphics;
import javax.swing.JPanel;

public class StudsLinje extends JPanel implements Runnable
{
	// Instansvariabler
	private Thread aktivitet;		// Tr�den
	private boolean ritaMedLinje;	// Ska vi rita en linje mellan punkterna?

	private final int antalPunkter;	// Hur m�nga punkter ska ritas?
	private int x[];				// Punkternas xkordinat
	private int y[];				// Punkternas ykordinat
	private int xfart[];			// Punkternas fart i x-led
	private int yfart[];			// Punkternas fart i y-led

	// Konstruktor
	public StudsLinje(int antalPunkter, boolean ritaMedLinje)
	{
		this.antalPunkter = antalPunkter;
		this.ritaMedLinje = ritaMedLinje;

		// Skapar arrayer f�r kordinater och fart
		x = new int[antalPunkter];
		y = new int[antalPunkter];
		xfart = new int[antalPunkter];
		yfart = new int[antalPunkter];
	}

	// Metod f�r att slumpa fram kordinater och fart f�r alla punkter
	private void slumpaPunkter()
	{
		for (int i = 0; i < antalPunkter; i++)
		{
			// Slumpa kordinater och fart. Anv�nder getWidth och getHeight
			// f�r att ta reda p� hur stor rityta vi har tillg�ng till
			x[i] = (int)(Math.random() * getWidth());
			y[i] = (int)(Math.random() * getHeight());
			xfart[i] = (int)(Math.random() * 5) - 2;
			yfart[i] = (int)(Math.random() * 5) - 2;

			// Skulle farten vara noll s�tter vi annat v�rde s�
			// att punkten verkligen r�r p� sig
			if (xfart[i] == 0) xfart[i] = -1;
			if (yfart[i] == 0) yfart[i] = 2;
		}
	}

	// Metod som startar tr�den
	public void start()
	{
		// Kontrollerar om tr�den redan �r startad eller inte
		// Om aktivetet refererar till null vet vi att en tr�d
		// inte �r skapad och det �r s�kert att skapa och starta
		// en ny tr�d. G�rs inte denna kontroll kommer ett exeption
		// att genereras om vi skapar en ny tr�d
		if (aktivitet == null)
		{
			// Vi slumpar fram nya punkter varje g�ng tr�den startas om
			// Vi skulle kunna l�gga slumpen n�gpn annan stans om vi vill att
			// punkterna ska starta fr�n samma st�lle som n�r tr�den stannas
			slumpaPunkter();
			aktivitet = new Thread(this);
			aktivitet.start();
		}
	}

	// Metod som stoppar tr�den
	public void stop()
	{
		// Det �r endast s�kert att stoppa/avbryta tr�den om den k�rs
		// D�rf�r kontrollerar vi s� att aktivitet inte refererar till null
		// eftersom vi d� skulle f� ett NullPointerException om vi skulle
		// anropa interrupt()
		if (aktivitet != null)
		{
			// Vi ber tr�den avbryta och s�tter aktivitet att referera till null
			aktivitet.interrupt();
			aktivitet = null;
		}
	}

	// Tr�dens run-metod
	public void run()
	{
		// Vi k�r s� l�nge som inte n�gon bett tr�den att avbryta
		while (!aktivitet.interrupted())
		{
			// Loopar igenom alla punkter
			for (int i = 0; i < antalPunkter; i++)
			{
				// kontrollerar om punkterna studsar mot n�gon kant
				// i s� fall �ndrar vi punktens riktningen
				if (x[i] < 0 || x[i] > getWidth())
					xfart[i] = -xfart[i];

				if (y[i] < 0 || y[i] > getHeight())
					yfart[i] = -yfart[i];

				// �ndrar punktens position
				x[i] += xfart[i];
				y[i] += yfart[i];
			}

			// Ber panelen att rita om sig
			repaint();

			// Sover en kort stund innan vi �ndrar positionen igen
			try
			{
				aktivitet.sleep(10);
			}
			catch (InterruptedException ie)
			{
				// Om tr�den avbryts medan den sover s�tter vi aktivitet
				// till att referera till null (s� som vi g�r i metoden stop())
				// samt att vi avbryter while-loopen med break
				aktivitet = null;
				break;
			}
		}
	}

	// Metod f�r att rita i panelen
	public void paintComponent(Graphics g)
	{
		// F�r att rensa bakgrunden
		super.paintComponent(g);

		// Loopa igenom alla punkter och rita ut dessa som en fylld oval
		for (int i = 0; i < antalPunkter; i++)
		{
			g.fillOval(x[i], y[i], 2, 2);

			// Ska en linje ritas mellan punkterna g�rs detta genom
			// att rita en polygon
			if (ritaMedLinje)
			{
				g.drawPolygon(x, y, antalPunkter);
			}
		}
	}
}