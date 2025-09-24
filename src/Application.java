import models.Goods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Application {
    public static void runApplication() {
        List<Goods> randomGoods = getRandomGoods(7);
        printGoods(randomGoods);

        System.out.println("-------------");

        getWorseIfRain(randomGoods);
        printGoods(randomGoods);


    }

    private static List<Goods> getRandomGoods(int count) {
        List<Goods> goods = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            goods.add(new Goods());
        }
        Collections.shuffle(goods);
        return goods;
    }

    private static void getWorseIfRain(List<Goods> goods) {
        Random rnd = new Random();
        double chanceOfRain = 0.3;
        if (rnd.nextDouble() < chanceOfRain) {
            goods.get(rnd.nextInt(goods.size())).decreaseQuality();
            System.out.println("Дождь испортил товар.");
        }
    }

    private static void printGoods(List<Goods> list) {
        for (Goods goods : list) {
            System.out.println(goods);
        }
    }
}
