package Fachhochschulreife;

import java.util.ArrayList;

public class Schueler {
	
	private String vorname;
	private String ersterLK;
	private String zweiterLK;
	public ArrayList<Kurs> kursListe = new ArrayList<>();
	
	Schueler(String name, String ersterLK, int p1LK1, String zweiterLK, int p1LK2) {
		this.vorname = name;
		if ("D".equals(ersterLK) || "E".equals(ersterLK) || "M".equals(ersterLK)) {
			this.ersterLK = ersterLK;
			kursListe.add(new Kurs(ersterLK, "LK", "12.1", p1LK1));
		}
		if ("BRC".equals(zweiterLK)) {
			this.zweiterLK = zweiterLK;
			kursListe.add(new Kurs(zweiterLK, "LK", "12.1", p1LK2));
		}
		
	}
	
	public void setVorname(String name) {
		this.vorname = name;
	}
	
	public String getVorname() {
		return this.vorname;
	}
	
	public void setErsterLK(String ersterLK) {
		if ("D".equals(ersterLK) || "E".equals(ersterLK) || "M".equals(ersterLK)) {
			this.ersterLK = ersterLK;
		}
	}
	
	public String getErsterLK() {
		return this.ersterLK;
	}
	
	public void setZweiterLK(String zweiterLK) {
		if ("BRC".equals(zweiterLK)) {
			this.zweiterLK = zweiterLK;
		}
	}
	
	public String getZweiterLK() {
		return this.zweiterLK;
	}
	
	public void alleKurseAusgeben() {
		for (int i=0; i<kursListe.size(); i++) {
			System.out.println(kursListe.get(i).toString());
		}
	}
	
	public void kursHinzufügen(String fachcode, String kurstyp, String lehrjahr, int punkte) {
		Kurs kurs = new Kurs(fachcode, kurstyp, lehrjahr, punkte);
		int count = 0;
		for (int i=0; i<kursListe.size(); i++) {
			if (fachcode.equals(kursListe.get(i).getFachcode()) && lehrjahr.equals(kursListe.get(i).getLehrjahr())) {
				count =1;
				System.out.println("Dieser Kurs ist schon in der Kurliste vorhanden, doppelte EIngaben sind nicht erlaubt!");
			}
		}
		if (count == 0 ) {
			kursListe.add(kurs);
		}
	}
	
	public void kursLöschen(String fachcode, String lehrjahr) {
		for (int i=0; i<kursListe.size(); i++) {
			if (fachcode.equals(kursListe.get(i).getFachcode()) && lehrjahr.equals(kursListe.get(i).getLehrjahr())) {
				kursListe.remove(i);
			} else {
				System.out.println("Dieser Kurs existiert nicht in der Kursliste, wähle einen neuen Kurs!");
			}
		}
	}
	
	public int ermittleGesamtpunktzahl(ArrayList<Kurs> kurs) {
		int gesamtpunktzahl = 0;
		for (int i=0; i<kursListe.size(); i++) {
			String kursTyp = kursListe.get(i).getKurstyp();
			gesamtpunktzahl += kursListe.get(i).getPunktzahl() * kursListe.get(i).anrechnungsFaktor(kursTyp);
		}
		return gesamtpunktzahl;
	}
	
	public double berechneDurschnittsnote() {
		int gesamtpunktzahl = this.ermittleGesamtpunktzahl(kursListe);
		double durschnittsnote = 5.0 + 2.0/3.0 - gesamtpunktzahl/57.0;
		return durschnittsnote;
	}
	
	public boolean kontrolle() {
		boolean bestanden = false;
		bestanden = this.istKriterium1und2();
		if (bestanden == true) {
			bestanden = this.istKriterium3();
			if (bestanden == true) {
				bestanden = this.istKriterium4();
				if (bestanden == true) {
					bestanden = this.istKriterium5();
					if (bestanden == true) {
						bestanden = this.istKriterium6und7();
					}
				}
			}
		}
		return bestanden;
	}
	
	private boolean istKriterium1und2() {
		boolean bool = true;
		ArrayList<Kurs> LKs = new ArrayList<>();
		for (int i=0; i<kursListe.size(); i++) {
			if ("LK".equals(kursListe.get(i).getKurstyp())) {
				LKs.add(kursListe.get(i));
			}
		}
		for (int i=0; i<LKs.size(); i++) {
			if (0 == LKs.get(i).getPunktzahl()) {
				bool = false;
			}
		}
		return bool;
	}
	
	private boolean istKriterium3() {
		boolean bool = true;
		int punkte = 0;
		ArrayList<Kurs> LKs = new ArrayList<>();
		for (int i=0; i<kursListe.size(); i++) {
			if ("LK".equals(kursListe.get(i).getKurstyp())) {
				LKs.add(kursListe.get(i));
			}
		}
		for (int i=0; i<LKs.size(); i++) {
			punkte += LKs.get(i).getPunktzahl();
		}
		punkte = punkte * 2;
		if (punkte < 40) {
			bool = false;
		}
		return bool;
	}
	
	private boolean istKriterium4() {
		boolean bool = true;
		ArrayList<Kurs> GKs = new ArrayList<>();
		for (int i=0; i<kursListe.size(); i++) {
			if ("GK".equals(kursListe.get(i).getKurstyp())) {
				GKs.add(kursListe.get(i));
			}
		}
		if (GKs.size() < 11) {
			bool = false;
		}
		return bool;
	}
	
	private boolean istKriterium5() {
		boolean bool = true;
		int punkte = 0;
		ArrayList<Kurs> GKs = new ArrayList<>();
		for (int i=0; i<kursListe.size(); i++) {
			if ("GK".equals(kursListe.get(i).getKurstyp())) {
				GKs.add(kursListe.get(i));
			}
		}
		for (int i=0; i<GKs.size(); i++) {
			punkte += GKs.get(i).getPunktzahl();
		}
		if (punkte < 55) {
			bool = false;
		}
		return bool;
	}
	
	private boolean istKriterium6und7() {
		boolean bool = true;
		ArrayList<Kurs> Sprachen = new ArrayList<>();
		for (int i=0; i<kursListe.size(); i++) {
			if ("D".equals(kursListe.get(i).getFachcode()) || "E".equals(kursListe.get(i).getFachcode()) || "F".equals(kursListe.get(i).getFachcode()) || "S".equals(kursListe.get(i).getFachcode())) {
				Sprachen.add(kursListe.get(i));
			}
		}
		if (Sprachen.size() < 4) {
			bool = false;
		}
		return bool;
	}
}
