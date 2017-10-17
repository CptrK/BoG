package Diceware;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Diceware {

	public static void main(String[] args) throws FileNotFoundException {
		DictionaryService service = new DictionaryService("D:\\Schule\\Programs\\src_smallProjects\\Diceware\\Diceware.txt");
		Wuerfelbecher becher = new Wuerfelbecher();
		String password ="";
		System.out.println("Of how many words should your password consist?");
        Scanner sc = new Scanner(System.in);
        int words = sc.nextInt();
        if (words <= 4) {
            System.out.println("This password would be unsafe!");
        } else {
        	for (int i = 0; i < words; i++) {
        		becher.schuetteln();
        		password += service.findWord(becher.getWuerfelErg());
        	}
        }
        service.fileScanner.close();
        sc.close();
        if ("".equals(password)) {
        } else {
        	System.out.println("This is your password: " + password);
        }
	}
}
