package RegEx;

import java.io.IOException;

public class RegExTest {
	
	private static String path = "D:/git/SchoolProjects/Projects/src_smallProjects/RegEx/example.txt";
	
	public static void main (String[] args) throws IOException {
		//write starting comment
		System.out.println("Start program... \n\n");
		
		//set strings for RegEx's
		String regexDate = "(\\d{1,2}\\.){2}(?:\\d{2}){1,2}";
		String regexPostal = "(\\ )[34][0-5](\\d{3})(\\ )";
		String regexTel = "(\\w{7}\\ )(\\+49\\ )(\\d{1,4}\\ )(\\d{1,7})(?:\\-|\\ )(?:\\d{0,5})";
		String regexInet = "(?:w{3}\\.|\\bWWW\\ )(\\S*\\.)(\\w{2,3})";
		String regexMail = "(\\S*\\@)(\\S*\\.)(\\w{2,3})";
		
		//creating various RegExFinder
		RegExFinder regDate = new RegExFinder(regexDate, path);
		RegExFinder regPostal = new RegExFinder(regexPostal, path);
		RegExFinder regTel = new RegExFinder(regexTel, path);
		RegExFinder regInet = new RegExFinder(regexInet, path);
		RegExFinder regMail = new RegExFinder(regexMail, path);
		
		//start search with the RegExFinder
		System.out.println("Start listing dates found in document...\n");
		regDate.search();
		
		System.out.println("Start listing postalcodes found in document...\n");
		regPostal.search();
		
		System.out.println("Start listing telephone numbers found in document...\n");
		regTel.search();
		
		System.out.println("Start listing internet addresses found in document...\n");
		regInet.search();
		
		System.out.println("Start listing emails found in document...\n");
		regMail.search();
		
		//giving information about how much RegEx's were found in total
		int overall = regDate.getOccurs() + regPostal.getOccurs() + regTel.getOccurs() + regInet.getOccurs() + regMail.getOccurs();
		System.out.println("In the current run " + overall + " substrings were found that matched one of the given regular expressions: \n"
				+ "Regular expression for dates was: " + regexDate + "\n"
				+ "Regular expression for postalcodes was: " + regexPostal + "\n"
				+ "Regular expression for telephone numbers was: " + regexTel + "\n"
				+ "Regular expression for internet addresses was: " + regexInet + "\n"
				+ "Regular expression for mail addresses was: " + regexMail + ".");
		
		//write finishing comments
		System.out.println("\n\nEnd program...");
		System.out.println("Finished.");
	}

}
