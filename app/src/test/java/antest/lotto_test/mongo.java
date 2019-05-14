package antest.lotto_test;

import java.util.Random;

public class mongo {


    public static void main(String[] args) {

        Random rand = new Random();

        assert rand != null;
        int bbbb = rand.nextInt(7 - 1 + 1) + 1;


        System.out.println ("====bbbb==== : "+bbbb);
    }


}



