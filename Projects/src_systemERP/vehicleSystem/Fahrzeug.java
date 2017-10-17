package vehicleSystem;

public class Fahrzeug {
	//variables to give information about one object of this class
	private static boolean isBelegt;
	public String name;
	private String fahrzeugNummer;
	public static String belegtVon;
	public String fahrzeugTyp;
	//constructor method when an object is created
	public Fahrzeug (String fahrzeugTyp, String name, String fahrzeugNummer) {
		setIsBelegt(false);
		isBelegt = getIsBelegt();
		this.name=name;
		this.fahrzeugTyp=fahrzeugTyp;
		this.fahrzeugNummer = fahrzeugNummer;
	}
	
	public String getFahrzeugTyp() {
		return this.fahrzeugTyp;
	}
	
	public void setFahrzeugTyp(String typ) {
		fahrzeugTyp = typ;
	}
	
	public String getFahrzeugNummer() {
		return this.fahrzeugNummer;
	}
	
	public void setFahrzeugNummer(String nummer) {
		fahrzeugNummer = nummer;
	}
	
	public String getBelegtVon() {
		return belegtVon;
	}
	
	public void setBelegtVon(String name) {
		belegtVon = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getIsBelegt() {
		return isBelegt;
	}
	
	public void setIsBelegt(boolean bool)	{
		isBelegt = bool;
	}
	
	public double getDurschnittsverbrauch(int kilometerstand, double liter) {
		double verbrauch = liter/kilometerstand;
		return verbrauch;
	}
	
}
