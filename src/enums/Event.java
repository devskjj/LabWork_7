package enums;

import helper.Helper;
import models.Goods;
import models.Trader;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public enum Event {
    NORMAL_DAY("Обычный день") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: "+ Event.NORMAL_DAY.value);
            trader.setSpeedDay(3);
            Helper.print("Ничего не произошло");
        }
    },
    RAIN("Дождь") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: "+ Event.RAIN.value);
            Random rnd = new Random();
            double chanceOfRain = 0.3;
            if (rnd.nextDouble() < chanceOfRain) {
                trader.setSpeedDay(trader.getSpeedDay()-2);
                trader.getPurchasedGoods().get(rnd.nextInt(trader.getPurchasedGoods().size())).decreaseQuality();
                Helper.print("Дождь испортил товар.");
            }else {
                Helper.print("К счастью дождя не было");
            }
        }
    },
    SMOOTH_ROAD("Ровная дорога") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: "+ Event.SMOOTH_ROAD.value);
            if(trader.getSpeedDay()+2<=5){
                trader.setSpeedDay(trader.getSpeedDay()+2);
            }
        }
    },
    WHEEL_BROKE("Сломалось колесо") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: "+ Event.WHEEL_BROKE.value);
            // Как сделать день в пустую
        }
    },
    RIVER("Река") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: "+ Event.RIVER.value);
            // Как сделать день в пустую
        }
    },
    MET_LOCAL("Встетил местоного") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: "+ Event.MET_LOCAL.value);
            // тут мне нужно растояние
        }
    },
    BANDITS("Разбойники большой дороги") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: "+ Event.BANDITS.value);
            Comparator<Goods> cmp = Comparator.comparingDouble(Goods::getRateFromQuality).thenComparingInt(Goods::getPriceOfPurchase);
            Goods best = Collections.max(trader.getPurchasedGoods(), cmp);
            System.out.println("Лучший товар - " + best);
            trader.getPurchasedGoods().remove(best);
        }
    },
    ROADSIDE_TAVERN("Придорожный трактир") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: "+ Event.ROADSIDE_TAVERN.value);
            // тут мне нужно реализовать ночлег еда продажа товара
        }
    },
    PRODUCT_DETERIORATED("Товар испотился") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: "+ Event.PRODUCT_DETERIORATED.value);
            trader.getPurchasedGoods().get(Helper.getRandom(trader.getPurchasedGoods().size()-1)).decreaseQuality();

        }
    };
    public abstract void consequenceOfEvents(Trader trader);
    String value;

    Event(String  value) {
        this.value = value;
    }
}
