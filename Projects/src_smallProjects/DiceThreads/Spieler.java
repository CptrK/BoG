package DiceThreads;

public class Spieler {
	
	private String name;
	
	Spieler (String name){
		this.name = name;
	}

	public void becherSchuetteln(Wuerfelbecher wuerfelbecher) {
		wuerfelbecher.schuetteln();
	}

	public void becherZeigen(Wuerfelbecher wuerfelbecher) {
		wuerfelbecher.anzeigen();
	}
	
	public String becherUebergeben(Wuerfelbecher wuerfelbecher) {
		return wuerfelbecher.uebergeben();
	}
	
	public String getName() {
		return this.name;
	}
	
}