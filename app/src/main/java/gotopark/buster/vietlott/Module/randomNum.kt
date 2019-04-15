package gotopark.buster.vietlott.Module

import java.util.*

/**
 * Created by buster on 17. 9. 14.
 * 남아프리리카 로또 방법
 * 1.Lotto : 6 numbers from 1 to 52
 * 2.Lotto Plus : choose 6 numbers from 1 to 52
 * 3.Lotto Plus 2 : 6 numbers from 1 to 52
 * 4.Powerball :  5 numbers from 1 to 45 on and 1 extra number from 1 to 20
 * 5.Powerball Plus :  choose 5 numbers from 1 to 45 on and 1 extra number from 1 to 20
 */

class randomNum {

    fun lotArray(input1: Int, input2: Int): IntArray {

        val num = IntArray(input1)
        var xnum: Int
        var icount: Int
        var j: Int
        val rand = Random()
        //         로또 번호 발생 및  중복 제거

        icount = 0
        while (icount < input1) {
            xnum = rand.nextInt(input2) + 1
            num[icount] = xnum
            j = 0
            while (j < icount) {
                if (num[icount] == num[j]) {
                    xnum = rand.nextInt(input2) + 1
                    num[icount] = xnum
                    icount = icount - 1
                    break
                }
                j++
            }
            icount++
        }
        //   오름 차순으로  정렬 및 딜레이 시간
        Arrays.sort(num)
        return num

    }

}