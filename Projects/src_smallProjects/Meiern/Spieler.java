package Meiern;

import javax.swing.JOptionPane;

public class Spieler {
	
	private String name;
	
	Spieler (String name){
		this.name = name;
	}
	
	public boolean aufdecken(Wuerfelbecher wuerfelbecher, int zahl) {
		boolean bool = true;
		wuerfelbecher.anzeigen();
		if (wuerfelbecher.zahlUebergeben() != zahl) {
			bool = false;
		}
		return bool;
	}
	
	public void becherSchuetteln(Wuerfelbecher wuerfelbecher) {
		System.out.println(this.name + " schüttelt den Würfelbecher...");
		wuerfelbecher.schuetteln();
		wuerfelbecher.anzeigen();
	}
	
	public int zahlAnsagen() {
		int zahl = Integer.parseInt(JOptionPane.showInputDialog("Bitte, gebe die Zahl ein, die du ansagen möchtest!"));
		System.out.println(this.name + ": Ich habe eine " + zahl + " gewürfelt.");
		return zahl;
	}
	
	public String getName() {
		return this.name;
	}
	
}