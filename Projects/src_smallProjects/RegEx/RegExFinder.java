package RegEx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExFinder {
	
	private String regex;
	private String path;
	public int occurs;

	RegExFinder (String regex, String path) {
		this.regex = regex;
		this.path = path;
	}
	
	public void search() throws IOException {
		int count=0;
		String foundSubStr ="";
		//reading the input file
		BufferedReader br = new BufferedReader(new FileReader(path));
		//writing read content in variable
		String temp;
		while((temp = br.readLine()) != null) {
			//set RegEx pattern and file that is searched in
			Pattern p = Pattern.compile(regex);
		    Matcher m = p.matcher(temp);
		    //search for substrings and writing them in variable
		    while(m.find()) {
		    	//remove leading and ending white spaces or additional lead string
		    	String temp2 = m.group();
		    	if(temp2.startsWith(" ") || temp2.endsWith(" ") || temp2.startsWith("Telefon") || temp2.startsWith("WWW")) {
		    		temp2 = temp2.replaceAll("^\\ |Telefon\\ |WWW\\ |\\ $", "");
		    	}
		    	foundSubStr += temp2 + "\n";
		    	count++;
		    }
		}
		br.close();
	    //give information or the founded substrings out on console
	    if (count > 0) {
	    	System.out.println(foundSubStr);
	    	System.out.println("There were " + count + " matches found for your requested regular expression.\n");
	    } else {
	    	System.out.println("Expected some but got no matches? Please check if you chose the right input file.\n");
	    }
		this.occurs = count;
	}
	
	public int getOccurs() {
		return this.occurs;
	}
}
