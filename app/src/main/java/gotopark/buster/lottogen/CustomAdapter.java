package gotopark.buster.lottogen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;

    public CustomAdapter(Context context) {

        inflater = LayoutInflater.from(context);
        this.context = context;
    }


    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = inflater.inflate(R.layout.note_list_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder myViewHolder, int i) {


    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Button lstbtn1;
        private TextView lstext1;

        MyViewHolder(View itemView) {
            super(itemView);

            lstext1 = (TextView) itemView.findViewById(R.id.lsttxt1);
            lstbtn1 = (Button) itemView.findViewById(R.id.lstbtn1);

            lstbtn1.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == lstbtn1.getId()) {

                lstext1.setText("버튼 클릭 됐어요 !");

            }
        }
    }


}
