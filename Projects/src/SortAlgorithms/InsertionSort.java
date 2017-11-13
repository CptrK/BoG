package SortAlgorithms;

public class InsertionSort {
	
	private int count;
	
	public void doInsertionSort(int[] array){
        
        int temp;
        for (int i = 1; i < array.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(array[j] < array[j-1]){
                    temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
            }
            printValues(array);
            count++;
        }
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
