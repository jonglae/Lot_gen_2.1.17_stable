package gotopark.buster.lottogen;

/**
 * Created by ravi on 20/02/18.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import gotopark.buster.lottogen.Module.MonyCalc;
import gotopark.buster.lottogen.Module.arraycompare2;
import gotopark.buster.lottogen.database.DatabaseHelper;
import gotopark.buster.lottogen.database.model.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {
    private Context context;

    private List<Note> notesList;
    private DatabaseHelper db;
    private List<String[]> complist;

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView note;
        TextView dot;
        TextView timestamp;
        TextView lstext1;
        TextView lstext2;
        Button lstbtn1;

        Model model;

        MyViewHolder(View view) {
            super(view);

            db = new DatabaseHelper(context);

            note = view.findViewById(R.id.note);
            dot = view.findViewById(R.id.dot);
            lstext1 = view.findViewById(R.id.lsttxt1);
            lstext2 = view.findViewById(R.id.lsttxt2);
            lstbtn1 = view.findViewById(R.id.lstbtn1);
            timestamp = view.findViewById(R.id.timestamp);
            lstbtn1.setOnClickListener(this);




        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onClick(View v) {


            model = new Model();
//            arraycompare2 acom2 = new arraycompare2();
//            String aaaa = model.getrLotnum();
//
//            String[] bbbb = aaaa.split(",");
//            String cccc = String.valueOf(model.getSum_num());
//            String[] dddd = cccc.split(",");
//            String[] adadad =  MonyCalc.concatenate(bbbb,dddd);
//            String results = acom2.ccomp2(adadad);

            arraycompare2 acom2 = new arraycompare2();
            String Thisknum = model.getrLotnum();

            String[] thisbonho = Thisknum.split(",");

            String thisWeeknum = String.valueOf(model.getSum_num());
            String[] SthisWeeknum = thisWeeknum.split(",");
            String[] adadad = MonyCalc.concatenate(thisbonho, SthisWeeknum);
            String results = acom2.ccomp2(adadad);

            lstext2.setText("당첨번호는 : "+results);
//
//            if (v.getId() == lstbtn1.getId()) {
//
//
//                lstext2.setText("당첨번호는 : "+results);

//                notifyDataSetChanged();

//            }


        }
    }


    // 이곳이 매우 중요한 부분
    NotesAdapter(Context context, List<Note> notesList) {
        this.notesList = notesList;

        // 이곳이 매우 중요한 부분 context 가 되는지 확인 되는 부분
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = notesList.get(position);

        holder.note.setText(note.getNote());
        holder.lstext1.setText(note.getAlot());

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));

        // Formatting and displaying timestamp
        holder.timestamp.setText(formatDate(note.getTimestamp()));


    }

    @Override
    public int getItemCount() {
        return notesList.size();

    }

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    private String formatDate(String dateStr) {
        try {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat fmtOut = new SimpleDateFormat("-- MM월 dd일 (HH:mm) --");
            return fmtOut.format(date);
        } catch (ParseException ignored) {

        }

        return "";
    }
}
