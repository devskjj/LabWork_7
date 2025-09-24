import enums.Event;
import helper.Helper;
import models.Goods;
import models.Trader;

import java.util.*;

public class Application {
    public static void runApplication() {
        List<Goods> goods = getRandomGoods(5);

        Trader trader = new Trader(1000,2000);
        printAllGoods(goods);
        for (int i = 0; i < goods.size(); i++) {
            trader.buy(goods.get(i));
        }
        System.out.println("Все что купил");

        printAllGoods(trader.getPurchasedGoods());

        Event.values()[Helper.getRandom(Event.values().length-1)].consequenceOfEvents(trader);

        System.out.println("Все что осталось после событий");
        printAllGoods(trader.getPurchasedGoods());




//
//        System.out.println("-------------");
//        goods.get(0).setQuality(Quality.HALF_DAMAGED);
//        goods.get(1).setQuality(Quality.ALMOST_FULL_DAMAGED);
//
//
//        printAllGoods(goods);
//        System.out.println(goods.size());
//
//
//        deleteBestGoodIfBandits(goods);
//        System.out.println("-------------");
//
//        printAllGoods(goods);
//        System.out.println(goods.size());
//
//        getWorseGood(goods);
//        printAllGoods(goods);


//        getWorseIfRain(goods);
//        printGoods(goods);


    }

    private static List<Goods> getRandomGoods(int count) {
        List<Goods> goods = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            goods.add(new Goods());
        }
        Collections.shuffle(goods);
        return goods;
    }

    private static void getWorseGoodIfRain(List<Goods> goods) {
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

    private static void getWorseGood(List<Goods> goods) {
        Random rnd = new Random();
        goods.get(rnd.nextInt(goods.size())).decreaseQuality();
        System.out.println("Случайно испортился один из товаров. Это печально.");
    }

    private static void printAllGoods(List<Goods> list) {
        for (Goods goods : list) {
            System.out.println(goods);
        }
    }
}