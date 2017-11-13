package Streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.JOptionPane;

public class StreamTests {
	
	private static List<Integer> list = Arrays.asList(1,2,3,4,19,10,2,27);
	private static Stream<Integer> listStream = list.stream();
	
	public static void main (String[] args) {
		
		//print array before task
		System.out.println(list);
		
		//creating user dialog
		String taskNumber = (String) JOptionPane.showInputDialog("Please choose which task you would like to run... (Input: '1'-'3')");
		
		switch(taskNumber) {
			case "1":
				System.out.println(Arrays.toString(listStream.distinct().sorted().toArray()));
				break;
			case "2":
				System.out.println(Arrays.toString(listStream.filter(n -> n % 2 == 0).distinct().sorted().toArray()));
				break;
			case "3":
				System.out.println("The sum of all uneven values is: " + listStream.filter(n -> n % 2 != 0).mapToInt(i -> i).sum());
				break;
		}
	}
}
