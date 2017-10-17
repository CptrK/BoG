package Meiern;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Spiel {
	
	private static ArrayList<Spieler> SpielerListe = new ArrayList<>();
	final static int[] WurfListe = {0, 31, 32, 41, 42, 43, 51, 52, 53, 54, 61, 62, 63, 64, 65, 11, 22, 33, 44, 55, 66, 21};
	private static int counter=0;
	private static boolean spielbeginn=true;
	private static int vorherigeZahl=0;
	private static int gewuerfelteZahl;
	private static int gesagteZahl;
	
	public static void main(String[] args) {
		Wuerfelbecher wuerfelbecher = new Wuerfelbecher();
		int zahl = Integer.parseInt(JOptionPane.showInputDialog("Bitte, gebe die Anzahl der Spieler ein!"));
		for (int i=0; i<zahl; i++) {
			String name = (String) JOptionPane.showInputDialog("Gebe einen Namen für Spieler " + (i+1) + " ein!");
			addSpieler(name);
		}
		while (counter < SpielerListe.size()) {
			if (spielbeginn == true) {
				String eingabe = (String) JOptionPane.showInputDialog("Möchtest du eine neue/weitere Runde spielen? 'Y' or 'N'?");
				if ("Y".equals(eingabe)) {
					spielbeginn = false;
					SpielerListe.get(counter).becherSchuetteln(wuerfelbecher);
					gewuerfelteZahl = wuerfelbecher.zahlUebergeben();
					gesagteZahl = SpielerListe.get(counter).zahlAnsagen();	
				} else {
					break;
				}
			} else if (spielbeginn == false) {
				if (gesagteZahl == 21) {
					System.out.println(SpielerListe.get(counter).getName() + ": Da bleibt mir nichts anderes übrig, als nachzuschauen.");
					if (SpielerListe.get(counter).aufdecken(wuerfelbecher, gesagteZahl) == true) {
						System.out.println(SpielerListe.get(counter).getName() + ": Oh, du hattest ja tatsächlich einen Meier!");
						System.out.println("Der Verlierer dieser Runde ist: " + SpielerListe.get(counter).getName() + "!");
					} else {
						System.out.println(SpielerListe.get(counter).getName() + ": Ha! Du hattest gar keinen Meier!");
						System.out.println("Der Verlierer dieser Runde ist: " + SpielerListe.get(counter-1).getName() + "!");
					}
					spielbeginn = true;
				} else {
					String eingabe = (String) JOptionPane.showInputDialog("Was möchtest du tun? 'aufdecken' oder 'wuerfeln'?");
					if ("aufdecken".equals(eingabe)) {
						int posGewuerfelteZahl = 0;
						int posVorherigeZahl = 0;
						for (int i = 0; i < WurfListe.length; i++) {
							if (gewuerfelteZahl == WurfListe[i]) {posGewuerfelteZahl = i;}
							if (vorherigeZahl == WurfListe[i]) {posVorherigeZahl = i;}
						}
						System.out.println(SpielerListe.get(counter).getName() + ": Nein, das glaube ich dir nicht!");
						if (gesagteZahl == gewuerfelteZahl && posGewuerfelteZahl > posVorherigeZahl) {
							System.out.println(SpielerListe.get(counter).getName() + ": Ach Mist! Du hattest wirklich eine " + gewuerfelteZahl + " gewürfelt!");
							System.out.println("Der Verlierer dieser Runde ist: " + SpielerListe.get(counter).getName() + "!");
						} else if (gesagteZahl != gewuerfelteZahl) {
							System.out.println(SpielerListe.get(counter).getName() + ": Haha! Du hast gar keine " + gesagteZahl + " gewürfelt!");
							if (SpielerListe.size()>1) {
								System.out.println("Der Verlierer dieser Runde ist: " + SpielerListe.get(counter-1).getName() + "!");
							} else {
								System.out.println("Der Verlierer dieser Runde ist: " + SpielerListe.get(counter).getName() + "!");
							}
						}
						spielbeginn = true;
					} else if ("wuerfeln".equals(eingabe)){
						vorherigeZahl = gesagteZahl;
						SpielerListe.get(counter).becherSchuetteln(wuerfelbecher);
						gewuerfelteZahl = wuerfelbecher.zahlUebergeben();
						gesagteZahl = SpielerListe.get(counter).zahlAnsagen();
					}
				}
			} 
			if (spielbeginn == false) {
				counter++;
			}
			if (counter == SpielerListe.size()) {
				counter=0;
			}
		}
	}
	
	private static void addSpieler(String name) {
		Spieler spieler = new Spieler(name);
		SpielerListe.add(spieler);
	}
}
