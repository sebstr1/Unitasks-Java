/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_11 - SlumpTr�d.java
 */

import java.awt.Graphics;
import javax.swing.JPanel;

public class SlumpTr�d extends Thread
{
	// Den panel i vilken vi ska rita punkterna
	private JPanel panel;

	// Konstruktorn
	public SlumpTr�d(JPanel panel)
	{
		this.panel = panel;
	}

	// Metod som anropas n�r vi vill avbryta tr�den
	public void avbryt()
	{
		System.out.println("SlumpPanel.avbryt()");
		this.interrupt();
	}

	public void run()
	{
		System.out.println("SlumpTr�d.run() - startar");

		// H�mtar graphic-objektet fr�n panelen och tar reda p� panelens storlek
		Graphics g = panel.getGraphics();
		int w = panel.getWidth();
		int h = panel.getHeight();

		// H�ll p� s� l�ngs som vi inte har blivit ombedd att avbryta
		while(!interrupted())
		{
			// Slumpa en punkt inom panelens omr�de
			int x = (int)(Math.random() * w);
			int y = (int)(Math.random() * h);

			// rita punkten
			g.fillOval(x, y, 4, 4);

			// Sover en stund
			try
			{
				this.sleep(10);
			}
			catch (InterruptedException ie)
			{
				// Tr�den har blivit ombedd att avbryta
				// Anv�nder break f�r att avbryta while-satsen
				break;
			}
		}
		System.out.println("SlumpTr�d.run() - slutar");
	}
}