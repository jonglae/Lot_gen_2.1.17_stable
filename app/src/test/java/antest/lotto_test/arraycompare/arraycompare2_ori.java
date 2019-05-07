package antest.lotto_test.arraycompare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class arraycompare2_ori {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();


        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("b");
        list.add("c");
        list.add("a");
        list.add("a");
        list.add("a");

        Map<String, Integer> map = new HashMap<>();
        for (String temp : list) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }
        printMap(map);
    }

    private static void printMap(Map<String, Integer> map){
        List<List<String>> secondStrings = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Element : " + entry.getKey() + " Count : " + entry.getValue());
            secondStrings.add(makeArray(entry.getKey(), entry.getValue()));
        }
        System.out.println(secondStrings.toString());
    }

    private static List<String> makeArray(String key, Integer value){
        List<String> firstStrings = new ArrayList<>();
        for (int i = 0; i < value; i++) {
            firstStrings.add(key);
        }
        return firstStrings;
    }

}
