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
            if(high - low + 1 <= 12 || array.length > 1000){
                insertionSort(array, low, high);
                return;
            }
            // int pivot = hoarePartition(array, low, high);
            // quickSort(array, low, pivot);
            // quickSort(array, pivot + 1, high);

            int pivot = tukeysNintherMedian(array, low, high);
            int[] pivots = bentleyMcIlroyPartition(array, low, high, pivot);
            int less = pivots[0];
            int great = pivots[1];

            quickSort(array, low, less - 1);
            quickSort(array, great + 1, high);
            
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
                swap(array, j-1, j);
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
        int pivot = array[low];
        if(high - low + 1 > 400){
            pivot = array[tukeysNintherMedian(array, low, high)];
        }
                
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
            swap(array, i, j);
        }
        
    }
    private static int[] bentleyMcIlroyPartition(int[]array,int low,int high,int pivot){
        int pivotValue = array[pivot];
        int lesserVal = low;
        int greaterVal = high;

        for(int i = low; i <= greaterVal;){
            if(array[i] > pivotValue){
                swap(array, greaterVal, i);
                greaterVal--;
            }
            else if(array[i] < pivotValue){
                swap(array, lesserVal, i);
                lesserVal++;
                i++;
            }
            else{
                i++;
            }
        }
        return new int[]{lesserVal, greaterVal};
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
     * Returns the median of index of three integers. Taken from
     * https://github.com/fracpete/princeton-java-algorithms/blob/master/src/main/java/edu/princeton/cs/algorithms/QuickX.java
     * @param a Integer
     * @param b Integer
     * @param c Integer
     * @return Index of the median of three values in the array
     */
    private static int medianIndex3(int[] v, int a, int b, int c){
        return (v[a] < v[b]) ?
               ((v[b] < v[c]) ? b : (v[a] < v[c] ? c : a)) :
               ((v[c] < v[b]) ? b : (v[c] < v[a] ? c : a));
    }
    private static void swap(int[] array, int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

}