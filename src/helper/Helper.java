package helper;

import java.util.Random;

public class Helper {
    public static int getRandom(int start, int end){
        Random random = new Random();
        return random.nextInt(start,end+1);
    }
    public static int getRandom( int end){
        Random random = new Random();
        return random.nextInt(end+1);
    }
    public static void print(String value){
        System.out.println(value);
    }
}
