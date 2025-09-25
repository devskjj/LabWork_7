package helper;

import models.Goods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public static List<Goods> getRandomGoods(int count) {
        List<Goods> goods = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            goods.add(new Goods());
        }
        Collections.shuffle(goods);
        return goods;
    }

    public static void printAllGoods(List<Goods> list) {
        print("-".repeat(76));
        for (Goods goods : list) {
            print(goods);
        }
        print("-".repeat(76));
    }

    private static void print(Goods goods) {
        System.out.println(goods);
    }

    public static void print(String fmt, Object... args) {
        System.out.println(String.format(fmt, args));
    }

    public static void print() {
        System.out.println();
    }
}
