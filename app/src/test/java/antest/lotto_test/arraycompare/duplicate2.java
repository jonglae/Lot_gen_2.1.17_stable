package antest.lotto_test.arraycompare;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class duplicate2 {

    public static void main(String[] args) {
        String[] users = "1,1,2,3,4,32,4,2,3,4,5,6,23,1,2,3,48,6,5".split(",");

        Set<String> uniquUsers = new HashSet<String>();

        for (int i = 0; i < users.length; i++) {
            if (!uniquUsers.add(users[i]))
                users[i] = "Duplicate"; // here I am assigning Duplicate instead if find duplicate
            // you can assign as null or whatever you want to do with duplicates.
        }
        System.out.println(Arrays.toString(users) + "\n");
    }

}