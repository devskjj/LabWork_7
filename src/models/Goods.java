package models;

import enums.Quality;
import enums.Types;

import java.util.Random;

public class Goods {
    private int weight;
    private Types type;
    private Quality quality;
    private int priceOfPurchase;

    public Goods() {
        Random rnd = new Random();
        Types[] types = Types.values();

        this.weight = rnd.nextInt(200) + 1;
        this.type = types[rnd.nextInt(types.length)];
        this.quality = Quality.NORMAL;
        this.priceOfPurchase = rnd.nextInt(200) + 50;
    }

    @Override
    public String toString() {
        return String.format("Тип: %-10s | Вес: %-3d | Качество: %s | Стоимость покупки: %-3d |", type.getValue(), weight, quality.getValue(), priceOfPurchase);
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
        System.out.printf("Состояние %s изменено на: %s%n", type.getValue(), quality.getValue());
    }

    public Quality getQuality() {
        return quality;
    }

    public static double getRateFromQuality(Goods good) {
        return good.getQuality().getRate();
    }

    public int getPriceOfPurchase() {
        return priceOfPurchase;
    }

    public void decreaseQuality() {
        quality.decreaseQuality(this);
    }

    public double getFinalPrice() {
        return priceOfPurchase * quality.getRate();
    }
}
