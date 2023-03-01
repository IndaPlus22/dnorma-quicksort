/**
 * QuickSort class that uses a recursive quicksort with a few optimizations.
 * Uses insertion sort for smaller arrays, otherwise Hoare's partition scheme.
 * 3-way partitioning is commented out as it generally gives slower results for
 * the given kattis task.
 */
public class QuickSort implements IntSorter{

    public void sort(int[] array){
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Recursive quicksort function that uses Hoare's partition scheme. The recursive
     * function call stack is handled by the compiler. 
     * 
     * @param array Array that contains input data
     * @param low Lowest index in the current array
     * @param high Highest index in the current array
     */
    public static void quickSort(int[]array, int low, int high){
        if(low < high){
            int size = high - low + 1;
            if(size <= 13){
                insertionSort(array, low, high);
            }
            //In case 3-way partitioning needs to be used
            
            // else{
            //     int[] pivots = bentleyMcIlroyPartition(array, low, high);
    
            //     quickSort(array, low, pivots[0] - 1);
            //     quickSort(array, pivots[1] + 1, high);
            // }
            else{
                int pivot = hoarePartition(array, low, high);
                quickSort(array, low, pivot);
                quickSort(array, pivot + 1, high);
            }
                       
        }
        return;
    }
    /**
     * Iterative insertion sort for small input sizes.
     * 
     * Average time complexity: O(n^2)
     * Worst time complexity: O(n^2)
     * 
     * @param array Array that contains input data
     * @param low Lowest index in the current array
     * @param high Highest index in the current array
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
     * Hoare's partition scheme based on pseudocode from wikipedia:
     * https://en.wikipedia.org/wiki/Quicksort. Method for choosing pivot changes
     * depending on size of the sub-array. Not great for input data that is almost
     * sorted or sorted. 
     * 
     * Average time complexity: O(n)
     * 
     * @param array Array that contains all the values
     * @param low Lowest index in the current array
     * @param high Highest index in the current array
     * @return The next pivot to be used for the recursive quicksort algorithm
     */
    private static int hoarePartition(int[] array, int low, int high){
        int pivot = array[low];
        int len = high - low + 1;
        if(len > 1000){
            pivot = array[tukeysNintherMedian(array, low, high)];
        }
        else if(len > 100){
            pivot = array[medianIndex3(array, low, high, low + len/2)];
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

            if(i>=j){  //When the indexes meet each other, return
                return j;
            }
            swap(array, i, j);
        }
        
    }
    /**
     * 3-way partitioning based on Bentley-McIlroy's implementation. Is very good for arrays
     * with many repeated elements. Time complexity for the worst case of single-pivot partitioning,
     * which is O(n^2), is O(n) here. Method for choosing pivot changes depending on size of the 
     * sub-array. On average slightly slower than Hoare's method based on the kattis input data.
     * 
     * Average time complexity: O(n)
     * 
     * @param array Array with int values
     * @param low Lowest index of the subarray
     * @param high Highest index of the subarray
     * @return Array with two pivots for the recursive quicksort algorithm
     */
    private static int[] bentleyMcIlroyPartition(int[]array,int low,int high){
        int pivotValue = array[low];
        int len = high - low + 1;
        if(len > 1000){
            pivotValue = array[tukeysNintherMedian(array, low, high)];
        }
        else if(len > 50){
            pivotValue = array[medianIndex3(array, low, high, low + len/2)];
        }

        int lesserVal = low;
        int greaterVal = high;

        for(int i = low; i <= greaterVal;){ 
            if(array[i] > pivotValue){
                swap(array, greaterVal, i); //Move to a greater value if more than pivot
                greaterVal--;               //Move array pointer
            }
            else if(array[i] < pivotValue){
                swap(array, lesserVal, i); //Move to a lesser value if less than pivot
                lesserVal++;               //Move array pointer
                i++;                       //Compare next element later
            }
            else{
                i++;
            }
        }
        return new int[]{lesserVal, greaterVal};
  
    }
    /**
     * Tukey's ninther method. Finds the median of 3 medians which are taken from different parts
     * of the array. Taken from:
     * https://algs4.cs.princeton.edu/23quicksort/QuickBentleyMcIlroy.java.html
     * @param array Array with int values
     * @param low Lowest index of the subarray
     * @param high Highest index of the subarray
     * @return Index of the median from 9 values in the array
     */
    private static int tukeysNintherMedian(int[]array, int low, int high){
        int len = high - low + 1;
        int d = len/8; //The array is split in 9 pieces, 8 partitionings is therefore required
        int median1 = medianIndex3(array, low, low + d, low + 2*d);
        int median2 = medianIndex3(array, low + len/2 - d, low + len/2, low + len/2 + d);
        int median3 = medianIndex3(array, high - 2*d, high - d, high);
        return medianIndex3(array, median1, median2, median3); 
    }
    /**
     * Returns the index that belongs to the median of the three values
     * gotten from three indexes a, b and c. Inspired by:
     * https://algs4.cs.princeton.edu/23quicksort/QuickBentleyMcIlroy.java.html
     * @param a Index of an integer
     * @param b Index of an integer
     * @param c Index of an integer
     * @return Index of the median of three values in the array
     */
    private static int medianIndex3(int[] v, int a, int b, int c){
        return (v[a] < v[b]) ?
               ((v[b] < v[c]) ? b : (v[a] < v[c] ? c : a)) :
               ((v[c] < v[b]) ? b : (v[c] < v[a] ? c : a));
    }
    /**
     * Swaps two integers in the given array
     * @param array Array that contains the values
     * @param a Index of integer to be swapped
     * @param b Index of integer to be swapped
     */
    private static void swap(int[] array, int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

}