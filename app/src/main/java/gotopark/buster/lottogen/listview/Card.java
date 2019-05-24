package gotopark.buster.lottogen.listview;

public class Card {

    private String line1;
    private String line2;
    private String line3;
    private String line4;
    private String line5;
    private String line6;



    public Card(String line1, String line2, String line3, String line4, String line5, String line6) {
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;
        this.line5 = line5;
        this.line6 = line6;
    }


    String getLine1() {
        return line1;
    }

    String getLine2() {
        return line2;
    }

    String getLine3() {
        return line3;
    }

    String getLine4() {
        return line4;
    }

    String getLine5() {
        return line5;
    }

    String getLine6() {
        return line6;
    }

}
