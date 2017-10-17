package Fachhochschulreife;

public class Test {
	public static void main (String [] args) {
		Schueler schueler1 = new Schueler("Schueler","D", 8,"BRC", 8);
		schueler1.kursHinzufügen("M", "GK", "12.1", 8);
		schueler1.kursHinzufügen("S", "GK", "12.1", 8);
		schueler1.kursHinzufügen("Bio", "GK", "12.1", 8);
		schueler1.kursHinzufügen("GmG", "GK", "12.1", 8);
		schueler1.kursHinzufügen("E", "GK", "12.1", 8);
		schueler1.kursHinzufügen("WI", "GK", "12.1", 8);
		schueler1.kursHinzufügen("VWL", "GK", "12.1", 8);
		schueler1.kursHinzufügen("D", "LK", "12.2", 7);
		schueler1.kursHinzufügen("BRC", "LK", "12.2", 7);
		schueler1.kursHinzufügen("M", "GK", "12.2", 7);
		schueler1.kursHinzufügen("S", "GK", "12.2", 7);
		schueler1.kursHinzufügen("Bio", "GK", "12.2", 7);
		schueler1.kursHinzufügen("GmG", "GK", "12.2", 7);
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
