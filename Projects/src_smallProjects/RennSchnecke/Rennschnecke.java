package RennSchnecke;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Random;

public class Rennschnecke {
	
	//array zur Auswahl der Rassen wird erzeugt
	private static final String[] rassen = { "KriechSchnecke", "AusdauerSchnecke","TempoSchnecke" };
	//globale Variablen für die Klasse werden initialisiert
	private int tempo=0;
	private int weg =0;
	private String rasse = "";
	private String name = "";
	private int tempWeg = 0;
	
	//Konstruktor der Klasse Rennschnecke
	Rennschnecke () {
		
		//der Instanz Name wird ein Wert zugewiesen der in ein Textfeld eingeben werden kann
		name = (String) JOptionPane.showInputDialog("Bitte gebe einen Namen für die Schnecke ein.");
		
		//der Instanz rasse wird mithilfe einer Auswahl eine Rasse zugeordnet
		JFrame frame = new JFrame("Rassenwahl");
		String tempRasse = (String) JOptionPane.showInputDialog(frame, "Bitte wähle eine Rasse", "Schneckenauswahl:", JOptionPane.PLAIN_MESSAGE, null, rassen, rassen[0]);
		if(tempRasse.equals("KriechSchnecke")) { rasse = "KriechSchnecke"; }
		else if(tempRasse.equals("AusdauerSchnecke")) { rasse = "AusdauerSchnecke"; }
		else if(tempRasse.equals("TempoSchnecke")) { rasse = "TempoSchnecke"; }
		
		//der Instanz tempo wird ein Wert zugewiesen, anhängig von der Rasse
		if(rasse.equals("KriechSchnecke")) { tempo = 41; }
		else if(rasse.equals("AusdauerSchnecke")) { tempo = 61; }
		else if(rasse.equals("TempoSchnecke")) { tempo = 101; }
		
		//der Instanz weg wird der Wert 0 zugewiesen, da die Schnecke, bei der Erzeugung, noch keine Strecke zurückgelegt hat
		weg = 0;
	}
	
	//diese Methode lässt die Schnecken eine zufällige Strecke zwischen 0 und tempo zurücklegen
	public void krieche() {
		Random random = new Random();
		//kriechen für die KriechSchnecke ist konstant zwischen 30mm und 40mm
		if(rasse.equals("KriechSchnecke")) { 
			tempWeg = 0;
			tempWeg = random.nextInt(tempo)+1;
			if (tempWeg <= 10){tempWeg += 30; }
			else if (tempWeg <= 20){tempWeg += 20; }
			else if (tempWeg <= 30){tempWeg += 10; }
			weg+=tempWeg;
		}
		//kriechen für die AusdauerSchnecke kann bis zu 60mm sein nimmt aber nach 7000mm langsam ab
		else if(rasse.equals("AusdauerSchnecke")) { 
			tempWeg = 0;
			if (weg >= 9000){tempWeg = random.nextInt(tempo-20)+1; }
			else if (weg >= 7000){tempWeg = random.nextInt(tempo-10)+1; }
			else if (weg <= 7000){tempWeg = random.nextInt(tempo)+1; }
			weg+=tempWeg;
		}
		//kriechen für die TempoSchnecke kann bis zu 100mm sein nimmt aber nach 5000mm stark ab, am Ende ist sie erschöpft und langsam
		else if(rasse.equals("TempoSchnecke")) { 
			tempWeg = 0;
			if (weg >= 9000){tempWeg = random.nextInt(tempo-70)+1; }
			else if (weg >= 7000){tempWeg = random.nextInt(tempo-50)+1; }
			else if (weg >= 5000){tempWeg = random.nextInt(tempo-20)+1; }
			else if (weg <= 5000){tempWeg = random.nextInt(tempo)+1; }
			weg+=tempWeg;
		}
	}
	
	//diese Methode gibt die derzeitigen Werte der Schnecken wieder (toString-Methode)
	@Override
	public String toString() {
		String daten = "Name: " + name + "\n" +
					   "Rasse: " + rasse + "\n" +
					   "Maximalgeschwindigkeit: " + tempo + "\n" +
					   "zurückgelegte Strecke: " + weg + "\n";
		return daten;
	}

    public int getDistanz() {
    	int distanz = Rennen.strecke - weg;
        return distanz;
    }
    
}
