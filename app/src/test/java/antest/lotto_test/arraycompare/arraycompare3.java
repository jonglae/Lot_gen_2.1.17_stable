package antest.lotto_test.arraycompare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class arraycompare3 {


    public static void main(String[] string) {

        List<String> listA = Arrays.asList("1", "23", "3", "4", "5", "6", "7", "1", "23", "23");
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
    }

    @Override
    public int hashCode() {

        return super.hashCode();
    }
}
