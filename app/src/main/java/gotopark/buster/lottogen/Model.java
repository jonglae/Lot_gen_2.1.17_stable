package gotopark.buster.lottogen;

import android.util.Log;

import gotopark.buster.lottogen.database.model.Note;

public class Model {

    private int position;
    private Note iid;

    public Note getIid() {
        return iid;
    }

    public void setIid(Note iid) {

        Log.d("=====", String.valueOf(iid));
        this.iid = iid;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {

        Log.d("====","====>  "+position);

        this.position = position;
    }
}
