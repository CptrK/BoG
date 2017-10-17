package Diceware;

import java.util.Random;
	
public class Wuerfel {
	
	private byte augenzahl;
	private Random generator;
	
	Wuerfel () {
		generator = new Random();
	}
	
	public int getAugenzahl() {
		return this.augenzahl;
	}
	
	public void wuerfeln() {
		byte augenzahl = (byte) (generator.nextInt(6) +1);
		this.augenzahl = augenzahl;
	}
}
