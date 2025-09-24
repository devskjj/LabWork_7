import enums.Event;
import helper.Helper;
import models.Goods;
import models.Trader;
import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class Application {
    public static void runApplication() {
        List<Goods> goods = getRandomGoods(5);
        Trader trader = new Trader(300);

        initializeBuyingProcess(goods, trader);

        Event.values()[Helper.getRandom(Event.values().length - 1)].consequenceOfEvents(trader);

        print("Все что осталось после событий");
        print("Денег: " + trader.getMany());
        printAllGoods(trader.getPurchasedGoods());
    }


    private static void initializeBuyingProcess(List<Goods> goods, Trader trader) {
        print("Список доступных товаров для покупки: ");
        printAllGoods(goods);
        print();

        print("Денег у торговца: " + trader.getMany());
        print("Грузоподъемность телеги: " + trader.getMaxLoad());
        print();

        print("Процесс покупки...");
        buyGoods(goods, trader);
        print();

        print("Денег осталось: " + trader.getMany());
        print("Место в телеге осталось: " + trader.getMaxLoad());
        print();

        print("Купленные товары в телеге: ");
        printAllGoods(trader.getPurchasedGoods());
    }

    private static void buyGoods(List<Goods> goods, Trader trader) {
        print("-".repeat(90));
        for (Goods good : goods) {
            if (trader.isEnoughToBuy(good)) {
                trader.buy(good);
                print("Куплен товар: " + good);
                print("-".repeat(90));
            } else {
                print("Нельзя взять: " + good);
                print("-".repeat(90));
            }
        }
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
            print("Дождь испортил товар.");
        }
    }

    private static void deleteBestGoodIfBandits(List<Goods> goods) {
        Comparator<Goods> cmp = Comparator.comparingDouble(Goods::getRateFromQuality).thenComparingInt(Goods::getPriceOfPurchase);
        Goods best = Collections.max(goods, cmp);
        print("Лучший товар - " + best);
        goods.remove(best);
    }

    private static void getWorseGood(List<Goods> goods) {
        Random rnd = new Random();
        goods.get(rnd.nextInt(goods.size())).decreaseQuality();
        print("Случайно испортился один из товаров. Это печально.");
    }

    private static void printAllGoods(List<Goods> list) {
        print("-".repeat(76));
        for (Goods goods : list) {
            print(goods);
        }
        print("-".repeat(76));
    }

    private static void print(Goods goods) {
        System.out.println(goods);
    }

    private static void print(String fmt, Object... args) {
        System.out.println(String.format(fmt, args));
    }

    private static void print() {
        System.out.println();
    }
}