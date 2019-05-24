package gotopark.buster.lottogen;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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

import gotopark.buster.lottogen.SelectNum.GridItemView;
import gotopark.buster.lottogen.SelectNum.GridViewAdapter;
import gotopark.buster.lottogen.database.DatabaseHelper;
import gotopark.buster.lottogen.listview.Card;
import gotopark.buster.lottogen.listview.OneCardAdapter;

import static gotopark.buster.lottogen.R.*;


/**
 * Created by User on 4/15/2017.
 */

public class Main3Activity extends AppCompatActivity {

    private ArrayList<String> selectedStrings;
    private DatabaseHelper db;
    private static final String TAG = "Main3Activity";
    private RecyclerView mRecyclerView;
    private static final String[] numbers = new String[]{
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
            "41", "42", "43", "44", "45"};

    public OneCardAdapter cardArrayAdapter;
    public ListView listView;
    int LottoCount = 0;
    GridViewAdapter adapter;
    int IndexGesu = 1;
    int selectedIndex;
    int Click_true = 1;

    String pbnum;
    String old_pbnum;
    int MaxBall = 6;
    int tak, tok;
    SoundPool soundpool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        db = new DatabaseHelper(this);

        GridView gridView = (GridView) findViewById(id.grid);
        View btnGo = findViewById(id.button);
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

                Log.d("====selectedIndex=====", String.valueOf(selectedIndex));

                if (selectedIndex > -1) {
                    soundpool.play(tok, 1, 1, 0, 0, 1);

                    adapter.selectedPositions.remove(selectedIndex);
                    ((GridItemView) v).display(false);
                    selectedStrings.remove((String) parent.getItemAtPosition(position));

                    Log.d("====parent=====", (String) parent.getItemAtPosition(position));

                    IndexGesu = IndexGesu - 1;
                } else {

                    if (IndexGesu > MaxBall) {

                        Click_true = 0;
                        Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();

                    } else {
                        soundpool.play(tak, 1, 1, 0, 0, 1);

                        adapter.selectedPositions.add(position);
                        ((GridItemView) v).display(true);
                        selectedStrings.add((String) parent.getItemAtPosition(position));
                        IndexGesu = IndexGesu + 1;
                    }


                }
                Log.d("====IndexGesu=====", String.valueOf(IndexGesu));


            }
        });


        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundpool.play(tok, 1, 1, 0, 0, 1);

                pbnum = String.valueOf(selectedStrings);
//
                if (IndexGesu > MaxBall && Click_true == 0) {
                    if (old_pbnum == pbnum) {


                    } else {
                        LottoCount += 1;


                        pbnum = String.valueOf(selectedStrings);
                        pbnum = pbnum.replace("[", "");
                        pbnum = pbnum.replace("]", "");

                        Card card = new Card(LottoCount + "번째번호 저장됐습니다.", pbnum, "", "", "", "");
                        old_pbnum = pbnum;

                        //DB 입력
                        db.insertNote(pbnum);

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








