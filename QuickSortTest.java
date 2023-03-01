import org.junit.Before;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
/**
 * Simple tests to check 
 */
public class QuickSortTest{

    private QuickSort quickSort;
    private int[] smallArray;
    private int[] sortedArray;
    private int[] unsortedArray;
    private int[] annoyingArray;

    @Before
    public void setup(){
        smallArray = new int[]{2, 3, 7, 9, 8, 10, 12, 11};
        sortedArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 ,17};
        unsortedArray = new int[]{9, 3, 6, 5, 7, 2, 2, 2, 2, 9, 200, 20, 10, 340, 891, 63, 7123, 12309, 2};
        annoyingArray = new int[500];
        Arrays.fill(annoyingArray, 1);
        quickSort = new QuickSort();
    }

    @Test
    public void insertionSortWorksCorrectly(){
        //Arrange
        int[] expectedArray = Arrays.copyOf(smallArray, smallArray.length);
        Arrays.sort(expectedArray);
         //Act
        quickSort.sort(smallArray);
        //Assert
        for(int i = 0; i < expectedArray.length; i++){
            assertThat(expectedArray[i], equalTo(smallArray[i]));
        }
    }

    @Test
    public void sortDoesNotChangeSizeOfArray(){
        //Arrange
        int sortedSize = sortedArray.length;
        int unsortedSize = unsortedArray.length;
        int smallSize = smallArray.length;

        //Act
        quickSort.sort(sortedArray);
        quickSort.sort(unsortedArray);
        quickSort.sort(smallArray);

        //Assert
        assertThat(sortedSize, is(sortedArray.length));
        assertThat(unsortedSize, is(unsortedArray.length));
        assertThat(smallSize, is(smallArray.length));
    }

    @Test
    public void quicksortGivesSortedArray(){
        //Arrange
        int[] expectedSorted = Arrays.copyOf(sortedArray, sortedArray.length);
        int[] expectedUnsorted = Arrays.copyOf(unsortedArray, unsortedArray.length);
        Arrays.sort(expectedSorted);
        Arrays.sort(expectedUnsorted);

        //Act
        quickSort.sort(sortedArray);
        quickSort.sort(unsortedArray);

        //Assert
        for(int i = 0; i < expectedSorted.length; i++){
            assertThat(expectedSorted[i], equalTo(sortedArray[i]));
        }
        for(int i = 0; i < expectedUnsorted.length; i++){
            assertThat(expectedUnsorted[i], equalTo(unsortedArray[i]));
        }
    }

    @Test
    public void arrayWithSameElementsIsSame(){
        //Arrange
        int[] expectedArray = Arrays.copyOf(annoyingArray, annoyingArray.length);
        Arrays.sort(expectedArray);
        //Act
        quickSort.sort(annoyingArray);
        //Assert
        assertThat(expectedArray, equalTo(annoyingArray));
    }
}