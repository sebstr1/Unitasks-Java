/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_03 - V�xerKrymper.java
 */public interface V�xerKrymper
{
	// publika konstanter f�r att indikera ett objekts tillst�nd
	public static final int V�XER = 1;
	public static final int STILLA = 0;
	public static final int KRYMPER = -1;

	// metoder
	public void v�x();
	public void krymp();
	public void rita(java.awt.Graphics g);
}