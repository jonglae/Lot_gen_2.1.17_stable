package gotopark.buster.lottogen.Module

/**
 * Created by buster on 17. 10. 9.
 */

class MonyCalc {

    companion object {


        fun convertHangul(money: String): String {
            val han1=arrayOf("", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구")
            val han2=arrayOf("", "십", "백", "천")
            val han3=arrayOf("", "만", "억", "조", "경")

            //        String[] han3 = {"","만","억","조","경"};

            val result=StringBuilder()
            val len=money.length
            for (i in len - 1 downTo 0) {
                result.append(han1[Integer.parseInt(money.substring(len - i - 1, len - i))])
                if (Integer.parseInt(money.substring(len - i - 1, len - i)) > 0)
                    result.append(han2[i % 4])
                if (i % 4 == 0)
                    result.append(han3[i / 4])
            }

            return result.toString()
        }
    }


}