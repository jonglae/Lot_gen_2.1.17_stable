package gotopark.buster.lottogen;

import android.util.Log;

public class Model {

    private int position;
    static String rLotnum;
    static  String Sum_num;

    String getrLotnum() {
        return rLotnum;
    }

    public String getSum_num() {
        return Sum_num;
    }

    public void setSum_num(String sum_num) {

        Log.d("==ffff==",sum_num);
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
