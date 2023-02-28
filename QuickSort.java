import java.util.Arrays;

public class QuickSort implements IntSorter{
    public void sort(int[] array){
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
            if(high - low + 1 <= 15){
                insertionSort(array, low, high); //high - low + 1 is the length of the array
                return;
            }
            int pivot = hoarePartition(array, low, high);
            quickSort(array, low, pivot);
            quickSort(array, pivot + 1, high);
        }
        return;
    }
    /**
     * Iterative insertion sort
     * @param array The array to be sorted
     * @param n Length of the array
     */
    private static void insertionSort(int[] array, int low, int high){
        for(int i = low; i <= high; i++){
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
     * Hoare partition based on pseudocode from wikipedia https://en.wikipedia.org/wiki/Quicksort
     * @param array Array to be sorted
     * @param low 
     * @param high
     * @return
     */
    private static int hoarePartition(int[] array, int low, int high){
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
    private static int tukeysNintherMedian(int[]array, int low, int high){
        int len = high - low + 1;
        int d = len/8; //The array is split in 9 pieces, 8 partitionings is required
        int median1 = medianIndex3(array, low, low + d, low + 2*d);
        int median2 = medianIndex3(array, low + len/2 - d, low + len/2, low + len/2 + d);
        int median3 = medianIndex3(array, high - 2*d, high - d, high);
        return medianIndex3(array, median1, median2, median3); 
    }
    /**
     * Returns the median of index of three integers. Inspired by https://stackoverflow.com/a/14676309
     * @param a
     * @param b
     * @param c
     * @return
     */
    private static int medianIndex3(int[] v, int a, int b, int c){
        int max = Math.max(Math.max(v[a],v[b]),v[c]);
        int min = Math.min(Math.min(v[a],v[b]),v[c]);
        return v[a]^v[b]^v[c]^v[max]^v[min]; //Integers that share values turn to 0 thanks to XOR
    }
    
}