/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_07 - VäxerKrymperPanel.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VäxerKrymperPanel extends JPanel implements ActionListener, MouseListener
{
	// Instansvariabler
	// Anledningen till att vi måste ange klassens fullständiga namn
	// är att det även i java.awt finns en klass men namnet List
	private java.util.List<VäxerKrymper> ritobjekt;

	public VäxerKrymperPanel()
	{
		// Skapar en "lista som innehåller element av typen VäxerKrymper"
		ritobjekt = new java.util.ArrayList<VäxerKrymper>();

		// Skapar och startar timern för att rita ut alla objekt vid jämna mellanrum
		Timer t = new Timer(50, this);
		t.start();

		// Panelen ska lyssna efter mushändelser (vänster-, högerklick) så
		// vi registrerar den egna klassen som en lyssnare efter sådana händelser
		addMouseListener(this);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// Loopa igenom alla ritobjekt och be dem rita sig själva
		for (VäxerKrymper vk : ritobjekt)
		{
			vk.rita(g);
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		// Ett ActionEvent från timern så vi ber panelen rita om sig
		repaint();
	}

	public void mouseClicked(MouseEvent me)
	{
		// Vi undersöker MouseEvent för att avgöra om
		// använderen tryckte på vänster eller höger knapp
		if (me.getButton() == MouseEvent.BUTTON1)
		{
			// Vi har tryckt ner vänster knapp och lägger till en ny boll
			ritobjekt.add(new Boll(me.getX(), me.getY()));
		}
		else
		{
			// Vi har tryckt på någon annan knapp och lägger till en linje
			ritobjekt.add(new Linje(me.getX(), me.getY()));
		}
	}

	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
}