package gotopark.buster.lottogen.Module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class arraycompare2 {

    public String main(String[] args) {
        List<String> list = Arrays.asList(args);

        Map<String, Integer> map = new HashMap<>();
        for (String temp : list) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }
        return printMap(map);
    }

    private static String printMap(Map<String, Integer> map) {

        String outPutString1 = "";
        String outPutString2 = "";

        List<List<String>> secondStrings = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println("Element : " + entry.getKey() + " Count : " + entry.getValue());
            outPutString1 = "Element : " + entry.getKey() + " Count : " + entry.getValue() + "\n";
            outPutString2 = outPutString2 + outPutString1;

            secondStrings.add(makeArray(entry.getKey(), entry.getValue()));
        }
//        System.out.println(secondStrings.toString());
        System.out.println(outPutString2);

        return outPutString2;
    }

    private static List<String> makeArray(String key, Integer value) {
        List<String> firstStrings = new ArrayList<>();
        for (int i = 0; i < value; i++) {
            firstStrings.add(key);
        }
        return firstStrings;
    }

}
