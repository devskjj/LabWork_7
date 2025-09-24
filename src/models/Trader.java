package models;

import helper.Helper;

import java.util.ArrayList;
import java.util.List;

public class Trader {
   private int maxLoad;
   private int speedDay;
   private int many;
   private List<Goods> purchasedGoods; // список того что останется после покупки у торговца

    public Trader(int maxLoad, int many) {
        this.maxLoad = maxLoad;
        this.speedDay = 3;
        this.many = Helper.getRandom(100,200);
        this.purchasedGoods = new ArrayList<>();
    }

    public boolean isEnoughToBuy(Goods good) {
        return maxLoad >= good.getWeight() && many >= good.getPriceOfPurchase(); //првоерка на деньги/вес
    }

    public void buy(Goods good) {  //изменения полей при покупке, это разовый метод
        if (isEnoughToBuy(good)) {
            many -= good.getPriceOfPurchase();
            maxLoad -= good.getWeight();
            purchasedGoods.add(good);
        }
    }

    public List<Goods> getPurchasedGoods() {
        return purchasedGoods;
    }

    public int getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(int maxLoad) {
        this.maxLoad = maxLoad;
    }

    public int getSpeedDay() {
        return speedDay;
    }

    public void setSpeedDay(int speedDay) {
        this.speedDay = speedDay;
    }

    public int getMany() {
        return many;
    }

    public void setMany(int many) {
        this.many = many;
    }
}
