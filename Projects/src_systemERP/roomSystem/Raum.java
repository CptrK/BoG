package roomSystem;

public class Raum {
	//variables to give information about one object of this class
	private static boolean isBelegt;
	final public String name;
	public static String belegtVon;
	final private String rNummer;
	final private double grundflaeche;
	final private String rHoehe;
	final private double rBreite;
	final private double rLaenge;
	//constructor method when an object is created
	Raum (String rNummer, String laenge, String breite, String hoehe)	{
		setIsBelegt(false);
		isBelegt = getIsBelegt();
		this.rNummer = rNummer;
		this.name = buildRaumName(rNummer);
		rBreite = Double.parseDouble(laenge);
		rLaenge = Double.parseDouble(breite);
		this.grundflaeche = rBreite*rLaenge;
		this.rHoehe = hoehe + "m";
	}
	
	public String getRaumNummer() {
		return this.rNummer;
	}
	
	public double getGrundflaeche() {
		return this.grundflaeche;
	}
	
	public String getHoehe() {
		return this.rHoehe;
	}
	
	public double getBreite() {
		return rBreite;
	}
	
	public double getLaenge() {
		return rLaenge;
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
	
	private String buildRaumName(String nummer) {
		final String fileName = "Raum " + nummer;
		return fileName;
	}
	
	public boolean getIsBelegt() {
		return isBelegt;
	}
	
	public void setIsBelegt(boolean bool)	{
		isBelegt = bool;
	}

}
