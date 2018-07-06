/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_03 - VäxerKrymper.java
 */public interface VäxerKrymper
{
	// publika konstanter för att indikera ett objekts tillstånd
	public static final int VÄXER = 1;
	public static final int STILLA = 0;
	public static final int KRYMPER = -1;

	// metoder
	public void väx();
	public void krymp();
	public void rita(java.awt.Graphics g);
}