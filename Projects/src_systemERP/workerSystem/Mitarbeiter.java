package workerSystem;

public class Mitarbeiter {
	//variables to give information about one object of this class
	private static boolean isBelegt;
	final public String name;
	public static String belegtVon;
	final private String mNummer;
	public String login;
	public String email;
	public String vorname;
	public String nachname;
	public String geburtstag;
	public String gruppe;
	private boolean admin;
	//constructor method when an object is created
	Mitarbeiter (String mNummer, String name, String birth, String gruppe, String login) {
		setIsBelegt(false);
		isBelegt = getIsBelegt();
		this.mNummer = mNummer;
		this.name = name;
		buildNames(name);
		this.geburtstag = birth;
		this.login = login;
		buildEmailAdress(vorname, nachname);
		this.gruppe = gruppe;
		if (this.gruppe.equals("Admin")) {
			admin = true;
		}
	}
	
	public String getMitarbeiterNummer() {
		return this.mNummer;
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
	
	public boolean getIsBelegt() {
		return isBelegt;
	}
	
	public void setIsBelegt(boolean bool)	{
		isBelegt = bool;
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getVorName() {
		return this.vorname;
	}
	
	public String getNachName() {
		return this.nachname;
	}
	
	private void buildNames(String name) {
		String [] names = name.split("[.]");
		String name1 = names[0];
		this.vorname = name1;		
		String name2 = names[1];
		this.nachname = name2;		
	}
	
	private void buildEmailAdress(String vorname, String nachname) {
		this.email = vorname + "." + nachname + "@erpsystem.de";
	}
	
	public String getEmail() {
		return this.email;
	}
	
	
	public String getBirthdate() {
		return this.geburtstag;
	}
	
	public String getGruppe() {
		return this.gruppe;
	}
	
	public void setAdmin(Boolean bool) {
		this.admin = bool;
	}
	
	public boolean getAdmin() {
		return this.admin;
	}
}
