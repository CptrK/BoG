package SortAlgorithms;

public class SelectionSort {
	
	private int count;
	
	public void doSelectionSort(int[] array){
        
        for (int i = 0; i < array.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j] < array[index]) 
                    index = j;
      
            int smallerNumber = array[index];  
            array[index] = array[i];
            array[i] = smallerNumber;
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
