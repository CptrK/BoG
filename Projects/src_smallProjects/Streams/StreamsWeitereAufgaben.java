package Streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JOptionPane;

public class StreamsWeitereAufgaben {

	//lists for the different tasks
	private static List<String> strList = Arrays.asList("abc", "", "bcd", "", "defg","jk");
	private static List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany","Italy", "U.K.", "Canada");
	private static List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
	private static Stream<String> listStream;
	private static Stream<Integer> numStream;
	private static int count;

	public static void main(String[] args) {
		
		//creating user dialog
		String taskNumber = (String) JOptionPane.showInputDialog("Please choose which task you would like to run... (Input: 'A1'-'A6')");
		
		switch(taskNumber) {
			case "A1":
				//A1) counting the empty strings
				listStream = strList.stream();
				count = (int) listStream.filter(entity -> "".equals(entity)).count();
				System.out.println(strList);
				System.out.println("The list contains " + count + " empty string/s.");
				break;
			case "A2":
				//A2) counting strings with 3 or more chars
				listStream = strList.stream();
				count = (int) listStream.filter(entity -> entity.length() >= 3).count();
				System.out.println(strList);
				System.out.println("The list contains " + count + " string/s with 3 or more chars.");
				break;
			case "A3":
				//A3) counting strings that start with 'a'
				listStream = strList.stream();
				count = (int) listStream.filter(entity -> entity.startsWith("a")).count();
				System.out.println(strList);
				System.out.println("The list contains " + count + " string/s that starts with 'a'.");
				break;
			case "A4":
				//A4) creating new list without empty strings
				listStream = strList.stream();
				List<String> nyuList = listStream.filter(entity -> !"".equals(entity)).collect(Collectors.toList());
				System.out.println(strList);
				System.out.println(nyuList);
				break;
			
			case "A5":
				//A5) creating new list with all strings in upper case
				listStream = G7.stream();
				List<String> yewList = listStream.map(entity -> entity.toUpperCase()).collect(Collectors.toList());
				System.out.println(G7);
				System.out.println(yewList);
				break;
			
			case "A6":
				//A6) printing a list with the square number of each value one time
				numStream = numbers.stream();
				System.out.println(numbers);
				System.out.println(Arrays.toString(numbers.stream().distinct().toArray()));
				System.out.println(Arrays.toString(numbers.stream().distinct().sorted().toArray()));
				System.out.println(Arrays.toString(numStream.distinct().sorted().map(n -> n*n).toArray()));
				break;
		}
	}
}
