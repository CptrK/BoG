package Vererbung_Flugzeuge;

abstract class Verkehrsflugzeug extends Flugzeug {
	
	private int passengers; // Anzahl Passagiere
	private boolean looping; // Aussage, ob das Flugzeug einen Looping machen kann
	
	public Verkehrsflugzeug (String hersteller, int maxSpeed, String immatNr , int passengers) {
		super(hersteller,maxSpeed,1);
		super.setImmatNummer(immatNr);
		this.passengers = passengers;
		this.setLooping(false);
	}

	public int getAnzahlPassagiere() {
		return passengers;
		
	}
	
	protected void setAnzahlPassagiere(int passagiere) {
		this.passengers = passagiere;
		
	}

	public boolean isLooping() {
		return looping;
	}

	public void setLooping(boolean looping) {
		this.looping = looping;
	}
}
