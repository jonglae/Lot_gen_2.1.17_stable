package gotopark.buster.lottogen

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import gun0912.ted.tedadmobdialog.TedAdmobDialog
import hotchemi.android.rate.AppRate
import hotchemi.android.rate.StoreType
import java.util.*

internal class BackProcessHandler(context: Activity) {

    private val toast: Toast?=null

    init {
        activity=context as MainActivity
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private lateinit var activity: MainActivity

        fun onBackPressed() {

            val builder: AlertDialog.Builder
            builder=AlertDialog.Builder(activity)
            builder.setTitle(R.string.app_name)
            builder.setIcon(R.mipmap.ic_launcher)
            builder.setMessage(activity.getString(R.string.scr_EXIT_Mesg1))
                    .setCancelable(true)
                    .setPositiveButton(activity.getString(R.string.scr_EXIT_Mesg2)) { dialog, id ->
                        TedAdmobDialog.Builder(activity, TedAdmobDialog.AdType.BANNER, activity.getString(R.string.banner_ad_unit_id))
                                .setAdListener(object : AdListener() {


                                    override fun onAdClicked() {
                                        super.onAdClicked()


                                    }


                                })
                                .showReviewButton(true)
                                .create()
                                .show()
                    }


                    .setNegativeButton(activity.getString(R.string.scr_EXIT_Mesg3)) { dialog, id ->
                        AnRate()
                        dialog.cancel()
                        activity.finish()
                    }
            val alert=builder.create()
            alert.show()
        }

        fun AnRate() {

            AppRate.with(activity)
                    .setStoreType(StoreType.GOOGLEPLAY) //default is Google, other option is Amazon
                    .setInstallDays(1) // default 10, 0 means install day.
                    .setLaunchTimes(10) // default 10 times.
                    .setRemindInterval(1) // default 1 day.
                    .setShowLaterButton(true) // default true.
                    .setDebug(false) // default false.
                    .setCancelable(false) // default false.
                    .setOnClickButtonListener { which -> // callback listener.
                        Log.d(MainActivity::class.java.name, Integer.toString(which))
                    }.monitor()

            AppRate.showRateDialogIfMeetsConditions(activity)
        }


        fun ClipsBoards() {

            val msg=Intent(Intent.ACTION_SEND)
            msg.addCategory(Intent.CATEGORY_DEFAULT)
            msg.putExtra(Intent.EXTRA_SUBJECT, "동행복권 로또 넘버")
            msg.putExtra(Intent.EXTRA_TEXT, activity.ctextR)
            msg.putExtra(Intent.EXTRA_TITLE, "#동행복권 로또 넘버")
            msg.type="text/plain"
            activity.startActivity(Intent.createChooser(msg, "Share"))
        }

        fun frontSay(): String {

            val rand=Random()
            val res=activity.resources
            val sayword=res.getStringArray(R.array.FrontItems)

            val wrdMax=sayword.size
            val i=rand.nextInt(wrdMax)

            Log.e("=========", Arrays.toString(sayword))

            return sayword[i]
        }
    }

}