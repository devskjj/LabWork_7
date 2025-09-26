package models;

import enums.Types;
import helper.Helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Trader {
    private int maxLoad;
    private int speedDay;
    private double money;
    private List<Goods> purchasedGoods;
    private final int baseSpeedDay = 3;
    private City currentCity;
    private City newDestinationCity;
    private boolean changeCity;

    public Trader() {
        this.maxLoad = Helper.getRandom(500, 700);
        this.speedDay = baseSpeedDay;
        this.money = Helper.getRandom(400, 1000);
        this.purchasedGoods = new ArrayList<>();
        this.changeCity = false;
    }

    public boolean isEnoughToBuy(Goods good) {
        return maxLoad >= good.getWeight() && money >= good.getPriceOfPurchase();
    }

    public void buy(Goods good) {
        if (isEnoughToBuy(good)) {
            money -= good.getPriceOfPurchase();
            maxLoad -= good.getWeight();
            purchasedGoods.add(good);
        }
    }

    public void sell(Goods good) {
        good.getFinalPrice();
    }

    public void sellSpecialGoods(City city) {
        Set<Types> citySpecials = city.getSpecialGoods();
        List<Goods> toRemove = new ArrayList<>();
        Helper.print("-".repeat(120));
        for (Goods good : purchasedGoods) {
            if (citySpecials.contains(good.getType())) {
                double bonus = good.getFinalPrice() * 2;
                setMoney(getMoney() + bonus);
                Helper.print("Специализированный товар! Продано за x2: " + good + " Продали за " + String.format("%.2f", bonus));
                Helper.print("-".repeat(120));
            } else {
                double price = good.getFinalPrice();
                setMoney(getMoney() + price);
                Helper.print("Обычный товар! Продано: " + good + " Продали за " + String.format("%.2f", price));
                Helper.print("-".repeat(120));
            }
            toRemove.add(good);
        }
        purchasedGoods.removeAll(toRemove);
        Helper.print("Прибыль торговца: %.2f", getMoney());
        Helper.print("Итого денег у торговца: " + String.format("%.2f", getMoney()));
    }

    public double riskBanditsLoss(int days, double chanceOfBandits) {
        double banditsEvents = days * chanceOfBandits;
        double goodLoss = Math.max(0, banditsEvents - 1);
        goodLoss = Math.min(goodLoss, purchasedGoods.size());
        return goodLoss;
    }

    public double riskGoodsPriceLoss(double goodLoss) {
        if (goodLoss <= 0) return 0;
        purchasedGoods.sort(Comparator.comparingDouble(Goods::getFinalPrice));
        double loss = 0;
        int count = (int) Math.ceil(goodLoss);

        for (int i = 0; i < count && i < purchasedGoods.size(); i++) {
            loss += purchasedGoods.get(purchasedGoods.size() - 1 - i).getFinalPrice();
        }
        return loss;
    }

    public double newProfit(City city) {
        double profit = 0;
        for (Goods good : purchasedGoods) {
            double basePrice = good.getFinalPrice();
            if (city.getSpecialGoods().contains(good.getType())) {
                profit += basePrice * 2;
            } else {
                profit += basePrice;
            }
        }
        return profit;
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void resetSpeedDay() {
        this.speedDay = baseSpeedDay;
    }

    public boolean isChangeCity() {
        return changeCity;
    }

    public void setChangeCity(boolean changeCity) {
        this.changeCity = changeCity;
    }

    public void setNewDestinationCity(City newDestinationCity) {
        this.newDestinationCity = newDestinationCity;
    }

    public City getNewDestinationCity() {
        return newDestinationCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public City getCurrentCity() {
        return currentCity;
    }
}