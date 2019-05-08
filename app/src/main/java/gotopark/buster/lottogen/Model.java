package gotopark.buster.lottogen;

public class Model {

    private int position;
    private static String rLotnum;
    private static  String Sum_num;
    private static String[] Weeknum;

    static String[] getWeeknum() {
        return Weeknum;
    }

    static void setWeeknum(String[] weeknum) {
        Weeknum = weeknum;
    }

    String getrLotnum() {
        return rLotnum;
    }

    void setrLotnum(String rLotnum) {
        Model.rLotnum = rLotnum;
    }

    String getSum_num() {
        return Sum_num;
    }

    void setSum_num(String sum_num) {

//        Log.d("==ffff==",sum_num);
        Sum_num = sum_num;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {

//        Log.d("====","====>  "+position);

        this.position = position;
    }
}
