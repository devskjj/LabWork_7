package models;

import enums.QualityOfGoods;
import enums.TypeOfGoods;

import java.util.Random;

public class Goods {
    private int weight;
    private TypeOfGoods type;
    private QualityOfGoods quality;
    private int price;

    public Goods() {
        Random rnd = new Random();
        TypeOfGoods[] types = TypeOfGoods.values();

        this.weight = rnd.nextInt();
        this.type = types[rnd.nextInt(types.length)];
        this.quality = QualityOfGoods.NORMAL;
        this.price = rnd.nextInt();
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
}
