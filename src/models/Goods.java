package models;

import enums.QualityOfGoods;
import enums.TypeOfGoods;

import java.util.Random;

public class Goods {
    private int weight;
    private TypeOfGoods type;
    private QualityOfGoods quality;
    private int priceOfPurchase;

    public Goods() {
        Random rnd = new Random();
        TypeOfGoods[] types = TypeOfGoods.values();

        this.weight = rnd.nextInt(200) + 1;
        this.type = types[rnd.nextInt(types.length)];
        this.quality = QualityOfGoods.NORMAL;
        this.priceOfPurchase = rnd.nextInt(200) + 50;
    }

    @Override
    public String toString() {
        return String.format("Тип: %-10s | Вес: %-3d | Качество: %s | Стоимость покупки: %-3d |", type.getValue(), weight, quality.getValue(), priceOfPurchase);
    }

    public void setQuality(QualityOfGoods quality) {
        this.quality = quality;
        System.out.println("Состояние изменено на: " + quality);
    }

    public void decreaseQuality() {
        quality.decreaseQuality(this);
    }

    public double getFinalPrice() {
        return priceOfPurchase * quality.getRate();
    }
}
