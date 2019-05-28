package gotopark.buster.lottogen;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

import gotopark.buster.lottogen.Module.ArrCom;
import gotopark.buster.lottogen.database.DatabaseHelper;
import gotopark.buster.lottogen.database.model.Note;
import gotopark.buster.lottogen.utils.MyDividerItemDecoration;
import gotopark.buster.lottogen.utils.RecyclerTouchListener;

public class Main2Activity extends AppCompatActivity {
    private NotesAdapter mAdapter;
    private List<Note> notesList = new ArrayList<>();
    private TextView noNotesView;

    private DatabaseHelper db;
    private ArrCom arrcom;

    int tak, tok;
    SoundPool soundpool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


//        CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinator_layout);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        noNotesView = findViewById(R.id.empty_notes_view);

        TextView toolbar = findViewById(R.id.saveTitle);
        toolbar.setText("로또 번호 저장 리스트");

//        Model model = new Model();
        arrcom = new ArrCom();


        soundpool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        tak = soundpool.load(this, R.raw.short_click2, 1);
        tok = soundpool.load(this, R.raw.click1_rebert1, 1);

        db = new DatabaseHelper(this);

        notesList.addAll(db.getAllNotes());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoteDialog(false, null, -1);
            }
        });

        mAdapter = new NotesAdapter(this, notesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        toggleEmptyNotes();

        /**
         * On long press on RecyclerView item, open alert dialog
         * with options to choose
         * Edit and Delete
         * */
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                soundpool.play(tok, 1, 1, 1, 0, 1);

                String[] ClickNum = new String[6];
                String results = "";

                //이번주 로또 넘버
                String[] thisWeekNum = Week_sixnum(Model.getWeeknum());
                String thisBonusNum = bonus_num(Model.getWeeknum());

                // 클릭한 넘버 가저와 어레이에 넣기
                Note n = notesList.get(position);
                String mlotnum = n.getNote();
                mlotnum = mlotnum.replace(" ", "");
                ClickNum = mlotnum.split(",");

                //중복 check 메소드
                results = arrcom.comp(ArrCom.concatenate(thisWeekNum, ClickNum));

                if (results.equals("")) {
                    updateNote2("이번 회차와 맞는번호 없습니다.", position);
                    Toast.makeText(Main2Activity.this, "꽝 입니다!!", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(Main2Activity.this, results, Toast.LENGTH_SHORT).show();
                    updateNote2("당첨번호:" + results + "보너스:" + thisBonusNum, position);
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));


        Admob_is();

        Toast.makeText(Main2Activity.this, "꽝 입니다!!", Toast.LENGTH_SHORT).show();

    }

    /**
     * Inserting new note in db
     * and refreshing the list
     */
    private void createNote(String note, String alot,String magroup) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertColumn(note, alot, magroup);
        // get the newly inserted note from db
        Note n = db.getNote(id);

        if (n != null) {
            // adding new note to array list at 0 position
            notesList.add(0, n);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptyNotes();
        }
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateNote(String string, int position) {
        Note n = notesList.get(position);

        // updating note text
        n.setNote(string);

        // updating note in db
        db.updateNote(n);

        // refreshing the list
        notesList.set(position, n);
        mAdapter.notifyItemChanged(position);

        toggleEmptyNotes();
    }


    private void updateNote2(String string, int position) {
        Note n = notesList.get(position);

        // updating note text
        n.setAlot(string);

        // updating note in db
        db.updateData(n);

        // refreshing the list
        notesList.set(position, n);
        mAdapter.notifyItemChanged(position);

        toggleEmptyNotes();
    }


    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteNote(int position) {
        // deleting the note from db
        db.deleteNote(notesList.get(position));

        // removing the note from the list
        notesList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyNotes();
    }

    /**
     * Opens dialog with Edit - Delete options
     * Edit - 0
     * Delete - 0
     */
    private void showActionsDialog(final int position) {
//        CharSequence[] colors = new CharSequence[]{"Edit", "Delete"};
        CharSequence[] colors = new CharSequence[]{"[번호삭제?]"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 정말로 삭제 하시 겠습니까.
        builder.setTitle("정말로 번호를 지우시겠습니까？");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 1) {
                    showNoteDialog(true, notesList.get(position), position);
                } else {
                    soundpool.play(tok, 1, 1, 1, 0, 1);

                    deleteNote(position);
                }
            }
        });
        builder.show();
    }


    /**
     * Shows alert dialog with EditText options to enter / edit
     * a note.
     * when shouldUpdate=true, it automatically displays old note and changes the
     * button text to UPDATE
     */
    private void showNoteDialog(final boolean shouldUpdate, final Note note, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        @SuppressLint("InflateParams") View view = layoutInflaterAndroid.inflate(R.layout.note_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(Main2Activity.this);
        alertDialogBuilderUserInput.setView(view);

        final EditText inputNote = view.findViewById(R.id.note);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
//        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_note_title) : getString(R.string.lbl_edit_note_title));

        if (shouldUpdate && note != null) {
            inputNote.setText(note.getNote());
        }
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message when no text is entered
                if (TextUtils.isEmpty(inputNote.getText().toString())) {
                    Toast.makeText(Main2Activity.this, "Enter note!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }

                // check if user updating note
                if (shouldUpdate && note != null) {
                    // update note by it's id
                    updateNote(inputNote.getText().toString(), position);

                } else {
                    // create new note

//                    createNote(inputNote.getText().toString(),inputNote.getText().toString());
                }
            }
        });
    }

    /**
     * Toggling list and empty notes view
     */
    private void toggleEmptyNotes() {
        // you can check notesList.size() > 0

        if (db.getNotesCount() > 0) {
            noNotesView.setVisibility(View.GONE);
        } else {
            noNotesView.setVisibility(View.VISIBLE);
        }
    }

    public void Admob_is() {

        AdView mAdView = findViewById(R.id.adView);
        MobileAds.initialize(getApplicationContext(), getString(R.string.google_banner_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public String[] Week_sixnum(String[] weeknum) {

        String[] WeekNum = Model.getWeeknum();
        String[] week_six_num = new String[6];

        week_six_num[0] = WeekNum[0];
        week_six_num[1] = WeekNum[1];
        week_six_num[2] = WeekNum[2];
        week_six_num[3] = WeekNum[3];
        week_six_num[4] = WeekNum[4];
        week_six_num[5] = WeekNum[5];

        return week_six_num;
//                Log.d("====sumnum====", temp_rnum[0]);

    }


    public String bonus_num(String[] weeknum) {

        String[] WeekNum = Model.getWeeknum();
        String Bonus_num = "";
        Bonus_num = WeekNum[6];
        //                Log.d("====sumnum====", temp_rnum[0]);

        return Bonus_num;

    }


}
