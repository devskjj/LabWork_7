package models;

import helper.Helper;

import java.util.ArrayList;
import java.util.List;

public class Trader {
    private int maxLoad;
    private int speedDay;
    private double many;
    private List<Goods> purchasedGoods;
    private final int baseSpeedDay = 3;

    public Trader() {
        this.maxLoad = Helper.getRandom(500, 700);
        this.speedDay = baseSpeedDay;
        this.many = Helper.getRandom(400, 1000);
        this.purchasedGoods = new ArrayList<>();
    }

    public boolean isEnoughToBuy(Goods good) {
        return maxLoad >= good.getWeight() && many >= good.getPriceOfPurchase(); //првоерка на деньги/вес
    }

    public void buy(Goods good) {
        if (isEnoughToBuy(good)) {
            many -= good.getPriceOfPurchase();
            maxLoad -= good.getWeight();
            purchasedGoods.add(good);
        }
    }

    public void sell(Goods good) {
        this.many += good.getFinalPrice();
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

    public double getMany() {
        return many;
    }

    public void setMany(double many) {
        this.many = many;
    }
}