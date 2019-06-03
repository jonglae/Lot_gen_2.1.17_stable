package gotopark.buster.lottogen;

/**
 * Created by ravi on 20/02/18.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import gotopark.buster.lottogen.Module.numtoimg;
import gotopark.buster.lottogen.database.model.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {
    private Context context;

    private List<Note> notesList;

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView note;
        TextView dot;
        TextView timestamp;
        TextView lstext1;
        TextView MAgroup;

        TextView Rtext1;
        TextView Rtext2;
        TextView Rtext3;
        TextView Rtext4;
        TextView Rtext5;
        TextView Rtext6;

        MyViewHolder(View view) {
            super(view);

            note = view.findViewById(R.id.note);
            dot = view.findViewById(R.id.dot);
            lstext1 = view.findViewById(R.id.lsttxt1);
            MAgroup = view.findViewById(R.id.MAgroup);
            timestamp = view.findViewById(R.id.timestamp);

            Rtext1 = view.findViewById(R.id.Rtext1);
            Rtext2 = view.findViewById(R.id.Rtext2);
            Rtext3 = view.findViewById(R.id.Rtext3);
            Rtext4 = view.findViewById(R.id.Rtext4);
            Rtext5 = view.findViewById(R.id.Rtext5);
            Rtext6 = view.findViewById(R.id.Rtext6);

        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onClick(View v) {

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
        numtoimg NumtoI = new numtoimg();

        int dball1;
        int dball2;
        int dball3;
        int dball4;
        int dball5;
        int dball6;

        Note note = notesList.get(position);


        String[] res = note.getNote().split(",");



        dball1 = NumtoI.Numimg(Integer.parseInt(res[0]));
        dball2 = NumtoI.Numimg(Integer.parseInt(res[1]));
        dball3 = NumtoI.Numimg(Integer.parseInt(res[2]));
        dball4 = NumtoI.Numimg(Integer.parseInt(res[3]));
        dball5 = NumtoI.Numimg(Integer.parseInt(res[4]));
        dball6 = NumtoI.Numimg(Integer.parseInt(res[5]));

        holder.Rtext1.setText(res[0]);
        holder.Rtext2.setText(res[1]);
        holder.Rtext3.setText(res[2]);
        holder.Rtext4.setText(res[3]);
        holder.Rtext5.setText(res[4]);
        holder.Rtext6.setText(res[5]);
//        if(!note.getMagroup().equals("자동번호") ) {
        holder.MAgroup.setTextColor(Color.parseColor("#42A02B"));
//        }



        holder.Rtext1.setBackgroundResource(dball1);
        holder.Rtext2.setBackgroundResource(dball2);
        holder.Rtext3.setBackgroundResource(dball3);
        holder.Rtext4.setBackgroundResource(dball4);
        holder.Rtext5.setBackgroundResource(dball5);
        holder.Rtext6.setBackgroundResource(dball6);


//        holder.note.setText(note.getNote());
        holder.lstext1.setText(note.getAlot());
        holder.MAgroup.setText(note.getMagroup());
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
            SimpleDateFormat fmtOut = new SimpleDateFormat(" MM월 dd일 (HH:mm) -");
            return fmtOut.format(date);
        } catch (ParseException ignored) {

        }

        return "";
    }
}