import java.util.Arrays;

public class QuickSort implements IntSorter{
    public void sort(int[] array){
        if(array.length <= 15){
            insertionSort(array);
        }
        quickSort(array, 0, array.length - 1);
    }
    /**
     * Recursive quicksort function
     * @param array
     * @param low
     * @param high
     */
    public static void quickSort(int[]array, int low, int high){
        if(low < high){
            if(array.length <= 15){
                insertionSort(array);
                return;
            }
            int pivot = hoarePartition(array, low, high);
            quickSort(array, low, pivot);
            quickSort(array, pivot + 1, high);
        }
        return;
    }
    public static void insertionSort(int[] array){
        int n = array.length;
        for(int i = 1; i < n; i++){
            int j = i;
            while(j > 0 && array[j-1] > array[j]){
                int temp = array[j-1];
                array[j-1] = array[j];
                array[j] = temp;
                j--;
            }
        }
    }
    /**
     * Hoare partition based on pseudocode from wikipedia
     * @param array
     * @param low
     * @param high
     * @return
     */
    public static int hoarePartition(int[] array, int low, int high){
        int pivot = array[(high+low)/2];

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
}