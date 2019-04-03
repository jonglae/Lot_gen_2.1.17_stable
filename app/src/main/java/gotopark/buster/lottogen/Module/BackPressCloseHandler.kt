package gotopark.buster.lottogen.Module

/**
 * Created by buster on 4/29/17.
 */
import android.app.Activity
import android.widget.Toast

import gotopark.buster.lottogen.MainActivity

class BackPressCloseHandler(context: Activity) {

    private var backKeyPressedTime: Long = 0
    private val toast: Toast? = null

    private val activity: MainActivity

    init {
        this.activity = context as MainActivity
    }

    fun onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 3000) {
            backKeyPressedTime = System.currentTimeMillis()
            showGuide()
            return
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 3000) {
            activity.finish()
            toast!!.cancel()
        }
    }

    private fun showGuide() {
        Toast.makeText(activity, "뒤로 버튼을 두번누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        activity.finish()

    }
}
