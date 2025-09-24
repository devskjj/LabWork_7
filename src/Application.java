import enums.Quality;
import models.Goods;

import java.util.*;

public class Application {
    public static void runApplication() {
        List<Goods> randomGoods = getRandomGoods(5);
        printGoods(randomGoods);

        System.out.println("-------------");
        randomGoods.get(0).setQuality(Quality.HALF_DAMAGED);
        randomGoods.get(1).setQuality(Quality.ALMOST_FULL_DAMAGED);
        printGoods(randomGoods);
        System.out.println(randomGoods.size());


        deleteBestGoodIfBandits(randomGoods);
        System.out.println("-------------");

        printGoods(randomGoods);
        System.out.println(randomGoods.size());

//        getWorseIfRain(randomGoods);
//        printGoods(randomGoods);


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

    private static void deleteBestGoodIfBandits(List<Goods> goods) {
        Comparator<Goods> cmp = Comparator.comparingDouble(Goods::getRateFromQuality).thenComparingInt(Goods::getPriceOfPurchase);
        Goods best = Collections.max(goods, cmp);
        System.out.println("Лучший товар - " + best);
        goods.remove(best);
    }

    private static void printGoods(List<Goods> list) {
        for (Goods goods : list) {
            System.out.println(goods);
        }
    }
}
