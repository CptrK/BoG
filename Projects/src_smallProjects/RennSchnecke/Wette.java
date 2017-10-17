package RennSchnecke;

public class Wette {
	
	public static String schneckenName="";
	public static int wettEinsatz=0;
	public static String spieler="";

	//Konstruktor der Klasse Wette
	Wette(String schneckenName, int wettEinsatz,String wettSpieler) {
		Wettbuero.wetteAnnehmen(schneckenName,wettEinsatz,wettSpieler);
	}
	
	//diese Methode gibt die derzeitigen Werte der Wette wieder (toString-Methode)
	@Override
	public String toString() {
		String wetteDaten = "Der Spieler : " + spieler +
					   "setzt einen Betrag von " + wettEinsatz +
					   "€, auf die Schnecke:" + schneckenName + ".\n";
		return wetteDaten;
	}
	
}
