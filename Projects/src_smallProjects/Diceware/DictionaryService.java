package Diceware;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DictionaryService {
	
	public Scanner fileScanner;
	private Map<String, String> dictionaryCache;
	
	DictionaryService(String filePath) throws FileNotFoundException {
		this.fileScanner = new Scanner(new FileReader(filePath));
        this.dictionaryCache = new HashMap<>();
        while (fileScanner.hasNext()) {
            String zeile = fileScanner.nextLine();
            String split[] = zeile.split(";");
            dictionaryCache.put(split[0], split[1]);
        }
	}
	
	public String findWord(String number) {
		String word = dictionaryCache.get(number);
		return word;
	}
}
