package Fachhochschulreife;

public class Test {
	public static void main (String [] args) {
		Schueler schueler1 = new Schueler("Schueler","D", 8,"BRC", 8);
		schueler1.kursHinzuf�gen("M", "GK", "12.1", 8);
		schueler1.kursHinzuf�gen("S", "GK", "12.1", 8);
		schueler1.kursHinzuf�gen("Bio", "GK", "12.1", 8);
		schueler1.kursHinzuf�gen("GmG", "GK", "12.1", 8);
		schueler1.kursHinzuf�gen("E", "GK", "12.1", 8);
		schueler1.kursHinzuf�gen("WI", "GK", "12.1", 8);
		schueler1.kursHinzuf�gen("VWL", "GK", "12.1", 8);
		schueler1.kursHinzuf�gen("D", "LK", "12.2", 7);
		schueler1.kursHinzuf�gen("BRC", "LK", "12.2", 7);
		schueler1.kursHinzuf�gen("M", "GK", "12.2", 7);
		schueler1.kursHinzuf�gen("S", "GK", "12.2", 7);
		schueler1.kursHinzuf�gen("Bio", "GK", "12.2", 7);
		schueler1.kursHinzuf�gen("GmG", "GK", "12.2", 7);
		schueler1.alleKurseAusgeben();
		
		System.out.println(schueler1.ermittleGesamtpunktzahl(schueler1.kursListe));
		System.out.println(schueler1.berechneDurschnittsnote());
		
		if (schueler1.kontrolle() == true) {
			System.out.println("Du hast deine Fachhochschulreife erlangt!");
		} else {
			System.out.println("Du hast deine Fachhochschulreife nicht erlangt!");
		}
	}
}
