package gotopark.buster.lottogen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;

import java.util.Arrays;
import java.util.Random;

import gun0912.ted.tedadmobdialog.TedAdmobDialog;
import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;
import hotchemi.android.rate.StoreType;

class BackProcessHandler {

    private Toast toast;

    @SuppressLint("StaticFieldLeak")
    private static MainActivity activity;

    BackProcessHandler(Activity context) {
        activity = (MainActivity) context;
    }

    static void onBackPressed() {

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage(activity.getString(R.string.scr_EXIT_Mesg1))
                .setCancelable(true)
                .setPositiveButton(activity.getString(R.string.scr_EXIT_Mesg2), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        new TedAdmobDialog.Builder(activity, TedAdmobDialog.AdType.BANNER, activity.getString(R.string.banner_ad_unit_id))
                                .setAdListener(new AdListener() {




                                    @Override
                                    public void onAdClicked() {
                                        super.onAdClicked();


                                    }





                                })
                                .showReviewButton(true)
                                .create()
                                .show();

                    }


                })


                .setNegativeButton(activity.getString(R.string.scr_EXIT_Mesg3), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        AnRate();
                        dialog.cancel();
                        activity.finish();

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    static void AnRate() {

        AppRate.with(activity)
                .setStoreType(StoreType.GOOGLEPLAY) //default is Google, other option is Amazon
                .setInstallDays(1) // default 10, 0 means install day.
                .setLaunchTimes(10) // default 10 times.
                .setRemindInterval(2) // default 1 day.
                .setShowLaterButton(true) // default true.
                .setDebug(false) // default false.
                .setCancelable(false) // default false.
                .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
                    @Override
                    public void onClickButton(int which) {
                        Log.d(MainActivity.class.getName(), Integer.toString(which));
                    }
                }).monitor();

        AppRate.showRateDialogIfMeetsConditions(activity);
    }


    static void ClipsBoards(){

        Intent msg = new Intent(Intent.ACTION_SEND);
        msg.addCategory(Intent.CATEGORY_DEFAULT);
        msg.putExtra(Intent.EXTRA_SUBJECT, "동행복권 로또 넘버");
        msg.putExtra(Intent.EXTRA_TEXT, activity.ctextR);
        msg.putExtra(Intent.EXTRA_TITLE, "#동행복권 로또 넘버");
        msg.setType("text/plain");
        activity.startActivity(Intent.createChooser(msg, "Share"));
    }

    static String frontSay() {

        Random rand = new Random();
        Resources res = activity.getResources();
        String[] sayword = res.getStringArray(R.array.FrontItems);

        int wrdMax = sayword.length;
        int i = rand.nextInt(wrdMax);

        Log.e("=========", Arrays.toString(sayword));

        return sayword[i];
    }

}