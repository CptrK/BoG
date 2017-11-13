package SortAlgorithms;

public class MergeSort {
	
	private int count;
	private int[] array;
    private int[] tempArr;
    private int length;
	
	public void doMergeSort(int[] inputArr) {
        this.array = inputArr;
        this.length = inputArr.length;
        this.tempArr = new int[length];
        doMergeSort(0, length - 1);
    }
 
    private void doMergeSort(int lowerIndex, int higherIndex) {
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }
 
    private void mergeParts(int lowerIndex, int middle, int higherIndex) {
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempArr[i] = array[i];
        }
        int lowIndx = lowerIndex;
        int medianAddOne = middle + 1;
        int i = lowerIndex;
        while (lowIndx <= middle && medianAddOne <= higherIndex) {
            if (tempArr[lowIndx] <= tempArr[medianAddOne]) {
                array[i] = tempArr[lowIndx];
                lowIndx++;
            } else {
                array[i] = tempArr[medianAddOne];
                medianAddOne++;
            }
            i++;
            count++;
            printValues(array);
        }
        while (lowIndx <= middle) {
            array[i] = tempArr[lowIndx];
            i++;
            lowIndx++;
        }
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
