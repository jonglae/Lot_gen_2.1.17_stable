package antest.lotto_test.arraycompare;

import java.util.Arrays;

import antest.lotto_test.bbbb;



public class duplicate {

    public static void main(String[] args)
    {
//        String[] bbbb =new String[100];
        String text1="";
        int[] my_array = {1, 2, 5, 5, 6, 6, 7, 2};

        for (int i = 0; i < my_array.length-1; i++)
        {
            for (int j = i+1; j < my_array.length; j++)
            {
                if ((my_array[i] == my_array[j]) && (i != j))
                {
                    int aaa = my_array[j];
                    String bbb = String.valueOf(aaa);

//                    String ccc = bbb;

                      text1 = text1 + bbb;


                }


            }


        }


        System.out.println("Duplicate Element : " + text1);


    }

}

