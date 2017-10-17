package Fachhochschulreife;

public class Kurs {
	
	private String fachcode;
	private String kursTyp;
	private String lehrjahr;
	private int punktzahl;
	final private String error = "Dein Kurs konnte nicht erstellt werden, da die eingegebenen Daten nicht korrekt sind!";
	final String[] codelist = {"BRC","D","M","E","F","S","Bio","Che","Phy","GmG","VWL","WI","Reli","Philo","PM","Mark","Sport"};
	

	Kurs (String fachcode, String kursTyp, String lehrjahr, int punktzahl) {
		for (int i =0; i<codelist.length; i++) {
			if (codelist[i].equals(fachcode)) {
				this.fachcode = fachcode;
			}
		}
		if ("LK".equals(kursTyp) || "GK".equals(kursTyp)) { 
			this.kursTyp = kursTyp;
		} if (punktzahl >= 0 && punktzahl <= 15) {
			this.punktzahl = punktzahl;
		} if ("12.1".equals(lehrjahr) || "12.2".equals(lehrjahr)) {
			this.lehrjahr = lehrjahr;
		}
		else {
			System.out.println(this.error);
		}
	}
	
	public int anrechnungsFaktor(String kursTyp) {
		int faktor = 1;
		if ("LK".equals(kursTyp)) {
			faktor = 2;
		} else if ("GK".equals(kursTyp)){
			faktor = 1;
		}
		
		return faktor;
	}
	
	@Override
	public String toString() {
		String kursDaten = fachcode + " " + kursTyp + " " + this.lehrjahr + " Punkte: " + punktzahl;
		return kursDaten;
	}
	
	public void setFachcode(String code) {
		this.fachcode = code;
	}
	
	public String getFachcode() {
		return this.fachcode;
	}
	
	public void setKurstyp(String typ) {
		if ("GK".equals(typ) || "LK".equals(typ)) {
			this.kursTyp = typ;
		}
	}
	
	public String getKurstyp() {
		return this.kursTyp;
	}
	
	public void setLehrjahr(String jahrgang) {
		if ("12.1".equals(jahrgang) || "12.2".equals(jahrgang)) {
			this.lehrjahr = jahrgang;
		}
	}
	
	public String getLehrjahr() {
		return lehrjahr;
	}
	
	public void setPunktzahl(int punktzahl) {
		this.punktzahl = punktzahl;
	}
	
	public int getPunktzahl() {
		return this.punktzahl;
	}
}
