package RegEx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExFinder {
	
	String regex;
	String content;

	RegExFinder (String regex, String path) throws IOException {
		
		this.regex = regex;
		BufferedReader br = new BufferedReader(new FileReader(path));
		content = br.readLine();
		br.close();
	}
	
	public void search() {
		System.out.println(content);
		/*Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(content);
	    m.find();
	    System.out.println(m.group());*/
		
	}
}
