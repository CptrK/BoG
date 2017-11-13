package SortAlgorithms;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class Sorter {

	private static Random random = new Random();
	private static int swapCounter;
	private static int numberOfValues = 50;
	private static double effort;
	
	public static void main(String[] args) {
		//starting message
		System.out.println("Starting the program... \n\n");

		//creating of a new array with 50 values
		ArrayList<Integer> arraylist = new ArrayList<Integer>();
		for(int i = numberOfValues; i > 0; i--) {
			int num = random.nextInt(201)-100;
			arraylist.add(num);
		}
		int[] array = new int[arraylist.size()];
		for (int i=0; i < array.length; i++) {
	        array[i] = arraylist.get(i).intValue();
	    }
		
		//creating user dialog and deciding on sorting method
		String task = (String) JOptionPane.showInputDialog
				("Please write which sort you want to activate..."
						+ "('BubbleSort', 'HeapSort', 'InsertionSort', 'MergeSort', 'QuickSort', 'SelectionSort', 'ShellSort')");
		if (!"".equals(task)) {
			task = task.toLowerCase();
			switch(task) {
				case "bubblesort":
					BubbleSort bubble = new BubbleSort();
					bubble.doBubbleSort(array);
					swapCounter = bubble.getCount();
					break;
				case "heapsort":
					HeapSort heap = new HeapSort();
					heap.doHeapSort(array);
					swapCounter = heap.getCount();
					break;
				case "insertionsort":
					InsertionSort insertion = new InsertionSort();
					insertion.doInsertionSort(array);
					swapCounter = insertion.getCount();
					break;
				case "mergesort":
					MergeSort merge = new MergeSort();
					merge.doMergeSort(array);
					swapCounter = merge.getCount();
					break;
				case "quicksort":
					QuickSort quick = new QuickSort();
					quick.doQuickSort(array);
					swapCounter = quick.getCount();
					break;
				case "selectionsort":
					SelectionSort selection = new SelectionSort();
					selection.doSelectionSort(array);
					swapCounter = selection.getCount();
					break;
				case "shellsort":
					ShellSort shell = new ShellSort();
					shell.doShellSort(array);
					swapCounter = shell.getCount();
					break;
			}
		}
		effort = (swapCounter*1.0)/numberOfValues;
		
		//after sorting informations
		System.out.println("\nThere were " + swapCounter + " number swaps needed to sort the array.");
		System.out.println("\nThe effort was " + effort + "x (times) than values in the array.");
		//ending message
		System.out.println("\n\nEnding programm...");
	}

}
