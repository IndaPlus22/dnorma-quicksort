public class InsertionSort implements IntSorter{
    public void sort(int[] v){
        int n = v.length;
        for(int i = 1; i < n; i++){
            int j = i;
            while(j > 0 && v[j-1] > v[j]){
                int temp = v[j-1];
                v[j-1] = v[j];
                v[j] = temp;
                j--;
            }
        }
    }
}