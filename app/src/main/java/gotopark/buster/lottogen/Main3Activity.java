package gotopark.buster.lottogen;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Objects;

import gotopark.buster.lottogen.SelectNum.GridItemView;
import gotopark.buster.lottogen.SelectNum.GridViewAdapter;
import gotopark.buster.lottogen.database.DatabaseHelper;
import gotopark.buster.lottogen.listview.Card;
import gotopark.buster.lottogen.listview.OneCardAdapter;

import static gotopark.buster.lottogen.R.array;
import static gotopark.buster.lottogen.R.id;
import static gotopark.buster.lottogen.R.layout;



/**
 * Created by User on 4/15/2017.
 */

public class Main3Activity extends AppCompatActivity {

    private ArrayList<String> selectedStrings;
    private DatabaseHelper db;
    private static final String TAG = "Main3Activity";

    public OneCardAdapter cardArrayAdapter;
    public ListView listView;
    int LottoCount = 0;
    GridViewAdapter adapter;
    int selectedIndex;
    int Click_true = 1;

    String pbnum;
    String old_pbnum="";
    int MaxBall = 6;
    int tak, tok;
    SoundPool soundpool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        Resources sevenball = getResources();
        String[] numbers = sevenball.getStringArray(array.Lottoball) ;

        db = new DatabaseHelper(this);

        GridView gridView = (GridView) findViewById(id.grid);

        View btnGo = findViewById(id.button);
        View btnClear = findViewById(id.button2);

        selectedStrings = new ArrayList<>();
        listView = (ListView) findViewById(id.card_listView);
        cardArrayAdapter = new OneCardAdapter(getApplicationContext(), layout.tab1_lot_list);


        adapter = new GridViewAdapter(numbers, this);
        gridView.setAdapter(adapter);

        soundpool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        tak = soundpool.load(this, R.raw.short_click2, 1);
        tok = soundpool.load(this, R.raw.click1_rebert1, 1);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                selectedIndex = adapter.selectedPositions.indexOf(position);

                if (selectedStrings.size() == (MaxBall-1)) {
                    Click_true = 0;
                }

                if (selectedIndex > -1) {
                    soundpool.play(tok, 1, 1, 0, 0, 1);

                    adapter.selectedPositions.remove(selectedIndex);
                    ((GridItemView) v).display(false);
                    selectedStrings.remove((String) parent.getItemAtPosition(position));

                } else {

                    if (selectedStrings.size() == MaxBall) {

                        Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();

                    } else {
                        soundpool.play(tak, 1, 1, 0, 0, 1);

                        adapter.selectedPositions.add(position);
                        ((GridItemView) v).display(true);
                        selectedStrings.add((String) parent.getItemAtPosition(position));
                    }


                }
            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                soundpool.play(tok, 1, 1, 0, 0, 1);

                adapter.selectedPositions.clear();
                selectedStrings.clear();
                adapter.notifyDataSetChanged();
            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                soundpool.play(tok, 1, 1, 0, 0, 1);

                pbnum = String.valueOf(selectedStrings);
//
                if (selectedStrings.size() == MaxBall   && Click_true == 0) {
                    if (Objects.equals(old_pbnum, pbnum)) {
                        Toast.makeText(getApplicationContext(), "Same Number", Toast.LENGTH_LONG).show();

                    } else {
                        LottoCount += 1;

                        old_pbnum = pbnum;
                        pbnum = String.valueOf(selectedStrings);
                        pbnum = pbnum.replace("[", "");
                        pbnum = pbnum.replace("]", "");
                                        Log.d("====sumnum====", pbnum);

                        Card card = new Card(LottoCount + "번째번호 저장됐습니다.", pbnum, "", "", "", "");
                        pbnum = pbnum.replace(" ", "");


                        //DB 입력
                        db.insertNote(pbnum,"수동번호");

                        cardArrayAdapter.add(card);
                        listView.setAdapter(cardArrayAdapter);
                        Click_true = 1;

                    }
                }
            }
        });

        Admob_is();
    }

    public void Admob_is() {
        // admob
        AdView mAdView = (AdView) findViewById(id.adView);
        MobileAds.initialize(getApplicationContext(), getString(R.string.google_banner_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}