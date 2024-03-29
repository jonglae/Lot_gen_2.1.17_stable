package gotopark.buster.vietlott;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Objects;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import gotopark.buster.vietlott.Module.MonyCalc;
import gotopark.buster.vietlott.Module.numtoimg;
import gotopark.buster.vietlott.Module.numtoimg2;
import gotopark.buster.vietlott.Module.randomNum;
import gotopark.buster.vietlott.qrCodeReader.FullScannerFragmentActivity;

public class MainActivity extends AppCompatActivity {


    private TextView text1;

    private TextView Balltxt1;
    private TextView Balltxt2;
    private TextView Balltxt3;
    private TextView Balltxt4;
    private TextView Balltxt5;
    private TextView Balltxt6;

    private TextView Rtext1;
    private TextView Rtext2;
    private TextView Rtext3;
    private TextView Rtext4;
    private TextView Rtext5;
    private TextView Rtext6;
    private TextView Rtext7;
    private TextView Rtext8;

    private ImageView Ballimg1;
    private ImageView Ballimg2;
    private ImageView Ballimg3;
    private ImageView Ballimg4;
    private ImageView Ballimg5;
    private ImageView Ballimg6;

    private ImageView RBall1;
    private ImageView RBall2;
    private ImageView RBall3;
    private ImageView RBall4;
    private ImageView RBall5;
    private ImageView RBall6;
    private ImageView RBall7;
    private ImageView RBall8;

    private TextView Rtilte;
    private TextView Resultwin1;
    private TextView Resultwin2;
    private TextView Resultwin3;
    private TextView Resultwin4;
    private TextView Resultwin5;
    private InterstitialAd mInterstitialAd;
    BackPressCloseHandler backHandler;


    private String ctextR;
    String SUM_lotto_num;
    String LotDate;

    private Class<?> mClss;
    private static final int ZXING_CAMERA_PERMISSION = 1;

    private Button.OnClickListener MDCT = new View.OnClickListener() {
        public void onClick(View v) {
            String url = getString(R.string.mdct_links);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    };

//    String[] l_number = new String[8];

    private Button.OnClickListener Lot_share = new View.OnClickListener() {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View v) {

            LotCOPY();


            String comText = Balltxt1.getText().toString();

            if (Objects.equals("0", comText)) {

                text1.setText("우선 번호생성 부터 하세요!\n");
                text1.append(getString(R.string.app_Das));

            } else {
                Intent msg = new Intent(Intent.ACTION_SEND);
                msg.addCategory(Intent.CATEGORY_DEFAULT);
                msg.putExtra(Intent.EXTRA_SUBJECT, "동행복권 로또 넘버");
                msg.putExtra(Intent.EXTRA_TEXT, ctextR);
                msg.putExtra(Intent.EXTRA_TITLE, "동행복권 로또 넘버");
                msg.setType("text/plain");
                startActivity(Intent.createChooser(msg, "Share"));

                Show_front();
            }
        }
    };


    Button.OnClickListener EXIT = new View.OnClickListener() {
        public void onClick(View v) {
            onBackPressed();


        }
    };

    Button.OnClickListener copy = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @SuppressLint("SetTextI18n")
        public void onClick(View arg0) {
            LotCOPY();
            String comText = getString(R.string.Main_text);

            if (Objects.equals(ctextR, comText)) {

                text1.setText("우선 번호생성 부터 하세요 !\n");
                text1.append(getString(R.string.app_Das));


            } else {

                ClipData clip = ClipData.newPlainText("Copied", ctextR);
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                assert clipboard != null;
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity.this, "행운의 로또번호가 복사됐습니다.", Toast.LENGTH_SHORT).show();
                text1.setText("!!! Lotto Number Copied !!!");
            }
        }
    };

    Button.OnClickListener gen = new View.OnClickListener() {


        public void onClick(View v) {

            Random rand = new Random();
            // 반복 회수 지정
            int Times_Ran[] = {150, 250, 350, 550, 650, 850, 950, 1150, 1450, 1550};
            int xnum = rand.nextInt(9);
            int millisec = Times_Ran[xnum];
            // 반복 회수 끝

            CountDownTimer start = new CountDownTimer(millisec, 50) {

                @SuppressLint("SetTextI18n")
                public void onTick(long millisUntilFinished) {

//                    text2.setTextSize (TypedValue.COMPLEX_UNIT_SP, 24);
                    text1.setText(" - 소수 분석중 - " + millisUntilFinished / 50 + "0ms 남았습니다.");
                    GenNumber();


                }

                @SuppressLint("SetTextI18n")
                public void onFinish() {
                    String Scr_text2 = getString(R.string.scr_text2);
                    String Scr_text1 = getString(R.string.scr_text1);

                    text1.setText(Scr_text2);
                    text1.append("\n" + Scr_text1);

                }

            }.start();
        }
    };

    public void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            mClss = clss;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(this, clss);
            startActivity(intent);

        }

        Show_front();

    }

    public void launchFullFragmentActivity(View v) {
        launchActivity(FullScannerFragmentActivity.class);

    }


    private void LotCOPY() {
        String App_links1 = getString(R.string.app_Google_Play_links);
        String App_Share = getString(R.string.app_share);

        //텍스트 변환
        String ctext1;
        String ctext2;
        String ctext3;
        String ctext4;
        String ctext5;
        String ctext6;

        ctext1 = Balltxt1.getText().toString();
        ctext2 = Balltxt2.getText().toString();
        ctext3 = Balltxt3.getText().toString();
        ctext4 = Balltxt4.getText().toString();
        ctext5 = Balltxt5.getText().toString();
        ctext6 = Balltxt6.getText().toString();


//        ctextR = App_Share + "\n" + ctext1 + ", " + ctext2 + ", " + ctext3 + ", " + ctext4 + ", " + ctext5 + ", " + ctext6 + "\n\n" + App_links1 + "\n1등 ^^당첨을 기원합니다.~♡";
        ctextR = App_Share + ctext1 + ", " + ctext2 + ", " + ctext3 + ", " + ctext4 + ", " + ctext5 + ", " + ctext6;

        ctextR = ctextR + "\n" + "추첨일 : " + LotDate;

        ctextR = ctextR + "\n" + SUM_lotto_num;

    }


    // MDCT  클릭시 브라우저 실행 url  이동하여 보여준다.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        FloatingActionButton fab = findViewById(R.id.fab);

        // wifi 또는 모바일 네트워크 어느 하나라도 연결이 되어있다면,
        if (wifi.isConnected() || mobile.isConnected()) {
            setContentView(R.layout.activity_main);
            backHandler = new BackPressCloseHandler(this);

            new LotonumCall().execute();
            Admob_is();
            Admob_Front();
            BackPressCloseHandler.AnRate();


        } else {

            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            {
                setContentView(R.layout.activity_main);
                ad.setTitle(getString(R.string.info_net1));
                ad.setMessage(getString(R.string.info_net2));
                ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        dialogInterface.dismiss();
                    }
                });
                ad.show();

            }
        }
        //========================================================================
        View textView1 = findViewById(R.id.textball_layout);
        View textView2 = findViewById(R.id.layout_resultwin);
        View ImageView = findViewById(R.id.lottoballs);

        //========================================================================

        text1 = findViewById(R.id.text1);
        TextView text10 = findViewById(R.id.textView10);

        Balltxt1 = findViewById(R.id.balltext1);
        Balltxt2 = findViewById(R.id.balltext2);
        Balltxt3 = findViewById(R.id.balltext3);
        Balltxt4 = findViewById(R.id.balltext4);
        Balltxt5 = findViewById(R.id.balltext5);
        Balltxt6 = findViewById(R.id.balltext6);


        Rtext1 = findViewById(R.id.Rtext1);
        Rtext2 = findViewById(R.id.Rtext2);
        Rtext3 = findViewById(R.id.Rtext3);
        Rtext4 = findViewById(R.id.Rtext4);
        Rtext5 = findViewById(R.id.Rtext5);
        Rtext6 = findViewById(R.id.Rtext6);
        Rtext7 = findViewById(R.id.Rtext7);
        Rtext8 = findViewById(R.id.Rtext8);


        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);


        Ballimg1 = findViewById(R.id.ballimage1);
        Ballimg2 = findViewById(R.id.ballimage2);
        Ballimg3 = findViewById(R.id.ballimage3);
        Ballimg4 = findViewById(R.id.ballimage4);
        Ballimg5 = findViewById(R.id.ballimage5);
        Ballimg6 = findViewById(R.id.ballimage6);


        //========================================================================

        RBall1 = findViewById(R.id.RBall1);
        RBall2 = findViewById(R.id.RBall2);
        RBall3 = findViewById(R.id.RBall3);
        RBall4 = findViewById(R.id.RBall4);
        RBall5 = findViewById(R.id.RBall5);
        RBall6 = findViewById(R.id.RBall6);
        RBall7 = findViewById(R.id.RBall7);
        RBall8 = findViewById(R.id.RBall8);

        //========================================================================

        Rtilte = findViewById(R.id.Rtitle);
        Resultwin1 = findViewById(R.id.winText1);
        Resultwin2 = findViewById(R.id.winText2);
        Resultwin3 = findViewById(R.id.winText3);
        Resultwin4 = findViewById(R.id.winText4);
        Resultwin5 = findViewById(R.id.winText5);
        /*
         *
         * 시작시 메세지 출력
         *
         * */
        String versionCode = BuildConfig.VERSION_NAME;
        String App_Mame = getString(R.string.app_name);
        text1.setText(getString(R.string.info_Mess));
        text1.append("\n" + getString((R.string.Main_text)));
        //엡 이름과 버젼 표시
        setTitle(App_Mame + " V" + versionCode);

        btn1.setOnClickListener(EXIT);
        btn2.setOnClickListener(gen);
        btn3.setOnClickListener(copy);
        btn4.setOnClickListener(Lot_share);
        text10.setOnClickListener(MDCT);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }



    public void Admob_is() {

        AdView mAdView = findViewById(R.id.adView);
        MobileAds.initialize(getApplicationContext(), getString(R.string.google_banner_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    public void Admob_Front() {
        Log.e("전면_Front_TEST =====> ", "=======================> OK");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);


    }

    public void Show_front() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }


    }



    @Override
    public void onBackPressed() {

        String iMesg = getString(R.string.scr_EXIT_Mesg1);
        BackPressCloseHandler.onBackPressed(iMesg);
    }


    @SuppressLint({"SetTextI18n", "ResourceType"})
    public void GenNumber() {
        int[] res = new int[6];

        int dball1;
        int dball2;
        int dball3;
        int dball4;
        int dball5;
        int dball6;

        randomNum Num = new randomNum();
        numtoimg NumtoI = new numtoimg();

        /** 로또 번호 발생 메소드 */
        res = Num.lotArray(6, 45);
//        text2.setTextSize (TypedValue.COMPLEX_UNIT_SP, 30);

        dball1 = NumtoI.Numimg(res[0]);
        dball2 = NumtoI.Numimg(res[1]);
        dball3 = NumtoI.Numimg(res[2]);
        dball4 = NumtoI.Numimg(res[3]);
        dball5 = NumtoI.Numimg(res[4]);
        dball6 = NumtoI.Numimg(res[5]);

        Balltxt1.setText(String.valueOf(res[0]));
        Balltxt2.setText(String.valueOf(res[1]));
        Balltxt3.setText(String.valueOf(res[2]));
        Balltxt4.setText(String.valueOf(res[3]));
        Balltxt5.setText(String.valueOf(res[4]));
        Balltxt6.setText(String.valueOf(res[5]));

        Ballimg1.setImageResource(dball1);
        Ballimg2.setImageResource(dball2);
        Ballimg3.setImageResource(dball3);
        Ballimg4.setImageResource(dball4);
        Ballimg5.setImageResource(dball5);
        Ballimg6.setImageResource(dball6);

    }


    @SuppressLint("StaticFieldLeak")
    public class LotonumCall extends AsyncTask<Void, Void, Void> {


        Elements F10;
        Elements F11;
        Elements F12;
        Elements F13;
        Elements F14;
        Elements F15;
        String tiTle;

        @Override
        public Void doInBackground(Void... params) {

//https 설정 ssl hanshare fail 적용 위한 code -->start
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @SuppressLint("TrustAllX509TrustManager")
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = null;
            try {
                sc = SSLContext.getInstance("TLS");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            try {
                assert sc != null;
                sc.init(null, trustAllCerts, new SecureRandom());
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//https 설정 ssl hanshare fail 적용 위한 code --> End

            String url = getString(R.string.JsoupOne);

            try {
                // Connect to the web site
                Document document = Jsoup.connect(url)
                        .timeout(10000)
                        .get();

                if (document != null) {

                    // Get the html document title
                    tiTle = document.title();
                    F10 = document.select(getString(R.string.jsoup_q1));
                    F11 = document.select(getString(R.string.jsoup_q2));
                    F12 = document.select(getString(R.string.jsoup_q3));
                    F13 = document.select(getString(R.string.jsoup_q4));
                    F14 = document.select(getString(R.string.jsoup_q5));
                    F15 = document.select(getString(R.string.jsoup_q6));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onPostExecute(Void result) {


            String[] lotto_num = new String[7];
            String LotCount;
//            String LotDate;
            String LotWin;
            String prize_data;
            if (tiTle != null) {

                String KoLotto = "";
                for (Element e : F12) {
                    String alt = e.attr("alt");

                    KoLotto += ", " + alt;
                }
                KoLotto = KoLotto.replaceAll(" ", "");

                KoLotto = KoLotto.substring(1);
                lotto_num = KoLotto.split(",");
                SUM_lotto_num = lotto_num[0] + ", " +
                        lotto_num[1] + ", " +
                        lotto_num[2] + ", " +
                        lotto_num[3] + ", " +
                        lotto_num[4] + ", " +
                        lotto_num[5] + "\n보너스 번호 :" +
                        lotto_num[6];

                LotCount = F10.toString().replaceAll("\\<.*?>", "");
                LotDate = F11.toString().replaceAll("\\<.*?>", "");

                // 로또 당첨 등수
                LotWin = F13.toString().replaceAll("\\<.*?>", "");
                LotWin = LotWin.replaceAll("  ", "");
                LotWin = LotWin.replaceAll("\n", "");
                LotWin = LotWin.replaceAll("", "");
                LotWin = LotWin.replaceAll("  ", "\n");
                LotWin = LotWin.replaceAll("당첨정보 상세보기", "");
                LotWin = LotWin.replaceAll("₩", " = (₩)");


                Log.e("================>", String.valueOf(F14));
                // 다음회차 정보
                String Linfo = F14.toString().replaceAll("\\<.*?>", "");
                Linfo = Linfo.replaceAll("\n", "");
                Log.e("1Linfo================>", String.valueOf(Linfo));

                Linfo = Linfo.replaceAll("   ", "\n");
                Log.e("2Linfo===============>", String.valueOf(Linfo));

                Linfo = Linfo.replaceAll("  ", "");
                Log.e("3Linfo===============>", String.valueOf(Linfo));
                prize_data = F15.toString().replaceAll("\\<.*?>", "");
                prize_data = prize_data.replaceAll("₩", "");
                prize_data = prize_data.replaceAll(",", "");

//                prize_data = String.valueOf(F15);


                String Cpriz_data = MonyCalc.convertHangul(prize_data);
                Log.e("3Linfo===============>", Cpriz_data);

                int[] res = new int[7];

                int dball1;
                int dball2;
                int dball3;
                int dball4;
                int dball5;
                int dball6;
                int dball8;

                numtoimg2 NumtoI2 = new numtoimg2();


                res[0] = Integer.parseInt(lotto_num[0]);
                res[1] = Integer.parseInt(lotto_num[1]);
                res[2] = Integer.parseInt(lotto_num[2]);
                res[3] = Integer.parseInt(lotto_num[3]);
                res[4] = Integer.parseInt(lotto_num[4]);
                res[5] = Integer.parseInt(lotto_num[5]);
                res[6] = Integer.parseInt(lotto_num[6]);


                dball1 = NumtoI2.Numimg2(res[0]);
                dball2 = NumtoI2.Numimg2(res[1]);
                dball3 = NumtoI2.Numimg2(res[2]);
                dball4 = NumtoI2.Numimg2(res[3]);
                dball5 = NumtoI2.Numimg2(res[4]);
                dball6 = NumtoI2.Numimg2(res[5]);
                dball8 = NumtoI2.Numimg2(res[6]);


                Rtilte.setText(LotCount + "추첨일:" + LotDate);
                Resultwin1.setText(LotWin);
                Resultwin2.setText("\n" + Linfo);
                Resultwin2.append("(" + Cpriz_data + ")");


                RBall1.setImageResource(dball1);
                RBall2.setImageResource(dball2);
                RBall3.setImageResource(dball3);
                RBall4.setImageResource(dball4);
                RBall5.setImageResource(dball5);
                RBall6.setImageResource(dball6);
                RBall8.setImageResource(dball8);


                Rtext1.setText(String.valueOf(lotto_num[0]));
                Rtext2.setText(String.valueOf(lotto_num[1]));
                Rtext3.setText(String.valueOf(lotto_num[2]));
                Rtext4.setText(String.valueOf(lotto_num[3]));
                Rtext5.setText(String.valueOf(lotto_num[4]));
                Rtext6.setText(String.valueOf(lotto_num[5]));
                Rtext7.setText("+");
                Rtext8.setText(String.valueOf(lotto_num[6]));


            } else {

                /** 네트웍 품질 문제 발생시 메세지 출력 */


                String iInfo1 = "Network Error!";
                String iInfo2 = "The network connection is poor.";
                String iInfo3 = "Try again later!!!";
                String iInfo4 = "If you To check your Lottery Number";
                String iInfo5 = "Connect to the Internet";

                Rtilte.setText(iInfo1);
                Resultwin1.setText(iInfo2);
                Resultwin2.setText(iInfo3);
                Resultwin3.setText(iInfo4);
                Resultwin3.setText(iInfo5);

            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}