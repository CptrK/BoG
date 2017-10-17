package Vererbung_Flugzeuge;

abstract class Flugzeug {

	private final String name; // HerstellerName
	private final int maxSpeed; //Max. Geschwindigkeit
	private String immatNr; // Immatrikulationsnummer
	private int wings = 1; // Anzahl Flügelpaare
	
	public Flugzeug (String name, int maxSpeed, int wings) {
		this.name = name;
		this.maxSpeed = maxSpeed;
		this.setWings(wings);
		
	}
	
	public String getImmatNummer(){
		return immatNr;	
	}
	
	protected void setImmatNummer (String immatNummer){
		this.immatNr = immatNummer;
		
	}
	
	public int getMaxSpeed(){
		return maxSpeed;
		
	}
@Override
	public String toString() {
		String daten = "";
		return daten;
		
	}

public String getName() {
	return name;
}

public int getWings() {
	return wings;
}

public void setWings(int wings) {
	this.wings = wings;
}
	
	//weitere Methoden
	
}
