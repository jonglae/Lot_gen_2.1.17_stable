package gotopark.buster.lottogen;

import android.util.Log;

import gotopark.buster.lottogen.database.model.Note;

public class Model {

    private int position;
    private String rLotnum;
    static  String Sum_num;

    String getrLotnum() {
        return rLotnum;
    }

    public String getSum_num() {
        return Sum_num;
    }

    public void setSum_num(String sum_num) {

        Log.d("====",sum_num);
        Sum_num = sum_num;
    }

    void setrLotnum(String rLotnum) {
        this.rLotnum = rLotnum;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {

        Log.d("====","====>  "+position);

        this.position = position;
    }
}
