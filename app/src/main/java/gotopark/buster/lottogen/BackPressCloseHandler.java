package gotopark.buster.lottogen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;
import hotchemi.android.rate.StoreType;

class BackPressCloseHandler {

    private Toast toast;

    @SuppressLint("StaticFieldLeak")
    private static MainActivity activity;

    BackPressCloseHandler(Activity context) {
        activity = (MainActivity) context;
    }

    static void onBackPressed(String iMsge) {

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage(iMsge)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

//                        activity.Show_front();
                        AnRate();

                        activity.finish();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                        AnRate();

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
}