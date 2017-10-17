package RennSchnecke;

import java.util.ArrayList;

public class Wettbuero {
	
	//es wird eine ArrayList erstellt in der die Schnecken abgebildet werden
	private ArrayList<Wette> Wetten;
	//globale Variablen für die Klasse werden initialisiert
	private double wettFaktor=0;
	private Rennen neueRennen;

	//Konstruktor der Klasse Wettbuero
	 Wettbuero (double faktor,String rennenName) {
		 
		 //der wettFaktor der beim erstellen des Bueros gesetzt wird, wird global als Variable gesetzt
		 wettFaktor = faktor;
		 
		 //Das Rennen wird dem Wettbuero hinzugefügt
		 neueRennen = new Rennen(rennenName);
		 
	 }
	 
	//diese Methode fügt der ArrayList Wetten eine Wette hinzu
	public void addWette(Wette neueWette) {
		Wetten.add(neueWette);
	}
	 

	 //diese Methode nimmt Daten für eine Wette an und setzt sie mit variablen der Wette gleich
	 public static void wetteAnnehmen(String schneckenName, int wettEinsatz,String spieler) {
		 Wette.schneckenName=schneckenName;
		 Wette.wettEinsatz=wettEinsatz;
		 Wette.spieler=spieler;
	 }
	 
	 //diese Methode führt das Rennen aus, dass im Wettbüro erstellt wurde
	 public void rennenDurchfuehren() {
		 neueRennen.rennenDurchfuehren();
	 }
	 
	//diese Methode gibt die derzeitigen Werte des Wettbueros wieder(toString-Methode)
	    @Override
	    public String toString() {
	        String wettbueroDaten = "Das Wettbüro strahlt heute " + neueRennen.toString() +
	        						"Der heutige Wettmultiplikator liegt bei: " + wettFaktor + " und diese Wetten wurden plaziert:\n";
	        for (Wette neueWette : Wetten) {
	        	wettbueroDaten += neueWette.toString() + "\n";
	        };
	        return wettbueroDaten;
	    }
	 
}
