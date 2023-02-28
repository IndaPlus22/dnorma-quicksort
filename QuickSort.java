import java.util.Arrays;

public class QuickSort implements IntSorter{
    public void sort(int[] array){
        if(array.length <= 15){
            InsertionSort inSort = new InsertionSort();
            inSort.sort(array);
        }
        quickSort(array, 0, array.length - 1);
    }
    /**
     * Recursive quicksort function
     * @param array
     * @param low
     * @param high
     */
    public void quickSort(int[]array, int low, int high){
        if(low < high){
            int pivot = hoarePartition(array, low, high);
            quickSort(array, low, pivot);
            quickSort(array, pivot + 1, high);
        }
        return;
    }
    /**
     * Hoare partition based on pseudocode from wikipedia
     * @param array
     * @param low
     * @param high
     * @return
     */
    public int hoarePartition(int[] array, int low, int high){
        int pivot = array[low];

        //Left index
        int i = low - 1;

        //Right index
        int j = high + 1;

        while(true){
            do { //Move the left index to the right while it is less than the pivot
                i++;
            } while (array[i] < pivot);

            do { //Move the right index to the left while it is more than the pivot
                j--;
            } while (array[j] > pivot);

            if(i>=j){ 
                return j;
            }
            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }
        
    }
    public static void main(String[]args){
        int[] smallArray = new int[]{2, 3, 7, 9, 8, 10, 12, 11};
        int[] expectedArray = Arrays.copyOf(smallArray, smallArray.length);
        QuickSort qsort = new QuickSort();
        InsertionSort inSort = new InsertionSort();

        inSort.sort(smallArray);
        Arrays.sort(expectedArray);

        System.out.println(Arrays.toString(smallArray));
        System.out.println(Arrays.toString(expectedArray));
    }
    
}