package Vererbung_Flugzeuge;

public class Doppeldecker extends Flugzeug {
	
	private final boolean offenesCockpit;
	private final int loopingSpeed = 320;

	public Doppeldecker (String hersteller, int maxSpeed, String immatNr , boolean offenesCockpit) {
		super(hersteller,maxSpeed,1);
		super.setImmatNummer(immatNr);
		this.offenesCockpit = offenesCockpit;
		// this.offenesCockpit = true;
		getLooping(maxSpeed);
	}
	
	public boolean isOffenesCockpit() {
		return offenesCockpit;
	
	}
	
	private boolean getLooping(int speed) {
		if (speed >= loopingSpeed){
			return true;
		}
		else {
			return false;
		}
	}
	
}
