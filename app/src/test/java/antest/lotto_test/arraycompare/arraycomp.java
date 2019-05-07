package antest.lotto_test.arraycompare;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class arraycomp {


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

    public static <T> T[] concatenate(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }
}
