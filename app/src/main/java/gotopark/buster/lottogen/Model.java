package gotopark.buster.lottogen;

import android.util.Log;

public class Model {

    private int position;
    private String[] iid;

    public String[] getIid() {
        return iid;
    }

    public void setIid(String[] iid) {
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
