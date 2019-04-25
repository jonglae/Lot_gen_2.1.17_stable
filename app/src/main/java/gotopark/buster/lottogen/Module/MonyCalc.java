package gotopark.buster.lottogen.Module;

/**
 * Created by buster on 17. 10. 9.
 */

public class MonyCalc {


    public static String convertHangul(String money) {
        String[] han1 = {"", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"};
        String[] han2 = {"", "십", "백", "천"};
        String[] han3 = {"", "만", "억", "조", "경"};

//        String[] han3 = {"","만","억","조","경"};

        StringBuilder result = new StringBuilder();
        int len = money.length();
        for (int i = len - 1; i >= 0; i--) {
            result.append(han1[Integer.parseInt(money.substring(len - i - 1, len - i))]);
            if (Integer.parseInt(money.substring(len - i - 1, len - i)) > 0)
                result.append(han2[i % 4]);
            if (i % 4 == 0)
                result.append(han3[i / 4]);
        }

        return result.toString();
    }
}