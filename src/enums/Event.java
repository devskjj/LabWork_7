package enums;

import helper.Helper;
import models.Goods;
import models.Trader;
import org.w3c.dom.ls.LSOutput;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public enum Event {
    NORMAL_DAY("Обычный день") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: " + Event.NORMAL_DAY.value);
            trader.setSpeedDay(3);
            Helper.print("Ничего не произошло");
        }
    },
    RAIN("Дождь") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: " + Event.RAIN.value);
            Random rnd = new Random();
            Helper.print("Скорость снижается на 2.");
            trader.setSpeedDay(Math.max(trader.getSpeedDay() - 2, 0));

            double chance = 0.3;
            if (rnd.nextDouble() < chance && !trader.getPurchasedGoods().isEmpty()) {
                trader.getPurchasedGoods().get(rnd.nextInt(trader.getPurchasedGoods().size())).decreaseQuality();
                Helper.print("Дождь испортил товар.");
            } else {
                Helper.print("К счастью товар не испортился.");
            }
        }
    },
    SMOOTH_ROAD("Ровная дорога") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: " + Event.SMOOTH_ROAD.value);
            if (trader.getSpeedDay() + 2 <= 5) {
                trader.setSpeedDay(trader.getSpeedDay() + 2);
            }
        }
    },
    WHEEL_BROKE("Сломалось колесо") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: " + Event.WHEEL_BROKE.value);
            Helper.print("Колесо сломалось, день потерян.");
            trader.setSpeedDay(0);
        }
    },
    RIVER("Река") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: " + Event.RIVER.value);
            Helper.print("Потратил целый день пока искал дорогу.");
            trader.setSpeedDay(0);
        }
    },
    MET_LOCAL("Встретил местного") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: " + Event.MET_LOCAL.value);
            // тут мне нужно растояние
        }
    },
    BANDITS("Разбойники большой дороги") {
        @Override
        public void consequenceOfEvents(Trader trader) {  // добавил что бы могли или деньги забрать или товар как по условию тз
            Helper.print("Событие: " + Event.BANDITS.value);
            if (trader.getMany() > 0) {
                int stolenMoney = trader.getMany();
                trader.setMany(0);
                Helper.print("У торговца украли деньги: " + stolenMoney);
            } else if (!trader.getPurchasedGoods().isEmpty()) { // может упасть ошибка если список пустой
                Helper.print("Денег нет, разбойники забирают лучший товар.");
                Comparator<Goods> cmp = Comparator.comparingDouble(Goods::getRateFromQuality).thenComparingInt(Goods::getPriceOfPurchase);
                Goods best = Collections.max(trader.getPurchasedGoods(), cmp);
                Helper.print("Лучший товар - " + best);
                trader.getPurchasedGoods().remove(best);
            } else {
                Helper.print("У торговца нет ни денег, ни товаров. Разбойники ушли ни с чем.");
            }
        }
    },
    ROADSIDE_TAVERN("Придорожный трактир") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: " + Event.ROADSIDE_TAVERN.value);
            // тут мне нужно реализовать ночлег еда продажа товара
        }
    },
    PRODUCT_DETERIORATED("Товар испотился") {
        @Override
        public void consequenceOfEvents(Trader trader) {
            Helper.print("Событие: " + Event.PRODUCT_DETERIORATED.value);
            if (!trader.getPurchasedGoods().isEmpty()) {
                trader.getPurchasedGoods().get(Helper.getRandom(trader.getPurchasedGoods().size() - 1)).decreaseQuality();
            } else {
                Helper.print("Нет товаров, которые могли бы испортиться.");
            }
        }
    };

    public abstract void consequenceOfEvents(Trader trader);

    String value;

    Event(String value) {
        this.value = value;
    }
}
