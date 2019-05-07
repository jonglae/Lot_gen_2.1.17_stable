package gotopark.buster.lottogen;

import android.util.Log;

import gotopark.buster.lottogen.database.model.Note;

public class Model {

    private int position;
    private String rLotnum;

    public String getrLotnum() {
        return rLotnum;
    }

    public void setrLotnum(String rLotnum) {
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
