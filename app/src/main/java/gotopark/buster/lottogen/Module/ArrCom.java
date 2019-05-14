package gotopark.buster.lottogen.Module;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrCom {

    public String comp(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        for (String temp : args) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }

        return printMap(map);
    }

    private static String printMap(Map<String, Integer> map) {

        StringBuilder outPutString2 = new StringBuilder();


        List<List<String>> secondStrings = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println("Element : " + entry.getKey() + " Count : " + entry.getValue());
//            outPutString1 = "맞은 번호는 : " + entry.getKey() + "입니다." + entry.getValue() + "\n";
            String outPutString1 = entry.getKey();

            if (entry.getValue() == 2) {
                outPutString2.append(outPutString1).append("  ");
                secondStrings.add(makeArray(entry.getKey(), entry.getValue()));

            }
        }
//        System.out.println(secondStrings.toString());

//        System.out.println(secondStrings.toString());

        return outPutString2.toString();
    }

    private static List<String> makeArray(String key, Integer value) {
        List<String> firstStrings = new ArrayList<>();
        for (int i = 0; i < value; i++) {
            firstStrings.add(key);
        }
        return firstStrings;
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
