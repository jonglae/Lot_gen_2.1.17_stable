package gotopark.buster.lottogen.listview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gotopark.buster.lottogen.Module.numtoimg;
import gotopark.buster.lottogen.R;

public class
OneCardAdapter extends ArrayAdapter<Card> {
    private static final String TAG = "OneCardAdapter";
    public List<Card> cardList = new ArrayList<Card>();

    public OneCardAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    static class CardViewHolder {

        TextView line1;
        TextView Rtext1;
        TextView Rtext2;
        TextView Rtext3;
        TextView Rtext4;
        TextView Rtext5;
        TextView Rtext6;

        ImageView Rball1;
        ImageView Rball2;
        ImageView Rball3;
        ImageView Rball4;
        ImageView Rball5;
        ImageView Rball6;

    }

    @Override
    public void add(Card object) {

        cardList.add(object);

        super.add(object);

    }

    @Override
    public int getCount() {

        return this.cardList.size();

    }

    @Override
    public Card getItem(int index)
    {

        return this.cardList.get(index);

    }

    @SuppressLint("WrongViewCast")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        CardViewHolder viewHolder;

        String[] Lotnum = new String[6];
        String sLotnum;
        numtoimg NumtoI = new numtoimg();

        int dball1;
        int dball2;
        int dball3;
        int dball4;
        int dball5;
        int dball6;

        if (row == null) {

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            row = inflater.inflate(R.layout.tab1_lot_list_2ball, parent, false);
            viewHolder = new CardViewHolder();

            viewHolder.line1 = (TextView) row.findViewById(R.id.line1);

            viewHolder.Rtext1 = (TextView) row.findViewById(R.id.Rtext1);
            viewHolder.Rtext2 = (TextView) row.findViewById(R.id.Rtext2);
            viewHolder.Rtext3 = (TextView) row.findViewById(R.id.Rtext3);
            viewHolder.Rtext4 = (TextView) row.findViewById(R.id.Rtext4);
            viewHolder.Rtext5 = (TextView) row.findViewById(R.id.Rtext5);
            viewHolder.Rtext6 = (TextView) row.findViewById(R.id.Rtext6);

            viewHolder.Rball1 = (ImageView) row.findViewById(R.id.RBall1);
            viewHolder.Rball2 = (ImageView) row.findViewById(R.id.RBall2);
            viewHolder.Rball3 = (ImageView) row.findViewById(R.id.RBall3);
            viewHolder.Rball4 = (ImageView) row.findViewById(R.id.RBall4);
            viewHolder.Rball5 = (ImageView) row.findViewById(R.id.RBall5);
            viewHolder.Rball6 = (ImageView) row.findViewById(R.id.RBall6);



            row.setTag(viewHolder);


        } else {


            viewHolder = (CardViewHolder)row.getTag();

        }

        Card card = getItem(position);

        assert card != null;
        sLotnum =card.getLine2().replace(" ","");

        Lotnum = sLotnum.split(",");

        Log.e(TAG,"================>"+ Arrays.toString(Lotnum));

        Log.e(TAG,"========Lotnum========>"+Lotnum[0]);
        Log.e(TAG,"========Lotnum========>"+Lotnum[1]);



        assert card != null;

        viewHolder.line1.setText(card.getLine1());

        dball1 = Integer.parseInt(Lotnum[0]);
        dball2 = Integer.parseInt(Lotnum[1]);
        dball3 = Integer.parseInt(Lotnum[2]);
        dball4 = Integer.parseInt(Lotnum[3]);
        dball5 = Integer.parseInt(Lotnum[4]);
        dball6 = Integer.parseInt(Lotnum[5]);

        dball1 = NumtoI.Numimg(dball1);
        dball2 = NumtoI.Numimg(dball2);
        dball3 = NumtoI.Numimg(dball3);
        dball4 = NumtoI.Numimg(dball4);
        dball5 = NumtoI.Numimg(dball5);
        dball6 = NumtoI.Numimg(dball6);

        viewHolder.Rtext1.setText(Lotnum[0]);
        viewHolder.Rtext2.setText(Lotnum[1]);
        viewHolder.Rtext3.setText(Lotnum[2]);
        viewHolder.Rtext4.setText(Lotnum[3]);
        viewHolder.Rtext5.setText(Lotnum[4]);
        viewHolder.Rtext6.setText(Lotnum[5]);

        viewHolder.Rball1.setImageResource(dball1);
        viewHolder.Rball2.setImageResource(dball2);
        viewHolder.Rball3.setImageResource(dball3);
        viewHolder.Rball4.setImageResource(dball4);
        viewHolder.Rball5.setImageResource(dball5);
        viewHolder.Rball6.setImageResource(dball6);

        Log.e(TAG,"================>"+card.getLine2());
//
//        viewHolder.line3.setTextColor(Color.parseColor("#27af3c"));
//        viewHolder.line3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
//        viewHolder.line3.setText(card.getLine2());

        return row;

    }

}