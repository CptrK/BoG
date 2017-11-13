package SortAlgorithms;

public class ShellSort {

	private int count=0;
	
    /* function to sort arr using shellSort */
    public void doShellSort(int inputArr[]) {
        int arrLength = inputArr.length;
        printValues(inputArr);
        // Start with a big gap, then reduce the gap
        for (int gap = arrLength/2; gap > 0; gap /= 2) {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements a[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            for (int i = gap; i < arrLength; i += 1) {
                // add a[i] to the elements that have been gap
                // sorted save a[i] in temp and make a hole at
                // position i
                int temp = inputArr[i];
 
                // shift earlier gap-sorted elements up until
                // the correct location for a[i] is found
                int j;
                for (j = i; j >= gap && inputArr[j - gap] > temp; j -= gap) {
                    inputArr[j] = inputArr[j - gap];
                }
                // put temp (the original a[i]) in its correct
                // location
                inputArr[j] = temp;
                count++;
                printValues(inputArr);
            }
        }
    }

	/* An utility function to print array of size n*/
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
