package DiceThreads;

public class Wuerfelbecher {
	
	private Wuerfel wuerfel1;
	private Wuerfel wuerfel2;
	private Spieler spieler;
	
	Wuerfelbecher(Spieler spieler) {
		this.wuerfel1 = new Wuerfel();
		this.wuerfel2 = new Wuerfel();
		this.spieler = spieler;
	}

	public void anzeigen() {
		String augenzahlW1 = Integer.toString(wuerfel1.getAugenzahl());
		String augenzahlW2 = Integer.toString(wuerfel2.getAugenzahl());
		if (augenzahlW1.equals(augenzahlW2)) {
			System.out.println(spieler.getName() + " hat einen " + augenzahlW1 + "er-Pasch gewürfelt!");
		} else {
			System.out.println(spieler.getName() + " hat " + augenzahlW1 + " und " + augenzahlW2 + " gewürfelt!");
		}
	}
	
	public String uebergeben() {
		String augenzahlW1 = Integer.toString(wuerfel1.getAugenzahl());
		String augenzahlW2 = Integer.toString(wuerfel2.getAugenzahl());
		if (augenzahlW1.equals(augenzahlW2)) {
			return augenzahlW1 + "er-Pasch";
		} else {
			return augenzahlW1 + " und " + augenzahlW2;
		}
	}
	
	public void schuetteln() {
		wuerfel1.wuerfeln();
		wuerfel2.wuerfeln();
	}
	
	public int getAugenzahl1() {
		return wuerfel1.getAugenzahl();
	}
	
	public int getAugenzahl2() {
		return wuerfel2.getAugenzahl();
	}
}
