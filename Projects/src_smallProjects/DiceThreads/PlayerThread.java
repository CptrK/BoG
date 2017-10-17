package DiceThreads;

import java.util.Random;

public class PlayerThread extends Thread {
	
	private Spieler spieler;
	private Wuerfelbecher becher;
	private String diceValue1="the";
	private String diceValue2="puuh";
	public int endValue;
	public int wurfZahl;
	public int random = 0;
	private Random generator;
	
	PlayerThread(Spieler spieler, Wuerfelbecher wuerfelbecher) {
		this.spieler = spieler;
		this.becher = wuerfelbecher;
		generator = new Random();
	}
	
	public void run() {
	      while(!diceValue1.equals(diceValue2)) {
	    	int random = generator.nextInt(2500) + 501;
	        spieler.becherSchuetteln(becher);
	        diceValue1 = Integer.toString(becher.getAugenzahl1());
	        diceValue2 = Integer.toString(becher.getAugenzahl2());
	        wurfZahl++;
	        this.random = this.random + random;
	        spieler.becherZeigen(becher);
	        if (diceValue1.equals(diceValue2)) {
	        	endValue = Integer.parseInt(diceValue1);
	        	System.out.println(spieler.getName() + " hat " + wurfZahl + " Würfe und eine Zeit von " + (double)this.random/1000 + " Sekunden benötigt!");
	        	break;
	        }
			try {
				sleep(random);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	    }
}
