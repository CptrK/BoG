package Meiern;

public class Wuerfelbecher {
	
	private Wuerfel wuerfel1;
	private Wuerfel wuerfel2;
	
	Wuerfelbecher() {
		this.wuerfel1 = new Wuerfel();
		this.wuerfel2 = new Wuerfel();
	}

	public void anzeigen() {
		int augenzahl=0;
		int augenzahlW1 = wuerfel1.getAugenzahl();
		int augenzahlW2 = wuerfel2.getAugenzahl();
		if (augenzahlW2 > augenzahlW1) {
			augenzahl = augenzahlW2*10 + augenzahlW1;
		} else if (augenzahlW2 == augenzahlW1) {
			augenzahl = augenzahlW1*10 + augenzahlW2;
		} else {
			augenzahl = augenzahlW1*10 + augenzahlW2;
		}
		System.out.println("Im Würfelbecher befindet sich die Zahl: " + augenzahl + ".");
	}
	
	public int zahlUebergeben() {
		int augenzahl=0;
		int augenzahlW1 = wuerfel1.getAugenzahl();
		int augenzahlW2 = wuerfel2.getAugenzahl();
		if (augenzahlW2 > augenzahlW1) {
			augenzahl = augenzahlW2*10 + augenzahlW1;
		} else if (augenzahlW2 == augenzahlW1) {
			augenzahl = augenzahlW1*10 + augenzahlW2;
		} else {
			augenzahl = augenzahlW1*10 + augenzahlW2;
		}
		return augenzahl;
	}
	
	public void schuetteln() {
		wuerfel1.wuerfeln();
		wuerfel2.wuerfeln();
	}
}
