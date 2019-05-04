package antest.lotto_test.arraycompare;

import java.io.IOException;
import java.util.Arrays;

public class arraycompare {

    public static void main(String[] args) throws IOException {


        int[] input1 = new int[]{1, 2, 3, 4, 5, 6};
        int[] array1 = new int[]{1, 8, 15, 22, 29, 36, 43};
        int[] array2 = new int[]{1, 8, 15, 22, 29, 36, 43};

        System.out.println("중첩 배열 항목값 비교: " + Arrays.deepEquals(new int[][]{input1}, new int[][]{array1}));
        System.out.println("==value==: " + input1[1]);

        System.out.println("중첩 배열 항목값 비교: " + Arrays.deepEquals(new int[][]{array2}, new int[][]{array1}));


    }

}