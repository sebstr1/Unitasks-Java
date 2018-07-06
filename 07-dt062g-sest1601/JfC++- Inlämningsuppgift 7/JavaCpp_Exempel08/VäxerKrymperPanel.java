/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_07 - V�xerKrymperPanel.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class V�xerKrymperPanel extends JPanel implements ActionListener, MouseListener
{
	// Instansvariabler
	// Anledningen till att vi m�ste ange klassens fullst�ndiga namn
	// �r att det �ven i java.awt finns en klass men namnet List
	private java.util.List<V�xerKrymper> ritobjekt;

	public V�xerKrymperPanel()
	{
		// Skapar en "lista som inneh�ller element av typen V�xerKrymper"
		ritobjekt = new java.util.ArrayList<V�xerKrymper>();

		// Skapar och startar timern f�r att rita ut alla objekt vid j�mna mellanrum
		Timer t = new Timer(50, this);
		t.start();

		// Panelen ska lyssna efter mush�ndelser (v�nster-, h�gerklick) s�
		// vi registrerar den egna klassen som en lyssnare efter s�dana h�ndelser
		addMouseListener(this);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// Loopa igenom alla ritobjekt och be dem rita sig sj�lva
		for (V�xerKrymper vk : ritobjekt)
		{
			vk.rita(g);
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		// Ett ActionEvent fr�n timern s� vi ber panelen rita om sig
		repaint();
	}

	public void mouseClicked(MouseEvent me)
	{
		// Vi unders�ker MouseEvent f�r att avg�ra om
		// anv�nderen tryckte p� v�nster eller h�ger knapp
		if (me.getButton() == MouseEvent.BUTTON1)
		{
			// Vi har tryckt ner v�nster knapp och l�gger till en ny boll
			ritobjekt.add(new Boll(me.getX(), me.getY()));
		}
		else
		{
			// Vi har tryckt p� n�gon annan knapp och l�gger till en linje
			ritobjekt.add(new Linje(me.getX(), me.getY()));
		}
	}

	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
}