package SortAlgorithms;

public class HeapSort {
	
	private int count;
	
	public void doHeapSort(int[] inputArr) {
        int arrLength = inputArr.length;
 
        // Build heap (rearrange array)
        for (int i = arrLength / 2 - 1; i >= 0; i--)
            heapify(inputArr, arrLength, i);
 
        // One by one extract an element from heap
        for (int i=arrLength-1; i>=0; i--)
        {
            // Move current root to end
            int temp = inputArr[0];
            inputArr[0] = inputArr[i];
            inputArr[i] = temp;
 
            // call max heapify on the reduced heap
            heapify(inputArr, i, 0);
        }
    }
 
    // To heapify a subtree rooted with node i which is
    // an index in array[].
    void heapify(int array[], int heapSize, int i) {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2
 
        // If left child is larger than root
        if (l < heapSize && array[l] > array[largest]) {
            largest = l;
        }
        // If right child is larger than largest so far
        if (r < heapSize && array[r] > array[largest]) {
            largest = r;
    	}
        // If largest is not root
        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
 
            // Recursively heapify the affected sub-tree
            heapify(array, heapSize, largest);
        }
        printValues(array);
        count++;
    }
 
    /* A utility function to print array of size n */
    void printValues(int arr[]) {
        int n = arr.length;
        for (int i=0; i<n; ++i) {
            System.out.print(arr[i]+" ");
        }
        System.out.println("\n");
    }
    
    public int getCount() {
    	return this.count;
    }
}
