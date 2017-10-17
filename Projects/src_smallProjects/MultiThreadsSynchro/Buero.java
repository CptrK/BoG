package MultiThreadsSynchro;

public class Buero {
	
	public int kartenKontingent;
	
	Buero (int kartenKontingent) {
		this.kartenKontingent = kartenKontingent;
	}
	
	public void karteVerkauft(int kartenAnzahl) {
		this.kartenKontingent -= kartenAnzahl;
		if (kartenAnzahl == 1) {
			System.out.println("Es wurde eine Karte verkauft!");
			if (this.kartenKontingent == 1) {
				System.out.println("Es ist noch eine Karte im Kontingent!");
			} else if (this.kartenKontingent > 1) {
				System.out.println("Es sind noch " + this.kartenKontingent + " Karten im Kontingent!");
			}
		}
	}
	
	public void kartenAufstocken(int kartenAnzahl) {
		this.kartenKontingent += kartenAnzahl;
		if (kartenAnzahl > 1) {
			System.out.println("Das Kontigent wurde um " + kartenAnzahl + " Karten aufgestockt!");
			if (this.kartenKontingent == 1) {
				System.out.println("Es ist eine Karte im Kontingent!");
			} else if (this.kartenKontingent > 1) {
				System.out.println("Es sind " + this.kartenKontingent + " Karten im Kontingent!");
			}
		}
	}
}

