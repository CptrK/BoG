package MultiThreadsSynchro;

import java.util.Random;

public class Einkauf extends Thread {
	
	private Random generator;
	private int loops;
	private Buero buero;
	
	Einkauf(Buero buero) {
		this.buero = buero;
		generator = new Random();
		loops = generator.nextInt(4)+2;
	}
	
	public void run() {
		/* 
		 * final int kartenKontingent = buero.kartenKontingent;
		 * synchronized kartenKontingent;
		*/
		int kartenAnzahl = 0;
		for (int i=0; i<loops ; i++) {
			kartenAnzahl = generator.nextInt(10)+5;
			buero.kartenAufstocken(kartenAnzahl);
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
