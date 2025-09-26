import enums.Event;
import helper.Helper;
import models.*;

import java.util.*;

public class Application {
    public static void runApplication() {
        List<Goods> goods = Helper.getRandomGoods(7);
        Trader trader = new Trader(500);

        initializeBuyingProcess(goods, trader);

        City startCity = new City();
        City destinationCity = new City();

        while (destinationCity.getName().equals(startCity.getName())) {
            destinationCity = new City();
        }
        trader.setCurrentCity(startCity);
        trader.setNewDestinationCity(destinationCity);

        Helper.print("=== Start ===");
        Helper.print("Start city: " + startCity.getName());
        Helper.print("End city: " + destinationCity.getName());
        Helper.print("Distance: " + destinationCity.getDistance() + " lig");
        Helper.print("Start goods:");
        Helper.printAllGoods(trader.getPurchasedGoods());
        int distanceRemaining = destinationCity.getDistance();
        int initialDistance = destinationCity.getDistance();

        while (distanceRemaining > 0) {
            Helper.print("New day");
            Helper.print("-------");
            trader.setSpeedDay(3);
            Event randomEvent = Event.values()[Helper.getRandom(Event.values().length - 1)];
            randomEvent.consequenceOfEvents(trader);

            if (randomEvent == Event.MET_LOCAL) {
                int bonus = Helper.getRandom(3, 6);
                distanceRemaining -= bonus;
                Helper.print("Местный подсказал короткий путь. Пройдено дополнительно " + bonus + " лиг.");
            }

            if (randomEvent == Event.ROADSIDE_TAVERN) {
                RoadsideTavern.runRoadsideTavernEvent(trader);
            }

            if (randomEvent == Event.TAVERN_RUMORS && !trader.isChangeCity()) {
                int distanceTraveled = initialDistance - distanceRemaining;
                TavernRumors.showInfo(trader, distanceRemaining, distanceTraveled);
                if (trader.isChangeCity()) {

                    initialDistance = trader.getNewDestinationCity().getDistance();
                    distanceRemaining = (distanceTraveled / 4) + (trader.getNewDestinationCity().getDistance() * 2 / 3);
                    destinationCity = trader.getNewDestinationCity();

                }
            }

            if (trader.getSpeedDay() > 0) {
                distanceRemaining -= trader.getSpeedDay();
                if (distanceRemaining < 0) distanceRemaining = 0;
            }

            Helper.print("Speed: " + trader.getSpeedDay() + " lig/day");
            Helper.print("go left: " + distanceRemaining + " lig");
            Helper.print("Денег осталось: " + trader.getMany());
            Helper.print("Место в телеге осталось: " + trader.getMaxLoad());
            Helper.print("Goods:");
            Helper.printAllGoods(trader.getPurchasedGoods());
        }
        Helper.print("The merchant reached the city: " + destinationCity.
                getName());
        Helper.print("Goods at the finish: ");
        Helper.print("Денег осталось: " + trader.getMany());
        Helper.print("Место в телеге осталось: " + trader.getMaxLoad());
        Helper.printAllGoods(trader.getPurchasedGoods());
        System.out.println("---------- продажа");

        if (trader.isChangeCity()) {
            trader.sellSpecialGoods(destinationCity);
        } else {
            sellGoods(trader);
        }
    }

    private static void sellGoods(Trader trader) {
        Helper.print("-".repeat(90));
        double profit = 0;
        for (Goods good : trader.getPurchasedGoods()) {
            trader.sell(good);
            Helper.print("Продан товар: " + good);
            Helper.print("На сумму: %.2f%n", good.getFinalPrice());
            profit += good.getFinalPrice();
            Helper.print("-".repeat(90));
        }
        trader.setMany(trader.getMany() + profit);
        Helper.print("Итоговая прибыль: %.2f", profit);
        Helper.print("Итого денег у торговца: %.2f", trader.getMany());
    }


    private static void initializeBuyingProcess(List<Goods> goods, Trader trader) {
        Helper.print("Список доступных товаров для покупки: ");
        Helper.printAllGoods(goods);
        Helper.print();

        Helper.print("Денег у торговца: " + trader.getMany());
        Helper.print("Грузоподъемность телеги: " + trader.getMaxLoad());
        Helper.print();

        Helper.print("Процесс покупки...");
        buyGoods(goods, trader);
        Helper.print();

        Helper.print("Денег осталось: " + trader.getMany());
        Helper.print("Место в телеге осталось: " + trader.getMaxLoad());
        Helper.print();

        Helper.print("Купленные товары в телеге: ");
        Helper.printAllGoods(trader.getPurchasedGoods());
    }

    private static void buyGoods(List<Goods> goods, Trader trader) {
        Helper.print("-".repeat(90));
        for (Goods good : goods) {
            if (trader.isEnoughToBuy(good)) {
                trader.buy(good);
                Helper.print("Куплен товар: " + good);
                Helper.print("-".repeat(90));
            } else {
                Helper.print("Нельзя взять: " + good);
                Helper.print("-".repeat(90));
            }
        }
    }

    private static void getWorseGoodIfRain(List<Goods> goods) {
        Random rnd = new Random();
        double chanceOfRain = 0.3;
        if (rnd.nextDouble() < chanceOfRain) {
            goods.get(rnd.nextInt(goods.size())).decreaseQuality();
            Helper.print("Дождь испортил товар.");
        }
    }

    private static void deleteBestGoodIfBandits(List<Goods> goods) {
        Comparator<Goods> cmp = Comparator.comparingDouble(Goods::getRateFromQuality).thenComparingInt(Goods::getPriceOfPurchase);
        Goods best = Collections.max(goods, cmp);
        Helper.print("Лучший товар - " + best);
        goods.remove(best);
    }

    private static void getWorseGood(List<Goods> goods) {
        Random rnd = new Random();
        goods.get(rnd.nextInt(goods.size())).decreaseQuality();
        Helper.print("Случайно испортился один из товаров. Это печально.");
    }
}