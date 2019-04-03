package gotopark.buster.lottogen.Module

import gotopark.buster.lottogen.R

/**
 * Created by buster on 18. 3. 26.
 */

class numtoimg2 {

    fun Numimg2(input1: Int): Int {

        val Imgnum = intArrayOf(
                R.drawable.ball1,
                R.drawable.ball2,
                R.drawable.ball3,
                R.drawable.ball4,
                R.drawable.ball5)

        return if (input1 in 1..10) {
            Imgnum[0]
        } else if (input1 in 11..20) {
            Imgnum[1]
        } else if (input1 in 21..30) {
            Imgnum[2]
        } else if (input1 in 31..40) {
            Imgnum[3]
        } else if (input1 in 41..45) {
            Imgnum[4]
        } else {
            Imgnum[4]
        }

    }

}