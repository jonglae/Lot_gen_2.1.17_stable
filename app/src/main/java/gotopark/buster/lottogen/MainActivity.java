package gotopark.buster.lottogen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.annotation.RequiresApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Objects;
import java.util.Random;

import gotopark.buster.lottogen.Module.numtoimg;
import gotopark.buster.lottogen.Module.randomNum;
import gotopark.buster.lottogen.database.DatabaseHelper;
import gotopark.buster.lottogen.qrCodeReader.FullScannerFragmentActivity;

public class MainActivity extends AppCompatActivity {


    public TextView text1;
    public TextView text10;

    public TextView Balltxt1;
    public TextView Balltxt2;
    public TextView Balltxt3;
    public TextView Balltxt4;
    public TextView Balltxt5;
    public TextView Balltxt6;

    public TextView Rtext1;
    public TextView Rtext2;
    public TextView Rtext3;
    public TextView Rtext4;
    public TextView Rtext5;
    public TextView Rtext6;
    public TextView Rtext7;
    public TextView Rtext8;

    public TextView Rtilte;
    public TextView Resultwin1;
    public TextView Resultwin2;
    public TextView Resultwin3;
    private InterstitialAd mInterstitialAd;

    private Switch sw1, sw2;

    public Model model;
    BackProcessHandler backHandler;
    DatabaseHelper db;


    public String ctextR, ctextRlist;

    int ClickCount = 0;

    int MultiClick;
    int randconut;

    int tak, tok;
    SoundPool soundpool;

    private static final int ZXING_CAMERA_PERMISSION = 1;

    @SuppressLint("StaticFieldLeak")
    public static Context CONTEXT;

    private Button.OnClickListener MDCT = new View.OnClickListener() {
        public void onClick(View v) {
            String url = getString(R.string.mdct_links);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    };

    private Button.OnClickListener Lot_share = new View.OnClickListener() {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View v) {
            soundpool.play(tak, 1, 1, 1, 0, 1);

            LotCOPY();
            ctextR = ctextR + "\n" + "추첨일 : " + Jsoup_Lotto.LotDate;
            ctextR = ctextR + "\n" + Jsoup_Lotto.SUM_lotto_num + "\n" + getString(R.string.twiter_CH);


            String comText = Balltxt1.getText().toString();

            if (Objects.equals("0", comText)) {

                text1.setText("우선 번호생성 부터 하세요!\n");
                text1.append(getString(R.string.app_Das));

            } else {
                BackProcessHandler.Companion.ClipsBoards();

            }
        }
    };


    Button.OnClickListener EXIT = new View.OnClickListener() {
        public void onClick(View v) {
            soundpool.play(tak, 1, 1, 1, 0, 1);

            onBackPressed();

//리스트 뷰 테스트
//            Intent intent = new Intent(MainActivity.this, ExternalDBActivity.class);
//            startActivity(intent);

        }
    };

    Button.OnClickListener copy = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @SuppressLint("SetTextI18n")
        public void onClick(View arg0) {
            soundpool.play(tak, 1, 1, 1, 0, 1);

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


        int count = 0;
        Random rand = new Random();
        int[] Times_Ran = {150, 250, 350, 550, 650, 850, 950, 1150, 1450, 1550};
        int primeWord = rand.nextInt(13) + 4;
        int millisec;

        public void onClick(View v) {
            soundpool.play(tak, 1, 1, 1, 0, 1);

//            MultiClick = Model.getClick();

            Log.d("====MultiClick====", String.valueOf(MultiClick));

            if (MultiClick == 1) {
                // 반복 회수 지정

                int xnum = rand.nextInt(9);
                millisec = Times_Ran[xnum];
            } else {


                millisec = 20;
            }

            // 반복 회수 끝

            CountDownTimer start = new CountDownTimer(millisec, 10) {

                @SuppressLint("SetTextI18n")
                public void onTick(long millisUntilFinished) {
                    soundpool.play(tak, 1, 1, 1, 0, 1);

                    text10.setText(" - 소수 분석중 - " + millisUntilFinished / 10 + "00ms 남았습니다.");
                    GenNumber();
                }

                @SuppressLint("SetTextI18n")
                public void onFinish() {

                    text10.setText(getString(R.string.scr_text2));
                    count = count + 1;

                    if (count == primeWord) {
                        String saywords = BackProcessHandler.Companion.frontSay();
                        text1.setText(saywords);
                        count = 0;
                        primeWord = rand.nextInt(11) + 3;

                    }
                    ClickCount = 0;
                }

            }.start();
        }
    };

    public void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION);
        } else {
            soundpool.play(tok, 1, 1, 1, 0, 1);
            Show_front();
            Intent intent = new Intent(this, clss);
            startActivity(intent);
        }
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

        ctextRlist = ctext1 + "," + ctext2 + "," + ctext3 + "," + ctext4 + "," + ctext5 + "," + ctext6;
        ctextR = App_Share + ctextRlist + "\n\n" + App_links1;
    }
    // MDCT  클릭시 브라우저 실행 url  이동하여 보여준다.
    @SuppressLint("CutPasteId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CONTEXT = this;
        model = new Model();
        db = new DatabaseHelper(this);
        backHandler = new BackProcessHandler(this);

        soundpool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        tak = soundpool.load(this, R.raw.short_click2, 1);
        tok = soundpool.load(this, R.raw.click1_rebert1, 1);

        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        FloatingActionButton fab = findViewById(R.id.fab);

        // wifi 또는 모바일 네트워크 어느 하나라도 연결이 되어있다면,
        if (wifi.isConnected() || mobile.isConnected()) {
            Admob_is();
            Admob_Front();
            BackProcessHandler.Companion.AnRate();
        } else {
            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            {
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

        text1 = findViewById(R.id.text1);
        text10 = findViewById(R.id.text10);

        Balltxt1 = findViewById(R.id.balltext1);
        Balltxt2 = findViewById(R.id.balltext2);
        Balltxt3 = findViewById(R.id.balltext3);
        Balltxt4 = findViewById(R.id.balltext4);
        Balltxt5 = findViewById(R.id.balltext5);
        Balltxt6 = findViewById(R.id.balltext6);

        Balltxt1.setBackgroundResource(R.drawable.ball1);
        Balltxt2.setBackgroundResource(R.drawable.ball2);
        Balltxt3.setBackgroundResource(R.drawable.ball3);
        Balltxt4.setBackgroundResource(R.drawable.ball4);
        Balltxt5.setBackgroundResource(R.drawable.ball5);
        Balltxt6.setBackgroundResource(R.drawable.ball6);

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
        Button btn5 = findViewById(R.id.button5);
        Button btn6 = findViewById(R.id.button6);
        Button btn7 = findViewById(R.id.button7);

        Rtilte = findViewById(R.id.rtitle);
        Resultwin1 = findViewById(R.id.winText1);
        Resultwin2 = findViewById(R.id.winText2);
        Resultwin3 = findViewById(R.id.winText3);

        sw1 = findViewById(R.id.switch1);
        sw2 = findViewById(R.id.switch2);

        String versionCode = BuildConfig.VERSION_NAME;
        String App_Mame = getString(R.string.app_name);
        text1.setText(getString(R.string.info_Mess));
        text1.append("\n" + getString((R.string.Main_text)));
        //엡 이름과 버젼 표시
        setTitle(App_Mame + " V" + versionCode);

        sw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundpool.play(tak, 1, 1, 1, 0, 1);
                if (sw2.isChecked()) {
                    MultiClick = 1;
                } else {
                    MultiClick = 0;
                }
            }
        });

        btn1.setOnClickListener(EXIT);
        btn2.setOnClickListener(gen);
        btn3.setOnClickListener(copy);
        btn4.setOnClickListener(Lot_share);
        btn5.setOnClickListener(Lot_save);
        btn6.setOnClickListener(Lot_list);
        btn7.setOnClickListener(Num_Choice);
        text10.setOnClickListener(MDCT);

        sw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundpool.play(tak, 1, 1, 1, 0, 1);
                if (sw1.isChecked()) {
                    randconut = 1;
                } else {
                    randconut = 0;
                }
            }
        });
    }

    private Button.OnClickListener Lot_save = new View.OnClickListener() {
        @SuppressLint("SetTextI18n")
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View v) {
            soundpool.play(tok, 1, 1, 1, 0, 1);
            if (ClickCount == 0) {
                LotCOPY();
                db.insertColumn(ctextRlist, "", "자동번호");
                ClickCount = 1;
                String Mesg1 = "번호 저장 완료";
                //The number has been saved.
                text10.setText(ctextRlist + " -> " + Mesg1);
                Toast.makeText(MainActivity.this, Mesg1, Toast.LENGTH_SHORT).show();
            } else {
                text10.setText("번호 생성 부터 해주세요");
            }
        }
    };

    private Button.OnClickListener Lot_list = new View.OnClickListener() {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View v) {
            soundpool.play(tok, 1, 1, 1, 0, 1);

            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);

        }
    };

    public Button.OnClickListener Num_Choice = new View.OnClickListener() {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View v) {
            soundpool.play(tok, 1, 1, 1, 0, 1);
            Intent intent = new Intent(MainActivity.this, Main3Activity.class);
            startActivity(intent);
        }
    };

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

    //버젼 ver : 1.0
    public void Show_front() {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    @Override
    public void onBackPressed() {
        BackProcessHandler.Companion.onBackPressed();
    }


    @SuppressLint({"SetTextI18n", "ResourceType", "Assert"})
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
        Random random = new Random();
        /** 로또 번호 발생 메소드 */


        if (randconut == 1) {
            assert false;
            res[0] = random.nextInt(7 - 1 + 1) + 1;
            res[1] = random.nextInt(16 - 8 + 1) + 8;
            res[2] = random.nextInt(24 - 17 + 1) + 17;
            res[3] = random.nextInt(33 - 25 + 1) + 25;
            res[4] = random.nextInt(41 - 34 + 1) + 34;
            res[5] = random.nextInt(45 - 42 + 1) + 42;

        } else {
            res = Num.lotArray(6, 45);
        }

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

        Balltxt1.setBackgroundResource(dball1);
        Balltxt2.setBackgroundResource(dball2);
        Balltxt3.setBackgroundResource(dball3);
        Balltxt4.setBackgroundResource(dball4);
        Balltxt5.setBackgroundResource(dball5);
        Balltxt6.setBackgroundResource(dball6);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Jsoup_Lotto jsoup_lotto = new Jsoup_Lotto(this );
        jsoup_lotto.execute();
    }
}