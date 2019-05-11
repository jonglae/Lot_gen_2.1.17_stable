package gotopark.buster.lottogen;

class Model {

    private int position;
    private static int click;
    private static  String Sum_num;
    private static String[] Weeknum;

    static String[] getWeeknum() {
        return Weeknum;
    }

    static void setWeeknum(String[] weeknum) {
        Weeknum = weeknum;
    }

    public static int getClick() {
        return click;
    }

    public static void setClick(int click) {
        Model.click = click;
    }
}
