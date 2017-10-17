package RennSchnecke;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Rennen {
	
	//arrays zur Auswahl der Streckenlänge und Teilnehmerzahl wird erzeugt
	private static final String[] streckenArten = { "10000","20000","50000" };
	private static final String[] anzahlTeilnehmer = { "4","6","8" };
	//es wird eine ArrayList erstellt in der die Schnecken abgebildet werden
	private ArrayList<Rennschnecke> Schnecken;
	//globale Variablen für die Klasse werden initialisiert
	private String rennenName ="";
	public static int strecke =0;
	private int teilnehmerZahl =0;
	
	//Konstruktor der Klasse Rennen
	Rennen(String rennName) {
		
		//der Instanz Name wird ein Wert zugewiesen der in ein Textfeld eingeben werden kann
		rennenName = rennName;
		
		//der Instanz strecke wird mithilfe einer Auswahl ein Wert zugeordnet
		JFrame frame = new JFrame("Streckenwahl");
		String tempStrecke =(String) JOptionPane.showInputDialog(frame, "Bitte wähle die Streckenlänge aus.", "Streckenauswahl:", JOptionPane.PLAIN_MESSAGE, null, streckenArten, streckenArten[0]);
		if(tempStrecke.equals("10000")) { strecke = 10000; }
		else if(tempStrecke.equals("20000")) { strecke = 20000; }
		else if(tempStrecke.equals("50000")) { strecke = 50000; }
		
		//der Instanz teilnehmerZahl wird mithilfe einer Auswahl ein Wert zugeordnet
		JFrame frame2 = new JFrame("Teilnehmerwahl");
		String tempZahl = (String) JOptionPane.showInputDialog(frame2, "Bitte wähle eine Teilnehmeranzahl aus", "Teilnehmeranzahl:", JOptionPane.PLAIN_MESSAGE, null, anzahlTeilnehmer, anzahlTeilnehmer[0]);
		if(tempZahl.equals("4")) { teilnehmerZahl = 4; }
		else if(tempZahl.equals("6")) { teilnehmerZahl = 6; }
		else if(tempZahl.equals("8")) { teilnehmerZahl = 8; }
		
		//die ArrayList Schnecken bekommt soviel Felder wie teilnehmerAnzahl
		Schnecken = new ArrayList<Rennschnecke>(teilnehmerZahl);
	}
	
	//diese Methode fügt der ArrayList eine Schecke hinzu
	public void addRennschnecke(Rennschnecke neueSchnecke) {
		Schnecken.add(neueSchnecke);
	}
	
	//diese Methode gibt die derzeitigen Werte des Rennens und der teilnehmenden Schnecken wieder (toString-Methode)
    @Override
    public String toString() {
        String rennDaten = "den " + rennenName + " GrandPrix aus.\n Die Länge des Rennen beträgt " + strecke + "mm!\n"
                + "Die teilnehmenden Schnecken sind: \n";
        for (Rennschnecke neueSchnecke : Schnecken) {
            rennDaten += neueSchnecke.toString() + "\n";
        }
        return rennDaten;
    }
    
    //diese Methode ermittelt den Gewinner des Rennens
    Rennschnecke ermittleGewinner() {
        for (Rennschnecke neueSchnecke : Schnecken) {
            if (neueSchnecke.getDistanz() >= strecke) {
                return neueSchnecke;
            }
        }
        return null;
    }
    
    //diese Methode lässt die Schnecken kriechen
    void lasseSchneckenKriechen() {
        for (Rennschnecke neueSchnecke : Schnecken) {
            neueSchnecke.krieche();
        }
    }
    
    //diese Methode führt ein Rennen durch bis ein Gewinner ermittelt wurde
    void rennenDurchfuehren() {
        while (ermittleGewinner() == null) {
            lasseSchneckenKriechen();
        }
    }
    
}
