package SortAlgorithms;

public class BubbleSort {
	
	private int count;
	
	// logic to sort the elements
    public void doBubbleSort(int array[]) {
        int arrLength = array.length;
        int posSecondValue;
        for (int m = arrLength; m >= 0; m--) {
            for (int i = 0; i < arrLength - 1; i++) {
                posSecondValue = i + 1;
                if (array[i] > array[posSecondValue]) {
                    swapValues(i, posSecondValue, array);
                }
            }
            printValues(array);
            count++;
        }
    }
    
    //logic to swap numbers
    private void swapValues(int posFirst, int posSecond, int[] array) {
        int temp;
        temp = array[posFirst];
        array[posFirst] = array[posSecond];
        array[posSecond] = temp;
    }
    
    //logic to print numbers of array
    private void printValues(int[] input) {
          
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + ", ");
        }
        System.out.println("\n");
    }

    public int getCount() {
    	return this.count;
    }
}
