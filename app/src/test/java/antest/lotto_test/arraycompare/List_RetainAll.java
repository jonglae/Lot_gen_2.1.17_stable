package antest.lotto_test.arraycompare;

import java.util.ArrayList;
import java.util.List;


public class List_RetainAll {
    public static void main(String[] args) {

        // 기준 리스트에 1,2,3,4 를 넣는다.
        final List<String> ori = new ArrayList<String>();
        ori.add("1");
        ori.add("2");
        ori.add("3");
        ori.add("12");
        ori.add("5");
        ori.add("6");

        /*
         * 기준리스트를 하나 임시로 복사한다.
         * retainAll를 사용시 기준과 비교 리스트에 중복된 값을 제외하고는 다 삭제 된다.
         */
        List<String> oriCOPY = new ArrayList<String>();
        oriCOPY.addAll(ori);

        // 기준리스트와 비교할 비교 리스트에 3,4,5 를 넣는다.
        List<String> target1 = new ArrayList<String>();
        target1.add("3");
        target1.add("4");
        target1.add("5");
        target1.add("1");
        target1.add("3");
        target1.add("21");
        target1.add("12");
        target1.add("5");

        // 기준과 비교를 중복환인한다.
        target1.retainAll(oriCOPY);

        // 중복 결과 출력
        for (String set : target1) {
            System.out.println(set);
        }

        System.out.println(" ------------------------------ ");

        // 새로운 비교 생성
        List<String> target2 = new ArrayList<String>();
        target2.add("1");

        // 기준과 비교를 중복환인한다.
        target2.retainAll(oriCOPY);

        // 중복 결과 출력
        for (String set : target2) {
            System.out.println(set);
        }
    }
}
