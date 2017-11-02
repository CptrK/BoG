package RegEx;

import java.io.IOException;

public class RegExTest {
	
	private static String path = "D:/git/SchoolProjects/Projects/src_smallProjects/RegEx/example.txt";
	
	public static void main (String[] args) throws IOException {
		//write start comment
		System.out.println("Start program... \n\n");
		
		//creating various RegExFinder
		RegExFinder regDate = new RegExFinder("^(\\d{1,2}\\.){2}(\\20\\d{2}|\\d{2})$", path);
		RegExFinder regPostal = new RegExFinder("^(\\d{5})$", path);
		RegExFinder regTel = new RegExFinder("^(\\+\\d{2}\\ )(\\d{1,4}\\ )(.{4,8})$", path);
		RegExFinder regInet = new RegExFinder("^(w{3}\\.)(\\S*\\.)(\\.{2,})$", path);
		RegExFinder regMail = new RegExFinder("^(\\S*\\@)(\\S*\\.)(\\w{2,3})$", path);
		
		//start search for the RegExFinder
		System.out.println("Start listing dates found in document... \n");
		regDate.search();
		
		/*~ System.out.println("\nStart listing postalcodes found in document... \n");
		regPostal.search();
		
		System.out.println("\nStart listing telephone numbers found in document... \n");
		regTel.search();
		
		System.out.println("\nStart listing internet addresses found in document... \n");
		regInet.search();
		
		System.out.println("\nStart listing emails found in document... \n");
		regMail.search(); ~*/
		
		//write finish comments
		System.out.println("\n\nEnd program...");
		System.out.println("Finished.");
	}

}
