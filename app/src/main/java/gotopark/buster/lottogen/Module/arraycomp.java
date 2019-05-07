package gotopark.buster.lottogen.Module;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class arraycomp {


    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<List<String>> comper(String[] string1, String[] string2) {


        String[] cccc  = concatenate(string1,string2);

        List<String> listA = Arrays.asList(cccc);

        System.out.println(" == "+listA);


        Map<String, Integer> countMap = new HashMap<>();
        listA.forEach(e -> {
            Integer count = countMap.get(e);
            countMap.put(e, count == null ? 1 : count + 1);
        });
        List<List<String>> resultList = new ArrayList<>();
        countMap.forEach((k, v) -> {
            List<String> list = new ArrayList<>();

            for (int i = 0; i < v; i++)
                list.add(k);

            System.out.println("결과값 == "+resultList);


            resultList.add(list);

        });
        return resultList;
    }

    @Override
    public int hashCode() {

        return super.hashCode();
    }

    private static <T> T[] concatenate(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }


    public static String comper2 (int[] string1)
    {

        StringBuilder text1= new StringBuilder();
        int[] my_array = string1;

        for (int i = 0; i < my_array.length-1; i++)
        {
            for (int j = i+1; j < my_array.length; j++)
            {
                if ((my_array[i] == my_array[j]) && (i != j))
                {
                    int aaa = my_array[j];
                    String bbb = String.valueOf(aaa);

//                    String ccc = bbb;

                    text1.append(" " + bbb);


                }


            }


        }


//        System.out.println("Duplicate Element : " + text1);

    return text1.toString();
    }

}
