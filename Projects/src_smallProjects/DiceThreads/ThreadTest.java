package DiceThreads;

public class ThreadTest {
	
	public static void main (String [] args) throws InterruptedException {
		Spieler spieler1 =  new Spieler("Frank");
		Spieler spieler2 =  new Spieler("Peter");
		Wuerfelbecher becher1 = new Wuerfelbecher(spieler1);
		Wuerfelbecher becher2 = new Wuerfelbecher(spieler2);
		PlayerThread pT1 = new PlayerThread(spieler1, becher1);
		PlayerThread pT2 = new PlayerThread(spieler2, becher2);
		pT1.start();
		pT2.start();
		pT1.join();
		pT2.join();
		if (pT1.endValue > pT2.endValue) {
			System.out.println(spieler1.getName() + " hat gewonnen!");
		} else if (pT2.endValue > pT1.endValue) {
			System.out.println(spieler2.getName() + " hat gewonnen!");
		} else if (pT1.endValue == pT2.endValue) {
			System.out.println("Das Spiel endet in einem Unentschieden!");
		}
	}

}
