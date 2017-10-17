package DiceThreads;

import java.util.Random;
	
public class Wuerfel {
	
	private int augenzahl;
	private Random generator;
	
	Wuerfel () {
		generator = new Random();
	}
	
	public int getAugenzahl() {
		return this.augenzahl;
	}
	
	public void wuerfeln() {
		int augenzahl = generator.nextInt(6) +1;
		this.augenzahl = augenzahl;
	}
}
