package Diceware;

public class Wuerfelbecher {
	
	private Wuerfel wuerfel;
	private String wuerfelErgebnis;
	
	Wuerfelbecher() {
		this.wuerfel = new Wuerfel();
	}
	
	public void schuetteln() {
		String temp="";
		for (int i=0; i < 5; i++) {
			wuerfel.wuerfeln();
			if ("".equals(temp)) {
				temp = Integer.toString(wuerfel.getAugenzahl());
			} else {
				temp = temp + Integer.toString(wuerfel.getAugenzahl());
			}
		}
		this.wuerfelErgebnis = temp;
	}
	
	public String getWuerfelErg() {
		return this.wuerfelErgebnis;
	}

}
