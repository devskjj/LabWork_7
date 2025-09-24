package models;

import enums.QualityOfGoods;
import enums.TypeOfGoods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Goods {
    private int weight;
    private TypeOfGoods type;
    private QualityOfGoods quality;
    private int price;

    public Goods() {
        Random rnd = new Random();
        TypeOfGoods[] types = TypeOfGoods.values();

        this.weight = rnd.nextInt(200) + 1;
        this.type = types[rnd.nextInt(types.length)];
        this.quality = QualityOfGoods.NORMAL;
        this.price = rnd.nextInt(200) + 50;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "weight=" + weight +
                ", type=" + type +
                ", quality=" + quality +
                ", price=" + price +
                '}';
    }

    public void setQuality(QualityOfGoods quality) {
        this.quality = quality;
        System.out.println("Состояние изменено на: " + quality);
    }

    public void decreaseQuality() {
        quality.decreaseQuality(this);
    }

    public double getFinalPrice() {
        return price * quality.getRate();
    }
}
