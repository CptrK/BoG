package SortAlgorithms;

public class QuickSort {

	private int count;
	private int array[];
    private int length;
 
    public void doQuickSort(int[] inputArr) {
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = inputArr.length;
        printValues(array);
        sorting(0, length - 1);
    }
 
    private void sorting(int lowerIndex, int higherIndex) {
        int lowIndx = lowerIndex;
        int highIndx = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        // Divide into two arrays
        while (lowIndx <= highIndx) {
            /**
             * In each iteration, we will identify a number from left side which 
             * is greater then the pivot value, and also we will identify a number 
             * from right side which is less then the pivot value. Once the search 
             * is done, then we exchange both numbers.
             */
            while (array[lowIndx] < pivot) {
                lowIndx++;
            }
            while (array[highIndx] > pivot) {
                highIndx--;
            }
            if (lowIndx <= highIndx) {
                swapNumbers(lowIndx, highIndx);
                //move index to next position on both sides
                lowIndx++;
                highIndx--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < highIndx)
            sorting(lowerIndex, highIndx);
        if (lowIndx < higherIndex)
            sorting(lowIndx, higherIndex);
    }
 
    private void swapNumbers(int lowIndx, int highIndx) {
        int temp = array[lowIndx];
        array[lowIndx] = array[highIndx];
        array[highIndx] = temp;
        count++;
        printValues(array);
    }
    
    // A utility function to print an array
    void printValues(int arr[]) {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println("\n");
    }
    
    public int getCount() {
    	return this.count;
    }
}
