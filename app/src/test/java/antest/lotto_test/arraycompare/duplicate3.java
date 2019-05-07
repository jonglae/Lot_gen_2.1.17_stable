package antest.lotto_test.arraycompare;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class duplicate3 {

    public static void main(String[] args) {
        String[] news_data = "1,1,2,3,4,32,4,2,3,4,5,6,23,1,2,3,48,6,5".split(",");

        for (int i = 0; i < news_data.length; i++) {
            for (int j = i + 1; j < news_data.length; j++) {
                if (news_data[i].equals(news_data[j])) {
                    news_data = removeElement(news_data, j);
                }
            }
        }
        System.out.println(Arrays.toString(news_data) + "\n");

    }

        public static String[] removeElement (String[]original,int element){
            String[] n = new String[original.length - 1];
            System.arraycopy(original, 0, n, 0, element);
            System.arraycopy(original, element + 1, n, element, original.length - element - 1);
            return n;
        }
    }
