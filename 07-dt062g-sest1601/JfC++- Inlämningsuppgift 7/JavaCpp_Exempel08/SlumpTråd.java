/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_11 - SlumpTråd.java
 */

import java.awt.Graphics;
import javax.swing.JPanel;

public class SlumpTråd extends Thread
{
	// Den panel i vilken vi ska rita punkterna
	private JPanel panel;

	// Konstruktorn
	public SlumpTråd(JPanel panel)
	{
		this.panel = panel;
	}

	// Metod som anropas när vi vill avbryta tråden
	public void avbryt()
	{
		System.out.println("SlumpPanel.avbryt()");
		this.interrupt();
	}

	public void run()
	{
		System.out.println("SlumpTråd.run() - startar");

		// Hämtar graphic-objektet från panelen och tar reda på panelens storlek
		Graphics g = panel.getGraphics();
		int w = panel.getWidth();
		int h = panel.getHeight();

		// Håll på så längs som vi inte har blivit ombedd att avbryta
		while(!interrupted())
		{
			// Slumpa en punkt inom panelens område
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
				// Tråden har blivit ombedd att avbryta
				// Använder break för att avbryta while-satsen
				break;
			}
		}
		System.out.println("SlumpTråd.run() - slutar");
	}
}